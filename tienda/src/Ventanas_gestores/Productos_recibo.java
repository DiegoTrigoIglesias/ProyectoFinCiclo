package Ventanas_gestores;

import Conexion_BBDD.conectar;
import java.sql.*;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * @author DIEGOT SE LLAMA DESDE EL APARTADO RECIBO LISTADO CON LOS PRODUCTOS
 * QUE HAY EN LA BASE DE DATOS
 */
public class Productos_recibo extends javax.swing.JInternalFrame {//SE CREA UN NUEVO FORMULARIO PARA EL LISTADO DE PRODUCTOS 
    DefaultTableModel tabla;
    public Productos_recibo() {
        initComponents();
        cargarlistaproductos("");//CARGAR LISTA CON TODOS LOS PRODUCTOS QUE TENEMOS 
    }
    
    String comparar(String cod) {//COMPARACION DE QUE LOS PRODUCTOS SEAN LOS CORRECTOS MEDIANTE SU CODIGO 
        String cant = "";//DATOS DEL PRODUCTO QUE SE VAN A O¡MOSTRAR 
        try {
            Statement st = cn.createStatement();//CREACION DE LA SENTENCIA SQL 
            ResultSet rs = st.executeQuery("SELECT * FROM producto WHERE cod_pro='" + cod + "'");//CONSULTA SQL PARA BUSCAR POR CODIGO DE PRODUCTO 
            while (rs.next()) {//MOSTRAR LOS DATOS DE LAS COLUMNAS  
                cant = rs.getString(4);//Nº DE COLUMNAS A MOSTRAR 
            }
        } catch (SQLException ex) { Logger.getLogger(Productos_facturas.class.getName()).log(Level.SEVERE, null, ex);}//MANEJO DE LA EXCEPCION 
        return cant;//MOSTRAR TODOS LOS DATOS DE LOS PRODUCTOS 
    }

    void cargarlistaproductos(String dato) {//MOSTRAR LAS PROPIEDADES DE CADA PRODUCTO 
        String[] Titulo = {"Codigo", "Descripcion", "Precio", "Stock"};//NOMBRE DE LAS COLUMNAS 
        tabla = new DefaultTableModel(null, Titulo);//PONERLE TITULO A LAS COLUMNAS
        String[] Registro = new String[4];//NUMERO DE REGISTROS A OBTENER 
        String mostrar = "SELECT * FROM producto WHERE CONCAT (cod_pro,'',descripcion) LIKE '%" + dato + "%'"; //CONSULTA SWL PARA OBTENER LOS DAROS 
        try {
            Statement st = cn.createStatement();//CREAR LA SENTENCIA SQL
            ResultSet rs = st.executeQuery(mostrar);//EJECUTAR 
            while (rs.next()) {//MOSTRAR LOS DATOS EN LA COLUMNA CORRESPONDIENTE 
                Registro[0] = rs.getString("cod_pro");
                Registro[1] = rs.getString("Descripcion");
                Registro[2] = rs.getString("precio");
                Registro[3] = rs.getString("Stock");
                tabla.addRow(Registro);//METER LOS RESULTADOS EN LA CABLA 
            }
            tbprod.setModel(tabla);//CARGAR EL MODELO DE LA TABLA
        } catch (SQLException ex) {Logger.getLogger(Productos_facturas.class.getName()).log(Level.SEVERE, null, ex);}//MANEJO DE LA EXCEPCION 
    }
// Este método se llama desde el constructor para inicializar el formulario
//ADVERTENCIA: NO modificar este código.generado por el Editor de formularios
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        mnenviar = new javax.swing.JMenuItem();
        txtprod = new javax.swing.JTextField();
        btnmostrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbprod = new javax.swing.JTable();

        mnenviar.setText("Enviar a Recibo");
        mnenviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnenviarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnenviar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("PRODUCTOS");

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
                .addContainerGap(15, Short.MAX_VALUE))
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
                .addContainerGap(36, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void txtprodKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtprodKeyReleased
//TEXTO ESCRITO A LA HORA DE LA BUSQUEDA DE UN PRODUCTO
    cargarlistaproductos(txtprod.getText());//OBTENER EL TEXTO ESCRITO EN LA ETIQUETA 
}//GEN-LAST:event_txtprodKeyReleased

