package com.location_api.helpers;

public class GeolocationCalculator {
    private static final double EARTH_RADIUS = 3958.8; // Approx Earth radius in miles

    public static double[] getBoundaries(double centerLat, double centerLon, double radius) {
        GeoLocation location = GeoLocation.fromRadians(centerLat, centerLon);
        GeoLocation[] boundingCoordinates = location.boundingCoordinates(radius, EARTH_RADIUS);
        GeoLocation bound1 = boundingCoordinates[0];
        GeoLocation bound2 = boundingCoordinates[1];

        double minLat = bound1.getLatitudeInRadians();
        double maxLat = bound2.getLatitudeInRadians();
        double minLon;
        double maxLon;
        if (bound1.getLongitudeInRadians() <= bound2.getLongitudeInRadians()) {
            minLon = bound1.getLongitudeInRadians();
            maxLon = bound2.getLongitudeInRadians();
        } else {
            maxLon = bound1.getLongitudeInRadians();
            minLon = bound2.getLongitudeInRadians();
        }

        return new double[]{minLat, maxLat, minLon, maxLon};
    }
}
