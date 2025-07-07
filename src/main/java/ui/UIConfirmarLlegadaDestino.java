package ui;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UIConfirmarLlegadaDestino extends JFrame {
    private static final Color BACKGROUND_GENERAL = Color.decode("#F5F3F1");
    private static final Color HEADER_BACKGROUND = BACKGROUND_GENERAL;
    private static final Color FORM_BACKGROUND = Color.decode("#DCDCDC");
    private static final Color TEXT_PRIMARY = Color.decode("#000000");
    private static final Color TEXT_SECONDARY = Color.decode("#5A5A5A");
    private static final Color BUTTON_BACKGROUND = Color.decode("#2B2B2B");
    private static final Color BUTTON_TEXT = Color.WHITE;
    private static final Color INPUT_BORDER = Color.BLACK;

    private JLabel origenLabel;
    private JLabel destinoLabel;
    private JLabel taxistaLabel;
    private JLabel carroLabel;

    public UIConfirmarLlegadaDestino() {
        super("Confirmar Llegada del Cliente");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 400);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BACKGROUND_GENERAL);
        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(HEADER_BACKGROUND);
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel logo = new JLabel("SYSTEM");
        logo.setFont(new Font("Impact", Font.BOLD, 24));
        logo.setForeground(TEXT_PRIMARY);
        header.add(logo, BorderLayout.WEST);

        JPanel nav = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        nav.setBackground(HEADER_BACKGROUND);
        nav.add(createNavLabel("RUTA EN CURSO"));
        nav.add(createNavLabel("DETALLES"));
        nav.add(createNavLabel("AYUDA"));
        nav.add(createNavLabel("USUARIO"));
        header.add(nav, BorderLayout.CENTER);

        JLabel profileIcon = new JLabel();
        profileIcon.setFont(new Font("Arial", Font.PLAIN, 20));
        header.add(profileIcon, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // Contenido principal
        JPanel content = new JPanel();
        content.setBackground(BACKGROUND_GENERAL);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel title = new JLabel("CONFIRMAR LLEGADA DEL CLIENTE");
        title.setFont(new Font("Arial Black", Font.BOLD, 32));
        title.setForeground(TEXT_PRIMARY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(title);
        content.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel infoPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        infoPanel.setBackground(FORM_BACKGROUND);
        infoPanel.setBorder(new LineBorder(INPUT_BORDER));
        infoPanel.setPreferredSize(new Dimension(840, 160));

        // Origen
        infoPanel.add(createInfoLabel("Origen:"));
        origenLabel = createValueLabel();
        infoPanel.add(origenLabel);

        // Destino
        infoPanel.add(createInfoLabel("Destino:"));
        destinoLabel = createValueLabel();
        infoPanel.add(destinoLabel);

        // Taxista
        infoPanel.add(createInfoLabel("Taxista:"));
        taxistaLabel = createValueLabel();
        infoPanel.add(taxistaLabel);

        // Carro
        infoPanel.add(createInfoLabel("Carro:"));
        carroLabel = createValueLabel();
        infoPanel.add(carroLabel);

        content.add(infoPanel);
        content.add(Box.createRigidArea(new Dimension(0, 30)));

        // Botón confirmar (sin funcionalidad)
        JButton confirmarBtn = new JButton("CONFIRMAR LLEGADA");
        confirmarBtn.setBackground(BUTTON_BACKGROUND);
        confirmarBtn.setForeground(BUTTON_TEXT);
        confirmarBtn.setFont(new Font("Arial", Font.BOLD, 16));
        confirmarBtn.setBorder(new LineBorder(INPUT_BORDER));
        confirmarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmarBtn.setPreferredSize(new Dimension(200, 45));
        // No se le agrega ningún ActionListener
        content.add(confirmarBtn);

        add(content, BorderLayout.CENTER);
    }

    private JLabel createNavLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setForeground(TEXT_SECONDARY);
        return lbl;
    }

    private JLabel createInfoLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Arial", Font.BOLD, 16));
        lbl.setForeground(TEXT_PRIMARY);
        return lbl;
    }

    private JLabel createValueLabel() {
        JLabel lbl = new JLabel(" ");
        lbl.setFont(new Font("Arial", Font.PLAIN, 16));
        lbl.setOpaque(true);
        lbl.setBackground(Color.WHITE);
        lbl.setBorder(new LineBorder(INPUT_BORDER));
        lbl.setPreferredSize(new Dimension(300, 30));
        return lbl;
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatDarkLaf());
        SwingUtilities.invokeLater(() -> {
            UIConfirmarLlegadaDestino ui = new UIConfirmarLlegadaDestino();
            ui.setVisible(true);
        });
    }
}

