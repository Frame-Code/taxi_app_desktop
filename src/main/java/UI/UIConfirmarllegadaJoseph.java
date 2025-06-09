package UI;

import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UIConfirmarllegadaJoseph extends JFrame {
    // Colores constantes
    private static final Color BACKGROUND_GENERAL = Color.decode("#F5F3F1");
    private static final Color HEADER_BACKGROUND = BACKGROUND_GENERAL;
    private static final Color FORM_BACKGROUND = Color.decode("#DCDCDC");
    private static final Color TEXT_PRIMARY = Color.decode("#000000");
    private static final Color TEXT_SECONDARY = Color.decode("#5A5A5A");
    private static final Color BUTTON_BACKGROUND = Color.decode("#2B2B2B");
    private static final Color BUTTON_TEXT = Color.WHITE;
    private static final Color INPUT_BORDER = Color.BLACK;

    // Campos de texto editables
    private JTextField origenField;
    private JTextField destinoField;
    private JTextField taxistaField;
    private JTextField carroField;

    public UIConfirmarllegadaJoseph() {
        super("Confirmar Llegada");
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
        nav.add(createNavLabel("EMPEZAR RUTA"));
        nav.add(createNavLabel("ABOUT"));
        nav.add(createNavLabel("LOCATIONS AVAILABLE"));
        nav.add(createNavLabel("USERNAME"));
        header.add(nav, BorderLayout.CENTER);

        JLabel profileIcon = new JLabel("👤");
        profileIcon.setFont(new Font("Arial", Font.PLAIN, 20));
        header.add(profileIcon, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // Content
        JPanel content = new JPanel();
        content.setBackground(BACKGROUND_GENERAL);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Título
        JLabel title = new JLabel("CONFIRMAR LLEGADA AL DESTINO");
        title.setFont(new Font("Arial Black", Font.BOLD, 32));
        title.setForeground(TEXT_PRIMARY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(title);
        content.add(Box.createRigidArea(new Dimension(0, 20)));

        // Panel de formulario editable
        JPanel infoPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        infoPanel.setBackground(FORM_BACKGROUND);
        infoPanel.setBorder(new LineBorder(INPUT_BORDER));
        infoPanel.setPreferredSize(new Dimension(840, 160));

        // Origen
        infoPanel.add(createInfoLabel("Origen:"));
        origenField = new JTextField();
        origenField.setFont(new Font("Arial", Font.PLAIN, 16));
        origenField.setBorder(new LineBorder(INPUT_BORDER));
        infoPanel.add(origenField);

        // Destino
        infoPanel.add(createInfoLabel("Destino:"));
        destinoField = new JTextField();
        destinoField.setFont(new Font("Arial", Font.PLAIN, 16));
        destinoField.setBorder(new LineBorder(INPUT_BORDER));
        infoPanel.add(destinoField);

        // Taxista
        infoPanel.add(createInfoLabel("Taxista:"));
        taxistaField = new JTextField();
        taxistaField.setFont(new Font("Arial", Font.PLAIN, 16));
        taxistaField.setBorder(new LineBorder(INPUT_BORDER));
        infoPanel.add(taxistaField);

        // Carro
        infoPanel.add(createInfoLabel("Carro:"));
        carroField = new JTextField();
        carroField.setFont(new Font("Arial", Font.PLAIN, 16));
        carroField.setBorder(new LineBorder(INPUT_BORDER));
        infoPanel.add(carroField);

        content.add(infoPanel);
        content.add(Box.createRigidArea(new Dimension(0, 30)));

        // Botón confirmar
        JButton confirmarBtn = new JButton("CONFIRMAR");
        confirmarBtn.setBackground(BUTTON_BACKGROUND);
        confirmarBtn.setForeground(BUTTON_TEXT);
        confirmarBtn.setFont(new Font("Arial", Font.BOLD, 16));
        confirmarBtn.setBorder(new LineBorder(INPUT_BORDER));
        confirmarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmarBtn.setPreferredSize(new Dimension(200, 45));
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
        lbl.setFont(new Font("Arial", Font.PLAIN, 16));
        lbl.setForeground(TEXT_PRIMARY);
        return lbl;
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatDarkLaf());
        SwingUtilities.invokeLater(() -> {
            UIConfirmarllegadaJoseph ui = new UIConfirmarllegadaJoseph();
            ui.setVisible(true);
        });
    }
}

