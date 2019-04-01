package com.blog.traloc.process;

import com.blog.traloc.analysis.SentimentAnalyzer;
import com.blog.traloc.connectors.Connector;
import com.blog.traloc.entity.TransformedData;

import java.util.ArrayList;
import java.util.List;

/**
 * Processes the data
 */
public class Process {

    /**
     * Loads all the data into cassandra
     */
    private static void loadData() {
        ProcessUtils process = new ProcessUtils();
        process.readFromFile();
        process.readRestaurantFile();
        process.readCountryFile();
    }

    /**
     * Transforms and loads transformed data into cassandra
     */
    private static void processData() {
        List<TransformedData> transformedData = new ArrayList<>();
        List<String> countries = Connector.getDistinctCountry().collect();
        for (String country : countries) {
            transformedData.add(new TransformedData(SentimentAnalyzer.analyze(Connector.readRawData(country)), country));
        }
        Connector.writeTransformedData(transformedData);
    }

    /**
     * Loads and processes raw data
     */
    public static void loadAndProcessData() {
        Process.loadData();
        Process.processData();
    }
}
