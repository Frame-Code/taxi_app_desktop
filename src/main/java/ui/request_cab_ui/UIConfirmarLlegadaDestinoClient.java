package ui.request_cab_ui;

import lombok.extern.apachecommons.CommonsLog;
import service.interfaces.ride_module.IRideClientService;
import shared.dto.CabDTO;
import shared.dto.CoordinatesRideDTO;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@CommonsLog
public class UIConfirmarLlegadaDestinoClient extends JFrame {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final CoordinatesRideDTO coordinatesRideDTO;
    private final IRideClientService rideService;
    private JButton confirmarBtn;

    public UIConfirmarLlegadaDestinoClient(IRideClientService rideService, CabDTO cabDTO, CoordinatesRideDTO coordinatesRideDTO, Long id_ride) {
        super("Cliente: Confirmar llegada al destino");
        this.coordinatesRideDTO = coordinatesRideDTO;
        this.rideService = rideService;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(900, 600); // Increased height
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel logo = new JLabel("SYSTEM");
        logo.setFont(new Font("Impact", Font.BOLD, 24));
        header.add(logo, BorderLayout.WEST);

        JLabel profileIcon = new JLabel();
        profileIcon.setFont(new Font("Arial", Font.PLAIN, 20));
        header.add(profileIcon, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // Contenido principal
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel title = new JLabel("CONFIRMAR LLEGADA AL DESTINO");
        title.setFont(new Font("Arial Black", Font.BOLD, 32));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(title);
        content.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel infoContainer = new JPanel(new GridLayout(2, 2, 20, 20));
        infoContainer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Origen Panel
        JPanel origenPanel = new JPanel();
        origenPanel.setLayout(new BoxLayout(origenPanel, BoxLayout.Y_AXIS));
        origenPanel.add(createInfoLabel("Origen:"));
        origenPanel.add(
                createValueTextArea(
                        parseCoordinates(
                                coordinatesRideDTO.originReference(),
                                String.valueOf(coordinatesRideDTO.originLatitude()),
                                String.valueOf(coordinatesRideDTO.originLongitude())
                        )
                )
        );
        infoContainer.add(origenPanel);

        // Destino Panel
        JPanel destinoPanel = new JPanel();
        destinoPanel.setLayout(new BoxLayout(destinoPanel, BoxLayout.Y_AXIS));
        destinoPanel.add(createInfoLabel("Destino:"));
        destinoPanel.add(
                createValueTextArea(
                        parseCoordinates(
                                coordinatesRideDTO.destinyReference(),
                                String.valueOf(coordinatesRideDTO.destinyLatitude()),
                                String.valueOf(coordinatesRideDTO.destinyLongitude())
                        )
                )
        );
        infoContainer.add(destinoPanel);

        // Taxista Panel
        JPanel taxistaPanel = new JPanel();
        taxistaPanel.setLayout(new BoxLayout(taxistaPanel, BoxLayout.Y_AXIS));
        taxistaPanel.add(createInfoLabel("Taxista:"));
        taxistaPanel.add(createValueTextArea(parseCab(cabDTO)));
        infoContainer.add(taxistaPanel);

        // Carro Panel
        JPanel carroPanel = new JPanel();
        carroPanel.setLayout(new BoxLayout(carroPanel, BoxLayout.Y_AXIS));
        carroPanel.add(createInfoLabel("Carro:"));
        carroPanel.add(createValueTextArea(parseVehicle(cabDTO)));
        infoContainer.add(carroPanel);

        content.add(infoContainer);
        content.add(Box.createRigidArea(new Dimension(0, 30)));

        // Botón confirmar (sin funcionalidad)
        JButton confirmarBtn = new JButton("Confirmar llegada");
        confirmarBtn.setFont(new Font("Arial", Font.BOLD, 16));
        confirmarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmarBtn.setPreferredSize(new Dimension(200, 45));
        confirmarBtn.setEnabled(false);
        content.add(confirmarBtn);

        add(content, BorderLayout.CENTER);

        initVerifier(id_ride);
    }

    private void initControllers() {
        confirmarBtn.addActionListener(e -> {
           JOptionPane.showMessageDialog(this, "Gracias por usar nuestros servicios!", "Carrera terminada", JOptionPane.INFORMATION_MESSAGE);
        });
    }

    private void initVerifier(Long id_ride) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            try {
                if(!rideService.isReadyToFinally(id_ride)) {
                    return;
                }
                confirmarBtn.setEnabled(true);
            } catch (InterruptedException e) {
                log.warn("Task was interrupted: " + e);
            }

        });
    }


    private JLabel createInfoLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Arial", Font.BOLD, 16));
        return lbl;
    }

    private JTextArea createValueTextArea(String text) {
        JTextArea textArea = new JTextArea(text);
        textArea.setFont(new Font("Arial", Font.PLAIN, 16));
        textArea.setOpaque(true);
        textArea.setBackground(Color.WHITE);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setMargin(new Insets(5, 5, 5, 5));
        textArea.setRows(5); // Increased rows
        return textArea;
    }

    private String parseCoordinates(String reference, String latitude, String longitude) {
        return "Referencia: " +
                reference +
                "\n" +
                "Latitude: " +
                latitude +
                "\n" +
                "Longitude: " +
                longitude;
    }

    private String parseCab(CabDTO cabDTO) {
        return "Nombres completos: " +
                cabDTO.fullNames() +
                "\n" +
                "Correo: " +
                cabDTO.email() +
                "\n" +
                "Telefono: " +
                cabDTO.phone();
    }

    private String parseVehicle(CabDTO cabDTO) {
        return "Marca: " +
                cabDTO.brand() +
                "\n" +
                "Modelo: " +
                cabDTO.model() +
                "\n" +
                "Placa: " +
                cabDTO.licensePLate();
    }

}


