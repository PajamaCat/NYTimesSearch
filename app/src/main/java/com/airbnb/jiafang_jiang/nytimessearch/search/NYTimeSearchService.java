package com.airbnb.jiafang_jiang.nytimessearch.search;

import android.support.annotation.Nullable;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by jiafang_jiang on 10/25/16.
 */

public interface NYTimeSearchService {

    @GET("svc/search/v2/articlesearch.json")
    Observable<SearchArticleResponse> listArticles(
            @Query("api-key") String apiKey,
            @Query("q") String query,
            @Query("page") int page,
            @Nullable @Query("fq") String filter,
            @Nullable @Query("sort") String sort,
            @Nullable @Query("begin_date") String beginDate);
}
