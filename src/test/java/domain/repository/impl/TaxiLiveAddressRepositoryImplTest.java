package domain.repository.impl;

import domain.entities.Cab;
import domain.entities.Car;
import domain.entities.Driver;
import domain.entities.License;
import domain.entities.TaxiLiveAddress;
import domain.entities.UserEntity;
import domain.repository.interfaces.TaxiLiveAddressRepository;
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

    @BeforeAll
    static void setUp() {
        taxiLiveAddressRepository = new TaxiLiveAddressRepositoryImpl(HibernateUtil.getSessionFactory("hibernate-test.cfg.xml"));
    }

    @Test
    void findNearbyCabs() {
        loadData();
        List<Cab> nearbyCabs = taxiLiveAddressRepository.findNearbyCabs(
                GeolocationUtil.coordinatesToWKT(-2.166028, -79.830170),
                8000);
        assertFalse(nearbyCabs.isEmpty());
    }

    private void loadData() {
        for (int i = 0; i < 3; i++) {
            Cab cab1 = Cab.builder()
                    .driver(Driver.builder()
                            .address("one direction")
                            .entryDate(LocalDate.now())
                            .userEntity(UserEntity.builder()
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
                                    .licenseType('a')
                                    .expirationDate(LocalDate.now())
                                    .issuanceDate(LocalDate.now())
                                    .build())
                            .build())
                    .vehicle(Car.builder()
                            .chassisNumber("123" + i)
                            .year("2025")
                            .licensePlate("j1" + i)
                            .brand("b1" + i)
                            .model("Chevrolet")
                            .build())
                    .status(STATUS_TAXI.ENABLE)
                    .build();

            TaxiLiveAddress taxiLiveAddress = null;
            switch (i) {
                case 0 -> taxiLiveAddress = TaxiLiveAddress.builder()
                        .cab(cab1)
                        .reference("San gabriel")
                        .location(GeolocationUtil.createPoint(-2.155412, -79.823990))
                        .build();
                case 1 -> taxiLiveAddress = TaxiLiveAddress.builder()
                        .cab(cab1)
                        .reference("America del sur")
                        .location(GeolocationUtil.createPoint(-2.153026, -79.826456))
                        .build();
                case 2 -> taxiLiveAddress = TaxiLiveAddress.builder()
                        .cab(cab1)
                        .reference("Elsa bucaran")
                        .location(GeolocationUtil.createPoint(-2.158131, -79.822651))
                        .build();
            }
            taxiLiveAddressRepository.save(taxiLiveAddress);
        }
    }
}