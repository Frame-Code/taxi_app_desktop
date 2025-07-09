package service.impl.ride_service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import domain.entities.Ride;
import domain.repository.interfaces.CabRepository;
import domain.repository.interfaces.RideRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import service.external.client.openrouteservice.IOpenRouteServiceClient;
import service.interfaces.ride_module.IRideService;
import shared.dto.CoordinatesRideDTO;
import shared.dto.InfoRideDTO;
import shared.enums.STATUS_RIDE;
import shared.enums.STATUS_TAXI;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author Daniel Mora Cantillo
 */
@CommonsLog
@RequiredArgsConstructor
public class RideServiceImpl implements IRideService {
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    private final RideRepository repository;
    private final CabRepository cabRepository;
    private final IOpenRouteServiceClient openRouteServiceClient;
    private final Gson gson = new Gson();

    @Override
    public Ride save(Ride ride) {
        ride.getCab().setStatus(STATUS_TAXI.WORKING);
        cabRepository.update(ride.getCab());
        return repository.save(ride);
    }

    @Override
    public Optional<Ride> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public boolean setOriginConfirm(Long id) {
        return findById(id)
                .map(ride -> {
                    ride.setStatus(STATUS_RIDE.ORIGIN_CONFIRMED);
                    repository.update(ride);
                    return true;
                })
                .orElse(false);
    }

    @Override
    public boolean isReadyToStart(Long id) throws InterruptedException {
        log.info("Starting verifier task for ride...");
        boolean isReady = false;
        while (true) {
            var rideOpt = repository.findById(id);
            if (rideOpt.isEmpty()) {
                break;
            }

            if (rideOpt.get().isReadyToStart()) {
                isReady = true;
                break;
            }

            Thread.sleep(2000);
        }
        return isReady;
    }

    @Override
    public boolean isOriginConfirm(Long id) throws InterruptedException {
        log.info("Starting verifier task for ride...");
        boolean isOriginConfirmed = false;
        while (true) {
            var rideOpt = repository.findById(id);
            if (rideOpt.isEmpty()) {
                break;
            }

            if (rideOpt.get().isOriginConfirmed()) {
                isOriginConfirmed = true;
                break;
            }

            Thread.sleep(2000);
        }
        return isOriginConfirmed;
    }

    @Override
    public boolean isEnded(Long id) {
        throw new UnsupportedOperationException("No implemented");
    }

    @Override
    public Optional<Ride> findByCab(Long id_cab) {
        return repository.findByCab(id_cab);
    }

    @Override
    public Optional<InfoRideDTO> getInfoRide(CoordinatesRideDTO coordinatesRideDTO) throws IOException, NullPointerException {
        String response = openRouteServiceClient.getResponse(coordinatesRideDTO);
        JsonElement rootElement = gson.fromJson(response, JsonElement.class);
        if (rootElement.isJsonObject()) {
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
