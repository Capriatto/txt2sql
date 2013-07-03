/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package packato;
import java.sql.*;
import javax.swing.*;
/**
 *
 * @author oky
 */
public class ConecciónDB {
    Connection conn= null;
   public Connection conexion()
    {
      try {
             
           //Cargamos el Driver MySQL
           Class.forName("org.gjt.mm.mysql.Driver");
           conn = DriverManager.getConnection("jdbc:mysql://localhost/bd","root",""); // DONDE DICE BD VA EL NOMBRE DE TU BASE DE DATOS, DONDE DICE ROOT Y "" ES TU NOMBR DE USUARIO Y TU CONTRASEÑA.
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error "+e);
        }
        return conn;
     
}
}
