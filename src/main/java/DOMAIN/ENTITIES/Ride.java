package DOMAIN.ENTITIES;

import SHARED.ENUMS.STATUS_ROAD;
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

    @Column(nullable = false)
    private LocalDateTime endDate;

    @ManyToOne
    @JoinColumn(name = "id_address_origin")
    private Address startAddress;

    @ManyToOne
    @JoinColumn(name = "id_address_destiny")
    private Address endAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private STATUS_ROAD status;

    @ManyToOne
    @JoinColumn(name = "id_payment")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "id_cab")
    private Cab cab;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Client client;
}