package domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import shared.enums.STATUS_RIDE;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author Daniel Mora Cantillo
 * */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ride{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String startAddressReference;

    private String endAddressReference;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address_origin")
    private Address startAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address_destiny")
    private Address endAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private STATUS_RIDE status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_payment")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "id_cab")
    private Cab cab;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;

    public double getOriginLongitude() {
        return startAddress.getLocation().getX();
    }

    public double getOriginLatitude() {
        return startAddress.getLocation().getY();
    }

    public double getDestinyLongitude() {
        return endAddress.getLocation().getX();
    }

    public double getDestinyLatitude() {
        return endAddress.getLocation().getX();
    }

    public boolean isReadyToStart() {
        return status.equals(STATUS_RIDE.READY_TO_START);
    }

    public boolean isOriginConfirmed() {
        return status.equals(STATUS_RIDE.ORIGIN_CONFIRMED);
    }

}