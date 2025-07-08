package ui;

import com.formdev.flatlaf.FlatLightLaf;
import domain.entities.Client;
import domain.repository.impl.CabRepositoryImpl;
import domain.repository.impl.ClientRepositoryImpl;
import domain.repository.impl.FareRepositoryImpl;
import domain.repository.impl.ProvinceRepositoryImpl;
import domain.repository.impl.RideRepositoryImpl;
import domain.repository.impl.TaxiLiveAddressRepositoryImpl;
import service.external.client.opencage.IOpenCageClient;
import service.external.client.opencage.OpenCageClientImpl;
import service.external.client.openrouteservice.OpenRouteServiceClientImpl;
import service.impl.location_module.ProvinceServiceImpl;
import service.impl.matching_module.FindCabsServiceImpl;
import service.impl.matching_module.MatchServiceImpl;
import service.impl.payment_module.PaymentFactoryImpl;
import service.impl.ride_service.FareServiceImpl;
import service.impl.ride_service.IRideCalculationsServiceImpl;
import service.impl.ride_service.RideServiceImpl;
import service.interfaces.location_module.IProvinceService;
import service.interfaces.matching_module.IMatchService;
import service.interfaces.payment_module.PaymentFactory;
import service.interfaces.ride_module.IFareService;
import service.interfaces.ride_module.IRideCalculationsService;
import service.interfaces.ride_module.IRideService;
import shared.dto.CoordinatesRideDTO;
import shared.utils.HibernateUtil;
import ui.components.RoundedPanelWithShadow;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jxmapviewer.viewer.GeoPosition;

/**
 * @author Daniel Mora Cantillo
 */
public class CabRequestView extends javax.swing.JFrame {
    private final IMapViewer mapViewer;
    private final IOpenCageClient openCageClient;
    private final IRideService rideService;
    private final IRideCalculationsService rideCalculationsService;
    private final IFareService fareService;
    private final IMatchService matchService;
    private final IProvinceService provinceService;
    private final PaymentFactory paymentFactory;
    private final Client client;
    private GeoPosition origin;
    private GeoPosition destiny;

    public CabRequestView(IMapViewer mapViewer,
                          IOpenCageClient openCageClient,
                          IRideService rideService,
                          IRideCalculationsService rideCalculationsService,
                          IFareService fareService,
                          IMatchService matchService,
                          IProvinceService provinceService,
                          PaymentFactory paymentFactory,
                          Client client) {
        this.mapViewer = mapViewer;
        this.openCageClient = openCageClient;
        this.rideService = rideService;
        this.rideCalculationsService = rideCalculationsService;
        this.fareService = fareService;
        this.matchService = matchService;
        this.provinceService = provinceService;
        this.paymentFactory = paymentFactory;
        this.client = client;
        initComponents();
        initMap();
        initControllers();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Solicitar taxi ahora");
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
     // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        contentPane = new javax.swing.JPanel();
        requestTaxi = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtOriginReference = new javax.swing.JTextField();
        txtDestinyReference = new javax.swing.JTextField();
        btnFindCabs = new javax.swing.JButton();
        map = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        pnlMap = new javax.swing.JPanel();
        btnSelectOrigin = new javax.swing.JButton();
        btnSelectDestiny = new javax.swing.JButton();
        txtCoordinates = new javax.swing.JTextField();
        jPanel1 = new RoundedPanelWithShadow(16, new Color(48, 51, 52, 100), 4);
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel2.setText("SOLICITAR");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Seleccione la ubicación de origen en el mapa:");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Seleccione la ubicación de destino en el mapa:");

        jLabel7.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        jLabel7.setText("TAXI AHORA");

        txtOriginReference.setEditable(false);
        txtOriginReference.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtOriginReference.setForeground(new java.awt.Color(51, 51, 51));
        txtOriginReference.setText("Referencia de la ubicación seleccionada");

        txtDestinyReference.setEditable(false);
        txtDestinyReference.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtDestinyReference.setForeground(new java.awt.Color(51, 51, 51));
        txtDestinyReference.setText("Referencia de la ubicación seleccionada");

        btnFindCabs.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnFindCabs.setText("BUSCAR TAXIS");

