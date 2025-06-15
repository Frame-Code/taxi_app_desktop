package domain.repository.IMPL;

import domain.entities.Cab;
import domain.entities.Car;
import domain.entities.Driver;
import domain.entities.License;
import domain.entities.TaxiLiveAddress;
import domain.entities.User;
import domain.repository.INTERFACES.CabRepository;
import domain.repository.INTERFACES.CarRepository;
import domain.repository.INTERFACES.DriverRepository;
import domain.repository.INTERFACES.TaxiLiveAddressRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import shared.enums.STATUS_TAXI;
import shared.utils.GeolocationUtil;
import shared.utils.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaxiLiveAddressRepositoryImplTest {
    private static TaxiLiveAddressRepository taxiLiveAddressRepository;
    private static DriverRepository driverRepository;
    private static CarRepository carRepository;
    private static CabRepository cabRepository;

    @BeforeAll
    static void setUp() {
        taxiLiveAddressRepository = new TaxiLiveAddressRepositoryImpl(HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"));
        driverRepository = new DriverRepositoryImpl(HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"));
        carRepository = new CarRepositoryImpl(HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"));
        cabRepository = new CabRepositoryImpl(HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"));
    }

    @Test
    void findNearbyCabs() {
        loadData();
        List<Cab> nearbyCabs = taxiLiveAddressRepository.findNearbyCabs(
                GeolocationUtil.coordinatesToWKT(-2.166028, -79.830170),
                8000);
        System.out.println(nearbyCabs.isEmpty());
        nearbyCabs.forEach(System.out::println);
    }

    private void loadData() {
        for (int i = 0; i < 3; i++) {
            Driver driver1 = Driver.builder()
                    .address("one direction")
                    .entryDate(LocalDate.now())
                    .user(User.builder()
                            .names("Daniel")
                            .lastNames("Mora")
                            .phone("0909" + i)
                            .email("mail" + i + "@email.com")
                            .passwordHash("pass")
                            .createdBy("Admin")
                            .bornDate(LocalDate.now())
                            .build())
                    .license(License.builder()
                            .driverLicense("jkl1" + i)
                            .licenseType(1)
                            .expirationDate(LocalDate.now())
                            .issuanceDate(LocalDate.now())
                            .build())
                    .build();
            Car car1 = Car.builder()
                    .chassisNumber("123" + i)
                    .year("2025")
                    .licensePlate("j1" + i)
                    .color("black")
                    .brand("b1" + i)
                    .model("Chevrolet")
                    .build();
            driverRepository.save(driver1);
            carRepository.save(car1);
            Cab cab1 = Cab.builder()
                    .vehicle(car1)
                    .status(STATUS_TAXI.ENABLE)
                    .driver(driver1)
                    .build();
            cabRepository.save(cab1);
            TaxiLiveAddress taxiLiveAddress = null;
            switch (i) {
                case 0 -> taxiLiveAddress = TaxiLiveAddress.builder()
                        .cab(cab1)
                        .location(GeolocationUtil.createPoint(-2.155412, -79.823990))
                        .build();
                case 1 -> taxiLiveAddress = TaxiLiveAddress.builder()
                        .cab(cab1)
                        .location(GeolocationUtil.createPoint(-2.153026, -79.826456))
                        .build();
                case 2 -> taxiLiveAddress = TaxiLiveAddress.builder()
                        .cab(cab1)
                        .location(GeolocationUtil.createPoint(-2.158131, -79.822651))
                        .build();
            }
            taxiLiveAddressRepository.save(taxiLiveAddress);
        }

    }

    @Test
    void save() {
    }
}