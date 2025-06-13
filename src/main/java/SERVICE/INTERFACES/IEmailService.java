package SERVICE.INTERFACES;

public interface IEmailService {
    boolean enviarCorreoRecuperacion(String emailDestino);
    boolean validarCodigoVerificacion(String email, String codigo);
    boolean esEmailValido(String email);
    void limpiarCodigosExpirados();
    boolean actualizarContrasena(String email, String nuevaContrasena);
}
