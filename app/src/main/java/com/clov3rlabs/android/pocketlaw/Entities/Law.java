package com.clov3rlabs.android.pocketlaw.Entities;

/**
 * Created by reneval on 8/11/14.
 */

import java.util.HashMap;
import java.util.Map;

public class Law {

    private int id;
    private String name;
    private String aprovedDate;
    private String publishDate;
    private String code;
    private int lawCatId;
    private String createdAt;
    private String updatedAt;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAprovedDate() {
        return aprovedDate;
    }

    public void setAprovedDate(String aprovedDate) {
        this.aprovedDate = aprovedDate;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLawCatId() {
        return lawCatId;
    }

    public void setLawCatId(int lawCatId) {
        this.lawCatId = lawCatId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}