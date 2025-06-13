package SERVICE.IMPL;

import DOMAIN.ENTITIES.City;
import DOMAIN.ENTITIES.Province;
import DOMAIN.REPOSITORY.INTERFACES.ProvinceRepository;
import SERVICE.INTERFACES.IProvinceService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

/**
 * @author Daniel Mora Cantillo
 * */
@RequiredArgsConstructor
public class ProvinceServiceImpl implements IProvinceService {
    private final ProvinceRepository repository;

    /**
     * Obtiene todas las provincias.
     * @return Lista de entidades Province.
     */
    @Override
    public List<Province> getProvinces() {
        return repository.findAll();
    }

    /**
     * Guarda una provincia.
     * @param province Entidad Province a guardar.
     * @return La entidad Province guardada
     */
    @Override
    public Province save(Province province) {
        return repository.save(province);
    }


    /**
     * Crea y guarda una nueva provincia por su nombre.
     * @param provinceName Nombre de la nueva provincia.
     * @return La entidad Province creada y guardada.
     */
    @Override
    public Province save(String provinceName) {
        return repository.save(Province.builder()
                .name(provinceName)
                .build());
    }

    /**
     * Busca una provincia por su nombre.
     * @param name Nombre de la provincia a buscar.
     * @return Optional con la Provincia si existe.
     */
    @Override
    public Optional<Province> findByName(String name) {
        return repository.findByName(name);
    }

    /**
     * Busca una provincia por nombre, añade una nueva ciudad y actualiza la provincia.
     * @param provinceName Nombre de la provincia.
     * @param cityName Nombre de la nueva ciudad.
     * @return Optional con la Provincia actualizada si se encontró la provincia original.
     */
    @Override
    public Optional<Province> addCity(String provinceName, String cityName) {
        return repository.findByName(provinceName)
                .map(province -> {
                    province.addCity(City.builder()
                            .name(cityName)
                            .build());
                    return repository.update(province);
                }).or(Optional::empty);
    }
}
