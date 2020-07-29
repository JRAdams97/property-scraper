package au.com.jradams.propertyscraper.model;

import java.util.ArrayList;
import java.util.List;

public class ConfigModel {

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //  Instance Fields
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    private int bedrooms;
    private int bathrooms;
    private int carparks;
    private long minPrice;
    private long maxPrice;

    private List<String> filterInKeywords = new ArrayList<>();
    private List<String> filterOutKeywords = new ArrayList<>();
    private List<String> suburbs = new ArrayList<>();

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //  Constructor(s)
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public ConfigModel() {
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //  Accessors & Mutators
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getCarparks() {
        return carparks;
    }

    public void setCarparks(int carparks) {
        this.carparks = carparks;
    }

    public long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(long minPrice) {
        this.minPrice = minPrice;
    }

    public long getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(long maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<String> getFilterInKeywords() {
        return filterInKeywords;
    }

    public void setFilterInKeywords(List<String> filterInKeywords) {
        this.filterInKeywords = filterInKeywords;
    }

    public List<String> getFilterOutKeywords() {
        return filterOutKeywords;
    }

    public void setFilterOutKeywords(List<String> filterOutKeywords) {
        this.filterOutKeywords = filterOutKeywords;
    }

    public List<String> getSuburbs() {
        return suburbs;
    }

    public void setSuburbs(List<String> suburbs) {
        this.suburbs = suburbs;
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //  Methods
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

    @Override
    public String toString() {
        return "ConfigModel{" +
                "bedrooms=" + bedrooms +
                ", bathrooms=" + bathrooms +
                ", carparks=" + carparks +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", filterInKeywords=" + filterInKeywords +
                ", filterOutKeywords=" + filterOutKeywords +
                ", suburbs=" + suburbs +
                '}';
    }
}
