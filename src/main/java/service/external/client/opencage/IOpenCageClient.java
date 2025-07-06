package service.external.client.opencage;

import java.util.Optional;

/**
 *
 * @author Daniel Mora Cantillo
 */
public interface IOpenCageClient {
    String format(double latitude, double longitude);
    public Optional<String> getState(double latitude, double longitude);
}
