package shared.dto;

import java.util.concurrent.TimeUnit;

public record InfoRideDTO(
        double approxDistance,
        double approxTime) {

    public double getApproxTimeAsMinutes() {
        return (TimeUnit.SECONDS.toMinutes((long) approxTime)
                - (TimeUnit.SECONDS.toHours((long) approxTime) * 60));
    }
}
