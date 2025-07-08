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

    public String getOrigin(String splitter) {
        return originLongitude + splitter + originLatitude;
    }

    public String getDestiny(String splitter) {
        return destinyLongitude + splitter + destinyLatitude;
    }
}
