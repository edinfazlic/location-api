package com.location_api.repositories;

import com.location_api.models.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LocationRepository extends MongoRepository<Location, String> {

    List<Location> findByLatBetweenAndLngBetween(Double minLat, Double maxLat, Double minLon, Double maxLon);

}
