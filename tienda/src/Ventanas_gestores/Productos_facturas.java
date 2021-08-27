package Ventanas_gestores;

import Conexion_BBDD.conectar;
import java.awt.JobAttributes;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author DIEGOT
 * SE LLAMA DESDE EL APARTADO FACTURAS
 * LISTADO CON LOS PRODUCTOS QUE HAY EN LA BASE DE DATOS 
 */
public class Productos_facturas extends javax.swing.JInternalFrame { //NUEVO FORMULARIO PARA EL LISTADO DE PRODUCTOS 
    DefaultTableModel tabla;
   
    public Productos_facturas() {
        initComponents();
        cargarlistaproductos("");//CARGAR LA LISTA DE LOS PRODUCTOS 
    }

    String comparar(String cod) {//FUNCION DE BUSQUEDA DE LOS PRODUCTOS POR CODIGO 
        String cant = "";//DATOS DEL PRODUCTO QUE SE VAN A MOSTRAR 
        try {
            Statement st = cn.createStatement();//CREAR LA SENTENCIA SQL 
            ResultSet rs = st.executeQuery("SELECT * FROM producto WHERE cod_pro='" + cod + "'");//CONSULTA SQL PARA BUSCAR POR COD DE PRODUCTO 
            while (rs.next()) {//LLENADO DE LAS TABLAS CON LOS DATOS DE LOS PRODUCTOS 
                cant = rs.getString(4);
            }
        } catch (SQLException ex) { Logger.getLogger(Productos_facturas.class.getName()).log(Level.SEVERE, null, ex);//MANEJO DE LA EXCEPCION 
        }
        return cant;//DEVOLVER LOS DATOS DEL PRODUCTO RELACIONADOS CON SU CODIGO 
    }

    void cargarlistaproductos(String dato) {//FUNCION QUE MUESTRA LOS PRODUCTOS CON SUS PROPIEDADES 
        String[] Titulo = {"Codigo", "Descripcion", "Precio", "Stock"};//TITULOS DE LA TABLA 
        tabla = new DefaultTableModel(null, Titulo);//SE INTRODUCEN LOS TITULOS DE CADA COLUMNA
        String[] Registro = new String[4];//CREACION DE LAS COLUMNAS DE LA TABLA 
        String mostrar = "SELECT * FROM producto WHERE CONCAT (cod_pro,'',descripcion) LIKE '%" + dato + "%'";//SELECCIONAR TODOS LOS PRODUCTOS DE LA TABLA SEGUN SU DESCRIPCION 
        try {
            Statement st = cn.createStatement();//CREAR LA SENTECIA SQL 
            ResultSet rs = st.executeQuery(mostrar);//EJECUTAR LA SENRENCIA
            while (rs.next()) {//DATOS A MOSTRART EN CADA COLUMNA 
                Registro[0] = rs.getString("cod_pro");
                Registro[1] = rs.getString("Descripcion");
                Registro[2] = rs.getString("precio");
                Registro[3] = rs.getString("Stock");
                tabla.addRow(Registro);//AÑADIR LOS REGISTROS A LA TABLA 
            }
            tbprod.setModel(tabla);//CREAR MODELO DE LA TABLA CLIENTES 
        } catch (SQLException ex) {Logger.getLogger(Productos_facturas.class.getName()).log(Level.SEVERE, null, ex);}//MANEJO DE LA EXCEPCION 
    }
// Este método se llama desde el constructor para inicializar el formulario
//ADVERTENCIA: NO modificar este código.generado por el Editor de formularios
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        mnenviarpro = new javax.swing.JMenuItem();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbprod = new javax.swing.JTable();
        txtprod = new javax.swing.JTextField();
        btnmostrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        mnenviarpro.setText("Enviar a Factura");
        mnenviarpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnenviarproActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnenviarpro);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("PRODUCTOS");

        tbprod.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbprod.setComponentPopupMenu(jPopupMenu1);
        jScrollPane2.setViewportView(tbprod);

        txtprod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtprodKeyReleased(evt);
            }
        });

        btnmostrar.setText("Mostrar todo");
        btnmostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmostrarActionPerformed(evt);
            }
        });

        jLabel1.setText("Buscar Productos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtprod, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnmostrar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnmostrar)
                    .addComponent(txtprod, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnmostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmostrarActionPerformed
// MOSTRAR LOS PRODUCTOS 
    cargarlistaproductos("");
}//GEN-LAST:event_btnmostrarActionPerformed

private void txtprodKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprodKeyReleased
//TENER EN CUENTA EL TEXTO INTRODUCIDO POR EL USUARIO
    cargarlistaproductos(txtprod.getText());//OBTENER EK TEXTO PARA LA COMPARACION
}//GEN-LAST:event_txtprodKeyReleased

