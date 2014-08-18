package com.clov3rlabs.android.pocketlaw.Entities;

/**
 * Created by reneval on 8/18/14.
 */

import java.util.Map;
import java.util.HashMap;

public class Article {

    private int id;
    private String name;
    private String text;
    private int lawSectionId;
    private boolean abolished;
    private Object aboArt;
    private Object aboLaw;
    private String createdAt;
    private String updatedAt;
    private int lawId;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLawSectionId() {
        return lawSectionId;
    }

    public void setLawSectionId(int lawSectionId) {
        this.lawSectionId = lawSectionId;
    }

    public boolean isAbolished() {
        return abolished;
    }

    public void setAbolished(boolean abolished) {
        this.abolished = abolished;
    }

    public Object getAboArt() {
        return aboArt;
    }

    public void setAboArt(Object aboArt) {
        this.aboArt = aboArt;
    }

    public Object getAboLaw() {
        return aboLaw;
    }

    public void setAboLaw(Object aboLaw) {
        this.aboLaw = aboLaw;
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

    public int getLawId() {
        return lawId;
    }

    public void setLawId(int lawId) {
        this.lawId = lawId;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
