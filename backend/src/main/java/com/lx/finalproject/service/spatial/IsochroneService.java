package com.lx.finalproject.service.spatial;

import com.traveltime.sdk.TravelTimeSDK;
import com.traveltime.sdk.dto.requests.TimeMapRequest;
import com.traveltime.sdk.dto.responses.TimeMapResponse;
import com.traveltime.sdk.dto.common.Coordinates;
import com.traveltime.sdk.dto.common.transportation.Driving;
import com.traveltime.sdk.dto.common.transportation.PublicTransport;
import com.traveltime.sdk.dto.common.transportation.Walking;
import com.traveltime.sdk.dto.common.transportation.Transportation;
import com.traveltime.sdk.dto.requests.timemap.DepartureSearch;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;

@Service
public class IsochroneService {
    private final TravelTimeSDK sdk;

    public IsochroneService(TravelTimeSDK sdk) {
        this.sdk = sdk;
    }

    public TimeMapResponse getIsochrone(double lat, double lng, String transport, int duration) {
        Transportation transportation;

        // 교통수단 선택
        switch (transport.toLowerCase()) {
            case "walking":
                transportation =  new Walking(); 
                break;
            case "driving":
                transportation = Driving.builder().build();
                break;
            case "public_transport":
            default:
                transportation = PublicTransport.builder().build();
                break;
        }

        DepartureSearch departureSearch = DepartureSearch.builder()
            .id("Isochrone from point")
            .coords(new Coordinates(lat, lng))
            .transportation(transportation)  // 선택한 교통수단 사용
            .departureTime(Instant.now())
            .travelTime(duration)
            .build();

        TimeMapRequest request = TimeMapRequest.builder()
            .departureSearches(Collections.singletonList(departureSearch))
            .build();

        return sdk.send(request).getOrElseThrow(error -> new RuntimeException(error.getMessage()));
    }
}