private void btnmostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmostrarActionPerformed
// ACCION PARA EL BOTON DE MOSTRAR PRODUCTOS 
    cargarlistaproductos("");//LISTADO DE PRODUCTOS 
}//GEN-LAST:event_btnmostrarActionPerformed

private void mnenviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnenviarActionPerformed
//ACCION DE ENVIAR UN PRODUCTO A LA FACTURA 
    try {
        DefaultTableModel tabladet = (DefaultTableModel) Recibo.tbdetbol.getModel();//MODELO POR DEFECTO DE LA TABLA 
        String[] dato = new String[5];//COLUMNAS DE DATOS A ENVIAR 
        int fila = tbprod.getSelectedRow();//OBTENER LA FILA SELECCIONADA 
        if (fila == -1) {//EN CASO DE QUE NO SE SELECIONE NINGUNA 
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningun registro");//MENSAJE DE AVISO 
        } else {//EM CASO DE QUE HAYA SELECCIONADA UNA OBTENER LOS VALORES DE CADA FILA 
            String codig_producto = tbprod.getValueAt(fila, 0).toString();//OBTENER EL VALOR DEL CODIGO 
            String descrip_producto = tbprod.getValueAt(fila, 1).toString();//OBTENER LA DESCRIPCION
            String prec_producto = tbprod.getValueAt(fila, 2).toString();//OBTENER EL PRECCIO
            int recivo = 0;//CONTADOR DE RECIBOS
            int j = 0;//COMPARACION DE NUMERO DE RECIBOS 
            String cant_producto = JOptionPane.showInputDialog("ingrese cantidad");//CANTIDAD DE PRODUCTO VENDIDO AL CLIENTE 
            if ((cant_producto.equals("")) || (cant_producto.equals("0"))) {//EN CASO DE QUE NO SE ESCRIBA O LA CANTIDAD SEA 0 
                JOptionPane.showMessageDialog(this, "Debe ingresar algun valor mayor que 0");//MENSAJE DE AVISO 
            } else {//SI TODO ESTA CORRECTO 
                int canting = Integer.parseInt(cant_producto);//SE PASA EL NUMERO DE LA CANTIDAD 
                int comp = Integer.parseInt(comparar(codig_producto));// SE OBTIENE EL NUMERO DE STOCK DEL ALMACEN 
                if (canting > comp) {//COMPROBAMOS QUE HAYA STOCK
                    JOptionPane.showMessageDialog(this, "Stock insuficiente");//EN CASO DE NO TENER 
                } else {//SI HAY UNA CANTIDAD CORRECTS 
                    for (int i = 0; i < Recibo.tbdetbol.getRowCount(); i++) {//RECORRER LOS ELEMENTOS DEL RECIVO 
                        Object com = Recibo.tbdetbol.getValueAt(i, 0);//OBTENER EL CODIGO DEL RECIBO 
                        if (codig_producto.equals(com)) {//SI EL CODIGO CORRESPONDE LOS DARTOS SON CORRECTOS 
                            j = i;
                            Recibo.tbdetbol.setValueAt(cant_producto, i, 3);//SE OBTIENE EL PRECIO DEL PRODUCTO 
                            recivo = recivo + 1;//SE SUMA UNO AL CONTADOR DE PRODCUTOS 
                        }
                    }
                    if (recivo == 0) {//SE METEN TODOS LOS DATOS DEL RECIBO EN LA COLUMNA CORRESPONDIENTE 
                        dato[0] = codig_producto;//FILA UNO CODIGO DEL PRODCUTO 
                        dato[1] = descrip_producto;//FILA FOS DESCRIPCION DEL PRODUCTO 
                        dato[2] = prec_producto;//FILA TRES PRECIO DEL PRODUCTO
                        dato[3] = cant_producto;//FILA CUATRO CANTIDAD DEL PRODUCTO 
                        tabladet.addRow(dato);//AÑADIR LOS RESULTADOS A LA TABLA 
                        Recibo.tbdetbol.setModel(tabladet);
                    }
                }
            }
        }
    } catch (Exception e) {System.out.println(e);}//MANEJO DE LA EXCEPCION
}//GEN-LAST:event_mnenviarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnmostrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem mnenviar;
    private javax.swing.JTable tbprod;
    private javax.swing.JTextField txtprod;
    // End of variables declaration//GEN-END:variables
//SE REALIZA LA CONEXION CON LA BASE DE DATOS 
conectar cc = new conectar();
    Connection cn = cc.conexion();
}
