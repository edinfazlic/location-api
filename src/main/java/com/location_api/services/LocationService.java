package com.location_api.services;

import com.location_api.models.Location;

import java.util.List;

public interface LocationService {

    Location createLocation(Location location);

    void modifyLocation(Location location);

    void deleteLocation(String id);

    List<Location> getAll();

    Location getById(String id);

    List<Location> getFilteredLocations(String id, Double radius);

}
