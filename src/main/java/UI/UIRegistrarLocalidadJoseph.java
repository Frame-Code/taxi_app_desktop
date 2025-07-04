package UI;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
// Esto es para que el código se pueda ejecutar. Deberías tener estas clases en tu proyecto.
interface IProvinceService {
    List<Province> getProvinces();
    Province save(Province province);
    Optional<Province> findByName(String name);
    Optional<Province> addCity(String provinceName, String cityName);
}

class Province {
    private String name;
    private List<String> cities = new ArrayList<>();

    public Province(String name) { this.name = name; }
    public String getName() { return name; }
    public void addCity(String cityName) { this.cities.add(cityName); }
    @Override public String toString() { return "Province{name='" + name + "', cities=" + cities + '}'; }
}


public class UIRegistrarLocalidadJoseph extends JFrame {

    // -> PASO 1: DECLARAR COMPONENTES COMO VARIABLES DE INSTANCIA
    // Para poder acceder a ellos desde los métodos de lógica.
    private final IProvinceService provinceService;
    private JTextField txtNombreProvincia;
    private JButton btnGuardarProvincia;
    private JTextField txtNombreCiudad;
    private JComboBox<String> comboProvincias;
    private JButton btnGuardarCiudad;
    private JTextField searchField;
    private JButton searchBtn;
    private JTextArea resultsArea;


    // Colors
    private static final Color FORM_BACKGROUND = Color.decode("#DCDCDC");
    private static final Color TEXT_PRIMARY = Color.decode("#000000");
    // ... otros colores

    // -> PASO 2: MODIFICAR EL CONSTRUCTOR
    // Ahora recibe el servicio como parámetro.
    public UIRegistrarLocalidadJoseph(IProvinceService provinceService) {
        // -> Guardamos la instancia del servicio
        this.provinceService = provinceService;

        setTitle("Registrar Localidades");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- Header (Sin cambios) ---
        JPanel header = new JPanel(new BorderLayout());
        header.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        // ... código del header ...
        add(header, BorderLayout.NORTH);

        // --- Main Content ---
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(FORM_BACKGROUND);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("REGISTRAR LOCALIDADES");
        title.setFont(new Font("Arial Black", Font.BOLD, 32));
        title.setForeground(TEXT_PRIMARY);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel formsContainer = new JPanel(new GridLayout(1, 2, 20, 0));
        formsContainer.setBackground(FORM_BACKGROUND);

        // -> Se ha refactorizado la creación de los formularios
        // para asignar los componentes a las variables de instancia.

        // --- Panel de Ciudad ---
        JPanel cityPanel = new JPanel();
        setupFormPanel(cityPanel, "Ciudad");
        cityPanel.add(createLabel("Nombre:", TEXT_PRIMARY, 14));
        txtNombreCiudad = createTextField(20); // Asignación
        cityPanel.add(txtNombreCiudad);
        cityPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        cityPanel.add(createLabel("Provincia:", TEXT_PRIMARY, 14));
        comboProvincias = new JComboBox<>(); // Asignación
        cityPanel.add(comboProvincias);
        cityPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        btnGuardarCiudad = createButton("GUARDAR CIUDAD"); // Asignación
        cityPanel.add(btnGuardarCiudad);
        formsContainer.add(cityPanel);

        // --- Panel de Provincia ---
        JPanel provPanel = new JPanel();
        setupFormPanel(provPanel, "Provincia");
        provPanel.add(createLabel("Nombre:", TEXT_PRIMARY, 14));
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
        searchPanel.setBackground(FORM_BACKGROUND);
        searchPanel.add(createLabel("Buscar:", TEXT_PRIMARY, 16));
        searchField = createTextField(30); // Asignación
        searchPanel.add(searchField);
        searchBtn = createButton("BUSCAR"); // Asignación y cambio de texto
        searchPanel.add(searchBtn);
        mainPanel.add(searchPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // --- Área de Resultados ---
        resultsArea = new JTextArea(8, 80); // Asignación
        resultsArea.setBackground(Color.WHITE);
        resultsArea.setBorder(new LineBorder(Color.BLACK));
        resultsArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(resultsArea);
        mainPanel.add(scroll);

        add(mainPanel, BorderLayout.CENTER);

        // -> PASO 3: INICIALIZAR LA LÓGICA
        inicializarListeners();
        cargarProvinciasEnComboBox();
    }

    // -> PASO 4: IMPLEMENTAR LOS MÉTODOS DE LÓGICA

    /**
     * Carga las provincias desde el servicio y las muestra en el JComboBox.
     */
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
        provinceService.save(new Province(nombreNuevaProvincia));
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


    // --- Métodos de ayuda para crear componentes (refactorizados) ---

    private void setupFormPanel(JPanel panel, String legend) {
        panel.setBackground(FORM_BACKGROUND);
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
        btn.setBackground(Color.decode("#2B2B2B"));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setPreferredSize(new Dimension(180, 35)); // Ancho ajustado
        return btn;
    }

    // -> PASO 5: ACTUALIZAR EL MÉTODO MAIN PARA PRUEBAS
    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        // Se crea un servicio "falso" para poder probar la ventana.
        // En tu aplicación real, obtendrás este servicio de otra parte.
        IProvinceService servicioDePrueba = new IProvinceService() {
            private final List<Province> provinciasDB = new ArrayList<>();
            {
                // Datos iniciales para probar
                Province p1 = new Province("Guayas");
                p1.addCity("Guayaquil");
                p1.addCity("Duran");
                provinciasDB.add(p1);
                provinciasDB.add(new Province("Pichincha"));
            }
            @Override public List<Province> getProvinces() { return new ArrayList<>(provinciasDB); }
            @Override public Province save(Province province) {
                provinciasDB.add(province);
                System.out.println("Guardando: " + province);
                return province;
            }
            @Override public Optional<Province> findByName(String name) {
                return provinciasDB.stream().filter(p -> p.getName().equalsIgnoreCase(name)).findFirst();
            }
            @Override public Optional<Province> addCity(String provinceName, String cityName) {
                Optional<Province> p = findByName(provinceName);
                p.ifPresent(prov -> prov.addCity(cityName));
                System.out.println("Agregando '" + cityName + "' a '" + provinceName + "'");
                return p;
            }
        };

        UIManager.setLookAndFeel(new FlatDarkLaf());
        SwingUtilities.invokeLater(() -> {
            // Se le pasa el servicio de prueba al crear la UI
            UIRegistrarLocalidadJoseph ui = new UIRegistrarLocalidadJoseph(servicioDePrueba);
            ui.setVisible(true);
        });
    }
}

