package com.blog.traloc.entity;

import java.io.Serializable;
import java.util.UUID;

/**
 * POJO for initial raw data from cassandra
 */
public class RawData implements Serializable {
    private UUID id;
    private String url;
    private String content;
    private String country;

    public RawData() {
    }

    public RawData(UUID id, String url, String content, String country) {
        this.id = id;
        this.url = url;
        this.content = content;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RawData{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
