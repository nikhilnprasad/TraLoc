package com.blog.traloc.entity;

import java.io.Serializable;
import java.util.UUID;

/**
 * POJO for restaurant data from cassandra
 */
public class Restaurant implements Serializable{
    private UUID id;
    private String name;
    private String country;
    private double rating;
    private String cuisine;
    private double costForTwo;

    public Restaurant() {
    }

    public Restaurant(UUID id, String name, String country, double rating, String cuisine, double costForTwo) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.rating = rating;
        this.cuisine = cuisine;
        this.costForTwo = costForTwo;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public double getCostForTwo() {
        return costForTwo;
    }

    public void setCostForTwo(double costForTwo) {
        this.costForTwo = costForTwo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", rating=" + rating +
                ", cuisine='" + cuisine + '\'' +
                ", costForTwo=" + costForTwo +
                '}';
    }
}