        javax.swing.GroupLayout requestTaxiLayout = new javax.swing.GroupLayout(requestTaxi);
        requestTaxi.setLayout(requestTaxiLayout);
        requestTaxiLayout.setHorizontalGroup(
                requestTaxiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(requestTaxiLayout.createSequentialGroup()
                                .addGap(72, 72, 72)
                                .addGroup(requestTaxiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel4)
                                        .addGroup(requestTaxiLayout.createSequentialGroup()
                                                .addGap(93, 93, 93)
                                                .addComponent(btnFindCabs, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel3)
                                        .addComponent(txtOriginReference, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
                                        .addComponent(txtDestinyReference))
                                .addContainerGap(25, Short.MAX_VALUE))
        );
        requestTaxiLayout.setVerticalGroup(
                requestTaxiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(requestTaxiLayout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addGap(26, 26, 26)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtOriginReference, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(139, 139, 139)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDestinyReference, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnFindCabs, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(175, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Seleccione una ubicación en el mapa:");

        pnlMap.setBackground(new java.awt.Color(69, 72, 73));
        pnlMap.setLayout(new java.awt.BorderLayout());

        btnSelectOrigin.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSelectOrigin.setText("SELECCIONAR COMO ORIGEN");
        btnSelectOrigin.setEnabled(false);

        btnSelectDestiny.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnSelectDestiny.setText("SELECCIONAR COMO DESTINO");
        btnSelectDestiny.setEnabled(false);

        txtCoordinates.setEditable(false);
        txtCoordinates.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtCoordinates.setForeground(new java.awt.Color(0, 0, 0));
        txtCoordinates.setText("Coordenadas seleccionadas");

        javax.swing.GroupLayout mapLayout = new javax.swing.GroupLayout(map);
        map.setLayout(mapLayout);
        mapLayout.setHorizontalGroup(
                mapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mapLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(mapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mapLayout.createSequentialGroup()
                                                .addGroup(mapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(txtCoordinates)
                                                        .addGroup(mapLayout.createSequentialGroup()
                                                                .addComponent(btnSelectOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(btnSelectDestiny)))
                                                .addGap(35, 35, 35))
                                        .addGroup(mapLayout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addContainerGap(297, Short.MAX_VALUE))
                                        .addComponent(pnlMap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        mapLayout.setVerticalGroup(
                mapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mapLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pnlMap, javax.swing.GroupLayout.PREFERRED_SIZE, 487, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCoordinates, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(mapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnSelectOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnSelectDestiny, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(60, 60, 60))
        );

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("SYSTEM");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setText(client.getUserEntity().getFullNames());

        jLabel5.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel5.setText("SOLICITAR TAXI");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel5)
                                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 6, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setText("LOCACIONES DISPONIBLES");

        jLabel10.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/user-solid.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101)
                                .addComponent(jLabel9)
                                .addGap(88, 88, 88)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel6)
                                                .addComponent(jLabel9)
                                                .addComponent(jLabel10))
                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout contentPaneLayout = new javax.swing.GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(requestTaxi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(map, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(map, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(requestTaxi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(contentPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(contentPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void initMap() {
        mapViewer.initMap(pnlMap);
    }

    private void initControllers() {
        pnlMap.getComponent(0).addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                txtCoordinates.setText(mapViewer.getSelectedCoordinates().toString());
                btnSelectDestiny.setEnabled(true);
                btnSelectOrigin.setEnabled(true);
            }
        });

        btnSelectOrigin.addActionListener(e -> {
            setLocation(txtOriginReference);
            origin = mapViewer.getSelectedCoordinates();
        });

        btnSelectDestiny.addActionListener(e -> {
            setLocation(txtDestinyReference);
            destiny = mapViewer.getSelectedCoordinates();
        });

        btnFindCabs.addActionListener(e -> {
            if (origin == null || destiny == null) {
                JOptionPane.showMessageDialog(this, "Selecciona una ubicacion de origen y destino por favor", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            var provinceNameOpt = openCageClient.getState(mapViewer.getSelectedCoordinates().getLatitude(), mapViewer.getSelectedCoordinates().getLongitude());
            if (provinceNameOpt.isEmpty() || !provinceService.isLocationAvailable(provinceNameOpt.get())) {
                JOptionPane.showMessageDialog(this, "En la provincia seleccionada no esta disponible el sistema", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            CoordinatesRideDTO coordinatesRideDTO = new CoordinatesRideDTO(
                    txtOriginReference.getText(),
                    origin.getLatitude(),
                    origin.getLongitude(),
                    txtDestinyReference.getText(),
                    destiny.getLatitude(),
                    destiny.getLongitude());
            new ConfirmRideView(coordinatesRideDTO, rideService, rideCalculationsService, fareService, matchService, paymentFactory, client, this);
            dispose();
        });

    }

    private void setLocation(JTextField txtField) {
        txtField.setText(openCageClient.format(mapViewer.getSelectedCoordinates().getLatitude(), mapViewer.getSelectedCoordinates().getLongitude()));
    }

    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatLightLaf());
        java.awt.EventQueue.invokeLater(() -> {
            new CabRequestView(
                    new OpenStreetMapView(),
                    OpenCageClientImpl.getInstance(),
                    new RideServiceImpl(
                            new RideRepositoryImpl(
                                    HibernateUtil.getSessionFactory("hibernate-local.cfg.xml")), new CabRepositoryImpl(HibernateUtil.getSessionFactory("hibernate-local.cfg.xml")),
                            new OpenRouteServiceClientImpl()),
                    new IRideCalculationsServiceImpl(
                            new FareRepositoryImpl(
                                    HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"))),
                    new FareServiceImpl(
                            new FareRepositoryImpl(
                                    HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"))),
                    new MatchServiceImpl(
                            new FindCabsServiceImpl(
                                    new TaxiLiveAddressRepositoryImpl(
                                            HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"))),
                            new RideServiceImpl(
                                    new RideRepositoryImpl(
                                            HibernateUtil.getSessionFactory("hibernate-local.cfg.xml")),
                                    new CabRepositoryImpl(
                                            HibernateUtil.getSessionFactory("hibernate-local.cfg.xml")),
                                    new OpenRouteServiceClientImpl())),
                    new ProvinceServiceImpl(
                            new ProvinceRepositoryImpl(
                                    HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"))),
                    new PaymentFactoryImpl(),
                    new ClientRepositoryImpl(
                            HibernateUtil.getSessionFactory("hibernate-local.cfg.xml")).findByEmail("mail@email.com").get()
            );
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFindCabs;
    private javax.swing.JButton btnSelectDestiny;
    private javax.swing.JButton btnSelectOrigin;
    private javax.swing.JPanel contentPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel map;
    private javax.swing.JPanel pnlMap;
    private javax.swing.JPanel requestTaxi;
    private javax.swing.JTextField txtCoordinates;
    private javax.swing.JTextField txtDestinyReference;
    private javax.swing.JTextField txtOriginReference;
    // End of variables declaration//GEN-END:variables
}
