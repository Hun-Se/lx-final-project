package com.lx.finalproject.config;


import com.traveltime.sdk.TravelTimeSDK;
import com.traveltime.sdk.auth.TravelTimeCredentials;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IsochroneConfig {

    @Value("${traveltime.appId}")
    private String appId;

    @Value("${traveltime.apiKey}")
    private String apiKey;

    @Bean
    public TravelTimeSDK travelTimeSDK() {
        TravelTimeCredentials credentials = new TravelTimeCredentials(appId, apiKey);
        return new TravelTimeSDK(credentials);
    }
}