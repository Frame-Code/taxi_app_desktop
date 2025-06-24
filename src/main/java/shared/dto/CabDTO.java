package shared.dto;

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
