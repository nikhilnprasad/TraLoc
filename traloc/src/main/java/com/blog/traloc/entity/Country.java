package com.blog.traloc.entity;

import java.io.Serializable;

/**
 * POJO for country information
 */
public class Country implements Serializable{
    private String country;
    private String abbreviation;
    private String capital;
    private String north;
    private String south;
    private String east;
    private String west;
    private String continent;
    private String region;
    private String languages;
    private String area;
    private String elevation;
    private String population;
    private String populationDensity;
    private String currencyName;
    private String currencyCode;
    private String nationalSymbol;
    private String symbolName;
    private String nationalDish;
    private String averageTemperature;
    private String measurement;

    public Country() {
    }

    public Country(String country, String abbreviation, String capital, String north, String south, String east, String west, String continent, String region, String languages, String area, String elevation, String population, String populationDensity, String currencyName, String currencyCode, String nationalSymbol, String symbolName, String nationalDish, String averageTemperature, String measurement) {
        this.country = country;
        this.abbreviation = abbreviation;
        this.capital = capital;
        this.north = north;
        this.south = south;
        this.east = east;
        this.west = west;
        this.continent = continent;
        this.region = region;
        this.languages = languages;
        this.area = area;
        this.elevation = elevation;
        this.population = population;
        this.populationDensity = populationDensity;
        this.currencyName = currencyName;
        this.currencyCode = currencyCode;
        this.nationalSymbol = nationalSymbol;
        this.symbolName = symbolName;
        this.nationalDish = nationalDish;
        this.averageTemperature = averageTemperature;
        this.measurement = measurement;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

    public String getWest() {
        return west;
    }

    public void setWest(String west) {
        this.west = west;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        region = region;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getElevation() {
        return elevation;
    }

    public void setElevation(String elevation) {
        this.elevation = elevation;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getPopulationDensity() {
        return populationDensity;
    }

    public void setPopulationDensity(String populationDensity) {
        this.populationDensity = populationDensity;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getNationalSymbol() {
        return nationalSymbol;
    }

    public void setNationalSymbol(String nationalSymbol) {
        this.nationalSymbol = nationalSymbol;
    }

    public String getSymbolName() {
        return symbolName;
    }

    public void setSymbolName(String symbolName) {
        this.symbolName = symbolName;
    }

    public String getNationalDish() {
        return nationalDish;
    }

    public void setNationalDish(String nationalDish) {
        this.nationalDish = nationalDish;
    }

    public String getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(String averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    @Override
    public String toString() {
        return "Country{" +
                "country='" + country + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", capital='" + capital + '\'' +
                ", north='" + north + '\'' +
                ", south='" + south + '\'' +
                ", east='" + east + '\'' +
                ", west='" + west + '\'' +
                ", continent='" + continent + '\'' +
                ", Region='" + region + '\'' +
                ", languages='" + languages + '\'' +
                ", area='" + area + '\'' +
                ", elevation='" + elevation + '\'' +
                ", population='" + population + '\'' +
                ", populationDensity='" + populationDensity + '\'' +
                ", currencyName='" + currencyName + '\'' +
                ", currencyCode='" + currencyCode + '\'' +
                ", nationalSymbol='" + nationalSymbol + '\'' +
                ", symbolName='" + symbolName + '\'' +
                ", nationalDish='" + nationalDish + '\'' +
                ", averageTemperature='" + averageTemperature + '\'' +
                ", measurement='" + measurement + '\'' +
                '}';
    }
}
