package shared.dto;

/**
 *
 * @author Daniel Mora Cantillo
 */
public record CabDTO(
        String fullNames,
        String email,
        String phone,
        String brand,
        String model,
        String color,
        String licensePLate
) {
}
