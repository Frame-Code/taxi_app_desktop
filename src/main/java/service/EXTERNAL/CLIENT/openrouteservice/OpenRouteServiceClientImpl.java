package service.external.client.openrouteservice;

import shared.dto.CoordinatesToMatchDTO;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class OpenRouteServiceClientImpl {
    private final String API_KEY = "5b3ce3597851110001cf6248431dfed58cac459ab8a456569ef40232";


    String getResponse(CoordinatesToMatchDTO coordinatesToMatchDTO) {
        StringBuilder sb = new StringBuilder("https://api.openrouteservice.org/v2/directions/driving-car?");
        sb.append(API_KEY)
                .append("&start")
                .append(coordinatesToMatchDTO.originLongitude())
                .append(",")
                .append(coordinatesToMatchDTO.originLatitude())
                .append("&end").append(coordinatesToMatchDTO.destinyLongitude())
                .append(",").append(coordinatesToMatchDTO.destinyLatitude());

        Client client = ClientBuilder.newClient();
        Response response = client
                .target(sb.toString())
                .request(MediaType.TEXT_PLAIN_TYPE)
                .header("Accept", "application/json, application/geo+json, application/gpx+xml, img/png; charset=utf-8")
                .get();
        return response.toString();
    }

}
