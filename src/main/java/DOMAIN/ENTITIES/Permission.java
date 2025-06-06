package DOMAIN.ENTITIES;

import SHARED.ENUMS.PERMISSION_NAME;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Permission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PERMISSION_NAME permissionName;

    private LocalDate createdAt;
    private boolean isDeleted;

    @Column(columnDefinition = "TEXT")
    private String description;

    @PrePersist
    protected void prePersist() {
        this.createdAt = LocalDate.now();
        this.isDeleted = false;
    }
}
