package com.blog.traloc.entity;

import java.io.Serializable;
import java.util.List;

/**
 * POJO for the final response object
 */
public class CombinedDTO implements Serializable {
    private List<String> positive;
    private List<Restaurant> restaurants;
    private List<String> urls;
    private Country country;

    public CombinedDTO() {
    }

    public CombinedDTO(List<String> positive, List<Restaurant> restaurants, List<String> urls, Country country) {
        this.positive = positive;
        this.restaurants = restaurants;
        this.urls = urls;
        this.country = country;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public List<String> getPositive() {
        return positive;
    }

    public void setPositive(List<String> positive) {
        this.positive = positive;
    }

    public List<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    @Override
    public String toString() {
        return "CombinedDTO{" +
                "positive=" + positive +
                ", restaurants=" + restaurants +
                ", urls=" + urls +
                ", country='" + country + '\'' +
                '}';
    }
}
