package ui.request_cab_ui;

import domain.entities.Cab;
import domain.entities.Client;
import lombok.extern.apachecommons.CommonsLog;
import service.interfaces.matching_module.IMatchService;
import service.interfaces.payment_module.PaymentFactory;
import service.interfaces.ride_module.IFareService;
import service.interfaces.ride_module.IRideCalculationsService;
import service.interfaces.ride_module.IRideClientService;
import shared.dto.CabDTO;
import shared.dto.CoordinatesRideDTO;
import shared.enums.PAYMENT_METHOD;
import ui.components.LoadingDialog;

import java.io.IOException;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * @author Daniel Mora Cantillo
 */
@CommonsLog
public class ConfirmRideView extends javax.swing.JFrame {
    private final IFareService fareService;
    private final IRideClientService rideService;
    private final IMatchService matchService;
    private final IRideCalculationsService rideCalculationsService;
    private final PaymentFactory paymentFactory;
    private final Client client;
    private final CabRequestView cabRequestView;
    private final CoordinatesRideDTO coordinatesRideDTO;
    private double originLatitude;
    private double originLongitude;

    public ConfirmRideView(CoordinatesRideDTO coordinatesRideDTO,
            IRideClientService rideService,
            IRideCalculationsService rideCalculationsService,
            IFareService fareService,
            IMatchService matchService,
            PaymentFactory paymentFactory,
            Client client,
            CabRequestView cabRequestView) {
        this.rideService = rideService;
        this.rideCalculationsService = rideCalculationsService;
        this.fareService = fareService;
        this.matchService = matchService;
        this.originLatitude = coordinatesRideDTO.originLatitude();
        this.originLongitude = coordinatesRideDTO.originLongitude();
        this.client = client;
        this.cabRequestView = cabRequestView;
        this.paymentFactory = paymentFactory;
        this.coordinatesRideDTO = coordinatesRideDTO;
        initComponents();
        setResizable(false);
        setTitle("Confirmar ruta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initControllers();
        loadFields(coordinatesRideDTO);
    }

    private void initControllers() {
        btnConfirmRide.addActionListener(e -> {
            LoadingDialog loadingDialog = new LoadingDialog("Buscando taxi...", "Espere un momento, estamos asignandole un taxi");
            loadingDialog.setVisible(true);
            try {
                Thread.sleep(700);
                SwingUtilities.invokeLater(() -> {
                    List<Cab> cabsFounded = matchService.findNearbyCabs(originLatitude, originLongitude);
                    loadingDialog.dispose();
                    if (cabsFounded.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "No se han encontrado taxis disponibles, intente mas tarde :(", "Taxis no encontrados", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    var cabOpt = matchService.requestCab(coordinatesRideDTO, cabsFounded, client, paymentFactory.create(PAYMENT_METHOD.CASH, Double.parseDouble(finalPrice.getText().substring(1))));
                    if (cabOpt.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Error asignando taxi, intente mas tarde", "Error ", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    var rideOpt = rideService.findByCab(cabOpt.get().getId());
                    if(rideOpt.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "Fatal Error, consulte a TI", "Fatal error", JOptionPane.ERROR_MESSAGE);
                        throw new RuntimeException("Ride not founded");
                    }

                    btnConfirmRide.setEnabled(false);
                    new CabAssignedView(new CabDTO(
                            cabOpt.get().getDriver().getUserEntity().getFullNames(),
                            cabOpt.get().getDriver().getUserEntity().getEmail(),
                            cabOpt.get().getDriver().getUserEntity().getPhone(),
                            cabOpt.get().getVehicle().getBrand(),
                            cabOpt.get().getVehicle().getModel(),
                            cabOpt.get().getVehicle().getLicensePlate()),
                            coordinatesRideDTO,
                            rideService,
                            rideOpt.get().getId());
                });
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
        btnBack.addActionListener(e -> {
            cabRequestView.setVisible(true);
            this.dispose();
        });

    }

    private void loadFields(CoordinatesRideDTO coordinatesRideDTO) {
        var fareOpt = fareService.findFare();
        if (fareOpt.isEmpty()) {
            log.error("Impossible calculate price because is not possible to find fare");
            JOptionPane.showMessageDialog(this, "No se puede calcular precio, consulte al departamento TI", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            referenceOrigin.setText(coordinatesRideDTO.originReference());
            coordinatesOrigin.setText(coordinatesRideDTO.getOriginCoordinates());
            referenceDestiny.setText(coordinatesRideDTO.destinyReference());
            coordinatesDestiny.setText(coordinatesRideDTO.getDestinyCoordinates());
            rideService.getInfoRide(coordinatesRideDTO)
                    .map(infoRideDTO -> {
                        approxTime.setText(infoRideDTO.getApproxTimeAsMinutes() + " Minutos");
                        approxKm.setText(infoRideDTO.approxDistance() + " KM");
                        pricePerMinute.setText("$" + fareOpt.get().getPricePerMinute());
                        pricePerKm.setText("$" + fareOpt.get().getPricePerKm());
                        princeBase.setText("$" + fareOpt.get().getBaseFare());
                        finalPrice.setText("$" + rideCalculationsService.getPrice(infoRideDTO.approxDistance(), infoRideDTO.approxTime()));
                        setVisible(true);
                        return null;
                    });
        } catch (IOException ex) {
            log.error("Impossible calculate price because is not possible connect to external services");
            JOptionPane.showMessageDialog(this, "No se puede calcular precio, consulte a departamento TI", "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-1);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contentPane = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        pricePerMinute = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        pricePerKm = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        princeBase = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        finalPrice = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        referenceOrigin = new javax.swing.JLabel();
        coordinatesOrigin = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        referenceDestiny = new javax.swing.JLabel();
        coordinatesDestiny = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        approxTime = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        approxKm = new javax.swing.JLabel();
        btnConfirmRide = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 38)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CONFIRMAR RUTA");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Precio", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        pricePerMinute.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        pricePerMinute.setText("approx.");

        jLabel15.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel15.setText("Precio por minuto:");

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setText("Precio por kilometro:");

        pricePerKm.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        pricePerKm.setText("approx.");

        jLabel19.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel19.setText("Precio base:");

        princeBase.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        princeBase.setText("approx.");

        jLabel21.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Precio final");

        finalPrice.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        finalPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        finalPrice.setText("approx.");

        jLabel27.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel27.setText("Nota: Recuerda que el pago es en efectivo, asegurate de llevar suelto.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(finalPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pricePerMinute, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(princeBase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 188, Short.MAX_VALUE)
                            .addComponent(pricePerKm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(princeBase))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pricePerMinute))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pricePerKm)))
                .addGap(18, 18, 18)
                .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(finalPrice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jLabel27)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Origen y destino", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        jLabel31.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel31.setText("Origen");

        referenceOrigin.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        referenceOrigin.setText("reference...");

        coordinatesOrigin.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        coordinatesOrigin.setText("coordinates...");

        jLabel35.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel35.setText("Destino");

        referenceDestiny.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        referenceDestiny.setText("reference...");

        coordinatesDestiny.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        coordinatesDestiny.setText("coordinates...");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(referenceOrigin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(coordinatesOrigin, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
                    .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(referenceDestiny, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(coordinatesDestiny, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(referenceOrigin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(coordinatesOrigin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel35)
                .addGap(12, 12, 12)
                .addComponent(referenceDestiny)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(coordinatesDestiny)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ruta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 0, 12))); // NOI18N

        jLabel23.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel23.setText("Tiempo estimado:");

        approxTime.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        approxTime.setText("approx.");

        jLabel25.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel25.setText("Km estimados:");

        approxKm.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        approxKm.setText("approx.");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(approxTime, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(approxKm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(approxTime)
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(approxKm)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        btnConfirmRide.setText("CONFIRMAR RUTA");

        btnBack.setText("<- Regresar");

        javax.swing.GroupLayout contentPaneLayout = new javax.swing.GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(contentPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addComponent(btnConfirmRide, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(225, 225, 225))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contentPaneLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmRide, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(contentPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel approxKm;
    private javax.swing.JLabel approxTime;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnConfirmRide;
    private javax.swing.JPanel contentPane;
    private javax.swing.JLabel coordinatesDestiny;
    private javax.swing.JLabel coordinatesOrigin;
    private javax.swing.JLabel finalPrice;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel pricePerKm;
    private javax.swing.JLabel pricePerMinute;
    private javax.swing.JLabel princeBase;
    private javax.swing.JLabel referenceDestiny;
    private javax.swing.JLabel referenceOrigin;
    // End of variables declaration//GEN-END:variables
}
