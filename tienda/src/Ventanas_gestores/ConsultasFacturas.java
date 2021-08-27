package Ventanas_gestores;

import Conexion_BBDD.conectar;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.*;
import javax.swing.JOptionPane;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 * @author DIEGOT 
 * AQUI SE PODRAN HACER LAS BUSQUEDAS DE TODOS LAS FACTURAS QUE
 * VAMOS A TENER DE VARIOS MODOS SELECCIONADOS POR RADIO BUTON BUSQUEDA POR
 *      NUMERO DE FACTURA BUSQUEDA
 *      POR FECHA MOSTRAR 
 *      DIRECTAMENTE TODAS LAS FACTURAS
 * TANTO EN LAS FACTURAS COMO RECIBOS IMPORTANTE DESCARGAR EL PLUGIN JDATECOOSER PARA QUE FUNCIONE LA BUSQUEDA POR FECHA 
 * ACCION DE CONTROL DE LOS RADIO BUTONS PARA LAS OPCIONES DE BUSQUEDA ES IGUAL A LA DE LOS RECIBOS  
 */
public class ConsultasFacturas extends javax.swing.JInternalFrame {
    //CREACION DE UN NUEVO FORMULARIO PARA CONSULTAR LAS FACTURAS 
    public ConsultasFacturas() {
        initComponents();
        cargar_todas_facturas();//MOSTRAR TODAS LAS FACTURAS 
        this.setLocation(25, 15);//LOCALIZACION EN PANTALLA 
        jDateChooser1.setEnabled(false);//FECHA ACTUAL
    }

