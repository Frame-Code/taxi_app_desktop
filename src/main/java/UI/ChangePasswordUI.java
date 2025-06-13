package UI;

import SERVICE.INTERFACES.IEmailService;
import javax.swing.JOptionPane;

public class ChangePasswordUI extends javax.swing.JFrame {
    private final IEmailService emailService;
    private final String email; // Guarda el email para usar en la actualización

    public ChangePasswordUI(IEmailService emailService, String email) {
        this.emailService = emailService;
        this.email = email;
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cambiar contraseña");

        javax.swing.JLabel labelCodigo = new javax.swing.JLabel("Código recibido:");
        javax.swing.JTextField txtCodigo = new javax.swing.JTextField(20);

        javax.swing.JLabel labelPass1 = new javax.swing.JLabel("Nueva contraseña:");
        javax.swing.JPasswordField txtPass1 = new javax.swing.JPasswordField(20);

        javax.swing.JLabel labelPass2 = new javax.swing.JLabel("Confirmar contraseña:");
        javax.swing.JPasswordField txtPass2 = new javax.swing.JPasswordField(20);

        javax.swing.JButton btnCambiar = new javax.swing.JButton("Cambiar contraseña");

        btnCambiar.addActionListener(e -> {
            String codigo = txtCodigo.getText().trim();
            String pass1 = new String(txtPass1.getPassword());
            String pass2 = new String(txtPass2.getPassword());

            if (codigo.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor llena todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!pass1.equals(pass2)) {
                JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!emailService.validarCodigoVerificacion(email, codigo)) {
                JOptionPane.showMessageDialog(this, "Código de verificación incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            boolean actualizado = emailService.actualizarContrasena(email, pass1);
            if (actualizado) {
                JOptionPane.showMessageDialog(this, "Contraseña actualizada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar la contraseña.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelCodigo)
                                        .addComponent(txtCodigo)
                                        .addComponent(labelPass1)
                                        .addComponent(txtPass1)
                                        .addComponent(labelPass2)
                                        .addComponent(txtPass2)
                                        .addComponent(btnCambiar, javax.swing.GroupLayout.Alignment.CENTER))
                                .addGap(20))
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(20)
                        .addComponent(labelCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15)
                        .addComponent(labelPass1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15)
                        .addComponent(labelPass2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPass2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20)
                        .addComponent(btnCambiar)
                        .addGap(20)
        );

        pack();
        setLocationRelativeTo(null);
    }
}
