/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package packato;

import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author oky
 */
public final class MainForm extends javax.swing.JFrame {

    PreparedStatement ps;
    ResultSet rs;
    Conectar cc = new Conectar();
    Connection cn = cc.conexion();
    String sql;
    private ArrayList arrayEntidades;
    private String nombre1;
    private String nombre2;
    private String apellido1;
    private String apellido2;
    private String fechaN;
    private String entidad;
    private String claveEntidad;
    private String sexo;
    private String primerDígito;
    private String segundoDígito;
    private String tercerDígito;
    private String cuartoDígito;
    private String quintoDígito;
    private String sextoDígito;
    private String septimoDígito;
    private String octavoDígito;
    private String novenoDígito;
    private String decimoDígito;
    private String onceavoDígito;
    private String decimoSegundoDígito;
    private StringBuffer curpGenerado;

    /**
     * Creates new form MainForm
     */
    public MainForm() {
       initComponents();
        this.setIconImage (new ImageIcon(getClass().getResource("/Icon/CD.png")).getImage ());
        setLocationRelativeTo(null);
        setResizable(false);
        arrayEntidades = new ArrayList();
        dateFecha.setDate(new Date());
        llenarComboEntidad();
        curpGenerado = new StringBuffer();
        lblCurp.setVisible(false);
        lbltext.setVisible(false);
       
    }

    public void obtenerDatos() throws ParseException {
        Date fechaActual = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String actual = format.format(new Date());
        String escogida = format.format(dateFecha.getDate());

        if (fieldNombre1.getText().isEmpty() || fieldApellido1.getText().isEmpty() || actual.equals(escogida) || cboEntidad.getSelectedItem() == "--seleccione--") {
            JOptionPane.showMessageDialog(null, "Revise el formulario, faltan datos.", "Error", JOptionPane.ERROR_MESSAGE);

            if (actual.equals(escogida)) {
                lblAviso.setText("Escriba una fecha de nacimiento.");
            }

            if (cboEntidad.getSelectedItem() == "--seleccione--") {
                cboEntidad.setBackground(Color.pink);
            }
            if (fieldNombre1.getText().isEmpty()) {
                fieldNombre1.setBackground(Color.pink);
            }
            if (fieldApellido1.getText().isEmpty()) {
                fieldApellido1.setBackground(Color.pink);
            }

        } else { // abre else
            nombre1 = fieldNombre1.getText().trim();
            nombre2 = fieldNombre2.getText().trim();
            apellido1 = fieldApellido1.getText().trim();
            apellido2 = fieldApellido2.getText().trim();
            fechaN = format.format(dateFecha.getDate());
            entidad = cboEntidad.getSelectedItem().toString();
            if (radioF.isSelected()) {
                sexo = "F";
            } else {
                sexo = "M";
            }
            lblAviso.setText("");
            generarCurp();
            registrarDatos();
        }// cierra else
    }

    public void llenarComboEntidad() {
        cboEntidad.removeAllItems();
        arrayEntidades.add("--seleccione--");
        arrayEntidades.add("Aguascalientes");
        arrayEntidades.add("Baja California Norte");
        arrayEntidades.add("Baja California Sur");
        arrayEntidades.add("Campeche");
        arrayEntidades.add("Coahuila");
        arrayEntidades.add("Chiapas");
        arrayEntidades.add("Chihuahua");
        arrayEntidades.add("Durango");
        arrayEntidades.add("Estado de México");
        arrayEntidades.add("Guanajuato");
        arrayEntidades.add("Guerrero");
        arrayEntidades.add("Hidalgo");
        arrayEntidades.add("Jalisco");
        arrayEntidades.add("Michoacán");
        arrayEntidades.add("Morelos");
        arrayEntidades.add("México, D.F.");
        arrayEntidades.add("Nayarit");
        arrayEntidades.add("Nuevo León");
        arrayEntidades.add("Oxaca");
        arrayEntidades.add("Puebla");
        arrayEntidades.add("Querétaro");
        arrayEntidades.add("Quintana Roo");
        arrayEntidades.add("San Luis Potosí");
        arrayEntidades.add("Sinaloa");
        arrayEntidades.add("Sonora");
        arrayEntidades.add("Tabasco");
        arrayEntidades.add("Tamaulipas");
        arrayEntidades.add("Taxcala");
        arrayEntidades.add("Veracruz");
        arrayEntidades.add("Yucatán");
        arrayEntidades.add("Zacatecas");

        for (int i = 0; i < arrayEntidades.size(); i++) {
            cboEntidad.addItem(arrayEntidades.get(i));
        }
    }

