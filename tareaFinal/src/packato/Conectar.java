/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oky
 */
package packato;

import java.sql.*;
import javax.swing.*;
/**
 *
 * @author Sony
 */

public class Conectar {
Connection conn= null;
   public Connection conexion()
    {
      try {
           Class.forName("org.gjt.mm.mysql.Driver");
           conn = DriverManager.getConnection("jdbc:mysql://localhost/tareafinal","root","");
           
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null,"Error "+e);
        }
        return conn;
     
}}