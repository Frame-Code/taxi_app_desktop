
import SHARED.UTILS.HibernateUtil;
import UI.WelcomeTaxiShareUI;



/**
 *
 * @author Daniel Mora Cantillo
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        HibernateUtil.getSessionFactory();
        WelcomeTaxiShareUI ventana = new WelcomeTaxiShareUI();
            ventana.setVisible(true);
    }
}
