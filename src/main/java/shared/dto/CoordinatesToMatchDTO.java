package shared.dto;

public record CoordinatesToMatchDTO(
        double originLatitude,
        double originLongitude,
        double destinyLatitude,
        double destinyLongitude)
{
    public String getOrigin(String splitter) {
        return originLongitude + splitter + originLatitude;
    }

    public String getDestiny(String splitter) {
        return destinyLongitude + splitter + destinyLatitude;
    }
}
