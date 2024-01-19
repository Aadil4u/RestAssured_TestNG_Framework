package com.spotify.utils;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader() {
        properties = PropertyUtils.propertyLoader("src/main/resources/config.properties");
    }

    public static ConfigLoader getInstance() {
        if (configLoader == null)  {
            configLoader = new ConfigLoader();
        }

        return  configLoader;
    }

    public String getClientId() {
       String prop = properties.getProperty("client_id");
       if(prop !=null) {
           return prop;
       } else {
           throw new RuntimeException("Property Client Id is not specified");
       }
    }

    public String getRefreshToken() {
        String prop = properties.getProperty("refresh_token");
        if(prop !=null) {
            return prop;
        } else {
            throw new RuntimeException("Property refresh_token is not specified");
        }
    }

    public String getClientSecret() {
        String prop = properties.getProperty("client_secret");
        if(prop !=null) {
            return prop;
        } else {
            throw new RuntimeException("Property client_secret is not specified");
        }
    }

    public String getUserId() {
        String prop = properties.getProperty("userId");
        if(prop !=null) {
            return prop;
        } else {
            throw new RuntimeException("Property userId is not specified");
        }
    }

    public String getGrantType() {
        String prop = properties.getProperty("grant_type");
        if(prop !=null) {
            return prop;
        } else {
            throw new RuntimeException("Property grant_type is not specified");
        }
    }


}
