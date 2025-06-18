package service.interfaces.auth_module;

public interface ILogInService {
    boolean login(String email, String plainPassword);
}
