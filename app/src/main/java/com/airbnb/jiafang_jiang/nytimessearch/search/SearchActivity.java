package com.airbnb.jiafang_jiang.nytimessearch.search;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.airbnb.jiafang_jiang.nytimessearch.R;
import com.airbnb.jiafang_jiang.nytimessearch.filter.EditFilterDialogFragment;
import com.airbnb.jiafang_jiang.nytimessearch.filter.EndlessRecyclerViewScrollListener;
import com.google.common.collect.Lists;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import icepick.Icepick;
import icepick.State;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity {

    private static final String url = "https://api.nytimes.com/";
    private static final String apiKey = "3f3cb4637f05480187c9e01e8aed2c31";

    private ArticleViewAdapter adapter;
    private NYTimeSearchService searchService;
    private OkHttpClient client;
    private Retrofit retrofit;
    private EndlessRecyclerViewScrollListener scrollListener;
    private HandlerThread handlerThread;
    private Handler handler;

    @State ArrayList<Article> articles = Lists.newArrayList();
    @State String prevSearchQuery;
    @State String prevFilter;
    @State String prevBeginDate;
    @State String prevSort;
    @State int page;

    @BindView(R.id.toolbar) Toolbar searchToolbar;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);

//        if (savedInstanceState == null) {
            setContentView(R.layout.activity_search);
            ButterKnife.bind(this);

            setSupportActionBar(searchToolbar);
            setupArticleGrid();

            page = 0;
//        }
        client = new OkHttpClient();
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
        searchService = retrofit.create(NYTimeSearchService.class);
        handlerThread = new HandlerThread("RequestHandler");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
    }

    private void setupArticleGrid() {
        adapter = new ArticleViewAdapter(this, articles);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                handler.postDelayed(() -> sendRequest(prevSearchQuery, false), 350);
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.article_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setIconifiedByDefault(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // clear prev results
                clearPreviousSearch(false);

                handler.postDelayed(() -> sendRequest(prevSearchQuery, true), 350);
                prevSearchQuery = query;

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.filter_search) {
            EditFilterDialogFragment.newInstance().show(getSupportFragmentManager(), "editFilter");
        }

        return super.onOptionsItemSelected(item);
    }

    private void clearPreviousSearch(boolean includeFilter) {
        if (includeFilter) {
            prevBeginDate = null;
            prevFilter = null;
            prevSort = null;
        }
        page = 0;
        articles.clear();
        adapter.notifyDataSetChanged();
        scrollListener.resetState();
    }

    public void updateQueryAndSend(String filter, String sort, String beginDate) {
        clearPreviousSearch(true);
        prevFilter = filter;
        prevSort = sort;
        prevBeginDate = beginDate;

        handler.postDelayed(() -> sendRequest(prevSearchQuery, true), 350);
    }

    public void openArticleInChromeTab(int pos) {
        Article article = articles.get(pos);
        String url = article.getWebUrl();
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.addDefaultShareMenuItem();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(url));
    }

    private void sendRequest(String query, boolean isNewRequest) {
        Observable<SearchArticleResponse> articleResponse = searchService.listArticles(apiKey, query, page, prevFilter, prevSort, prevBeginDate);
        Subscription subscription = articleResponse
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            articles.addAll(response.getDocs().getArticles());
                            adapter.notifyDataSetChanged();
                            Log.d("[ARTICLE RESPONSE]", response.getDocs().toString());
                            page++;
                        },
                        throwable -> {
                            Log.d("[ERROR]", throwable.getMessage());
                            if (!throwable.getMessage().contains("429") && !isNewRequest) {
                                page++;
                            }
                            handler.postDelayed(() -> sendRequest(prevSearchQuery, false), 500);
                        },
                        () -> Log.d("[SEARCH]", "completed"));
    }

}
