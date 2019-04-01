package com.blog.traloc.connectors;

import com.blog.traloc.entity.*;
import com.datastax.driver.core.ConsistencyLevel;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import java.util.List;

import static com.datastax.spark.connector.japi.CassandraJavaUtil.*;

/**
 * Connector for reading and writing data from/to cassandra
 */
public class Connector {
    private static final String KEYSPACE = "travel";
    private static final String RAW_TABLE = "raw_data";
    private static final String COUNTRY = "country";
    private static final String TRANS_TABLE = "transformed_data";
    private static final String RESTAURANT_TABLE = "restaurant_data";
    private static final JavaSparkContext CONTEXT = ConnectorContext.getContext();

    /**
     * Writes raw data
     *
     * @param rawDataList list of raw data objects
     */
    public static void writeRawData(List<RawData> rawDataList) {
        JavaRDD<RawData> rawDataJavaRDD = CONTEXT.parallelize(rawDataList);
        javaFunctions(rawDataJavaRDD).writerBuilder(KEYSPACE, RAW_TABLE, mapToRow(RawData.class)).withConsistencyLevel(ConsistencyLevel.ONE).saveToCassandra();
    }

    /**
     * Writes country information
     *
     * @param countryData list of country objects
     */
    public static void writeCountries(List<Country> countryData) {
        JavaRDD<Country> rawDataJavaRDD = CONTEXT.parallelize(countryData);
        javaFunctions(rawDataJavaRDD).writerBuilder(KEYSPACE, COUNTRY, mapToRow(Country.class)).withConsistencyLevel(ConsistencyLevel.ONE).saveToCassandra();
    }

    /**
     * Writes restaurant information
     *
     * @param restaurants list of restaurants objects
     */
    public static void writeRestaurants(List<Restaurant> restaurants) {
        JavaRDD<Restaurant> rawDataJavaRDD = CONTEXT.parallelize(restaurants);
        javaFunctions(rawDataJavaRDD).writerBuilder(KEYSPACE, RESTAURANT_TABLE, mapToRow(Restaurant.class)).withConsistencyLevel(ConsistencyLevel.ONE).saveToCassandra();
    }

    /**
     * Writes transformed data
     *
     * @param transformedData list of transformed data objects
     */
    public static void writeTransformedData(List<TransformedData> transformedData) {
        JavaRDD<TransformedData> transformedDataJavaRDD = CONTEXT.parallelize(transformedData);
        javaFunctions(transformedDataJavaRDD).writerBuilder(KEYSPACE, TRANS_TABLE, mapToRow(TransformedData.class)).withConsistencyLevel(ConsistencyLevel.ONE).saveToCassandra();
    }

    /**
     * Writes combined (final) data
     *
     * @param combinedData combined data list
     */
    public static void writeCombinedData(List<CombinedDTO> combinedData) {
        JavaRDD<CombinedDTO> transformedDataJavaRDD = CONTEXT.parallelize(combinedData);
        javaFunctions(transformedDataJavaRDD).writerBuilder(KEYSPACE, "final_data", mapToRow(CombinedDTO.class)).withConsistencyLevel(ConsistencyLevel.ANY).saveToCassandra();
    }

    /**
     * Fetches restaurant data from cassandra
     *
     * @param country country to be matched with
     * @return iterable of restaurants
     */
    public static JavaRDD<Restaurant> readRestaurantData(String country) {
        JavaRDD<Restaurant> rdd = javaFunctions(CONTEXT).cassandraTable(KEYSPACE, RESTAURANT_TABLE, mapRowTo(Restaurant.class)).where("country=?", country).map(new Function<Restaurant, Restaurant>() {
            @Override
            public Restaurant call(Restaurant transformedData) throws Exception {
                return transformedData;
            }
        });
        return rdd;
    }

    /**
     * Fetches transformed data from cassandra
     *
     * @param country country to be matched with
     * @return iterable of transformed data
     */
    public static List<TransformedData> readTransformedData(String country) {
        JavaRDD<TransformedData> rdd = javaFunctions(CONTEXT).cassandraTable(KEYSPACE, TRANS_TABLE, mapRowTo(TransformedData.class)).where("country=?", country).map(new Function<TransformedData, TransformedData>() {
            @Override
            public TransformedData call(TransformedData transformedData) throws Exception {
                return transformedData;
            }
        });
        return rdd.collect();
    }

    /**
     * Fetches country data from cassandra
     *
     * @param country country to be matched with
     * @return iterable of country
     */
    public static List<Country> readCountryData(String country) {
        JavaRDD<Country> rdd = javaFunctions(CONTEXT).cassandraTable(KEYSPACE, COUNTRY, mapRowTo(Country.class)).where("country=?", country).map(new Function<Country, Country>() {
            @Override
            public Country call(Country transformedData) throws Exception {
                return transformedData;
            }
        });
        return rdd.collect();
    }

    /**
     * Fetches raw data from cassandra
     *
     * @param country country to be matched with
     * @return iterable of raw
     */
    public static JavaRDD<RawData> readRawData(String country) {
        JavaRDD<RawData> rdd = javaFunctions(CONTEXT).cassandraTable(KEYSPACE, RAW_TABLE, mapRowTo(RawData.class)).where("country=?", country).map(new Function<RawData, RawData>() {
            @Override
            public RawData call(RawData rawData) throws Exception {
                return rawData;
            }
        });
        return rdd;
    }

    /**
     * Fetches list of countries data is available for
     *
     * @return list of distinct countries
     */
    public static JavaRDD<String> getDistinctCountry() {
        JavaRDD<String> rdd = javaFunctions(CONTEXT).cassandraTable(KEYSPACE, RAW_TABLE, mapRowTo(RawData.class)).map(new Function<RawData, String>() {
            @Override
            public String call(RawData rawData) throws Exception {
                return rawData.getCountry();
            }
        }).distinct();
        return rdd;
    }

    /**
     * Fetches the top restaurants
     *
     * @param country country to filter by
     * @return list of restaurants
     */
    public static List<Restaurant> topRestaurants(String country) {
        JavaRDD<Restaurant> restaurantJavaRDD = readRestaurantData(country).sortBy(new Function<Restaurant, Double>() {
            @Override
            public Double call(Restaurant restaurant) throws Exception {
                return restaurant.getRating();
            }
        }, false, 1);
        return restaurantJavaRDD.collect();
    }
}
