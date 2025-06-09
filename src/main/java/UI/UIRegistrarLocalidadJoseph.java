package UI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UIRegistrarLocalidadJoseph extends JFrame {
    // Colors
    private static final Color BACKGROUND_GENERAL = Color.decode("#F5F3F1");
    private static final Color FORM_BACKGROUND = Color.decode("#DCDCDC");
    private static final Color TEXT_PRIMARY = Color.decode("#000000");
    private static final Color TEXT_SECONDARY = Color.decode("#5A5A5A");
    private static final Color BUTTON_BACKGROUND = Color.decode("#2B2B2B");
    private static final Color BUTTON_TEXT = Color.WHITE;
    private static final Color INPUT_BORDER = Color.BLACK;
    private static final Color INPUT_BACKGROUND = Color.WHITE;
    private static final Color PLACEHOLDER_TEXT = Color.decode("#A0A0A0");

    public UIRegistrarLocalidadJoseph() {
        setTitle("Registrar Localidades");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(BACKGROUND_GENERAL);
        setLayout(new BorderLayout(10, 10));

        // --- Header ---
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(BACKGROUND_GENERAL);
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel logo = new JLabel("SYSTEM");
        logo.setFont(new Font("Impact", Font.BOLD, 24));
        logo.setForeground(TEXT_PRIMARY);
        header.add(logo, BorderLayout.WEST);

        JPanel navButtons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        navButtons.setBackground(BACKGROUND_GENERAL);
        navButtons.add(createNavLabel("EMPEZAR RUTA"));
        navButtons.add(createNavLabel("ABOUT"));
        navButtons.add(createNavLabel("LOCATIONS AVAILABLE"));
        navButtons.add(createNavLabel("USERNAME"));
        header.add(navButtons, BorderLayout.CENTER);

        // Profile icon placeholder
        JLabel profile = new JLabel("👤");
        profile.setFont(new Font("Arial", Font.PLAIN, 20));
        header.add(profile, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // --- Main Content ---
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(FORM_BACKGROUND);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        // Title
        JLabel title = new JLabel("REGISTRAR LOCALIDADES");
        title.setFont(new Font("Arial Black", Font.BOLD, 32));
        title.setForeground(TEXT_PRIMARY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Forms container
        JPanel formsContainer = new JPanel(new GridLayout(1, 2, 20, 0));
        formsContainer.setBackground(FORM_BACKGROUND);

        // City form panel
        JPanel cityPanel = createFormPanel("Ciudad", "Nombre:", "Provincia:");
        formsContainer.add(cityPanel);

        // Province form panel
        JPanel provPanel = createFormPanel("Provincia", "Nombre:", null);
        formsContainer.add(provPanel);

        mainPanel.add(formsContainer);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Search panel
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(FORM_BACKGROUND);
        searchPanel.add(createLabel("Nombre:", TEXT_SECONDARY, 16));
        JTextField searchField = createTextField(30);
        searchField.setText("BUSCAR CIUDAD O PROVINCIA");
        searchField.setForeground(PLACEHOLDER_TEXT);
        searchPanel.add(searchField);
        JButton searchBtn = createButton("GUARDAR PROVINCIA");
        searchPanel.add(searchBtn);
        mainPanel.add(searchPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Results area
        JTextArea resultsArea = new JTextArea(8, 80);
        resultsArea.setBackground(INPUT_BACKGROUND);
        resultsArea.setBorder(new LineBorder(INPUT_BORDER));
        JScrollPane scroll = new JScrollPane(resultsArea);
        mainPanel.add(scroll);

        add(mainPanel, BorderLayout.CENTER);
    }

    private JLabel createNavLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setForeground(TEXT_SECONDARY);
        return lbl;
    }

    private JPanel createFormPanel(String legend, String label1, String label2) {
        JPanel panel = new JPanel();
        panel.setBackground(FORM_BACKGROUND);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(new LineBorder(INPUT_BORDER), legend));

        panel.add(createLabel(label1, TEXT_SECONDARY, 14));
        panel.add(createTextField(20));
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        if (label2 != null) {
            panel.add(createLabel(label2, TEXT_SECONDARY, 14));
            JComboBox<String> combo = new JComboBox<>(new String[]{"SELECCIONA UNA OPCIÓN"});
            combo.setBackground(INPUT_BACKGROUND);
            combo.setFont(new Font("Arial", Font.PLAIN, 14));
            combo.setBorder(new LineBorder(INPUT_BORDER));
            panel.add(combo);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        JButton btn = createButton(label2 == null ? "GUARDAR PROVINCIA" : "GUARDAR CIUDAD");
        panel.add(btn);
        return panel;
    }

    private JLabel createLabel(String text, Color color, int size) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Arial", Font.PLAIN, size));
        lbl.setForeground(color);
        return lbl;
    }

    private JTextField createTextField(int columns) {
        JTextField field = new JTextField(columns);
        field.setBackground(INPUT_BACKGROUND);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(new LineBorder(INPUT_BORDER));
        return field;
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(BUTTON_BACKGROUND);
        btn.setForeground(BUTTON_TEXT);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setBorder(new LineBorder(INPUT_BORDER));
        btn.setPreferredSize(new Dimension(150, 35));
        return btn;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UIRegistrarLocalidadJoseph ui = new UIRegistrarLocalidadJoseph();
            ui.setVisible(true);
        });
    }
}