    public void generarCurp() {
        primerDígito = String.valueOf(apellido1.charAt(0));
        curpGenerado.append(primerDígito);
        System.out.println("primerDígito = " + primerDígito);
        int contador = 0;
        int x;
        for (x = 0; x < apellido1.length(); x++) {
            if ((apellido1.charAt(x) == 'A') || (apellido1.charAt(x) == 'E') || (apellido1.charAt(x) == 'I') || (apellido1.charAt(x) == 'O') || (apellido1.charAt(x) == 'U')) {
                contador++;
            }
            if (contador == 1) {
                int posición = x;
                segundoDígito = apellido1.substring(posición, posición + 1);
                curpGenerado.append(segundoDígito);
                System.out.println("segundoDígito = " + segundoDígito);
                contador++;
            }
        }
        if (fieldApellido2.getText().trim().isEmpty()) {
            tercerDígito = "X";
            curpGenerado.append(tercerDígito);
            System.out.println("TercerDígito  = " + tercerDígito);
        } else {
            tercerDígito = String.valueOf(apellido2.charAt(0));
            curpGenerado.append(tercerDígito);
            System.out.println("TercerDígito  = " + tercerDígito);
        }
        if (nombre1.equals("MARÍA") || nombre1.equals("JOSÉ") || nombre1.equals("JOSE") || nombre1.equals("MARIA")) {
            cuartoDígito = String.valueOf(nombre2.charAt(0));
            curpGenerado.append(cuartoDígito);
            System.out.println("cuartoDígito = " + cuartoDígito);
        } else {
            cuartoDígito = String.valueOf(nombre1.charAt(0));
            curpGenerado.append(cuartoDígito);
            System.out.println("cuartoDígito = " + cuartoDígito);
        }
        SimpleDateFormat dateF = new SimpleDateFormat("yyMMdd");
        quintoDígito = dateF.format(dateFecha.getDate());
        curpGenerado.append(quintoDígito);
        System.out.println("quinto = " + quintoDígito);
        sextoDígito = sexo;
        curpGenerado.append(sextoDígito);
        System.out.println("sextoDígito = " + sextoDígito);
        claveEntidadesFederativas();
        septimoDígito = claveEntidad;
        curpGenerado.append(septimoDígito);
        System.out.println("septimoDígito = " + septimoDígito);
        int contadorConsonantesApellido1 = 0;
        int apellido1Length = fieldApellido1.getText().trim().length();
        for (int q = 1; q < apellido1.length(); q++) {
            if ((apellido1.charAt(q) != 'A') && (apellido1.charAt(q) != 'E') && (apellido1.charAt(q) != 'I') && (apellido1.charAt(q) != 'O') && (apellido1.charAt(q) != 'U')) {
                contadorConsonantesApellido1++;
                if (q + 1 != apellido1Length && contadorConsonantesApellido1 == 1) {
                    octavoDígito = String.valueOf(apellido1.charAt(q));
                }
            }
        }
        int contadorConsonantesApellido2 = 0;
        int apellido2Length = fieldApellido2.getText().trim().length();
        for (int r = 0; r < apellido2.length(); r++) {
            if ((apellido2.charAt(r) != 'A') && (apellido2.charAt(r) != 'E') && (apellido2.charAt(r) != 'I') && (apellido2.charAt(r) != 'O') && (apellido2.charAt(r) != 'U')) {
                contadorConsonantesApellido2++;
                if (r + 1 != apellido2Length && contadorConsonantesApellido2 == 1) {
                    novenoDígito = String.valueOf(apellido2.charAt(r));
                }
            }
        }
        int contadorConsonantesNombre = 0;
        int nombre1Length = fieldNombre1.getText().trim().length();
        for (int p = 1; p < nombre1.length(); p++) {

            if ((nombre1.charAt(p) != 'A') && (nombre1.charAt(p) != 'E') && (nombre1.charAt(p) != 'I') && (nombre1.charAt(p) != 'O') && (nombre1.charAt(p) != 'U')) {
                contadorConsonantesNombre++;
                if (p + 1 != nombre1Length && contadorConsonantesNombre == 1) {
                    decimoDígito = String.valueOf(nombre1.charAt(p));
                }
            }
        }
        if (decimoDígito == null) {
            decimoDígito = String.valueOf("X");
        }
        System.out.println("INICIALES = " + octavoDígito + novenoDígito + decimoDígito);
        curpGenerado.append(octavoDígito.concat(novenoDígito).concat(decimoDígito));
        onceavoDígito = "0";
        curpGenerado.append(onceavoDígito);
        int digitoVerificador = nombre1.length() + nombre2.length() + apellido1.length();
        if (digitoVerificador >= 30) {
            digitoVerificador -= 25;
        } else if (digitoVerificador >= 25) {
            digitoVerificador -= 20;
        } else if (digitoVerificador >= 20) {
            digitoVerificador -= 15;
        } else if (digitoVerificador >= 15) {
            digitoVerificador -= 10;
        } else if (digitoVerificador >= 10) {
            digitoVerificador -= 5;
        }
        decimoSegundoDígito = String.valueOf(digitoVerificador);
        curpGenerado.append(decimoSegundoDígito);
        System.out.println("DecimoSegundoDígito = " + decimoSegundoDígito);
        System.out.println("curp = " + curpGenerado);
        lblCurp.setText("" + curpGenerado);
        lblCurp.setVisible(true);
        lbltext.setVisible(true);


    }

