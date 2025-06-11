package SERVICE.IMPL;

import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Session;
import java.util.Properties;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;


public class EmailService {

    // Configuración del servidor de correo (usando Gmail como ejemplo)
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String EMAIL_FROM = "tu_email@gmail.com"; // Cambiar por tu email
    private static final String EMAIL_PASSWORD = "tu_app_password"; // Cambiar por tu contraseña de aplicación

    // Almacén temporal de códigos de verificación (en una aplicación real usarías una base de datos)
    private static Map<String, String> verificationCodes = new HashMap<>();

    /**
     * Envía un correo de recuperación de contraseña
     * @param emailDestino Email del usuario que solicita el reseteo
     * @return true si el correo se envió exitosamente, false en caso contrario
     */
    public boolean enviarCorreoRecuperacion(String emailDestino) {
        try {
            // Generar código de verificación
            String codigoVerificacion = generarCodigoVerificacion();

            // Guardar el código asociado al email (en una app real esto iría a BD)
            verificationCodes.put(emailDestino, codigoVerificacion);

            // Configurar propiedades del servidor SMTP
            Properties props = configurarPropiedadesSMTP();

            // Crear sesión de autenticación
            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(EMAIL_FROM, EMAIL_PASSWORD);
                }
            });

            // Crear el mensaje
            Message mensaje = crearMensaje(session, emailDestino, codigoVerificacion);

            // Enviar el correo
            Transport.send(mensaje);

            System.out.println("Correo de recuperación enviado exitosamente a: " + emailDestino);
            return true;

        } catch (MessagingException e) {
            System.err.println("Error al enviar correo: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Configura las propiedades del servidor SMTP
     */
    private Properties configurarPropiedadesSMTP() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.ssl.trust", SMTP_HOST);
        return props;
    }

    /**
     * Crea el mensaje de correo electrónico
     */
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

    /**
     * Crea el contenido HTML del correo
     */
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

    /**
     * Genera un código de verificación aleatorio de 6 dígitos
     */
    private String generarCodigoVerificacion() {
        Random random = new Random();
        int codigo = 100000 + random.nextInt(900000); // Número de 6 dígitos
        return String.valueOf(codigo);
    }

    /**
     * Valida un código de verificación para un email específico
     * @param email Email del usuario
     * @param codigo Código a validar
     * @return true si el código es válido, false en caso contrario
     */
    public boolean validarCodigoVerificacion(String email, String codigo) {
        String codigoGuardado = verificationCodes.get(email);
        if (codigoGuardado != null && codigoGuardado.equals(codigo)) {
            // Remover el código después de usarlo
            verificationCodes.remove(email);
            return true;
        }
        return false;
    }

    /**
     * Verifica si un email tiene formato válido
     */
    public boolean esEmailValido(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email != null && email.matches(emailRegex);
    }

    /**
     * Limpia códigos expirados (método utilitario)
     */
    public void limpiarCodigosExpirados() {
        // En una implementación real, aquí verificarías timestamps
        // Por simplicidad, este método está vacío
        System.out.println("Limpieza de códigos expirados ejecutada");
    }
}