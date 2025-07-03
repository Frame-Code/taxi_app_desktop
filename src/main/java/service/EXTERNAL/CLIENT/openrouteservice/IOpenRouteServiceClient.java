package service.external.client.openrouteservice;

import shared.dto.CoordinatesToMatchDTO;

import java.io.IOException;

/**
 *
 * @author Daniel Mora Cantillo
 */
public interface IOpenRouteServiceClient {
    String getResponse(CoordinatesToMatchDTO coordinatesToMatchDTO) throws IOException;
}
