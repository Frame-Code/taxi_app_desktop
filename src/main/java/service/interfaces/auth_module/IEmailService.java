package service.interfaces.auth_module;

public interface IEmailService {
    boolean enviarCorreoRecuperacion(String emailDestino);
    boolean validarCodigoVerificacion(String email, String codigo);
    boolean esEmailValido(String email);
    boolean actualizarContrasena(String email, String nuevaContrasena);
}
