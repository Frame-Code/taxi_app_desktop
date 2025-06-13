
import SERVICE.IMPL.EmailServiceImpl;
import SHARED.UTILS.HibernateUtil;
import UI.WelcomeTaxiShareUI;



/**
 *
 * @author Daniel Mora Cantillo
 */
public class App {

    public static void main(String[] args) {
        WelcomeTaxiShareUI ventana = new WelcomeTaxiShareUI(new EmailServiceImpl());
            ventana.setVisible(true);
    }
}
