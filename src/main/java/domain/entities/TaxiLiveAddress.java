package domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

/**
 * @author Daniel Mora Cantillo
 * */
@SuperBuilder
@Getter @Setter
@Entity(name = "taxi_live_address")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@PrimaryKeyJoinColumn(name = "id")
public class TaxiLiveAddress extends Address {
    @OneToOne
    @JoinColumn(name = "idCab", unique = true)
    private Cab cab;
}