    public void registrarDatos() {
        sql = "INSERT INTO registros (primerNombre, segundoNombre, primerApellido, segundoApellido, fechaNacimiento, entidad, sexo, curp) VALUES (?,?,?,?,?,?,?,?) ";
        try {
            ps = cn.prepareStatement(sql);
            ps.setString(1, nombre1);
            ps.setString(2, nombre2);
            ps.setString(3, apellido1);
            ps.setString(4, apellido2);
            ps.setString(5, fechaN);
            ps.setString(6, entidad);
            ps.setString(7, sexo);
            ps.setString(8, String.valueOf(curpGenerado));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro almacenado.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            curpGenerado.delete(0, curpGenerado.length());
        } catch (SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "No se pudo almacenar el registro." + "\n" + e, "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println("" + e);
        }

    }

    public void claveEntidadesFederativas() {
        if (entidad.equals("Aguascalientes")) {
            claveEntidad = "AS";
        }
        if (entidad.equals("Baja California Norte")) {
            claveEntidad = "BC";
        }
        if (entidad.equals("Baja California Sur")) {
            claveEntidad = "BS";
        }
        if (entidad.equals("Campeche")) {
            claveEntidad = "CC";
        }
        if (entidad.equals("Chiapas")) {
            claveEntidad = "CS";
        }
        if (entidad.equals("Chihuahua")) {
            claveEntidad = "CH";
        }
        if (entidad.equals("Coahuila")) {
            claveEntidad = "CL";
        }
        if (entidad.equals("Colima")) {
            claveEntidad = "CL";
        }
        if (entidad.equals("México, D.F.")) {
            claveEntidad = "DF";
        }
        if (entidad.equals("Durango")) {
            claveEntidad = "DG";
        }
        if (entidad.equals("Guanajuato")) {
            claveEntidad = "GT";
        }
        if (entidad.equals("Guerrero")) {
            claveEntidad = "GR";
        }
        if (entidad.equals("Hidalgo")) {
            claveEntidad = "HG";
        }
        if (entidad.equals("Jalisco")) {
            claveEntidad = "JC";
        }
        if (entidad.equals("Estado de México")) {
            claveEntidad = "MC";
        }
        if (entidad.equals("Michoacán")) {
            claveEntidad = "MN";
        }
        if (entidad.equals("Morelos")) {
            claveEntidad = "MS";
        }
        if (entidad.equals("Nayarit")) {
            claveEntidad = "NT";
        }
        if (entidad.equals("Nuevo León")) {
            claveEntidad = "NL";
        }
        if (entidad.equals("Oaxaca")) {
            claveEntidad = "OC";
        }
        if (entidad.equals("Puebla")) {
            claveEntidad = "PL";
        }
        if (entidad.equals("Querétaro")) {
            claveEntidad = "QT";
        }
        if (entidad.equals("Quintana Roo")) {
            claveEntidad = "BS";
        }
        if (entidad.equals("San Luis Potosí")) {
            claveEntidad = "SP";
        }
        if (entidad.equals("Sinaloa")) {
            claveEntidad = "SL";
        }
        if (entidad.equals("Sonora")) {
            claveEntidad = "SR";
        }
        if (entidad.equals("Tabasco")) {
            claveEntidad = "TC";
        }
        if (entidad.equals("Tlaxcala")) {
            claveEntidad = "TL";
        }
        if (entidad.equals("Tamaulipas")) {
            claveEntidad = "TS";
        }
        if (entidad.equals("Veracruz")) {
            claveEntidad = "VZ";
        }
        if (entidad.equals("Yucatán")) {
            claveEntidad = "YN";
        }
        if (entidad.equals("Zacatecas")) {
            claveEntidad = "ZS";

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoRadios = new javax.swing.ButtonGroup();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        botonRegistrar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dateFecha = new org.jdesktop.swingx.JXDatePicker();
        cboEntidad = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        radioM = new javax.swing.JRadioButton();
        radioF = new javax.swing.JRadioButton();
        fieldNombre1 = new javax.swing.JTextField();
        fieldApellido1 = new javax.swing.JTextField();
        fieldApellido2 = new javax.swing.JTextField();
        botonVisualizar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        fieldNombre2 = new javax.swing.JTextField();
        lblAviso = new javax.swing.JLabel();
        lblCurp = new javax.swing.JLabel();
        lbltext = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Primer nombre:");

        botonRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/onebit_20.png"))); // NOI18N
        botonRegistrar.setText("Registrar");
        botonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarActionPerformed(evt);
            }
        });

        jLabel3.setText("Primer apellido:");

        jLabel4.setText("Segundo apellido:");

        jLabel5.setText("Nacimiento:");

        dateFecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dateFechaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dateFechaMouseEntered(evt);
            }
        });
        dateFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateFechaActionPerformed(evt);
            }
        });

        cboEntidad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboEntidad.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboEntidadItemStateChanged(evt);
            }
        });
        cboEntidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEntidadActionPerformed(evt);
            }
        });

        jLabel6.setText("Entidad:");

        jLabel7.setText("Sexo:");

        grupoRadios.add(radioM);
        radioM.setSelected(true);
        radioM.setText("Masculino");

        grupoRadios.add(radioF);
        radioF.setText("Femenino");

        fieldNombre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fieldNombre1ActionPerformed(evt);
            }
        });
        fieldNombre1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldNombre1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldNombre1FocusLost(evt);
            }
        });
        fieldNombre1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldNombre1KeyTyped(evt);
            }
        });

        fieldApellido1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldApellido1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldApellido1FocusLost(evt);
            }
        });
        fieldApellido1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldApellido1KeyTyped(evt);
            }
        });

        fieldApellido2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldApellido2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldApellido2FocusLost(evt);
            }
        });
        fieldApellido2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldApellido2KeyTyped(evt);
            }
        });

        botonVisualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/onebit_39-20px.png"))); // NOI18N
        botonVisualizar.setText("Visualizar CURP's registrados");
        botonVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVisualizarActionPerformed(evt);
            }
        });

        jLabel2.setText("Segundo nombre:");

        fieldNombre2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldNombre2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldNombre2FocusLost(evt);
            }
        });
        fieldNombre2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldNombre2KeyTyped(evt);
            }
        });

        lblAviso.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblAviso.setForeground(new java.awt.Color(255, 51, 51));

        lblCurp.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCurp.setForeground(new java.awt.Color(0, 102, 153));
        lblCurp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCurp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lbltext.setText("Curp:");

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/Sin título-1.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(lblAviso)
                        .addGap(5, 5, 5)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(fieldNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(fieldNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(radioM)
                        .addGap(18, 18, 18)
                        .addComponent(radioF))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cboEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(fieldApellido1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(fieldApellido2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(84, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator1)
                        .addGap(42, 42, 42))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbltext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblCurp, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(botonRegistrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(lblAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1))
                    .addComponent(fieldNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(fieldNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldApellido2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(fieldApellido1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(dateFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cboEntidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel7))
                    .addComponent(radioM)
                    .addComponent(radioF))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbltext, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCurp, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(botonRegistrar)
                        .addComponent(botonVisualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 41, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarActionPerformed
        try {
            obtenerDatos();
        } catch (ParseException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_botonRegistrarActionPerformed

    private void fieldNombre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fieldNombre1ActionPerformed
    }//GEN-LAST:event_fieldNombre1ActionPerformed

    private void dateFechaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateFechaMouseClicked
    }//GEN-LAST:event_dateFechaMouseClicked

    private void dateFechaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateFechaMouseEntered
    }//GEN-LAST:event_dateFechaMouseEntered

    private void cboEntidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEntidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboEntidadActionPerformed

    private void cboEntidadItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboEntidadItemStateChanged
        cboEntidad.setBackground(Color.WHITE);        // TODO add your handling code here:
    }//GEN-LAST:event_cboEntidadItemStateChanged

    private void dateFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateFechaActionPerformed

        Date fechaActual = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String actual = format.format(new Date());
        String escogida = format.format(dateFecha.getDate());
        if (!actual.equals(escogida)) {
            lblAviso.setText("");
        }
    }//GEN-LAST:event_dateFechaActionPerformed

    private void fieldNombre1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldNombre1FocusGained
        fieldNombre1.setBackground(Color.white);
    }//GEN-LAST:event_fieldNombre1FocusGained

    private void fieldNombre2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldNombre2FocusGained
        fieldNombre2.setBackground(Color.white);          // TODO add your handling code here:
    }//GEN-LAST:event_fieldNombre2FocusGained

    private void fieldApellido1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldApellido1FocusGained
        fieldApellido1.setBackground(Color.white);          // TODO add your handling code here:
    }//GEN-LAST:event_fieldApellido1FocusGained

    private void fieldApellido2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldApellido2FocusGained
        fieldApellido2.setBackground(Color.white);          // TODO add your handling code here:
    }//GEN-LAST:event_fieldApellido2FocusGained

    private void fieldNombre1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldNombre1FocusLost
        fieldNombre1.setText(fieldNombre1.getText().toUpperCase());
    }//GEN-LAST:event_fieldNombre1FocusLost

    private void fieldNombre2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldNombre2FocusLost
        fieldNombre2.setText(fieldNombre2.getText().toUpperCase());
    }//GEN-LAST:event_fieldNombre2FocusLost

    private void fieldApellido1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldApellido1FocusLost
        fieldApellido1.setText(fieldApellido1.getText().toUpperCase());
    }//GEN-LAST:event_fieldApellido1FocusLost

    private void fieldApellido2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldApellido2FocusLost
        fieldApellido2.setText(fieldApellido2.getText().toUpperCase());
    }//GEN-LAST:event_fieldApellido2FocusLost

    private void botonVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVisualizarActionPerformed
        try {
            this.dispose();
              VerRegistros vr= new VerRegistros();
             vr.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonVisualizarActionPerformed

    private void fieldNombre1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldNombre1KeyTyped
  char car = evt.getKeyChar();

        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z')
                && car != 'á' //Minúsculas             
                && car != 'é'
                && car != 'í'
                && car != 'ó'
                && car != 'ú'
                && car != 'Á' //Mayúsculas             
                && car != 'É'
                && car != 'Í'
                && car != 'Ó'
                && car != 'Ú'
                && (car != (char) KeyEvent.VK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_fieldNombre1KeyTyped

    private void fieldNombre2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldNombre2KeyTyped
char car = evt.getKeyChar();

        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z')
                && car != 'á' //Minúsculas             
                && car != 'é'
                && car != 'í'
                && car != 'ó'
                && car != 'ú'
                && car != 'Á' //Mayúsculas             
                && car != 'É'
                && car != 'Í'
                && car != 'Ó'
                && car != 'Ú'
                && (car != (char) KeyEvent.VK_SPACE)) {
            evt.consume();
        }        // TODO add your handling code here:
    }//GEN-LAST:event_fieldNombre2KeyTyped

    private void fieldApellido1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldApellido1KeyTyped
     char car = evt.getKeyChar();

        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z')
                && car != 'á' //Minúsculas             
                && car != 'é'
                && car != 'í'
                && car != 'ó'
                && car != 'ú'
                && car != 'Á' //Mayúsculas             
                && car != 'É'
                && car != 'Í'
                && car != 'Ó'
                && car != 'Ú'
                && (car != (char) KeyEvent.VK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_fieldApellido1KeyTyped

    private void fieldApellido2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldApellido2KeyTyped
     char car = evt.getKeyChar();

        if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z')
                && car != 'á' //Minúsculas             
                && car != 'é'
                && car != 'í'
                && car != 'ó'
                && car != 'ú'
                && car != 'Á' //Mayúsculas             
                && car != 'É'
                && car != 'Í'
                && car != 'Ó'
                && car != 'Ú'
                && (car != (char) KeyEvent.VK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_fieldApellido2KeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
     try {
          
            UIManager.setLookAndFeel(new NimRODLookAndFeel());
            NimRODLookAndFeel nf = new NimRODLookAndFeel();
            String folder = System.getProperty("user.dir");
            String separator = System.getProperty("file.separator");
            NimRODTheme nt = new NimRODTheme(folder+ separator + "finalTheme.theme");
            NimRODLookAndFeel.setCurrentTheme(nt);
            UIManager.setLookAndFeel(nf);
            } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Error de " + ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
             new MainForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonRegistrar;
    private javax.swing.JButton botonVisualizar;
    private javax.swing.JComboBox cboEntidad;
    private org.jdesktop.swingx.JXDatePicker dateFecha;
    private javax.swing.JTextField fieldApellido1;
    private javax.swing.JTextField fieldApellido2;
    private javax.swing.JTextField fieldNombre1;
    private javax.swing.JTextField fieldNombre2;
    private javax.swing.ButtonGroup grupoRadios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAviso;
    private javax.swing.JLabel lblCurp;
    private javax.swing.JLabel lbltext;
    private javax.swing.JRadioButton radioF;
    private javax.swing.JRadioButton radioM;
    // End of variables declaration//GEN-END:variables
}
