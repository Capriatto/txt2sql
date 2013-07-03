/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package packato;

import java.awt.Image;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author oky
 */
public final class VerRegistros extends javax.swing.JFrame {

    /**
     * Creates new form VerRegistros
     */
    public VerRegistros() throws SQLException {
        initComponents();
        this.setIconImage (new ImageIcon(getClass().getResource("/Icon/CD.png")).getImage ());
        setLocationRelativeTo(null);
        setResizable(false);
        conectar();
        
    }

    public void conectar() throws SQLException {
        Conectar cn=new Conectar();
        Statement st= cn.conexion().createStatement();
        ResultSet rs = null;
        String query="SELECT r.primerNombre, r.segundoNombre, r.primerApellido, r.segundoApellido, r.FechaNacimiento, entidad, sexo, curp FROM registros r";
        Object rowData[][] = {{"Row1-Column1", "Row1-Column2", "Row1-Column3"}};
        Object columnNames[] = {"Nombre1", "Nombre2", "Apellido1", "Apellido2", "Fecha Nac.", "Entidad", "Sexo", "Curp"};
         DefaultTableModel mTableModel = new DefaultTableModel(rowData, columnNames);
         tabla.setModel(mTableModel);
         mTableModel.removeRow(0);
         Object[] rows;
         rs= st.executeQuery(query);
         while (rs.next()) {
   rows = new Object[]{rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)};
       mTableModel.addRow(rows);
       TableColumn columnSexo=tabla.getColumn("Sexo");
       TableColumn columnEntidad=tabla.getColumn("Entidad");
       TableColumn columnFecha=tabla.getColumn("Fecha Nac.");
       TableColumn columnCurp=tabla.getColumn("Curp");
       TableColumn columnNombre1=tabla.getColumn("Nombre1");
       TableColumn columnNombre2=tabla.getColumn("Nombre2");
       TableColumn columnApellido1=tabla.getColumn("Apellido1");
       TableColumn columnApellido2=tabla.getColumn("Apellido2");
       tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
       columnSexo.setPreferredWidth(43);
       columnEntidad.setPreferredWidth(90);
       columnFecha.setPreferredWidth(100);
       columnCurp.setPreferredWidth(156);
       columnNombre1.setPreferredWidth(132);
       columnNombre2.setPreferredWidth(132);
       columnApellido1.setPreferredWidth(132);
       columnApellido2.setPreferredWidth(132);
    }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/onebit_29.png"))); // NOI18N
        jButton1.setText("Regresar a registro");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 963, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
 this.dispose();
 MainForm main= new MainForm();
 main.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
