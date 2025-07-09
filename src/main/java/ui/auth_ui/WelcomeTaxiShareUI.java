package ui.auth_ui;


import domain.entities.Client;
import service.external.client.opencage.IOpenCageClient;
import service.interfaces.auth_module.IEmailService;
import service.interfaces.auth_module.ILogInService;
import service.interfaces.location_module.IProvinceService;
import service.interfaces.matching_module.IMatchService;
import service.interfaces.payment_module.PaymentFactory;
import service.interfaces.ride_module.IFareService;
import service.interfaces.ride_module.IRideCalculationsService;
import service.interfaces.ride_module.IRideClientService;
import ui.request_cab_ui.CabRequestView;
import ui.request_cab_ui.IMapViewer;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author RonaldPC
 */
public class WelcomeTaxiShareUI extends javax.swing.JFrame {
    private final IEmailService emailService;
    private final ILogInService loginService;

    public WelcomeTaxiShareUI(
            IEmailService emailService,
            ILogInService loginService,
            IMapViewer mapViewer,
            IOpenCageClient openCageClient,
            IRideClientService rideService,
            IRideCalculationsService rideCalculationsService,
            IFareService fareService,
            IMatchService matchService,
            IProvinceService provinceService,
            PaymentFactory paymentFactory,
            Client client) {
        this.emailService = emailService;
        this.loginService = loginService;
        initComponents();
        setSize(800, 500);
        setLocationRelativeTo(null);
        initControllers(mapViewer,
                openCageClient,
                rideService,
                rideCalculationsService,
                fareService,
                matchService,
                provinceService,
                paymentFactory,
                client);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();



        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setText("Welcome to TaxiShare");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Usuario");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Contraseña");

        jButton1.setText("Iniciar sesion");

        jButton2.setText("¿Olvidaste tu contraseña?");

        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel2)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(149, 149, 149)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );


        JPanel wrapperPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));


        wrapperPanel.add(jPanel1);

        getContentPane().removeAll(); // elimina contenido anterior
        getContentPane().setLayout(new BorderLayout()); // o cualquier layout simple
        getContentPane().add(wrapperPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Crear y mostrar la ventana de reset de contraseña
        ResetPasswordUI resetWindow = new ResetPasswordUI(emailService);
        resetWindow.setVisible(true);

        // Opcional: cerrar la ventana actual de login
        this.dispose();
    }

    private void initControllers(IMapViewer mapViewer,
                                 IOpenCageClient openCageClient,
                                 IRideClientService rideService,
                                 IRideCalculationsService rideCalculationsService,
                                 IFareService fareService,
                                 IMatchService matchService,
                                 IProvinceService provinceService,
                                 PaymentFactory paymentFactory,
                                 Client client) {
        jButton1.addActionListener(e -> {
            String email = jTextField1.getText();
            String password = new String(jPasswordField1.getPassword());

            boolean isValid = loginService.login(email, password);

            if (isValid) {
                CabRequestView cabRequestView = new CabRequestView(mapViewer,
                        openCageClient,
                        rideService,
                        rideCalculationsService,
                        fareService,
                        matchService,
                        provinceService,
                        paymentFactory,
                        client);
                this.dispose();
            } else {
                // Login fallido, mostrar mensaje
                javax.swing.JOptionPane.showMessageDialog(this,
                        "Usuario o contraseña incorrectos",
                        "Error de inicio de sesión",
                        javax.swing.JOptionPane.ERROR_MESSAGE);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
