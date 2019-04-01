package com.blog.traloc.controller;

import com.blog.traloc.entity.CombinedDTO;
import com.blog.traloc.service.TralocService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

/**
 * API to access the final object
 */
public class TralocController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TralocController.class);

    public static void main(String[] args) {
        staticFileLocation("/static/countries_html");
        get("/api/traloc", (request, response) -> {
            response.type("application/json");
            String country = request.queryParams("country");
            CombinedDTO combinedDTO = TralocService.getInfo(country);
            LOGGER.info(combinedDTO.toString());
            return new Gson().toJson(combinedDTO);
        });
    }
}
