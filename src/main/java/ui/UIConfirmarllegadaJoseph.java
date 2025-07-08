package ui;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;

public class UIConfirmarllegadaJoseph extends JFrame {
    // Campos de texto (solo lectura)
    private JTextField origenField;
    private JTextField destinoField;
    private JTextField taxistaField;

    public UIConfirmarllegadaJoseph() {
        super("Taxi: Confirmar Llegada al destino");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel logo = new JLabel("SYSTEM");
        logo.setFont(new Font("Impact", Font.BOLD, 24));
        header.add(logo, BorderLayout.WEST);

        JPanel nav = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        header.add(nav, BorderLayout.CENTER);

        JLabel profileIcon = new JLabel("👤");
        profileIcon.setFont(new Font("Arial", Font.PLAIN, 20));
        header.add(profileIcon, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // Content
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Título
        JLabel title = new JLabel("Confirmar llegada al destino");
        title.setFont(new Font("Arial Black", Font.BOLD, 32));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(title);
        content.add(Box.createRigidArea(new Dimension(0, 20)));

        // Panel de formulario (solo lectura)
        JPanel infoPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        infoPanel.setPreferredSize(new Dimension(840, 160));

        // Origen
        infoPanel.add(createInfoLabel("Origen:"));
        origenField = new JTextField();
        origenField.setFont(new Font("Arial", Font.PLAIN, 16));
        origenField.setEditable(false); // <- desactivado para que no se pueda escribir
        infoPanel.add(origenField);

        // Destino
        infoPanel.add(createInfoLabel("Destino:"));
        destinoField = new JTextField();
        destinoField.setFont(new Font("Arial", Font.PLAIN, 16));
        destinoField.setEditable(false); // <- desactivado para que no se pueda escribir
        infoPanel.add(destinoField);

        // Taxista
        infoPanel.add(createInfoLabel("Cliente: "));
        taxistaField = new JTextField();
        taxistaField.setFont(new Font("Arial", Font.PLAIN, 16));
        taxistaField.setEditable(false); // <- desactivado para que no se pueda escribir
        infoPanel.add(taxistaField);

        content.add(infoPanel);
        content.add(Box.createRigidArea(new Dimension(0, 30)));

        // Botón confirmar
        JButton confirmarBtn = new JButton("Confirmar");
        confirmarBtn.setFont(new Font("Arial", Font.BOLD, 16));
        confirmarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        confirmarBtn.setPreferredSize(new Dimension(200, 45));
        content.add(confirmarBtn);

        add(content, BorderLayout.CENTER);
    }

    private JLabel createNavLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return lbl;
    }

    private JLabel createInfoLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Arial", Font.PLAIN, 16));
        return lbl;
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatLightLaf());
        SwingUtilities.invokeLater(() -> {
            UIConfirmarllegadaJoseph ui = new UIConfirmarllegadaJoseph();
            ui.setVisible(true);
        });
    }
}




