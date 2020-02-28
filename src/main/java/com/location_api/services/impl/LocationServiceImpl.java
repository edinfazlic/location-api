package com.location_api.services.impl;

import com.location_api.helpers.GeolocationCalculator;
import com.location_api.models.Location;
import com.location_api.repositories.LocationRepository;
import com.location_api.services.LocationService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    private LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository repository) {
        locationRepository = repository;
    }

    /**
     * Creates location / saves location to data layer.
     * @param location an object to be saved.
     * @return object that was saved.
     */
    public Location createLocation(Location location) {
        location.setId(ObjectId.get().toString());
        locationRepository.save(location);
        return location;
    }

    /**
     * Updates the Location object on data layer.
     * @param location updated object.
     */
    public void modifyLocation(Location location) {
        locationRepository.save(location);
    }

    /**
     * Deletes Location from data layer by id.
     * @param id of a location to delete.
     */
    public void deleteLocation(String id) {
        locationRepository.deleteById(id);
    }

    /**
     *
     * @return all locations on data layer.
     */
    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    /**
     * Returns Location object by id from data layer.
     * @param id of the location.
     * @return location object.
     */
    public Location getById(String id) {
        return locationRepository.findById(id).orElse(null);
    }

    /**
     * Finds all locations on data layer which are at maximum given distance from given location.
     * @param id of a reference Location.
     * @param radius maximum distance.
     * @param isFilterByAddressId
     * @param lon
     * @param lat
     * @return list of locations whose distance from reference Location is smaller or equal to given radius.
     */
    public List<Location> getFilteredLocations(String id, Double radius, Boolean isFilterByAddressId, Double lon, Double lat) {
        double fromLon;
        double fromLat;
        if (isFilterByAddressId == null || radius == null) {
            return getAll();
        } else if (isFilterByAddressId && id != null && !"".equals(id)) {
            Location fromLocation = locationRepository.findById(id).orElse(null);
            if (null == fromLocation) {
                return new ArrayList<>();
            }
            fromLon = fromLocation.getLng();
            fromLat = fromLocation.getLat();
        } else if (!isFilterByAddressId && lon != null && lat != null) {
            fromLat = lat;
            fromLon = lon;
        } else {
            return getAll();
        }
        double[] boundaries = GeolocationCalculator.getBoundaries(fromLat, fromLon, radius);
        return locationRepository.findByLatBetweenAndLngBetween(boundaries[0], boundaries[1], boundaries[2], boundaries[3]);
    }
}
