package shared.dto;

public record CoordinatesRideDTO (
        String originReference,
        double originLatitude,
        double originLongitude,
        String destinyReference,
        double destinyLatitude,
        double destinyLongitude

){
    public String getOriginCoordinates() {
        return "latitude: " + originLatitude + ", Longitude: " + originLongitude;
    }

    public String getDestinyCoordinates() {
        return "latitude: " + destinyLatitude + ", Longitude: " + destinyLongitude;
    }
}
