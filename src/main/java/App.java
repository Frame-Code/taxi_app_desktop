import SHARED.UTILS.HibernateUtil;

/**
 *
 * @author Daniel Mora Cantillo
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Hello World!");
        HibernateUtil.getSessionFactory();
    }
}
