package shared.dto;

public record CoordinatesToMatchDTO(
        double originLatitude,
        double originLongitude,
        double destinyLatitude,
        double destinyLongitude

) {
    public String getOrigin(String split) {
        return originLongitude + split + originLatitude;
    }

    public String getDestiny(String split) {
        return destinyLongitude + split + destinyLatitude;
    }
}
