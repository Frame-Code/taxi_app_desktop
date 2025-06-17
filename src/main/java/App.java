import SERVICE.IMPL.EmailServiceImpl;
import SERVICE.IMPL.LogInServiceImpl;
import SERVICE.INTERFACES.IEmailService;
import SERVICE.INTERFACES.ILogInService;
import UI.WelcomeTaxiShareUI;

import DOMAIN.REPOSITORY.IMPL.ClientRepositoryImpl;
import DOMAIN.REPOSITORY.INTERFACES.ClientRepository;
import org.hibernate.SessionFactory;
import SHARED.UTILS.HibernateUtil;

public class App {
    public static void main(String[] args) {
        IEmailService emailService = new EmailServiceImpl();

        // Obtener la SessionFactory (asegúrate que SHARED.UTILS.HibernateUtil provee esta instancia)
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Crear repositorio con la sessionFactory
        ClientRepository clientRepository = new ClientRepositoryImpl(sessionFactory);

        // Crear el servicio de login con el repositorio
        ILogInService loginService = new LogInServiceImpl(clientRepository);

        // Pasar los servicios a la UI
        WelcomeTaxiShareUI ventana = new WelcomeTaxiShareUI(emailService, loginService);
        ventana.setVisible(true);
    }
}
