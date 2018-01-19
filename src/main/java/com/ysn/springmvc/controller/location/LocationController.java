package com.ysn.springmvc.controller.location;

import com.ysn.springmvc.model.base.Diagnostic;
import com.ysn.springmvc.model.location.Location;
import com.ysn.springmvc.repository.user.location.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    LocationRepository locationRepository;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> listAllLocations() {
        log("listAllLocations");
        Map<String, Object> mapData = new HashMap<>();
        List<Location> locations = new ArrayList<>();
        Diagnostic diagnostic = new Diagnostic(HttpStatus.OK.value(), HttpStatus.OK.name(), new Date().getTime());
        mapData.put("diagnostic", diagnostic);
        for (Location location : locationRepository.findAll()) {
            locations.add(location);
        }
        mapData.put("data", locations);
        return new ResponseEntity<>(mapData, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> createLocation(@RequestBody Location location) {
        log("createLocation");
        Map<String, Object> mapDataReturn = new HashMap<>();
        Diagnostic diagnostic = new Diagnostic();
        diagnostic.setUnix_timestamp(new Date().getTime());
        boolean isFieldValid = true;
        if (location.getLatitude() == 0) {
            diagnostic.setMessage("Field latitude is required");
        } else if (location.getLongitude() == 0) {
            diagnostic.setMessage("Field longitude is required");
        } else if (location.getSpeed() == 0) {
            diagnostic.setMessage("Field speed is required");
        }

        if (!isFieldValid) {
            diagnostic.setStatus(HttpStatus.BAD_REQUEST.value());
            mapDataReturn.put("diagnostic", diagnostic);
            return new ResponseEntity<>(mapDataReturn, HttpStatus.BAD_REQUEST);
        } else {
            diagnostic.setStatus(HttpStatus.CREATED.value());
            diagnostic.setMessage(HttpStatus.CREATED.name());
            mapDataReturn.put("diagnostic", diagnostic);
        }
        mapDataReturn.put("data", location);
        locationRepository.save(location);
        return new ResponseEntity<>(mapDataReturn, HttpStatus.CREATED);
    }

    private void log(String message) {
        System.out.println(message);
    }
}
