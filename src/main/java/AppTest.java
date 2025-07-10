import com.formdev.flatlaf.FlatLightLaf;
import domain.repository.impl.CabRepositoryImpl;
import domain.repository.impl.ClientRepositoryImpl;
import domain.repository.impl.DriverRepositoryImpl;
import domain.repository.impl.FareRepositoryImpl;
import domain.repository.impl.ProvinceRepositoryImpl;
import domain.repository.impl.RideRepositoryImpl;
import domain.repository.impl.RoleRepositoryImpl;
import domain.repository.impl.TaxiLiveAddressRepositoryImpl;
import domain.repository.impl.UserRepositoryImpl;
import domain.repository.interfaces.CabRepository;
import domain.repository.interfaces.ClientRepository;
import domain.repository.interfaces.DriverRepository;
import domain.repository.interfaces.FareRepository;
import domain.repository.interfaces.ProvinceRepository;
import domain.repository.interfaces.RideRepository;
import domain.repository.interfaces.RoleRepository;
import domain.repository.interfaces.UserRepository;
import org.hibernate.SessionFactory;
import service.external.client.opencage.IOpenCageClient;
import service.external.client.opencage.OpenCageClientImpl;
import service.external.client.openrouteservice.IOpenRouteServiceClient;
import service.external.client.openrouteservice.OpenRouteServiceClientImpl;
import service.impl.auth_module.EmailServiceImpl;
import service.impl.auth_module.LogInServiceImpl;
import service.impl.auth_module.SignUpClientServiceImpl;
import service.impl.auth_module.SignUpDriverServiceImpl;
import service.impl.auth_module.UserGeneralServiceImpl;
import service.impl.location_module.ProvinceServiceImpl;
import service.impl.matching_module.FindCabsServiceImpl;
import service.impl.matching_module.MatchServiceImpl;
import service.impl.payment_module.PaymentFactoryImpl;
import service.impl.ride_service.FareServiceImpl;
import service.impl.ride_service.IRideCalculationsServiceImpl;
import service.impl.ride_service.RideCabServiceImpl;
import service.impl.ride_service.RideClientServiceImpl;
import service.impl.role_module.RoleServiceImpl;
import service.interfaces.auth_module.IEmailService;
import service.interfaces.auth_module.ILogInService;
import service.interfaces.auth_module.ISignUpClientService;
import service.interfaces.auth_module.ISignUpDriverService;
import service.interfaces.auth_module.IUserGeneralService;
import service.interfaces.location_module.IProvinceService;
import service.interfaces.matching_module.IFindCabsService;
import service.interfaces.matching_module.IMatchService;
import service.interfaces.payment_module.PaymentFactory;
import service.interfaces.ride_module.IFareService;
import service.interfaces.ride_module.IRideCabService;
import service.interfaces.ride_module.IRideCalculationsService;
import service.interfaces.ride_module.IRideClientService;
import service.interfaces.role_module.IRoleService;
import shared.utils.HibernateUtil;
import ui.auth_ui.RegistroConductorUI;
import ui.auth_ui.RegistroPasajero;
import ui.auth_ui.WelcomeTaxiShareUI;
import ui.request_cab_ui.IMapViewer;
import ui.request_cab_ui.OpenStreetMapView;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class AppTest {
    private static final String HIBERNATE_CFG_XML = "hibernate-local.cfg.xml";

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // Initialize Hibernate SessionFactory
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory(HIBERNATE_CFG_XML);

            // Initialize Repositories
            UserRepository userRepository = new UserRepositoryImpl(sessionFactory);
            DriverRepository driverRepository = new DriverRepositoryImpl(sessionFactory);
            RoleRepository roleRepository = new RoleRepositoryImpl(sessionFactory);
            CabRepository cabRepository = new CabRepositoryImpl(sessionFactory);

            // Initialize Services
            ISignUpDriverService signUpDriverService = new SignUpDriverServiceImpl(userRepository, roleRepository, cabRepository);
            IRoleService roleService = new RoleServiceImpl(roleRepository);

            // Initialize UI
            java.awt.EventQueue.invokeLater(() ->
                    new RegistroConductorUI(
                            signUpDriverService,
                            driverRepository,
                            roleService)
                    .setVisible(true));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar la aplicación: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Error initializing the application " + e);
        }
    }
}