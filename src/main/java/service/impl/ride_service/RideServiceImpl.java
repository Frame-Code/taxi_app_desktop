package service.impl.ride_service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import domain.entities.Ride;
import domain.repository.interfaces.RideRepository;
import lombok.RequiredArgsConstructor;
import service.external.client.openrouteservice.IOpenRouteServiceClient;
import service.interfaces.ride_module.IRideService;
import shared.dto.CoordinatesToMatchDTO;
import shared.dto.InfoRideDTO;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
public class RideServiceImpl implements IRideService {
    private final RideRepository repository;
    private final IOpenRouteServiceClient openRouteServiceClient;
    private final Gson gson = new Gson();

    @Override
    public Ride save(Ride ride) {
        return repository.save(ride);
    }

    @Override
    public Optional<InfoRideDTO> getInfoRide(CoordinatesToMatchDTO coordinatesToMatchDTO) throws IOException, NullPointerException {
        String response = openRouteServiceClient.getResponse(coordinatesToMatchDTO);
        JsonElement rootElement = gson.fromJson(response, JsonElement.class);
        if(rootElement.isJsonObject()) {
            JsonObject rootObject = rootElement.getAsJsonObject();
            JsonElement rideSegments = rootObject.get("features")
                    .getAsJsonArray()
                    .get(0)
                    .getAsJsonObject()
                    .get("properties")
                    .getAsJsonObject()
                    .get("segments")
                    .getAsJsonArray()
                    .get(0)
                    .getAsJsonObject();
            return Optional.of(new InfoRideDTO(rideSegments.getAsJsonObject().get("distance").getAsDouble(),
                    rideSegments.getAsJsonObject().get("duration").getAsDouble()));
        }
        return Optional.empty();
    }
}
