package service.interfaces.auth_module;

/**
 *
 * @author Daniel Mora Cantillo
 */
public interface ILogInService {
    boolean login(String email, String plainPassword);
}
