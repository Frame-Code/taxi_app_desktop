import com.formdev.flatlaf.FlatLightLaf;
import domain.repository.impl.*;
import domain.repository.interfaces.*;
import org.hibernate.SessionFactory;
import service.external.client.opencage.IOpenCageClient;
import service.external.client.opencage.OpenCageClientImpl;
import service.external.client.openrouteservice.IOpenRouteServiceClient;
import service.external.client.openrouteservice.OpenRouteServiceClientImpl;
import service.impl.auth_module.*;
import service.impl.location_module.ProvinceServiceImpl;
import service.impl.matching_module.FindCabsServiceImpl;
import service.impl.matching_module.MatchServiceImpl;
import service.impl.payment_module.PaymentFactoryImpl;
import service.impl.ride_service.FareServiceImpl;
import service.impl.ride_service.IRideCalculationsServiceImpl;
import service.impl.ride_service.RideCabServiceImpl;
import service.impl.ride_service.RideClientServiceImpl;
import service.impl.role_module.RoleServiceImpl;
import service.interfaces.auth_module.*;
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
import ui.auth_ui.RegistroPasajero;
import ui.auth_ui.WelcomeTaxiShareUI;
import ui.request_cab_ui.IMapViewer;
import ui.request_cab_ui.OpenStreetMapView;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class App {
    private static final String HIBERNATE_CFG_XML = "hibernate-local.cfg.xml";

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());

            // Initialize Hibernate SessionFactory
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory(HIBERNATE_CFG_XML);

            // Initialize Repositories
            UserRepository userRepository = new UserRepositoryImpl(sessionFactory);
            ClientRepository clientRepository = new ClientRepositoryImpl(sessionFactory);
            RoleRepository roleRepository = new RoleRepositoryImpl(sessionFactory);
            CabRepository cabRepository = new CabRepositoryImpl(sessionFactory);
            FareRepository fareRepository = new FareRepositoryImpl(sessionFactory);
            RideRepository rideRepository = new RideRepositoryImpl(sessionFactory, cabRepository);
            ProvinceRepository provinceRepository = new ProvinceRepositoryImpl(sessionFactory);
            TaxiLiveAddressRepositoryImpl taxiLiveAddressRepository = new TaxiLiveAddressRepositoryImpl(sessionFactory);

            // Initialize External Clients
            IOpenCageClient openCageClient = OpenCageClientImpl.getInstance();
            IOpenRouteServiceClient openRouteServiceClient = new OpenRouteServiceClientImpl();

            // Initialize Services
            IEmailService emailService = new EmailServiceImpl();
            IFindCabsService findCabsService = new FindCabsServiceImpl(taxiLiveAddressRepository);
            IRideClientService rideClientService = new RideClientServiceImpl(rideRepository, cabRepository, openRouteServiceClient);
            IMatchService matchService = new MatchServiceImpl(findCabsService, rideClientService);
            IRideCabService rideCabService = new RideCabServiceImpl(rideRepository, cabRepository);
            IFareService fareService = new FareServiceImpl(fareRepository);
            IUserGeneralService userGeneralService = new UserGeneralServiceImpl(clientRepository, cabRepository);
            ISignUpClientService signUpClientService = new SignUpClientServiceImpl(userRepository);
            ILogInService logInService = new LogInServiceImpl(userRepository);
            IProvinceService provinceService = new ProvinceServiceImpl(provinceRepository);
            IRideCalculationsService rideCalculationsService = new IRideCalculationsServiceImpl(fareRepository);
            PaymentFactory paymentFactory = new PaymentFactoryImpl();
            IRoleService roleService = new RoleServiceImpl(roleRepository);

            // Initialize UI
            IMapViewer mapViewer = new OpenStreetMapView();

            // Initialize UI
            SwingUtilities.invokeLater(() -> {
                WelcomeTaxiShareUI welcomeTaxiShareUI = new WelcomeTaxiShareUI(
                        emailService,
                        logInService,
                        mapViewer,
                        openCageClient,
                        rideClientService,
                        rideCalculationsService,
                        fareService,
                        matchService,
                        provinceService,
                        paymentFactory,
                        userGeneralService,
                        rideCabService
                );

                RegistroPasajero registroPasajero = new RegistroPasajero(
                        signUpClientService,
                        clientRepository,
                        roleService
                );

                welcomeTaxiShareUI.setRegistroPasajero(registroPasajero);
                registroPasajero.setWelcomeTaxiShareUI(welcomeTaxiShareUI);

                welcomeTaxiShareUI.setVisible(true);
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al iniciar la aplicación: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException("Error initializing the application " + e);
        }
    }
}