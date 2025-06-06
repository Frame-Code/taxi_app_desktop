package DOMAIN.ENTITIES;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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
@Getter @Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60, nullable = false)
    private String names;

    @Column(length = 60, nullable = false)
    private String lastNames;
    private LocalDate bornDate;

    @Column(length = 80, nullable = false, unique = true)
    private String email;

    @Column(length = 10, nullable = false, unique = true)
    private String phone;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String passwordHash;
    private boolean isDeleted;
    private LocalDate createdAt;
    private String createdBy;
    @ManyToOne
    @JoinColumn(name = "idRole")
    private Role role;

    @PrePersist
    protected void prePersist() {
        this.createdAt = LocalDate.now();
        this.isDeleted = false;
    }
}
