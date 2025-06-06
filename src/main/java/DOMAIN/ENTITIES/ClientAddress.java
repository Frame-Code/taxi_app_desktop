package DOMAIN.ENTITIES;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter @Setter
@Entity(name = "client_address")
@PrimaryKeyJoinColumn(name = "id")
public class ClientAddress extends Address{
    @ManyToOne
    @JoinColumn(name = "idClient")
    private Client client;
}