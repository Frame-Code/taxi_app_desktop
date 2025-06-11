
import SHARED.UTILS.HibernateUtil;
import UI.WelcomeTaxiShareUI;



/**
 *
 * @author Daniel Mora Cantillo
 */
public class App {

    public static void main(String[] args) {
        HibernateUtil.getSessionFactory();
        WelcomeTaxiShareUI ventana = new WelcomeTaxiShareUI();
            ventana.setVisible(true);
    }
}
