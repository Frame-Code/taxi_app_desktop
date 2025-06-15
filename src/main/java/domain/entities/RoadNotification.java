package domain.entities;

import shared.enums.REQUEST_STATUS_NOTIFICATION;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author Daniel Mora Cantillo
 * */
@SuperBuilder
@Getter
@Setter
@Entity(name = "road_notification")
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class RoadNotification extends Notification {
    @ManyToOne
    @JoinColumn(name = "idTaxi")
    private Cab cab;

    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private REQUEST_STATUS_NOTIFICATION status;

    @PrePersist
    protected void prePersist() {
        status = REQUEST_STATUS_NOTIFICATION.PENDING;
    }
}