private void mnenviarproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnenviarproActionPerformed
//ACCION DE ENVIAR EL PRODUCTO A LA FACTURA 
    try {
        DefaultTableModel tabladet = (DefaultTableModel) Factura.tbdet.getModel();//MODELO POR DEFECTO DE LA FACTURA 
        String[] dato = new String[5];//COLUMNAS CON LOS DARTOS 
        int fila = tbprod.getSelectedRow();//OBTENER LA FILA SELECCIONADA 
        if (fila == -1) {//EN CASO DE QUE NO HAYA NINGUNA 
            JOptionPane.showMessageDialog(null, "No  ha seleccionado ningun registro");//MENSAJE DE AVISO 
        } else {//SI HAY UNA FILA SELECCIONADA SE OBTIENEN LOS VALORES SEGUN LAS FILAS
            String cod_producto = tbprod.getValueAt(fila, 0).toString();// OBTENER EL CODIGO DEL PRODUCTO
            String descrip_producto = tbprod.getValueAt(fila, 1).toString();//OBTENER LA CANTIDAD DEL PRODUCTO 
            String prec_producto = tbprod.getValueAt(fila, 2).toString();//OBTENER EL PRECIO DEL PRODUCTO S
            int facturas = 0;//CONTADOR DE FACTURAS 
            int j = 0;//COMPARACION DE NUERO DE FACTURA 
            String cant_producto = JOptionPane.showInputDialog("Ingrese cantidad");//CANTIDAD DE PRODUCTO A COMPRAR 
            if ((cant_producto.equals("")) || (cant_producto.equals("0"))) {//EN CASO DE QUE NO SE ESCRIBA O LA CANTIDAD SEA 0 
                JOptionPane.showMessageDialog(this, "Debe ingresar algun valor mayor que 0");//MENSAJE DE AVISO 
            } else {//SI SE ESCRIBE UNA CANTIDAD CORRECTA
                int canting = Integer.parseInt(cant_producto);// SE PASA A NUMER LA CANTIDAD 
                int comp = Integer.parseInt(comparar(cod_producto));//SE OBTIENE EL NUMERO DE STOCK DEL ALMACEN 
                if (canting > comp) {//COMPARAMOS UE HAYA STOCK SUFICIENTE 
                    JOptionPane.showMessageDialog(this, "Stock insuficiente");// DE NO HABERLO MENSAJE DR AVISO 
                } else {//SI TODO ES CORRECTO SE PASA A CALCULAR 
                    for (int i = 0; i < Factura.tbdet.getRowCount(); i++) {//RECORER TODOS LOS ELEMENTOS 
                        Object com = Factura.tbdet.getValueAt(i, 0);//OBTENER EL CODIGO DEL PRODCUTO 
                        if (cod_producto.equals(com)) {//SI EL CODIGO CORRESPONDE LOS DATOS SON CORRECTOS 
                            j = i;
                            Factura.tbdet.setValueAt(cant_producto, i, 3);//SE OBTIENE LA CANTIDAD DEL PRODUCTO 
                            facturas = facturas + 1;//SE SUMA UNO A CONRADOR DE FACTURAS 
                        }
                    }
                    if (facturas == 0) {//SE METEN LOS DATOS DE LA FACTUR EN LA COLUMNA CORRESPONDIENTE 
                        dato[0] = cod_producto;//FILA UNO CODIGO PRODUCTO 
                        dato[1] = descrip_producto;//FILA DOS DESCIPCION PRODUCTO
                        dato[2] = prec_producto;//FILA TRES PRECIO POR UNIDAD DE PRODUCTO 
                        dato[3] = cant_producto;//FILA CUATRO CANTIDAD DE PRODUCTO 
                        tabladet.addRow(dato);//SE AÑADEN LOS DATOS A LA TABLA
                        Factura.tbdet.setModel(tabladet);
                    }
                }
            }
        }
    } catch (Exception e) {System.out.println(e);}//MANEJO DE LA EXCEPCION
}//GEN-LAST:event_mnenviarproActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnmostrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenuItem mnenviarpro;
    private javax.swing.JTable tbprod;
    private javax.swing.JTextField txtprod;
    // End of variables declaration//GEN-END:variables

 //SE REALIZA LA CONEXION CON LA BASE DE DATOS    
conectar cc = new conectar();
    Connection cn = cc.conexion();

}
