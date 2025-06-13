package SERVICE.IMPL;
import SERVICE.EXTERNAL.CLIENT.OPENCAGE.IEmailService;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import java.util.Properties;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;


public class EmailServiceImpl implements IEmailService {

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String EMAIL_FROM = "tu_email@gmail.com"; // Cambia por tu email
    private static final String EMAIL_PASSWORD = "tu_app_password"; // Cambia por tu contraseña de aplicación

    private static Map<String, String> verificationCodes = new HashMap<>();

    @Override
    public boolean enviarCorreoRecuperacion(String emailDestino) {
        try {
            String codigoVerificacion = generarCodigoVerificacion();
            verificationCodes.put(emailDestino, codigoVerificacion);

            Properties props = configurarPropiedadesSMTP();

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(EMAIL_FROM, EMAIL_PASSWORD);
                }
            });

            Message mensaje = crearMensaje(session, emailDestino, codigoVerificacion);
            Transport.send(mensaje);

            System.out.println("Correo de recuperación enviado exitosamente a: " + emailDestino);
            return true;

        } catch (MessagingException e) {
            System.err.println("Error al enviar correo: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean validarCodigoVerificacion(String email, String codigo) {
        String codigoGuardado = verificationCodes.get(email);
        if (codigoGuardado != null && codigoGuardado.equals(codigo)) {
            verificationCodes.remove(email);
            return true;
        }
        return false;
    }

    @Override
    public boolean esEmailValido(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && email.matches(emailRegex);
    }

    @Override
    public void limpiarCodigosExpirados() {
        // Método de ejemplo
        System.out.println("Limpieza de códigos expirados ejecutada");
    }

    // --- Métodos privados auxiliares ---

    private Properties configurarPropiedadesSMTP() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.ssl.trust", SMTP_HOST);
        return props;
    }

    private Message crearMensaje(Session session, String emailDestino, String codigoVerificacion)
            throws MessagingException {
        Message mensaje = new MimeMessage(session);
        mensaje.setFrom(new InternetAddress(EMAIL_FROM));
        mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDestino));
        mensaje.setSubject("Recuperación de Contraseña");

        String contenidoHTML = crearContenidoHTML(codigoVerificacion);
        mensaje.setContent(contenidoHTML, "text/html; charset=utf-8");

        return mensaje;
    }

    private String crearContenidoHTML(String codigoVerificacion) {
        return "<html><body>" +
                "<h2>Recuperación de Contraseña</h2>" +
                "<p>Has solicitado restablecer tu contraseña.</p>" +
                "<p>Tu código de verificación es: <strong>" + codigoVerificacion + "</strong></p>" +
                "<p>Este código expira en 15 minutos.</p>" +
                "<p>Si no solicitaste este cambio, ignora este correo.</p>" +
                "<br>" +
                "<p>Saludos,<br>El equipo de soporte</p>" +
                "</body></html>";
    }

    private String generarCodigoVerificacion() {
        Random random = new Random();
        int codigo = 100000 + random.nextInt(900000);
        return String.valueOf(codigo);
    }
}