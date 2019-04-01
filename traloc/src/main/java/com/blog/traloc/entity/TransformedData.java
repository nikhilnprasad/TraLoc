package com.blog.traloc.entity;

import java.io.Serializable;
import java.util.List;

/**
 * POJO for transforming raw data after sentiment analysis
 */
public class TransformedData implements Serializable{
    private List<String> positive;
    private String country;

    public TransformedData() {
    }

    public TransformedData(List<String> positive, String country) {
        this.positive = positive;
        this.country = country;
    }

    public List<String> getPositive() {
        return positive;
    }

    public void setPositive(List<String> positive) {
        this.positive = positive;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "TransformedData{" +
                "positive=" + positive +
                ", country=" + country +
                '}';
    }
}