    void cargar_todas_facturas() {//MOSTRAR TODAS LAS FACTURAS QUE TENEMOS 
        DefaultTableModel tabla = new DefaultTableModel();//MODELO DE LA TABLA FACTURA 
        String[] titulos = {"NUMERO", "COD. CLIENTE", "ID CLIENTE", "SUBTOTAL", "IVA", "TOTAL", "FECHA"};//TITULOS DE LAS COLUMNAS
        tabla.setColumnIdentifiers(titulos);//PONER IDENTIFICADOR A CADA COLUMNA 
        this.tbfacturas.setModel(tabla);//DATOS DEL MODELO PASADOS A LA TABLA 
        String consulta = "SELECT * FROM factura";//CREACION DE LA CONSULTA SQL 
        String[] Datos = new String[7];//CONSULTAS A MOSTRAR 
        try {
            Statement st = cn.createStatement();//CREAR LA SENTENCIA SQL 
            ResultSet rs = st.executeQuery(consulta);//REALIZAR LA CONSULTA 
            while (rs.next()) {//MOSTRAR LOS DATOS EN LAS COLUMNAS, UNA VEZ FINALIZADA UNA, PASA A OTRA 
                Datos[0] = rs.getString("num_fac");
                Datos[1] = rs.getString("cod_cli");
                Datos[2] = rs.getString("ruc_cli");//REGISTRO UNICO DE CLIENTE, REALMENTE ES UN ID INTERNO
                Datos[3] = rs.getString("subtotal");
                Datos[4] = rs.getString("igv");//IVA (IMPUESTO GENERADO DE VENTA)
                Datos[5] = rs.getString("total");
                Datos[6] = rs.getString("fec_fac");
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
        jPopupMenu1 = new javax.swing.JPopupMenu();
        mnver = new javax.swing.JMenuItem();
        mneliminar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        rdbnnumero = new javax.swing.JRadioButton();
        rdbbnfecha = new javax.swing.JRadioButton();
        rdbntodos = new javax.swing.JRadioButton();
        txtnumero = new javax.swing.JTextField();
        btnbuscador = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbfacturas = new javax.swing.JTable();

        mnver.setText("Ver Detalle");
        mnver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnverActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnver);

        mneliminar.setText("Eliminar");
        mneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mneliminarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mneliminar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("CONSULTA DE FACTURAS");

        buttonGroup1.add(rdbnnumero);
        rdbnnumero.setSelected(true);
        rdbnnumero.setText("Mostrar  por Nº:");
        rdbnnumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbnnumeroActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdbbnfecha);
        rdbbnfecha.setText("Mostrar por Fecha");
        rdbbnfecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbbnfechaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdbntodos);
        rdbntodos.setText("Mostrar todas:");
        rdbntodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbntodosActionPerformed(evt);
            }
        });

        btnbuscador.setText("BUSCAR");
        btnbuscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdbnnumero)
                                .addGap(27, 27, 27)
                                .addComponent(txtnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(rdbbnfecha))
                        .addGap(26, 26, 26)
                        .addComponent(btnbuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rdbntodos))
                .addContainerGap(189, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdbnnumero))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rdbbnfecha)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rdbntodos))
                    .addComponent(btnbuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        tbfacturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbfacturas.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(tbfacturas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        setBounds(0, 0, 674, 308);
    }// </editor-fold>//GEN-END:initComponents

private void btnbuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscadorActionPerformed
// ACCION DEL BOTN BUSCADOR TENIENDO EN CUENTA LOS RADIO BUTON 
    String num = txtnumero.getText();//OBTENER EL NUMERO DE FACTURA A BUSCAR 
    String consulta = "";//CONSULTA SQL A REALIZAR 
    if (rdbnnumero.isSelected() == true) {//SI HAY SELECCIONADO BUSCAR POR NUMERO 
        consulta = "SELECT * FROM factura WHERE num_fac='" + num + "'";//CONSULTA SQL QUE BUSCA IGUALDAR DE DNUMERO 
    }
    if (rdbbnfecha.isSelected() == true) {//SI HAY SELECCIONADO BUSCAR POR FECHA 
        Date fecha = jDateChooser1.getDate();//OBTIENE LA FECHA 
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/YYYY"); //SE FEDINE FORMATO DE FECHA
        String fec = "" + formatofecha.format(fecha);//SE PASA LA FECHA A TEXTO
        consulta = "SELECT * FROM factura WHERE fec_fac='" + fec + "'";//SE BUSCA POR IGUALDAD DE FECHA 
    }
    if (rdbntodos.isSelected() == true) {//SI HAY SELECCIONADA LA OPCION BUSCAR TODOS
        consulta = "SELECT * FROM factura ";//MUESTRA TODAS LAS FACTUAS
    }
    DefaultTableModel tabla = new DefaultTableModel();//MODELO POR DEFECTO DE LA TABLA 
    String[] titulos = {"NUMERO", "COD. CLIENTE", "ID CLIENTE", "SUBTOTAL", "IVA", "TOTAL", "FECHA"};//TITULOS DE LA COLUMNAS
    tabla.setColumnIdentifiers(titulos);//PONER A CADA COLUMNA SU TITULO 
    this.tbfacturas.setModel(tabla);//DATOS DEL MODELO FACTURA PASADO A TABLAS
    String[] Datos = new String[7];//Nº COLUMNAS A MOSTRAR 
    try {
        Statement st = cn.createStatement();//CREAR LA SENTENCIA SQL 
        ResultSet rs = st.executeQuery(consulta);//REALIZAR LA CONSULTA SQL
        while (rs.next()) {//MOSTRAR LOS DATOS EN LAS COLUMNAS, UNA VEZ FINALIZADA UNA, PASA A OTRA 
            Datos[0] = rs.getString("num_fac");
            Datos[1] = rs.getString("cod_cli");
            Datos[2] = rs.getString("ruc_cli");
            Datos[3] = rs.getString("subtotal");
            Datos[4] = rs.getString("igv");//IVA (IMPUESTO GENERADO DE VENTA)
            Datos[5] = rs.getString("total");
            Datos[6] = rs.getString("fec_fac");
            tabla.addRow(Datos);//METER TODOS LOS MODELOS EN LAS COLUMNAS 
        }
    } catch (SQLException ex) {Logger.getLogger(ConsultasProductos.class.getName()).log(Level.SEVERE, null, ex);}//MENSAJE ERROR 
}//GEN-LAST:event_btnbuscadorActionPerformed

