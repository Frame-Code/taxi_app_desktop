package DOMAIN.ENTITIES;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class City {
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idProvince")
    private Province province;
}
