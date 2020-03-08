package com.location_api.controllers;

import com.location_api.models.Location;
import com.location_api.services.LocationService;
import com.location_api.services.impl.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/location")
@CrossOrigin("*") // todo: remove wildcard
public class LocationsController {
    private LocationService locationService;

    @Autowired
    public LocationsController(LocationServiceImpl locationService) {
        this.locationService = locationService;
    }

    @PostMapping()
    public Location create(@Valid @RequestBody Location location) {
        return locationService.createLocation(location);
    }

    @PutMapping()
    public void modify(@Valid @RequestBody Location location) {
        locationService.modifyLocation(location);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable String id) {
        locationService.deleteLocation(id);
    }

    @GetMapping(value = "/all")
    public List<Location> getAll() {
        return locationService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Location getSpecific(@PathVariable("id") String id) {
        return locationService.getById(id);
    }

    @GetMapping(value = "/filtered")
    public List<Location> getFiltered(@RequestParam(required = false) String addressId,
                                      @RequestParam(required = false) Double radius,
                                      @RequestParam(required = false) Boolean isFilterByAddressId,
                                      @RequestParam(required = false) Double lon,
                                      @RequestParam(required = false) Double lat) {
        return locationService.getFilteredLocations(addressId, radius, isFilterByAddressId, lon, lat);
    }
}
