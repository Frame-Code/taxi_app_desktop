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
        return "latitude: " + String.valueOf(originLatitude) + ", Longitude: " + String.valueOf(originLongitude);
    }

    public String getDestinyCoordinates() {
        return "latitude: " + String.valueOf(destinyLatitude) + ", Longitude: " + String.valueOf(destinyLongitude);
    }
}
