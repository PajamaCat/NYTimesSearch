package com.airbnb.jiafang_jiang.nytimessearch.search;

import android.text.TextUtils;

import com.google.common.collect.FluentIterable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Article {

    @SerializedName("web_url")
    @Expose
    private String webUrl;
    @SerializedName("snippet")
    @Expose
    private String snippet;
    @SerializedName("lead_paragraph")
    @Expose
    private String leadParagraph;
    @SerializedName("abstract")
    @Expose
    private String _abstract;
    @SerializedName("print_page")
    @Expose
    private String printPage;
    @SerializedName("blog")
    @Expose
    private List<Object> blog = new ArrayList<Object>();
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("multimedia")
    @Expose
    private List<Multimedium> multimedia = new ArrayList<Multimedium>();
    @SerializedName("headline")
    @Expose
    private Headline headline;
    @SerializedName("keywords")
    @Expose
    private List<Keywords> keywords;
    @SerializedName("pub_date")
    @Expose
    private String pubDate;
    @SerializedName("document_type")
    @Expose
    private String documentType;
    @SerializedName("news_desk")
    @Expose
    private String newsDesk;
    @SerializedName("section_name")
    @Expose
    private String sectionName;
    @SerializedName("subsection_name")
    @Expose
    private String subsectionName;
    @SerializedName("byline")
    @Expose
    private Byline byline;
    @SerializedName("type_of_material")
    @Expose
    private String typeOfMaterial;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("word_count")
    @Expose
    private String wordCount;
    @SerializedName("slideshow_credits")
    @Expose
    private String slideshowCredits;

    public String getThumbnail() {
        if (hasThumbnail()) {
            return FluentIterable.from(multimedia).firstMatch(media -> !TextUtils.isEmpty(media.getUrl())).get().getUrl();
        }
        return "";
    }

    private boolean hasThumbnail() {
        return !multimedia.isEmpty() && FluentIterable.from(multimedia).anyMatch(media -> !TextUtils.isEmpty(media.getUrl()));
    }

    /**
     *
     * @return
     * The webUrl
     */
    public String getWebUrl() {
        return webUrl;
    }

    /**
     *
     * @param webUrl
     * The web_url
     */
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    /**
     *
     * @return
     * The snippet
     */
    public String getSnippet() {
        return snippet;
    }

    /**
     *
     * @param snippet
     * The snippet
     */
    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    /**
     *
     * @return
     * The leadParagraph
     */
    public String getLeadParagraph() {
        return leadParagraph;
    }

    /**
     *
     * @param leadParagraph
     * The lead_paragraph
     */
    public void setLeadParagraph(String leadParagraph) {
        this.leadParagraph = leadParagraph;
    }

    /**
     *
     * @return
     * The _abstract
     */
    public String getAbstract() {
        return _abstract;
    }

    /**
     *
     * @param _abstract
     * The abstract
     */
    public void setAbstract(String _abstract) {
        this._abstract = _abstract;
    }

    /**
     *
     * @return
     * The printPage
     */
    public String getPrintPage() {
        return printPage;
    }

    /**
     *
     * @param printPage
     * The print_page
     */
    public void setPrintPage(String printPage) {
        this.printPage = printPage;
    }

    /**
     *
     * @return
     * The blog
     */
    public List<Object> getBlog() {
        return blog;
    }

    /**
     *
     * @param blog
     * The blog
     */
    public void setBlog(List<Object> blog) {
        this.blog = blog;
    }

    /**
     *
     * @return
     * The source
     */
    public String getSource() {
        return source;
    }

    /**
     *
     * @param source
     * The source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     *
     * @return
     * The multimedia
     */
    public List<Multimedium> getMultimedia() {
        return multimedia;
    }

    /**
     *
     * @param multimedia
     * The multimedia
     */
    public void setMultimedia(List<Multimedium> multimedia) {
        this.multimedia = multimedia;
    }

    /**
     *
     * @return
     * The headline
     */
    public Headline getHeadline() {
        return headline;
    }

    /**
     *
     * @param headline
     * The headline
     */
    public void setHeadline(Headline headline) {
        this.headline = headline;
    }

    /**
     *
     * @return
     * The keywords
     */
    public List<Keywords> getKeywords() {
        return keywords;
    }

    /**
     *
     * @param keywords
     * The keywords
     */
    public void setKeywords(List<Keywords> keywords) {
        this.keywords = keywords;
    }

    /**
     *
     * @return
     * The pubDate
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     *
     * @param pubDate
     * The pub_date
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    /**
     *
     * @return
     * The documentType
     */
    public String getDocumentType() {
        return documentType;
    }

    /**
     *
     * @param documentType
     * The document_type
     */
    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    /**
     *
     * @return
     * The newsDesk
     */
    public String getNewsDesk() {
        return newsDesk;
    }

    /**
     *
     * @param newsDesk
     * The news_desk
     */
    public void setNewsDesk(String newsDesk) {
        this.newsDesk = newsDesk;
    }

    /**
     *
     * @return
     * The sectionName
     */
    public String getSectionName() {
        return sectionName;
    }

    /**
     *
     * @param sectionName
     * The section_name
     */
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    /**
     *
     * @return
     * The subsectionName
     */
    public String getSubsectionName() {
        return subsectionName;
    }

    /**
     *
     * @param subsectionName
     * The subsection_name
     */
    public void setSubsectionName(String subsectionName) {
        this.subsectionName = subsectionName;
    }

    /**
     *
     * @return
     * The byline
     */
    public Byline getByline() {
        return byline;
    }

    /**
     *
     * @param byline
     * The byline
     */
    public void setByline(Byline byline) {
        this.byline = byline;
    }

    /**
     *
     * @return
     * The typeOfMaterial
     */
    public String getTypeOfMaterial() {
        return typeOfMaterial;
    }

    /**
     *
     * @param typeOfMaterial
     * The type_of_material
     */
    public void setTypeOfMaterial(String typeOfMaterial) {
        this.typeOfMaterial = typeOfMaterial;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The _id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The wordCount
     */
    public String getWordCount() {
        return wordCount;
    }

    /**
     *
     * @param wordCount
     * The word_count
     */
    public void setWordCount(String wordCount) {
        this.wordCount = wordCount;
    }

    /**
     *
     * @return
     * The slideshowCredits
     */
    public String getSlideshowCredits() {
        return slideshowCredits;
    }

    /**
     *
     * @param slideshowCredits
     * The slideshow_credits
     */
    public void setSlideshowCredits(String slideshowCredits) {
        this.slideshowCredits = slideshowCredits;
    }
}

class Keywords {

    @SerializedName("rank")
    @Expose
    private String rank;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("value")
    @Expose
    private String value;
    @SerializedName("is_major")
    @Expose
    private String isMajor;

    /**
     *
     * @return
     * The rank
     */
    public String getRank() {
        return rank;
    }

    /**
     *
     * @param rank
     * The rank
     */
    public void setRank(String rank) {
        this.rank = rank;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The value
     */
    public String getValue() {
        return value;
    }

    /**
     *
     * @param value
     * The value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     *
     * @return
     * The isMajor
     */
    public String getIsMajor() {
        return isMajor;
    }

    /**
     *
     * @param isMajor
     * The is_major
     */
    public void setIsMajor(String isMajor) {
        this.isMajor = isMajor;
    }

}

class Byline {

    @SerializedName("person")
    @Expose
    private List<Person> person = new ArrayList<Person>();
    @SerializedName("original")
    @Expose
    private String original;
    @SerializedName("organization")
    @Expose
    private String organization;

    /**
     *
     * @return
     * The person
     */
    public List<Person> getPerson() {
        return person;
    }

    /**
     *
     * @param person
     * The person
     */
    public void setPerson(List<Person> person) {
        this.person = person;
    }

    /**
     *
     * @return
     * The original
     */
    public String getOriginal() {
        return original;
    }

    /**
     *
     * @param original
     * The original
     */
    public void setOriginal(String original) {
        this.original = original;
    }

    /**
     *
     * @return
     * The organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     *
     * @param organization
     * The organization
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

}

class Headline {

    @SerializedName("main")
    @Expose
    private String main;
    @SerializedName("print_headline")
    @Expose
    private String printHeadline;
    @SerializedName("content_kicker")
    @Expose
    private String contentKicker;
    @SerializedName("kicker")
    @Expose
    private String kicker;

    /**
     *
     * @return
     * The main
     */
    public String getMain() {
        return main;
    }

    /**
     *
     * @param main
     * The main
     */
    public void setMain(String main) {
        this.main = main;
    }

    /**
     *
     * @return
     * The printHeadline
     */
    public String getPrintHeadline() {
        return printHeadline;
    }

    /**
     *
     * @param printHeadline
     * The print_headline
     */
    public void setPrintHeadline(String printHeadline) {
        this.printHeadline = printHeadline;
    }

    /**
     *
     * @return
     * The contentKicker
     */
    public String getContentKicker() {
        return contentKicker;
    }

    /**
     *
     * @param contentKicker
     * The content_kicker
     */
    public void setContentKicker(String contentKicker) {
        this.contentKicker = contentKicker;
    }

    /**
     *
     * @return
     * The kicker
     */
    public String getKicker() {
        return kicker;
    }

    /**
     *
     * @param kicker
     * The kicker
     */
    public void setKicker(String kicker) {
        this.kicker = kicker;
    }

}

class Legacy {

    @SerializedName("wide")
    @Expose
    private String wide;
    @SerializedName("wideheight")
    @Expose
    private String wideheight;
    @SerializedName("widewidth")
    @Expose
    private String widewidth;

    /**
     *
     * @return
     * The wide
     */
    public String getWide() {
        return wide;
    }

    /**
     *
     * @param wide
     * The wide
     */
    public void setWide(String wide) {
        this.wide = wide;
    }

    /**
     *
     * @return
     * The wideheight
     */
    public String getWideheight() {
        return wideheight;
    }

    /**
     *
     * @param wideheight
     * The wideheight
     */
    public void setWideheight(String wideheight) {
        this.wideheight = wideheight;
    }

    /**
     *
     * @return
     * The widewidth
     */
    public String getWidewidth() {
        return widewidth;
    }

    /**
     *
     * @param widewidth
     * The widewidth
     */
    public void setWidewidth(String widewidth) {
        this.widewidth = widewidth;
    }

}

class Person {

    @SerializedName("organization")
    @Expose
    private String organization;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("rank")
    @Expose
    private Integer rank;
    @SerializedName("lastname")
    @Expose
    private String lastname;

    /**
     *
     * @return
     * The organization
     */
    public String getOrganization() {
        return organization;
    }

    /**
     *
     * @param organization
     * The organization
     */
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    /**
     *
     * @return
     * The role
     */
    public String getRole() {
        return role;
    }

    /**
     *
     * @param role
     * The role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     *
     * @return
     * The firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     *
     * @param firstname
     * The firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     *
     * @return
     * The rank
     */
    public Integer getRank() {
        return rank;
    }

    /**
     *
     * @param rank
     * The rank
     */
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    /**
     *
     * @return
     * The lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     *
     * @param lastname
     * The lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}

class Multimedium {

    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("subtype")
    @Expose
    private String subtype;
    @SerializedName("legacy")
    @Expose
    private Legacy legacy;
    @SerializedName("type")
    @Expose
    private String type;

    /**
     * @return The width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * @param width The width
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return The height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * @param height The height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * @return The subtype
     */
    public String getSubtype() {
        return subtype;
    }

    /**
     * @param subtype The subtype
     */
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    /**
     * @return The legacy
     */
    public Legacy getLegacy() {
        return legacy;
    }

    /**
     * @param legacy The legacy
     */
    public void setLegacy(Legacy legacy) {
        this.legacy = legacy;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

}