private void rdbnnumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbnnumeroActionPerformed
//CONTROL DE SELECCION DEL RADIO BUTON DE BUSCAR POR NUMERO 
    if (rdbnnumero.isSelected() == true) {//EN CASO DE QUE SE SELECCIONE 
        txtnumero.setEnabled(true);//DESBLOQUEAR EL TEXTO DE ESCRITURA DE NUMERO 
        txtnumero.requestFocus();//PONER EL FOCO EN EL TEXTO 
        jDateChooser1.setDate(null);//VACIAR LA FECHA PARA EVITAR ERRORES 
        jDateChooser1.setEnabled(false);//BLOQUEAR LA ESCTIRURA 
    }
}//GEN-LAST:event_rdbnnumeroActionPerformed

private void rdbbnfechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbbnfechaActionPerformed
//CONTROL DE RADIO BUTON DE BUSCAR POR FECHA 
    if (rdbbnfecha.isSelected() == true) {//EN CASO DE QUE SE SELECCIONE 
        jDateChooser1.setEnabled(true);//ACTIVAR EL JDATECHOOSER 
        txtnumero.setEnabled(false);//BLOQUEAR EL TEXTO DE BUSQUEDA POR NUMERO 
        txtnumero.setText("");//TEXTO VACIO PARA EVITAR ERRORES 
    }
}//GEN-LAST:event_rdbbnfechaActionPerformed

private void rdbntodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbntodosActionPerformed
// CONTROL DE RADIO BUTON DE BUSCAR TODO
    if (rdbntodos.isSelected() == true) {//EN CASO DE QUE SE SELCCIONE 
        jDateChooser1.setEnabled(false);//BLOQUEAR EL JDATECHOOSER PARA EVITAR ERRORES
        jDateChooser1.setDate(null);//FECHA VACIA 
        txtnumero.setText("");//TEXTO DE NUMERO VACIO
        txtnumero.setEnabled(false);//BLOQUEAR BUSQUEDA POR NUMERO PARA EVITAR ERRORES
        cargar_todas_facturas();//MOSTRAR TODAS LAS FACTURAS 
    }
}//GEN-LAST:event_rdbntodosActionPerformed

