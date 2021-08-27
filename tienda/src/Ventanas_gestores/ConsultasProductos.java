package Ventanas_gestores;

import Conexion_BBDD.conectar;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 * @author DIEGOT 
 * AQUI SE PODRAN HACER LAS BUSQUEDAS DE TODOS LOS PRODUCTPS QUE
 * VAMOS A TENER DE VARIOS MODOS SELECCIONADOS POR RADIO BUTON BUSQUEDA POR
 *      DESCRIPCION DEL PRODUCTO (igualdad de texto) 
 *      MOSTRAR DIRECTAMENTE TODAS LOS PRODUCTOS 
 */
public class ConsultasProductos extends javax.swing.JInternalFrame {
    //CREACION DE UN NUEVO FORMULARIO PARA LA CONSULTA DE LOS PRODUCTOS 
    public ConsultasProductos() {
        initComponents();
        mostrar_todos_productos();//LLAMADA A MOSTRAR PRODUCTOS 
    }

    void mostrar_todos_productos() {
        DefaultTableModel tabla= new DefaultTableModel();//MODELO POR DEFECTO DE LA TABLA 
        String []titulos={"CODIGO","DESCRIPCION","PRECIO"};//TITULOS DE LAS COLUMNAS 
        tabla.setColumnIdentifiers(titulos);//PONERLE A CADA COLUMNA UN IDENTIFICADOR 
        this.tbproductos.setModel(tabla);//DATOS DEL MODELO PRODUCTOS PASADO A LA TABLA 
        String consulta= "SELECT * FROM producto";//CREACION DE LA SENTENCIA SQL 
        String []Datos= new String [3];//COLUMNAS A MOSTRAR
        try {
            Statement st = cn.createStatement();//CREAR LA SENTENCIA SQL 
            ResultSet rs= st.executeQuery(consulta);//REALIZAR LA CONSULTA SQL 
            while(rs.next()){//MOSTRAR LOS DATOS EN LAS COLUMNAS, UNA VEZ FINALIZADA UNA, PASA A OTRA 
                Datos[0]=rs.getString("cod_pro");
                Datos[1]=rs.getString("descripcion");
                Datos[2]=rs.getString("precio");
                tabla.addRow(Datos);//METER TODOS LOS MODELOS EN LAS COLUMNAS 
            }
        } catch (SQLException ex) {Logger.getLogger(ConsultasProductos.class.getName()).log(Level.SEVERE, null, ex);}//MENSAJE ERROR 
    }
// Este método se llama desde el constructor para inicializar el formulario
//ADVERTENCIA: NO modificar este código.generado por el Editor de formularios
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rbtndes = new javax.swing.JRadioButton();
        rbtntodo = new javax.swing.JRadioButton();
        txtdes = new javax.swing.JTextField();
        btnbuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbproductos = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("CONSULTA DE PRODUCTOS");

        buttonGroup1.add(rbtndes);
        rbtndes.setSelected(true);
        rbtndes.setText("Mostrar Productos por Descripcion");
        rbtndes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtndesActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtntodo);
        rbtntodo.setText("Mostrar todos los Productos");
        rbtntodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtntodoActionPerformed(evt);
            }
        });

        btnbuscar.setText("Buscar");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbtndes)
                        .addGap(18, 18, 18)
                        .addComponent(txtdes, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnbuscar))
                    .addComponent(rbtntodo))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtndes)
                    .addComponent(txtdes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtntodo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbproductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tbproductos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(24, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void rbtntodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtntodoActionPerformed
//CONTROL DE ACCION DEL RADIO BURTON PARA BUSCAR TODO 
    if (rbtntodo.isSelected() == true) {//SI SE MARCA ESTA OPCION
        txtdes.setText("");//TEXTO DE DESCRIPCION VACIO 
        txtdes.setEnabled(false);//SE BLOQUEA PARA EVITAR ERRORES 
        mostrar_todos_productos();//SE MUESTRAN TODOS LOS PRODUCTOS 
    }
}//GEN-LAST:event_rbtntodoActionPerformed

private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
//CONTROL DE SELECCION DEL RADIO BUTON DE BUSCAR POR DESCRIPCION
    String buscar=txtdes.getText();//OBTENER LA DESCRIPCION ESCRITA PARA COMPARAR 
    if(rbtndes.isSelected()==true){//SI LA OPCION ESTA MARCADA 
        DefaultTableModel tabla= new DefaultTableModel();//MODELO POR DEFECTO DE LA TABLA 
        String []titulos={"CODIGO","DESCRIPCION","PRECIO"};
        tabla.setColumnIdentifiers(titulos);//PONERLE A CADA COLUMNA UN IDENTIFICADOR 
        this.tbproductos.setModel(tabla);//DATOS DEL MODELO PRODUCTOS PASADO A LA TABLA 
        String consulta= "SELECT * FROM producto WHERE descripcion  LIKE '%"+buscar+"%'";//SENTENCIA SQL PARA BUSCAR POR IGUALDAD 
        String []Datos= new String [3];//COLUMNAS A MOSTRAR
        try {
            Statement st = cn.createStatement();//CREAR LA SENTENCIA SQL 
            ResultSet rs= st.executeQuery(consulta);//REALIZAR LA CONSULTA SQL 
            while(rs.next()){//MOSTRAR LOS DATOS EN LAS COLUMNAS, UNA VEZ FINALIZADA UNA, PASA A OTRA 
                Datos[0]=rs.getString("cod_pro");
                Datos[1]=rs.getString("descripcion");
                Datos[2]=rs.getString("precio");
                tabla.addRow(Datos);
            }
        } catch (SQLException ex) {Logger.getLogger(ConsultasProductos.class.getName()).log(Level.SEVERE, null, ex);}//MENSAJE ERROR        
    }else{
        mostrar_todos_productos();//MOSTRAR TODOS LOS ELEMENTOS 
    }
}//GEN-LAST:event_btnbuscarActionPerformed

private void rbtndesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtndesActionPerformed
// TEXTO ESCRITO PARA HACER A BUSQUEDA POR PROPIEDAD 
    if (rbtndes.isSelected() == true) {//SI SE MARCA ESTA OPCION
        txtdes.setEnabled(true);//TEXTO PARA LA DESCRIPCION ACTIVASO
        txtdes.requestFocus();//SE PONE EL FOCO EN EL TEXTO A BUSCAR 
    }
}//GEN-LAST:event_rbtndesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbuscar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtndes;
    private javax.swing.JRadioButton rbtntodo;
    private javax.swing.JTable tbproductos;
    private javax.swing.JTextField txtdes;
    // End of variables declaration//GEN-END:variables
//CONEXION CON LA BASE DE DATOS 
conectar cc = new conectar();
    Connection cn = cc.conexion();

}
