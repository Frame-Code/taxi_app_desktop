package service.external.client.openrouteservice;

import shared.dto.CoordinatesRideDTO;
import shared.dto.CoordinatesToMatchDTO;
import shared.utils.HttpClientUtil;

import java.io.IOException;
import java.util.Map;

/**
 *
 * @author Daniel Mora Cantillo
 */
public class OpenRouteServiceClientImpl implements IOpenRouteServiceClient{
    private final String API_KEY = "5b3ce3597851110001cf6248431dfed58cac459ab8a456569ef40232";
    private final String ENDPOINT_DRIVING_CAR = "https://api.openrouteservice.org/v2/directions/driving-car?";

    @Override
    public String getResponse(CoordinatesRideDTO coordinatesRideDTO) throws IOException {
        Map<String, String> params = Map.of(
                "api_key", API_KEY,
                "start", coordinatesRideDTO.getOrigin(","),
                "end",  coordinatesRideDTO.getDestiny(",")
        );
        Map<String, String> headers = Map.of(
                "Accept", "application/json, application/geo+json, application/gpx+xml, img/png; charset=utf-8"
        );

        return HttpClientUtil.getResponse(ENDPOINT_DRIVING_CAR, headers, params);
    }

}