private void mnverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnverActionPerformed
//OBTENER LOS DATOS DE LAS FILAS SELECCIONADAS PARA HACER LAS FACTURAS 
    int filasele = tbfacturas.getSelectedRow();//OBTENER FILA DE FACTURA SELECCIONADA
    if (filasele == -1) {//EN CASO DE QUE NO HAYA NINGUNA SELECCIONADA
        JOptionPane.showMessageDialog(null, "No Seleciono ninguna fila");//MENSAJE DE AVISO 
    } else {//SI HAY ALGUNA SELECCIONADA
        //OBTENER TODOS LOS DATOS COMO STRING DE LA FACTURA SELECCIONADA, DE SU NUMERO DE COLUMNA CORRESPONDIENTE 
        DetalleFactura detalle = new DetalleFactura();//NUEVO FORMULARIO DE LA FACTURA 
        Principal.jdpescritorio.add(detalle);//SE AÑADE LOS DATOS AL ARRAYLIST 
        detalle.toFront();//TRAER AL FRENRTE 
        detalle.setVisible(true);//VISIBILIDAD 
        String numfac = tbfacturas.getValueAt(filasele, 0).toString();//OBTENER COMO STRING EL TEXTO DEL NUMERO DE FACTURA 
        String cod = tbfacturas.getValueAt(filasele, 1).toString();//OBTENER COMO STRING EL CODGIO DEL CLIENTE DE LA FACTURA 
        String id = tbfacturas.getValueAt(filasele, 2).toString();//OBTENER COMO STRING EL IDENTIFICADOR DEL CLIENTE PARA LA FACTURA 
        String subtotal = tbfacturas.getValueAt(filasele, 3).toString();//OBTENER COMO STRING EL SUBTOTAL 
        String iva = tbfacturas.getValueAt(filasele, 4).toString();//OBTENER COMO STRING EL IVA
        String total = tbfacturas.getValueAt(filasele, 5).toString();//OBTENER COMO STRING EL TOTAL 
        String fecha = tbfacturas.getValueAt(filasele, 6).toString();//OBTENER COMO STRING LA FECHA DE LA FACTURA 
        DetalleFactura.txtfac.setText(numfac);//INTRODUCIR 
        DetalleFactura.txtcod.setText(cod);//INTRODUCIR 
        DetalleFactura.txtruc.setText(id);//INTRODUCIR 
        DetalleFactura.txtsub.setText(subtotal);//INTRODUCIR 
        DetalleFactura.txtigv.setText(iva);//INTRODUCIR 
        DetalleFactura.txttot.setText(total);//INTRODUCIR 
        DetalleFactura.txtfecha.setText(fecha);//INTRODUCIR 
        DefaultTableModel model = (DefaultTableModel) DetalleFactura.tbdetalle.getModel();//MODELO POR DEFECTO DE LA FACTURA 
        String ver = "SELECT * FROM detallefactura WHERE num_fac='" + numfac + "'";//CONSULTA SQL A REALIZAR
        String[] datos = new String[5];//NUMERO DE TABLAS CON DATOS 
        try {
            Statement st = cn.createStatement();//CREAR SENTENCIA SQL 
            ResultSet rs = st.executeQuery(ver);//REALIZAR LA CONSULTA 
            while (rs.next()) {//MOSTRAR LOS DATOS EN LAS COLUMNAS, UNA VEZ FINALIZADA UNA, PASA A OTRA 
                datos[0] = rs.getString("cod_pro");
                datos[1] = rs.getString("des_pro");
                datos[2] = rs.getString("cant_pro");
                datos[3] = rs.getString("pre_unit");
                datos[4] = rs.getString("pre_tot");
                model.addRow(datos);//METER TODOS LOS MODELOS EN LAS COLUMNAS 
            }
            DetalleFactura.tbdetalle.setModel(model);
        } catch (SQLException ex) {Logger.getLogger(ConsultasFacturas.class.getName()).log(Level.SEVERE, null, ex);}//MENSAJE ERROR 
    }
}//GEN-LAST:event_mnverActionPerformed

private void mneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mneliminarActionPerformed
// ACCION DE ELIMINAR UNA FACTURA 
    int fila = tbfacturas.getSelectedRow();//OBTENER LA FILA SELECCIONADA
    if (fila >= 0) {//EN CASO DE QUE SE HAYA SELECCIONADO UNA 
        String cod = tbfacturas.getValueAt(fila, 0).toString();//OBTENER EL CODIGO DE FILA PARTIENDO DEL PRIMER ELEMENTO 
        try {
            PreparedStatement pst = cn.prepareStatement("DELETE FROM factura WHERE num_fac='" + cod + "'");//CONSULTA SQL DE BORRADO POR EL CODIGO OBTENIDO 
           pst.executeUpdate();//ACTUALIZAR BBDD
        } catch (SQLException ex) {System.out.println(ex);}//MENSAJE ERROR 
        cargar_todas_facturas();//ACTUALIZAR RESULTADOS
    } else {JOptionPane.showMessageDialog(this, "Seleccione alguna fila");}//SI NO SE SELECCIONA NINGUNA PEDIRLO 
}//GEN-LAST:event_mneliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbuscador;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem mneliminar;
    private javax.swing.JMenuItem mnver;
    private javax.swing.JRadioButton rdbbnfecha;
    private javax.swing.JRadioButton rdbnnumero;
    private javax.swing.JRadioButton rdbntodos;
    public static javax.swing.JTable tbfacturas;
    private javax.swing.JTextField txtnumero;
    // End of variables declaration//GEN-END:variables
//CONEXION CON LA BASE DE DATOS 
conectar cc = new conectar();
    Connection cn = cc.conexion();
}
