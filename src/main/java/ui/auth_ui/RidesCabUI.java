
package ui.auth_ui;

import com.formdev.flatlaf.FlatLightLaf;
import domain.entities.Cab;
import domain.entities.Ride;
import domain.repository.impl.CabRepositoryImpl;
import domain.repository.impl.RideRepositoryImpl;
import lombok.extern.apachecommons.CommonsLog;
import service.impl.ride_service.RideCabServiceImpl;
import service.interfaces.ride_module.IRideCabService;
import shared.utils.HibernateUtil;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author RonaldPC
 */
@CommonsLog
public class RidesCabUI extends javax.swing.JFrame {
    private final ExecutorService executorService = Executors.newScheduledThreadPool(3);
    private final IRideCabService rideCabService;
    private final Long idCab;
    private Long idRide;

    public RidesCabUI(IRideCabService rideCabService, Long idCab, String fullNames) {
        this.rideCabService = rideCabService;
        this.idCab = idCab;
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        jLabel5.setText(fullNames);
        initControllers();
        verifyNewRide();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("SYSTEM");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel2.setText("SOLICITUDES DE");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel3.setText("RUTAS");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel4.setText("SOLICITUDES DE RUTAS");

        jLabel5.setText("USERNAME");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ruta actual", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(0, 0, 0));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("No hay rutas actuales");
        jTextArea1.setEnabled(false);
        jScrollPane1.setViewportView(jTextArea1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1)
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
                                .addContainerGap())
        );

        jButton1.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton1.setText("Confirmar llegada al destino");
        jButton1.setEnabled(false);

        jButton3.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        jButton3.setText("Confirmar llegada al origen");
        jButton3.setEnabled(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Historial de rutas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 18))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 356, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 632, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(92, 92, 92)
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 494, Short.MAX_VALUE)
                                                .addComponent(jLabel5)
                                                .addGap(69, 69, 69))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel2)
                                                                        .addComponent(jLabel3))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(14, 14, 14)))
                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel5))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(52, 52, 52)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel3)
                                                .addGap(18, 18, 18)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap())
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void verifyNewRide() {
        executorService.execute(() -> {
            try {
                Ride ride = rideCabService.findByCab(idCab);
                idRide = ride.getId();
                jTextArea1.setText(parseRide(ride));
                jButton3.setEnabled(true);
            } catch (InterruptedException e) {
                log.warn("Task to verify the new ride was interrupted: " + e);
            }
        });
    }

    private void initControllers() {
        jButton3.addActionListener(e -> {
            if (!rideCabService.setReadyToStart(idRide)) {
                JOptionPane.showMessageDialog(this, "Fatal Error, consulte a TI", "Fatal error", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException("Ride not founded");
            }
            JOptionPane.showMessageDialog(this, "Estado de la carrera modificada correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
            jButton3.setEnabled(false);

            executorService.execute(() -> {
                try {
                    if(rideCabService.isInProcess(idRide)) {
                        jButton1.setEnabled(true);
                    }
                } catch (InterruptedException ex) {
                    log.warn("Task to verify the new ride was interrupted: " + e);
                }
            });
        });

        jButton1.addActionListener(e -> {
            if (!rideCabService.setReadyToFinally(idRide)) {
                JOptionPane.showMessageDialog(this, "Fatal Error, consulte a TI", "Fatal error", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException("Ride not founded");
            }
            JOptionPane.showMessageDialog(this, "Estado de la carrera modificada correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE);
            jButton1.setEnabled(false);
            rideCabService.setCabEnable(idCab);
            addToHistory(jTextArea1.getText());
            jTextArea1.setText("No hay rutas actuales");
            verifyNewRide();
            executorService.execute(() -> {
                try {
                    if(rideCabService.isEnded(idRide)) {
                        JOptionPane.showMessageDialog(this, "Carrera completada correctamente!", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (InterruptedException ex) {
                    log.warn("Task to verify the new ride was interrupted: " + e);
                }
            });

        });

    }

    private void addToHistory(String text) {
        jPanel2.setLayout(new GridLayout(5, 1));
        JTextArea textArea = new JTextArea(text);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setOpaque(true);
        textArea.setBackground(Color.WHITE);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setMargin(new Insets(5, 5, 5, 5));
        textArea.setRows(5);
        jPanel2.add(textArea);
    }

    private String parseRide(Ride ride) {
        return "REFERENCIA DE ORIGEN: " +
                ride.getStartAddressReference() +
                "\n" +
                "ORIGEN-LATITUD: " +
                ride.getOriginLatitude() +
                "\n" +
                "ORIGEN-LONGITUD " +
                ride.getOriginLongitude() +
                "\n" +
                "REFERENCIA DE DESTINO " +
                ride.getEndAddressReference() +
                "\n" +
                "DESTINO-LATITUDE " +
                ride.getDestinyLatitude() +
                "\n" +
                "DESTINO-LONGITUDE " +
                ride.getDestinyLongitude() +
                "\n" +
                "=========================================================" +
                "\n" +
                "ESTADO DE LA RUTA: " +
                ride.getStatus().name() +
                "\n" +
                "TIPO DE PAGO: " +
                ride.getPayment().getPaymentMethod().name() +
                "\n" +
                "MONTO DE PAGO " +
                ride.getPayment().getAmount() + "$" +
                "\n" +
                "=========================================================" +
                "\n" +
                "NOMBRE CLIENTE: " +
                ride.getClient().getUserEntity().getFullNames() +
                "\n" +
                "CORREO CLIENTE: " +
                ride.getClient().getUserEntity().getEmail() +
                "\n" +
                "TELEFONO CLIENTE:" +
                ride.getClient().getUserEntity().getPhone();


    }

    /*public static void main(String args[]) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatLightLaf());
        java.awt.EventQueue.invokeLater(() -> new RidesCabUI(
                new RideCabServiceImpl(
                        new RideRepositoryImpl(HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"),
                                new CabRepositoryImpl(HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"))),
                        new CabRepositoryImpl(HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"))),
                3L

        ).setVisible(true));
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
