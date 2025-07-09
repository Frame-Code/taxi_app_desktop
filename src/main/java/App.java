import com.formdev.flatlaf.FlatLightLaf;
import domain.repository.impl.CabRepositoryImpl;
import domain.repository.impl.ClientRepositoryImpl;
import domain.repository.impl.FareRepositoryImpl;
import domain.repository.impl.ProvinceRepositoryImpl;
import domain.repository.impl.RideRepositoryImpl;
import domain.repository.impl.TaxiLiveAddressRepositoryImpl;
import service.external.client.opencage.OpenCageClientImpl;
import service.external.client.openrouteservice.OpenRouteServiceClientImpl;
import service.impl.location_module.ProvinceServiceImpl;
import service.impl.matching_module.FindCabsServiceImpl;
import service.impl.matching_module.MatchServiceImpl;
import service.impl.payment_module.PaymentFactoryImpl;
import service.impl.ride_service.FareServiceImpl;
import service.impl.ride_service.IRideCalculationsServiceImpl;
import service.impl.ride_service.RideServiceImpl;
import shared.utils.HibernateUtil;
import ui.CabRequestView;
import ui.OpenStreetMapView;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Daniel Mora Cantillo
 */
public class App {
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatLightLaf());
        java.awt.EventQueue.invokeLater(() -> {
            new CabRequestView(
                    new OpenStreetMapView(),
                    OpenCageClientImpl.getInstance(),
                    new RideServiceImpl(
                            new RideRepositoryImpl(
                                    HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"),
                                    new CabRepositoryImpl(HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"))),
                            new CabRepositoryImpl(HibernateUtil.getSessionFactory("hibernate-local.cfg.xml")),
                            new OpenRouteServiceClientImpl()),
                    new IRideCalculationsServiceImpl(
                            new FareRepositoryImpl(
                                    HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"))),
                    new FareServiceImpl(
                            new FareRepositoryImpl(
                                    HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"))),
                    new MatchServiceImpl(
                            new FindCabsServiceImpl(
                                    new TaxiLiveAddressRepositoryImpl(
                                            HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"))),
                            new RideServiceImpl(
                                    new RideRepositoryImpl(
                                            HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"),
                                            new CabRepositoryImpl(HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"))),
                                    new CabRepositoryImpl(
                                            HibernateUtil.getSessionFactory("hibernate-local.cfg.xml")),
                                    new OpenRouteServiceClientImpl())),
                    new ProvinceServiceImpl(
                            new ProvinceRepositoryImpl(
                                    HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"))),
                    new PaymentFactoryImpl(),
                    new ClientRepositoryImpl(
                            HibernateUtil.getSessionFactory("hibernate-local.cfg.xml")).findByEmail("mail@email.com").get()
            );
        });
    }
}
