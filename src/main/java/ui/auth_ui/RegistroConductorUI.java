package ui.auth_ui;

import com.formdev.flatlaf.FlatLightLaf;
import domain.entities.Car;
import domain.entities.Driver;
import domain.entities.License;
import domain.entities.UserEntity;
import domain.repository.impl.CabRepositoryImpl;
import domain.repository.impl.DriverRepositoryImpl;
import domain.repository.impl.RoleRepositoryImpl;
import domain.repository.impl.UserRepositoryImpl;
import domain.repository.interfaces.DriverRepository;
import lombok.extern.apachecommons.CommonsLog;
import service.impl.auth_module.SignUpDriverServiceImpl;
import service.interfaces.auth_module.ISignUpDriverService;
import shared.utils.HibernateUtil;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Alex
 */
@CommonsLog
public class RegistroConductorUI extends javax.swing.JFrame {
    private final ISignUpDriverService signUpDriverService;
    private final DriverRepository driverRepository;

    // Constructor de la ventana de registro
    public RegistroConductorUI(ISignUpDriverService signUpDriverService, DriverRepository driverRepository) {
        this.signUpDriverService = signUpDriverService;
        this.driverRepository = driverRepository;
        initComponents();
        setupListeners();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void setupListeners() {
        btnConfirmarRegistro.addActionListener(this::confirmarRegistroActionPerformed);
    }

    private void confirmarRegistroActionPerformed(ActionEvent evt) {
        try {
            // **1. Recopilar datos del formulario**
            String nombres = txtNombresConductor.getText();
            String apellidos = txtApellidosConductor.getText();
            String email = txtCorreoConductor.getText();
            String telefono = txtCelularConductor.getText();
            // Para la fecha de nacimiento
            int dia = (Integer) jSpinnerDia.getValue();
            int mesIndex = jComboBoxMes.getSelectedIndex();
            int mes = mesIndex + 1;
            int anio = (Integer) jSpinnerAnio.getValue();
            LocalDate fechaNacimiento = LocalDate.of(anio, mes, dia);

            String marcaVehiculo = txtMarcaVehiculo.getText();
            String modeloVehiculo = txtModeloVehiculo.getText();
            int anioVehiculo = Integer.parseInt(txtAnioVehiculo.getText());
            String matriculaVehiculo = txtMatricula.getText();

            String numeroLicencia = txtLicencia.getText();
            Character tipoLicencia = txtTipoLicencia.getText().charAt(0);

            var contrasena = new String(jPasswordFieldContrasenia.getPassword());
            String confirmarContrasena = new String(jPasswordFieldConfirmarContra.getPassword());

            //Realizar validaciones básicas de UI
            if (nombres.isEmpty() || apellidos.isEmpty() || email.isEmpty() || telefono.isEmpty() ||
                    marcaVehiculo.isEmpty() || modeloVehiculo.isEmpty() || matriculaVehiculo.isEmpty() ||
                    numeroLicencia.isEmpty() || tipoLicencia.toString().isEmpty() ||
                    contrasena.isEmpty() || confirmarContrasena.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error de Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (!contrasena.equals(confirmarContrasena)) {
                JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden.", "Error de Contraseña", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // **4. Crear las entidades**
            Driver driver = Driver.builder()
                    .userEntity(UserEntity.builder()
                            .names(nombres)
                            .lastNames(apellidos)
                            .email(email)
                            .phone(telefono)
                            .bornDate(fechaNacimiento)
                            .passwordHash(confirmarContrasena)
                            .createdBy("System")
                            .build())
                    .license(License.builder()
                            .driverLicense(numeroLicencia)
                            .licenseType(tipoLicencia)
                            .build())
                    .build();

            Car car = Car.builder()
                    .model(modeloVehiculo)
                    .year(String.valueOf(anioVehiculo))
                    .brand(marcaVehiculo)
                    .licensePlate(matriculaVehiculo)
                    .build();

            boolean registroExitoso = signUpDriverService.signUp(driver, car, driverRepository);

            if (registroExitoso) {
                JOptionPane.showMessageDialog(this, "Conductor registrado exitosamente.", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);
                clearFormFields();
            } else {
                JOptionPane.showMessageDialog(this, "Error: el email y telefono ya esta registrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese valores numéricos válidos para Año del Vehículo y Tipo de Licencia.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            log.error("Error de formato numérico: " + e.getMessage(), e); // Usa log de Lombok
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Error de registro: " + e.getMessage(), "Error de Datos", JOptionPane.WARNING_MESSAGE);
            log.warn("Error de registro (datos inválidos): " + e.getMessage(), e);
        } catch (IllegalStateException e) {
            JOptionPane.showMessageDialog(this, "Error de configuración del sistema: " + e.getMessage(), "Error Interno", JOptionPane.ERROR_MESSAGE);
            log.error("Error de configuración (rol no encontrado): " + e.getMessage(), e);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado al registrar el conductor: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            log.error("Error inesperado al registrar conductor, CONSULTE A DEPARTAMENTO TI ");
        }
    }
    
     private void clearFormFields() {
        txtNombresConductor.setText("");
        txtApellidosConductor.setText("");
        txtCorreoConductor.setText("");
        txtCelularConductor.setText("");
        jSpinnerDia.setValue(1); // 
        jComboBoxMes.setSelectedIndex(0); 
        jSpinnerAnio.setValue(1950); 
        txtMarcaVehiculo.setText("");
        txtModeloVehiculo.setText("");
        txtAnioVehiculo.setText("");
        txtMatricula.setText("");
        txtLicencia.setText("");
        txtTipoLicencia.setText("");
        jPasswordFieldContrasenia.setText("");
        jPasswordFieldConfirmarContra.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombresConductor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtApellidosConductor = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCorreoConductor = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtCelularConductor = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jSpinnerDia = new javax.swing.JSpinner();
        jComboBoxMes = new javax.swing.JComboBox<>();
        jSpinnerAnio = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtMarcaVehiculo = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtModeloVehiculo = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtAnioVehiculo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtMatricula = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtLicencia = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtTipoLicencia = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPasswordFieldContrasenia = new javax.swing.JPasswordField();
        jLabel19 = new javax.swing.JLabel();
        jPasswordFieldConfirmarContra = new javax.swing.JPasswordField();
        btnConfirmarRegistro = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setText("SharedTaxiManager");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel2.setText("Formulario del registro del conductor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 17;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(17, 1, 0, 0);
        jPanel1.add(jLabel2, gridBagConstraints);

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Información personal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 45, 0, 0);
        jPanel1.add(jLabel3, gridBagConstraints);

        txtNombresConductor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtNombresConductor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombresConductorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.ipadx = 135;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 45, 0, 0);
        jPanel1.add(txtNombresConductor, gridBagConstraints);

        jLabel4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel4.setText("Nombres completos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 45, 0, 0);
        jPanel1.add(jLabel4, gridBagConstraints);

        jLabel5.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel5.setText("Apellidos completos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 14, 0, 0);
        jPanel1.add(jLabel5, gridBagConstraints);

        txtApellidosConductor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtApellidosConductor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidosConductorActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 136;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 14, 0, 0);
        jPanel1.add(txtApellidosConductor, gridBagConstraints);

        jLabel6.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel6.setText("Correo electrónico");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 45, 0, 0);
        jPanel1.add(jLabel6, gridBagConstraints);

        txtCorreoConductor.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 8;
        gridBagConstraints.ipadx = 135;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 45, 0, 0);
        jPanel1.add(txtCorreoConductor, gridBagConstraints);

        jLabel7.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel7.setText("Celular");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 14, 0, 0);
        jPanel1.add(jLabel7, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 136;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 14, 0, 0);
        jPanel1.add(txtCelularConductor, gridBagConstraints);

        jLabel8.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel8.setText("Fecha de nacimiento");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 45, 0, 0);
        jPanel1.add(jLabel8, gridBagConstraints);

        jSpinnerDia.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jSpinnerDia.setModel(new javax.swing.SpinnerNumberModel(1, 1, 31, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.ipadx = -10;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 45, 0, 0);
        jPanel1.add(jSpinnerDia, gridBagConstraints);

        jComboBoxMes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jComboBoxMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.ipadx = 48;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 14, 0, 0);
        jPanel1.add(jComboBoxMes, gridBagConstraints);

        jSpinnerAnio.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jSpinnerAnio.setModel(new javax.swing.SpinnerNumberModel(1950, 1950, 2025, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.ipadx = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(15, 18, 0, 0);
        jPanel1.add(jSpinnerAnio, gridBagConstraints);

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("Detalles del vehiculo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 23;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 35, 0, 0);
        jPanel1.add(jLabel9, gridBagConstraints);

        jLabel10.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel10.setText("Marca");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 23;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 35, 0, 0);
        jPanel1.add(jLabel10, gridBagConstraints);

        txtMarcaVehiculo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 23;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 20;
        gridBagConstraints.ipadx = 136;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 35, 0, 0);
        jPanel1.add(txtMarcaVehiculo, gridBagConstraints);

        jLabel11.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel11.setText("Modelo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 43;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 37, 0, 0);
        jPanel1.add(jLabel11, gridBagConstraints);

        txtModeloVehiculo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 43;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 136;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 37, 0, 0);
        jPanel1.add(txtModeloVehiculo, gridBagConstraints);

        jLabel12.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel12.setText("Año");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 23;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 35, 0, 0);
        jPanel1.add(jLabel12, gridBagConstraints);

        txtAnioVehiculo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 23;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 20;
        gridBagConstraints.ipadx = 136;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 35, 0, 0);
        jPanel1.add(txtAnioVehiculo, gridBagConstraints);

        jLabel13.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel13.setText("Matrícula");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 43;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 37, 0, 0);
        jPanel1.add(jLabel13, gridBagConstraints);

