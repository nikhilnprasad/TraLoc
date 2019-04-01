package com.blog.traloc.service;

import com.blog.traloc.connectors.Connector;
import com.blog.traloc.entity.*;
import com.blog.traloc.process.Process;
import org.apache.commons.lang.WordUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer for fetching the combined data
 */
public class TralocService {

    /**
     * Fetches the combined data for the API
     *
     * @param country country to filter by
     * @return combined data
     */
    public static CombinedDTO getInfo(String country) {
        country = WordUtils.capitalize(country);

        //some country data contains special characters that need to be replaced
        String usaCountry = WordUtils.capitalize(country.replace("_", " "));

        //USA has different spellings and versions
        if (country.equals("Usa")) {
            usaCountry = "United States";
        }
        List<Country> countries = Connector.readCountryData(usaCountry);
        Country countryData = new Country();
        if (countries != null && countries.size() > 0) {
            countryData = countries.get(0);
        }
        List<String> positive = new ArrayList<>();
        List<TransformedData> data;
        if ((data = Connector.readTransformedData(country.toLowerCase())) != null && data.size() > 0) {
            positive.addAll(data.get(0).getPositive());
        }
        List<String> urls = new ArrayList<>();
        for (RawData rawData : Connector.readRawData(country.toLowerCase()).collect()) {
            urls.add(rawData.getUrl());
        }
        List<Restaurant> topRestaurants = new ArrayList<>();
        List<Restaurant> restaurants = Connector.topRestaurants(usaCountry);

        //get the top 5 restaurants
        if (restaurants.size() > 5) {
            topRestaurants.addAll(restaurants.subList(0, 5));
        } else {
            topRestaurants.addAll(restaurants);
        }
        return new CombinedDTO(positive, topRestaurants, urls, countryData);
    }
}
