/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication13;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.StringTokenizer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogOptions;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.FileChooser;

/**
 *
 * @author oky
 */
public class SampleController implements Initializable {
    
    ArrayList lineaTexto = new ArrayList();
    ArrayList tipoDato = new ArrayList();
    ArrayList separados = new ArrayList();//lineas sin delimitador
    String separador;
    String textoGenerado;
    Boolean estadoIf = false;
    StringBuffer stringbufer = new StringBuffer();
    int res;
    File file;
    @FXML // indica que se hace una referencia a los componentes del archivo FXML "Sample"
    public Label label;
    @FXML // indica que se hace una referencia a los componentes del archivo FXML "Sample"
    public TextField txtNombre;
    @FXML // indica que se hace una referencia a los componentes del archivo FXML "Sample"
    public Button buttonConvertir;
    @FXML // indica que se hace una referencia a los componentes del archivo FXML "Sample"
    public TextField txtApellidos;
    @FXML // indica que se hace una referencia a los componentes del archivo FXML "Sample"
    public Label lblpath;
    @FXML // indica que se hace una referencia a los componentes del archivo FXML "Sample"
    public CheckBox cbPeso;
    @FXML // indica que se hace una referencia a los componentes del archivo FXML "Sample"
    public CheckBox cbCantidad;
    @FXML // indica que se hace una referencia a los componentes del archivo FXML "Sample"
    public Label lblPeso;
    @FXML // indica que se hace una referencia a los componentes del archivo FXML "Sample"
    public Label lblCantidad;
    @FXML // indica que se hace una referencia a los componentes del archivo FXML "Sample"
    public String sCadena;
    @FXML // indica que se hace una referencia a los componentes del archivo FXML "Sample"
    ToggleGroup grupo;
    @FXML // indica que se hace una referencia a los componentes del archivo FXML "Sample"
    public TextField txtOtro;
    @FXML // indica que se hace una referencia a los componentes del archivo FXML "Sample"
    public Label lblSeparador;
    @FXML // indica que se hace una referencia a los componentes del archivo FXML "Sample"
    public RadioButton rbPuntoycoma;
    @FXML // indica que se hace una referencia a los componentes del archivo FXML "Sample"
    public RadioButton rbComa;
    @FXML // indica que se hace una referencia a los componentes del archivo FXML "Sample"
    public RadioButton rbBarras;
    @FXML // indica que se hace una referencia a los componentes del archivo FXML "Sample"
    FileReader fr;
    BufferedReader bf;
    String tipoTexto;
    String tipoNum;
    String tipoDate;
    String primeraLinea;
    int contador;
    int countTokens;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws FileNotFoundException {
        cbPeso.setSelected(false);
        lblPeso.setText("");
        cbCantidad.setSelected(false);
        lblCantidad.setText("");
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files y CSV files (*.txt, *.csv)", "*.txt", "*.csv");
        chooser.getExtensionFilters().add(extFilter);
        
        try {
            file = chooser.showOpenDialog(null);
            lblpath.setStyle("-fx-font-weight: normal;");
            lblpath.setText(file.getPath());
            cbPeso.setDisable(false);
            cbCantidad.setDisable(false);
            } catch (Exception e) {
            System.out.println("no se pudo leer un archivo o no fue cargado.");
            lblpath.setStyle("-fx-font-weight: bold; -fx-text-fill: #F27954;");
            lblpath.setText("No ha elegido un archivo, o está dañado.");
            cbPeso.setDisable(true);
            cbCantidad.setDisable(true);
        }
    }
    
