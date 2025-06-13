package SERVICE.EXTERNAL.CLIENT.OPENCAGE;

public interface IEmailService {
    boolean enviarCorreoRecuperacion(String emailDestino);
    boolean validarCodigoVerificacion(String email, String codigo);
    boolean esEmailValido(String email);
    void limpiarCodigosExpirados();
}
