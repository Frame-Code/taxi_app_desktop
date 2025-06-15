package service.interfaces.auth;

public interface ILogInService {
    boolean login(String email, String plainPassword);
}
