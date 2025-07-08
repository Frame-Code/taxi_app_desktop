package service.impl.ride_service;

import domain.entities.Ride;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import service.interfaces.ride_module.OpenViewObserver;
import shared.enums.STATUS_ROAD;

/**
 * @author Daniel Mora Cantillo
 * */
@Setter
public class ListenerRideDestiny implements PostUpdateEventListener {
    private boolean isActive = false;
    private static OpenViewObserver observer;

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        if(!isActive || !(event.getEntity() instanceof Ride)) {
            return;
        }

        String[] properties = event.getPersister().getPropertyNames();
        Object[] newValues = event.getState();

        for (int i = 0; i < properties.length; i++) {
            if(!"status".equals(properties[i])) {
                return;
            }
            STATUS_ROAD statusRoad = (STATUS_ROAD) newValues[i];
            if(!statusRoad.equals(STATUS_ROAD.READY_TO_START)) {
                return;
            }

            try {
                observer.open((Long) newValues[0]);
            } catch (ClassCastException e) {
                throw new RuntimeException("Error: Can't cast the ID of the ride to Long");
            }
        }
        
    }
}
