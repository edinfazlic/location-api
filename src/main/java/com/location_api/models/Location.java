package com.location_api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
public class Location {

    @Id
    private String id;

    private Double lat;
    private Double lng;
    private String locationName;

}
