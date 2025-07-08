import com.formdev.flatlaf.FlatDarkLaf;
import domain.repository.impl.ClientRepositoryImpl;
import domain.repository.interfaces.ClientRepository;
import lombok.extern.apachecommons.CommonsLog;
import org.hibernate.SessionFactory;
import service.impl.auth_module.EmailServiceImpl;
import service.impl.auth_module.LogInServiceImpl;
import service.interfaces.auth_module.IEmailService;
import service.interfaces.auth_module.ILogInService;
import shared.utils.HibernateUtil;
import ui.auth_ui.WelcomeTaxiShareUI;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import ui.auth_ui.RoutenanfragenUI;

//@CommonsLog
public class App {
    public static void main(String[] args) {
        /*try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            log.warn("Can't load theme look and feel, error: " + e);
        }
        IEmailService emailService = new EmailServiceImpl();

        // Obtener la SessionFactory (asegúrate que SHARED.UTILS.HibernateUtil provee esta instancia)
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Crear repositorio con la sessionFactory
        ClientRepository clientRepository = new ClientRepositoryImpl(sessionFactory);

        // Crear el servicio de login con el repositorio
        ILogInService loginService = new LogInServiceImpl(clientRepository);

        // Pasar los servicios a la UI
        WelcomeTaxiShareUI ventana = new WelcomeTaxiShareUI(emailService, loginService);
        ventana.setVisible(true);*/
        javax.swing.SwingUtilities.invokeLater(() -> {
            RoutenanfragenUI fm = new RoutenanfragenUI();
            fm.setVisible(true);
        });
    }
}
