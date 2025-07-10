import com.formdev.flatlaf.FlatLightLaf;
import domain.repository.impl.CabRepositoryImpl;
import domain.repository.impl.ClientRepositoryImpl;
import domain.repository.impl.FareRepositoryImpl;
import domain.repository.impl.ProvinceRepositoryImpl;
import domain.repository.impl.RideRepositoryImpl;
import domain.repository.impl.TaxiLiveAddressRepositoryImpl;
import domain.repository.impl.UserRepositoryImpl;
import service.external.client.opencage.OpenCageClientImpl;
import service.external.client.openrouteservice.OpenRouteServiceClientImpl;
import service.impl.auth_module.EmailServiceImpl;
import service.impl.auth_module.LogInServiceImpl;
import service.impl.auth_module.UserGeneralServiceImpl;
import service.impl.location_module.ProvinceServiceImpl;
import service.impl.matching_module.FindCabsServiceImpl;
import service.impl.matching_module.MatchServiceImpl;
import service.impl.payment_module.PaymentFactoryImpl;
import service.impl.ride_service.FareServiceImpl;
import service.impl.ride_service.IRideCalculationsServiceImpl;
import service.impl.ride_service.RideCabServiceImpl;
import service.impl.ride_service.RideClientServiceImpl;
import shared.utils.HibernateUtil;
import ui.auth_ui.WelcomeTaxiShareUI;
import ui.request_cab_ui.OpenStreetMapView;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Daniel Mora Cantillo
 */
public class App {
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatLightLaf());
        java.awt.EventQueue.invokeLater(() -> {
            new WelcomeTaxiShareUI(
                    new EmailServiceImpl(),
                    new LogInServiceImpl(
                            new UserRepositoryImpl(
                                    HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"))),
                    new OpenStreetMapView(),
                    OpenCageClientImpl.getInstance(),
                    new RideClientServiceImpl(
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
                            new RideClientServiceImpl(
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
                    new UserGeneralServiceImpl(
                            new ClientRepositoryImpl(
                                    HibernateUtil.getSessionFactory("hibernate-local.cfg.xml")),
                            new CabRepositoryImpl(
                                    HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"))
                    ),
                    new RideCabServiceImpl(
                            new RideRepositoryImpl(
                                    HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"),
                                    new CabRepositoryImpl(
                                            HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"))
                            ),
                            new CabRepositoryImpl(
                                    HibernateUtil.getSessionFactory("hibernate-local.cfg.xml")))

            );
        });
    }
}
