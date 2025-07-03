package service.external.client.openrouteservice;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import shared.dto.CoordinatesToMatchDTO;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class OpenRouteServiceClientImplTest {
    private static IOpenRouteServiceClient serviceClient;

    @BeforeAll
    static void setUp()  {
        serviceClient = new OpenRouteServiceClientImpl();
    }


    @Test
    void getResponse() throws IOException {
        System.out.println(serviceClient.getResponse(new CoordinatesToMatchDTO(-2.174030, -79.891824, -2.155412, -79.823990)));


    }
}