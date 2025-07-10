import domain.repository.impl.CabRepositoryImpl;
import domain.repository.impl.DriverRepositoryImpl;
import domain.repository.impl.RoleRepositoryImpl;
import domain.repository.impl.UserRepositoryImpl;
import domain.repository.interfaces.CabRepository;
import domain.repository.interfaces.DriverRepository;
import domain.repository.interfaces.RoleRepository;
import domain.repository.interfaces.UserRepository;
import org.hibernate.SessionFactory;
import service.impl.auth_module.SignUpDriverServiceImpl;
import service.impl.role_module.RoleServiceImpl;
import service.interfaces.auth_module.ISignUpDriverService;
import service.interfaces.role_module.IRoleService;
import shared.utils.HibernateUtil;
import ui.auth_ui.RegistroConductorUI;

import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class AppDriverRegister {
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