        txtMatricula.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 43;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 136;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 37, 0, 0);
        jPanel1.add(txtMatricula, gridBagConstraints);

        jLabel14.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel14.setText("Licencia");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 45, 0, 0);
        jPanel1.add(jLabel14, gridBagConstraints);

        jLabel15.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel15.setText("Número de licencia");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 45, 0, 0);
        jPanel1.add(jLabel15, gridBagConstraints);

        txtLicencia.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.ipadx = 136;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 45, 0, 0);
        jPanel1.add(txtLicencia, gridBagConstraints);

        jLabel16.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel16.setText("Tipo de licencia");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 14, 0, 0);
        jPanel1.add(jLabel16, gridBagConstraints);

        txtTipoLicencia.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 13;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.ipadx = 136;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(8, 14, 0, 0);
        jPanel1.add(txtTipoLicencia, gridBagConstraints);

        jLabel17.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel17.setText("Configuración de la cuenta");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 23;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.gridwidth = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 35, 0, 0);
        jPanel1.add(jLabel17, gridBagConstraints);

        jLabel18.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel18.setText("Contraseña");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 23;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 35, 0, 0);
        jPanel1.add(jLabel18, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 23;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 20;
        gridBagConstraints.ipadx = 136;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 35, 0, 0);
        jPanel1.add(jPasswordFieldContrasenia, gridBagConstraints);

        jLabel19.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel19.setText("Confirmar contraseña");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 43;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 37, 0, 0);
        jPanel1.add(jLabel19, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 43;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.ipadx = 136;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(6, 37, 0, 0);
        jPanel1.add(jPasswordFieldConfirmarContra, gridBagConstraints);

        btnConfirmarRegistro.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnConfirmarRegistro.setText("Confirmar registro");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.ipadx = 160;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(18, 5, 0, 0);
        jPanel1.add(btnConfirmarRegistro, gridBagConstraints);

        jLabel20.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel20.setText("¿Ya tienes una cuenta?");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 17;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 46, 0, 0);
        jPanel1.add(jLabel20, gridBagConstraints);

        jButton1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton1.setText("Inicia sesión");
        jButton1.setBorder(null);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 23;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(12, 6, 0, 0);
        jPanel1.add(jButton1, gridBagConstraints);

        jButton2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton2.setText("Inicio");
        jButton2.setBorder(null);

        jButton3.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton3.setText("Contáctanos");
        jButton3.setBorder(null);

        jButton4.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jButton4.setText("Sobre nosotros");
        jButton4.setBorder(null);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(17, 17, 17))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombresConductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombresConductorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombresConductorActionPerformed

    private void txtApellidosConductorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidosConductorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosConductorActionPerformed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatLightLaf());

        java.awt.EventQueue.invokeLater(() -> new RegistroConductorUI(
                new SignUpDriverServiceImpl(
                        new UserRepositoryImpl(
                                HibernateUtil.getSessionFactory("hibernate-local.cfg.xml")),
                        new RoleRepositoryImpl(
                                HibernateUtil.getSessionFactory("hibernate-local.cfg.xml")),
                        new CabRepositoryImpl(
                                HibernateUtil.getSessionFactory("hibernate-local.cfg.xml"))),
                new DriverRepositoryImpl(HibernateUtil.getSessionFactory("hibernate-local.cfg.xml")))
                .setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmarRegistro;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBoxMes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField jPasswordFieldConfirmarContra;
    private javax.swing.JPasswordField jPasswordFieldContrasenia;
    private javax.swing.JSpinner jSpinnerAnio;
    private javax.swing.JSpinner jSpinnerDia;
    private javax.swing.JTextField txtAnioVehiculo;
    private javax.swing.JTextField txtApellidosConductor;
    private javax.swing.JTextField txtCelularConductor;
    private javax.swing.JTextField txtCorreoConductor;
    private javax.swing.JTextField txtLicencia;
    private javax.swing.JTextField txtMarcaVehiculo;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtModeloVehiculo;
    private javax.swing.JTextField txtNombresConductor;
    private javax.swing.JTextField txtTipoLicencia;
    // End of variables declaration//GEN-END:variables

}
