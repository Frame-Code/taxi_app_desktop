package SERVICE.INTERFACES;

import java.time.LocalDate;

public interface ISignUpClientService {
    boolean signUp(String name, String lastNames, LocalDate bornDate, String email, String phone, String password);
}
