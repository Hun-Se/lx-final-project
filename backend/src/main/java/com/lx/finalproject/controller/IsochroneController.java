package com.lx.finalproject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lx.finalproject.service.spatial.IsochroneService;
import com.traveltime.sdk.dto.responses.TimeMapResponse;

@RestController
@RequestMapping("/api/isochrone/search")
public class IsochroneController {

    private final IsochroneService isochroneService;

    public IsochroneController(IsochroneService isochroneService) {
        this.isochroneService = isochroneService;
    }

    @GetMapping
    public ResponseEntity<TimeMapResponse> getIsochrone(
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam String transport,
            @RequestParam int duration) {
        try {
            TimeMapResponse response = isochroneService.getIsochrone(lat, lng,transport,duration );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
