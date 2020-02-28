package com.location_api.controllers;

import com.location_api.models.Location;
import com.location_api.services.LocationService;
import com.location_api.services.impl.LocationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class LocationsController {
    private LocationService locationService;

    @Autowired
    public LocationsController(LocationServiceImpl locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(value = "/location", method = RequestMethod.POST)
    public Location addLocation(@Valid @RequestBody Location location) {
        return locationService.createLocation(location);
    }

    @RequestMapping(value = "/location", method = RequestMethod.PUT)
    public void modify(@Valid @RequestBody Location location) {
        locationService.modifyLocation(location);
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.DELETE)
    public void deleteLocation(@PathVariable String id) {
        locationService.deleteLocation(id);
    }

    @RequestMapping(value = "/location", method = RequestMethod.GET)
    public List<Location> getAllLocations() {
        return locationService.getAll();
    }

    @RequestMapping(value = "/location/{id}", method = RequestMethod.GET)
    public Location getSpecificLocation(@PathVariable("id") String id) {
        return locationService.getById(id);
    }

    @RequestMapping(value = "/location/filtered", method = RequestMethod.GET)
    public List<Location> getFiltered(@RequestParam(required = false) String addressId,
                                      @RequestParam(required = false) Double radius,
                                      @RequestParam(required = false) Boolean isFilterByAddressId,
                                      @RequestParam(required = false) Double lon,
                                      @RequestParam(required = false) Double lat) {
        return locationService.getFilteredLocations(addressId, radius, isFilterByAddressId, lon, lat);
    }
}
