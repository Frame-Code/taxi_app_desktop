package domain.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUser", nullable = false)
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idLicense", nullable = false)
    private License license;

    @Column(nullable = false, length = 150)
    private String address;

    @Column(nullable = false)
    private LocalDate entryDate;

    private Integer experienceYears;
}
