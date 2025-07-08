package shared.utils;

import lombok.RequiredArgsConstructor;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.spi.BootstrapContext;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import service.impl.ride_service.ListenerRideDestiny;
import service.interfaces.ride_module.OpenViewObserver;

@RequiredArgsConstructor
public class IntegratorHibernate implements Integrator {
    private final OpenViewObserver openViewObserver;

    @Override
    public void integrate(Metadata metadata,
            BootstrapContext bootstrapContext,
            SessionFactoryImplementor sessionFactory) {
        EventListenerRegistry registry = sessionFactory
                .getServiceRegistry()
                .getService(EventListenerRegistry.class);

        registry.getEventListenerGroup(EventType.POST_UPDATE)
                .appendListener(new ListenerRideDestiny(openViewObserver));

    }
}
