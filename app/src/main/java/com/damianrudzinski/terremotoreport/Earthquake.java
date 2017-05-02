package com.damianrudzinski.terremotoreport;

/**
 * Created by damianrudzinski on 28.04.2017.
 */

public class Earthquake {

    private Double earthquakeMagnitude;
    private String earthquakeCity;
    private Long earthquakeDate;

    public Earthquake(Double mEarthquakeMagnitude, String mEarthquakeCity, Long mEarthquakeDate) {
        earthquakeMagnitude = mEarthquakeMagnitude;
        earthquakeCity = mEarthquakeCity;
        earthquakeDate = mEarthquakeDate;
    }

    public Earthquake(String mEarthquakeCity) {
        earthquakeCity = mEarthquakeCity;
    }

    public Double getEarthquakeMagnitude() {
        return earthquakeMagnitude;
    }

    public String getEarthquakeCity() {
        return earthquakeCity;
    }

    public Long getEarthquakeDate() {
        return earthquakeDate;
    }

}
