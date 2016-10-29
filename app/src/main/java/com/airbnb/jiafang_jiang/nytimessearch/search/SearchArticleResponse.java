package com.airbnb.jiafang_jiang.nytimessearch.search;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiafang_jiang on 10/25/16.
 */

public class SearchArticleResponse {

    @SerializedName("response")
    @Expose
    private Content docs = new Content();

    /**
     *
     * @return
     * The docs
     */
    public Content getDocs() {
        return docs;
    }

    /**
     *
     * @param docs
     * The docs
     */
    public void setDocs(Content docs) {
        this.docs = docs;
    }

    @Override
    public String toString() {
        return docs.toString();
    }
}

class Content {

    @SerializedName("docs")
    @Expose
    private List<Article> articles = new ArrayList<Article>();

    /**
     *
     * @return
     * The docs
     */
    public List<Article> getArticles() {
        return articles;
    }

    /**
     *
     * @param docs
     * The docs
     */
    public void setArticles(List<Article> docs) {
        this.articles = docs;
    }

    @Override
    public String toString() {
        String res = "";
        for (Article article : articles) {
            res += article.toString();
            res += '\n';
        }
        return articles.toString();
    }
}