    @FXML
    public void readingtxt() throws FileNotFoundException, IOException {
        fr = new FileReader(file);
        bf = new BufferedReader(fr);
        int longLinea = 0; //longitud de la línea del txt

        for (int i = 0; i < tipoDato.size(); i++) {
            String coincidencia = (String) tipoDato.get(i);
            int posicion = coincidencia.lastIndexOf("(");
            String impresion = coincidencia.substring(0, posicion);
            System.out.println("" + impresion);
        }
        
        stringbufer.append("INSERT INTO ".concat(txtNombre.getText().trim()));
        stringbufer.append(" ".concat("( )").concat(" VALUES").concat("\n"));
        while ((sCadena = bf.readLine()) != null) {
            lineaTexto.add(sCadena);
        }
        primeraLinea = lineaTexto.get(0).toString();
        StringTokenizer st = new StringTokenizer(primeraLinea, separador);
        while (st.hasMoreTokens()) {
            tipoDato.add(st.nextToken());
        }
        for (int i = 0; i < tipoDato.size(); i++) { // este ciclo imprime el contenido de el array tipoDato
            System.out.print("      " + tipoDato.get(i));
            ++contador;
        }
        System.out.print("\n");//Salto de línea
        for (int i = 1; i < lineaTexto.size(); i++) {
            StringTokenizer sto = new StringTokenizer(lineaTexto.get(i).toString(), separador);
            int line = (lineaTexto.size() - 1);
            
            for (int j = 0; j < contador; j++) { // imprimiendo todos los tokens de cada línea
                if (j == 0) {
                    stringbufer.append("(");
                }//cierrra for

                
                if (j != (contador - 1)) {
                    
                    stringbufer.append(sto.nextToken().concat(","));
                }//cierra if
                else if (i != line) {
                    stringbufer.append(sto.nextToken().concat("),"));
                }//cierra else
                else {
                    stringbufer.append(sto.nextToken().concat(");"));
                }
            }
            stringbufer.append("\r\n");
            
        }
        
        
        
        textoGenerado = stringbufer.toString();
        System.out.println("" + stringbufer);
    }
    
    public void checked(ActionEvent event) {
        
        try {
            if (cbPeso.isSelected()) {
                if (file.length() > 1000) {
                    double peso = file.length() / 1024.000;
                    DecimalFormat df = new DecimalFormat("#.##");
                    lblPeso.setText("" + df.format(peso) + " Kilobytes");
                } else {
                    double peso = file.length();
                    lblPeso.setText("" + peso + " Bytes");
                }
            } else {
                lblPeso.setText("");
            }
        } catch (Exception e) {
            System.out.println("No se pudo determinar el peso del archivo por Ausencia o Daño en el mismo.");
        }
        
    }
    
    public void numeroLineas(ActionEvent event) throws FileNotFoundException, IOException {
        if (cbCantidad.isSelected()) {
            fr = new FileReader(file);
            bf = new BufferedReader(fr);
            long lNumeroLineas = 0;
            
            while ((sCadena = bf.readLine()) != null) {
                lNumeroLineas++;
                System.out.println("" + sCadena);
            }
            lblCantidad.setText("" + lNumeroLineas + " líneas");
        } else {
            lblCantidad.setText("");
        }
    }
    
    public void exit(ActionEvent event) {
        DialogResponse response = Dialogs.showConfirmDialog(null, "¿Desea salir de la aplicación?",
                "SALIR", "Aviso importante", DialogOptions.OK_CANCEL);
        if (String.valueOf(response).equals("OK")) {
            System.exit(0);
        }
    }
    
    public void convert(ActionEvent event) throws FileNotFoundException, IOException {
        
        buttonConvertir.setDisable(true);
        Scanner s = new Scanner(file);
        
        
        if (txtOtro.getText().isEmpty() == false) {
            separador = txtOtro.getText().trim();
            lblSeparador.setStyle("-fx-font-weight: bold; -fx-text-fill: #1E70AB;");
            lblSeparador.setText("Se usará el que ingresó en el campo 'Otro'");
        } else if (grupo.getSelectedToggle().toString().equals("RadioButton[id=rbBarras, styleClass=radio-button]")) {
            separador = "||";
        } else if (grupo.getSelectedToggle().toString().equals("RadioButton[id=rbPuntoycoma, styleClass=radio-button]")) {
            separador = ";";
        } else if (grupo.getSelectedToggle().toString().equals("RadioButton[id=rbPuntoycoma, styleClass=radio-button]")) {
            separador = ";";
        } else if (grupo.getSelectedToggle().toString().equals("RadioButton[id=rbComa, styleClass=radio-button]")) {
            separador = ",";
        }
        
        System.out.println("al final el separador sql es: " + separador);
        
        readingtxt();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        System.out.println(stringbufer.toString());
        StringSelection strSel = new StringSelection(textoGenerado);
        clipboard.setContents(strSel, null);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
