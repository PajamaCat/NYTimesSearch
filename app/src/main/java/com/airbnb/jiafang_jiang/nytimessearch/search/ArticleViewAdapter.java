package com.airbnb.jiafang_jiang.nytimessearch.search;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.jiafang_jiang.nytimessearch.R;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticleViewAdapter extends RecyclerView.Adapter<ArticleViewAdapter.ArticleViewHolder> {

    public static final String urlPrefix = "https://nytimes.com/";

    private Context context;
    private List<Article> articles;

    public ArticleViewAdapter(Context context, List<Article> articles) {
        this.context = context;
        this.articles = articles;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View articleView = inflater.inflate(R.layout.item_article, parent, false);
        ArticleViewHolder viewHolder = new ArticleViewHolder(articleView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        Article article = articles.get(position);

        String thumbnail = article.getThumbnail();
        if (!TextUtils.isEmpty(thumbnail)) {
            Glide.with(context).load(urlPrefix + thumbnail).into(holder.ivThumbNail);
        }
        holder.tvHeadline.setText(article.getHeadline().getMain());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        @BindView(R.id.article_thumbnail) ImageView ivThumbNail;
        @BindView(R.id.article_headline) TextView tvHeadline;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                ((SearchActivity) context).openArticleInChromeTab(position);
            }
        }
    }
}
