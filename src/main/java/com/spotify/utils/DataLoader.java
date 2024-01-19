package com.spotify.utils;

import java.util.Properties;

public class DataLoader {
    private final Properties properties;
    private static DataLoader dataLoader;

    private DataLoader() {
        properties = PropertyUtils.propertyLoader("src/main/resources/data.properties");
    }

    public static DataLoader getInstance() {
        if (dataLoader == null)  {
            dataLoader = new DataLoader();
        }

        return  dataLoader;
    }


    public String getPlaylistId() {
        String prop = properties.getProperty("playlistId");
        if(prop !=null) {
            return prop;
        } else {
            throw new RuntimeException("Property playlistId is not specified");
        }
    }




}
