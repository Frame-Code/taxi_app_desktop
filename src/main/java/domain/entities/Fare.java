package domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author Daniel Mora Cantillo
 * */
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Fare {
    @Id
    private final Integer id = 1;

    @Column(nullable = false)
    private Double pricePerMinute;

    @Column(nullable = false)
    private Double pricePerKm;

    @Column(nullable = false)
    private Double baseFare;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private LocalDate updatedAt;

    @PrePersist
    protected void prePersist() {
        this.createdAt = LocalDate.now();
    }

    @PreUpdate
    protected void preUpdate() {
        this.updatedAt = LocalDate.now();
    }


}
