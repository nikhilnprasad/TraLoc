package com.blog.traloc.process;

import com.blog.traloc.connectors.Connector;
import com.blog.traloc.entity.Country;
import com.blog.traloc.entity.RawData;
import com.blog.traloc.entity.Restaurant;
import com.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * Utility class to enable the process in the project
 */
public class ProcessUtils {
    private static final String BLOG_FILE = "blog_data_final.csv";
    private static final String RESTAURANT_FILE = "zomato.csv";
    private static final String COUNTRY_FILE = "combine_final.csv";
    private static final Logger LOGGER = LoggerFactory.getLogger(Process.class);
    private static final ClassLoader CLASS_LOADER = ProcessUtils.class.getClassLoader();
    private static final String DATA_FOLDER = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "data";

    /**
     * Reads raw data from file
     */
    public void readFromFile() {
        try (Stream<Path> paths = Files.walk(Paths.get(DATA_FOLDER))) {
            paths.filter(Files::isRegularFile).filter(p -> p.toString().endsWith(BLOG_FILE)).forEach(file -> csvToCassandra(file.toString()));
        } catch (IOException ie) {
            LOGGER.error(ie.getMessage());
        }
    }

    /**
     * Reads file with country information
     */
    public void readCountryFile() {
        try (Stream<Path> paths = Files.walk(Paths.get(DATA_FOLDER))) {
            paths.filter(Files::isRegularFile).filter(p -> p.toString().endsWith(COUNTRY_FILE)).forEach(file -> csvToCassandraCountry(file.toString()));
        } catch (IOException ie) {
            LOGGER.error(ie.getMessage());
        }
    }

    /**
     * Reads from restaurant file
     */
    public void readRestaurantFile() {
        try (Stream<Path> paths = Files.walk(Paths.get(DATA_FOLDER))) {
            paths.filter(Files::isRegularFile).filter(p -> p.toString().endsWith(RESTAURANT_FILE)).forEach(file -> csvToCassandraZomato(file.toString()));
        } catch (IOException ie) {
            LOGGER.error(ie.getMessage());
        }
    }

    /**
     * Reads the CSV file and writes it into cassandra
     *
     * @param file file to be read
     */
    public void csvToCassandra(String file) {
        List<RawData> rawData = new ArrayList<>();
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(file));
            //reading the header line
            String[] line = reader.readNext();
            while ((line = reader.readNext()) != null) {
                rawData.add(new RawData(UUID.randomUUID(), line[0], line[2], line[1]));
            }
        } catch (IOException ie) {
            LOGGER.error(ie.getMessage());
        }
        Connector.writeRawData(rawData);
    }

    /**
     * Reads the CSV file and writes it into cassandra
     *
     * @param file file to be read
     */
    public void csvToCassandraZomato(String file) {
        List<Restaurant> restaurants = new ArrayList<>();
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(file));
            //reading the header line
            String[] line = reader.readNext();
            while ((line = reader.readNext()) != null) {
                restaurants.add(new Restaurant(UUID.randomUUID(), line[1], line[2], Double.parseDouble(line[17]), line[9], Double.parseDouble(line[10])));
            }
        } catch (IOException ie) {
            LOGGER.error(ie.getMessage());
        }
        Connector.writeRestaurants(restaurants);
    }

    /**
     * Reads the CSV file and writes it into cassandra
     *
     * @param file file to be read
     */
    public void csvToCassandraCountry(String file) {
        CSVReader reader = null;
        List<Country> countries = new ArrayList<>();
        try {
            reader = new CSVReader(new FileReader(file));
            //reading the header line
            String[] line = reader.readNext();
            while ((line = reader.readNext()) != null) {
                countries.add(new Country(line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7], line[8], line[9], line[10], line[11], line[12], line[13], line[14], line[15], line[16], line[17], line[18], line[19], line[20]));
            }
        } catch (IOException ie) {
            LOGGER.error(ie.getMessage());
        }
        Connector.writeCountries(countries);
    }
}
