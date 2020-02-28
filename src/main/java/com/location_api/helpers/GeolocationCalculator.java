package com.location_api.helpers;

public class GeolocationCalculator {
    private static final double EARTH_RADIUS = 3958.8; // Approx Earth radius in miles

    public static double[] getBoundaries(double centerLat, double centerLon, double radius) {
        GeoLocation location = GeoLocation.fromDegrees(centerLat, centerLon);
        GeoLocation[] boundingCoordinates = location.boundingCoordinates(radius, EARTH_RADIUS);
        GeoLocation bound1 = boundingCoordinates[0];
        GeoLocation bound2 = boundingCoordinates[1];

        double minLat = bound1.getLatitudeInDegrees();
        double maxLat = bound2.getLatitudeInDegrees();
        double minLon;
        double maxLon;
        if (bound1.getLongitudeInDegrees() <= bound2.getLongitudeInDegrees()) {
            minLon = bound1.getLongitudeInDegrees();
            maxLon = bound2.getLongitudeInDegrees();
        } else {
            maxLon = bound1.getLongitudeInDegrees();
            minLon = bound2.getLongitudeInDegrees();
        }

        return new double[]{minLat, maxLat, minLon, maxLon};
    }
}
