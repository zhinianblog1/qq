package com.tysong.qq.dto;

public class BlogRobotSerchInfo {
    /**
     * 文章URL
     */
    private String url;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 取得 文章URL
     * @return 文章URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置 文章URL
     * @param url 文章URL
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 取得 文章标题
     * @return 文章标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置 文章标题
     * @param title 文章标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }
}