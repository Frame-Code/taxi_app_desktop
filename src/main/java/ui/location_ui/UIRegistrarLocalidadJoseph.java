package ui.location_ui;

import com.formdev.flatlaf.FlatLightLaf;
import domain.entities.Province;
import domain.repository.impl.ProvinceRepositoryImpl;
import service.impl.location_module.ProvinceServiceImpl;
import service.interfaces.location_module.IProvinceService;
import shared.utils.HibernateUtil;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;
import java.util.Optional;


public class UIRegistrarLocalidadJoseph extends JFrame {

    private final IProvinceService provinceService;
    private JTextField txtNombreProvincia;
    private JButton btnGuardarProvincia;
    private JTextField txtNombreCiudad;
    private JComboBox<String> comboProvincias;
    private JButton btnGuardarCiudad;
    private JTextField searchField;
    private JButton searchBtn;
    private JTextArea resultsArea;

    public UIRegistrarLocalidadJoseph(IProvinceService provinceService) {
        this.provinceService = provinceService;

        setTitle("Registrar Localidades");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setResizable(false);

        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(header, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("REGISTRAR LOCALIDADES");
        title.setFont(new Font("Arial Black", Font.BOLD, 32));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel formsContainer = new JPanel(new GridLayout(1, 2, 20, 0));

        JPanel cityPanel = new JPanel();
        setupFormPanel(cityPanel, "Ciudad");
        cityPanel.add(createLabel("Nombre:", null, 14));
        txtNombreCiudad = createTextField(20); // Asignación
        cityPanel.add(txtNombreCiudad);
        cityPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        cityPanel.add(createLabel("Provincia:", null, 14));
        comboProvincias = new JComboBox<>();
        cityPanel.add(comboProvincias);
        cityPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        btnGuardarCiudad = createButton("GUARDAR CIUDAD"); // Asignación
        cityPanel.add(btnGuardarCiudad);
        formsContainer.add(cityPanel);

        JPanel provPanel = new JPanel();
        setupFormPanel(provPanel, "Provincia");
        provPanel.add(createLabel("Nombre:", null, 14));
        txtNombreProvincia = createTextField(20); // Asignación
        provPanel.add(txtNombreProvincia);
        provPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        btnGuardarProvincia = createButton("GUARDAR PROVINCIA"); // Asignación
        provPanel.add(btnGuardarProvincia);
        formsContainer.add(provPanel);

        mainPanel.add(formsContainer);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // --- Panel de Búsqueda ---
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(createLabel("Buscar:", null, 16));
        searchField = createTextField(30); // Asignación
        searchPanel.add(searchField);
        searchBtn = createButton("BUSCAR"); // Asignación y cambio de texto
        searchPanel.add(searchBtn);
        mainPanel.add(searchPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        resultsArea = new JTextArea(8, 80); // Asignación
        resultsArea.setBackground(Color.WHITE);
        resultsArea.setBorder(new LineBorder(Color.BLACK));
        resultsArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(resultsArea);
        mainPanel.add(scroll);

        add(mainPanel, BorderLayout.CENTER);

        inicializarListeners();
        cargarProvinciasEnComboBox();
    }

    private void cargarProvinciasEnComboBox() {
        comboProvincias.removeAllItems(); // Limpia el combo
        List<Province> provincias = provinceService.getProvinces();
        if (provincias.isEmpty()) {
            comboProvincias.addItem("No hay provincias registradas");
        } else {
            for (Province p : provincias) {
                comboProvincias.addItem(p.getName());
            }
        }
    }

    /**
     * Configura los ActionListeners para todos los botones de la interfaz.
     */
    private void inicializarListeners() {
        btnGuardarProvincia.addActionListener(e -> guardarNuevaProvincia());
        btnGuardarCiudad.addActionListener(e -> guardarNuevaCiudad());
        searchBtn.addActionListener(e -> buscarLocalidad());
    }

    private void guardarNuevaProvincia() {
        String nombreNuevaProvincia = txtNombreProvincia.getText().trim();
        if (nombreNuevaProvincia.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre de la provincia no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        provinceService.save(Province.builder()
                .name(nombreNuevaProvincia)
                .build());
        JOptionPane.showMessageDialog(this, "Provincia guardada con éxito.");
        txtNombreProvincia.setText("");
        cargarProvinciasEnComboBox(); // Actualiza el combo con la nueva provincia
    }

    private void guardarNuevaCiudad() {
        String nombreNuevaCiudad = txtNombreCiudad.getText().trim();
        if (nombreNuevaCiudad.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre de la ciudad no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Object itemSeleccionado = comboProvincias.getSelectedItem();
        if (itemSeleccionado == null || itemSeleccionado.toString().equals("No hay provincias registradas")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar una provincia válida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String provinciaSeleccionada = itemSeleccionado.toString();
        provinceService.addCity(provinciaSeleccionada, nombreNuevaCiudad);
        JOptionPane.showMessageDialog(this, "Ciudad '" + nombreNuevaCiudad + "' guardada en " + provinciaSeleccionada);
        txtNombreCiudad.setText("");
    }

    private void buscarLocalidad() {
        String busqueda = searchField.getText().trim();
        if (busqueda.isEmpty()) {
            resultsArea.setText("Por favor, ingrese un término de búsqueda.");
            return;
        }
        Optional<Province> resultado = provinceService.findByName(busqueda);
        if (resultado.isPresent()) {
            resultsArea.setText("Búsqueda exitosa:\n\n" + resultado.get().toString());
        } else {
            resultsArea.setText("No se encontraron resultados para '" + busqueda + "'.");
        }
    }

    private void setupFormPanel(JPanel panel, String legend) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), legend));
    }

    private JLabel createLabel(String text, Color color, int size) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Arial", Font.PLAIN, size));
        lbl.setForeground(color);
        return lbl;
    }

    private JTextField createTextField(int columns) {
        JTextField field = new JTextField(columns);
        field.setBackground(Color.WHITE);
        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setBorder(new LineBorder(Color.BLACK));
        return field;
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(180, 35)); // Ancho ajustado
        return btn;
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatLightLaf());
        SwingUtilities.invokeLater(() -> {
            UIRegistrarLocalidadJoseph ui = new UIRegistrarLocalidadJoseph(new ProvinceServiceImpl(new ProvinceRepositoryImpl(HibernateUtil.getSessionFactory("hibernate-test.cfg.xml"))));
            ui.setVisible(true);
        });
    }
}

