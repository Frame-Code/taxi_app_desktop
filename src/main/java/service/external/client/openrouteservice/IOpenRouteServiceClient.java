package service.external.client.openrouteservice;

import shared.dto.CoordinatesRideDTO;

import java.io.IOException;

/**
 *
 * @author Daniel Mora Cantillo
 */
public interface IOpenRouteServiceClient {
    String getResponse(CoordinatesRideDTO coordinatesRideDTO) throws IOException;
}
