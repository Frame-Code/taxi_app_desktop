package service.interfaces.auth_module;

/**
 *
 * @author Daniel Mora Cantillo
 */
public interface ILogInService {
    boolean login(String email, String plainPassword);
    boolean isClient(String email);
    boolean isDriver(String email);
    boolean isAdministrator(String email);
}
