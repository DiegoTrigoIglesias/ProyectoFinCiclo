package Ventanas_gestores;

import Conexion_BBDD.conectar;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author DIEGOT AQUI SE PODRAN HACER LAS BUSQUEDAS DE TODOS LOS RECIBOS QUE
 * VAMOS A TENER DE VARIOS MODOS SELECCIONADOS POR RADIO BUTON BUSQUEDA POR
 *      NUMERO RE RECIBO 
 *      BUSQUEDA POR FECHA DE EXPEDICION 
 *      MOSTRAR DIRECTAMENTE TODOS LOS RECIBOS
 * TANTO EN LAS FACTURAS COMO RECIBOS IMPORTANTE DESCARGAR EL PLUGIN JDATECOOSER PARA QUE FUNCIONE LA BUSQUEDA POR FECHA 
 * ACCION DE CONTROL DE LOS RADIO BUTONS PARA LAS OPCIONES DE BUSQUEDA ES IGUAL A LA DE LAS FACTURAS 
 */
public class ConsultasRecibos extends javax.swing.JInternalFrame {
    //CREAR UN NUEVO FORMULARIO PARA BUSCAR FACTURAS 
    public ConsultasRecibos() {
        initComponents();
        cargar_todos_recibos();//LLAMADA A MOSTRAR TODOS LOS RECIBOS
        jDateChooser1.setEnabled(false);//OPCION DE FECHA BLOQUEADA 
    }
    void cargar_todos_recibos() {
        DefaultTableModel tabla = new DefaultTableModel();//MODELO POR DEFECTO DE LA TABLA 
        String[] titulos = {"NUMERO", "COD. CLIENTE", "TOTAL A PAGAR", "FECHA"};//TITULOS DE LAS COLUMNAS QUE VA A TENER 
        tabla.setColumnIdentifiers(titulos);//PONERLE A CADA COLUMNA SU TITULO 
        this.tbrecibos.setModel(tabla);//DATOS DEL MODELO RECIBO PASADOS A LA TABLA
        String consulta = "SELECT * FROM recibo";//CREAR LA CONSULTA SQL 
        String[] Datos = new String[4];//COLUMNAS A MOSTRAR
        try {
            Statement st = cn.createStatement();//CREAR LA SENTENCIA SQL 
            ResultSet rs = st.executeQuery(consulta);//REALIZAR LA CONSULTA 
            while (rs.next()) {//MOSTRAR LOS DATOS EN LAS COLUMNAS, UNA VEZ FINALIZADA UNA, PASA A OTRA 
                Datos[0] = rs.getString("num_rec");
                Datos[1] = rs.getString("cod_cli");
                Datos[2] = rs.getString("pre_tot");
                Datos[3] = rs.getString("fecha");
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
        tbrecibos = new javax.swing.JTable();

        mnver.setText("Ver Detalle");
        mnver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnverActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnver);

        mneliminar.setText("mneliminar");
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
        setTitle("CONSULTAS DE RECIBOS");

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
        rdbntodos.setText("Mostrar todos:");
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdbbnfecha)
                    .addComponent(rdbntodos)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdbnnumero)
                        .addGap(27, 27, 27)
                        .addComponent(txtnumero, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addComponent(btnbuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbrecibos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbrecibos.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(tbrecibos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void rdbnnumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbnnumeroActionPerformed
//ACCION DEL RADIO BUTON DE BUSCAR POR NUMERO DE RECIBO
    if (rdbnnumero.isSelected() == true) {//EN CASO DE QUE SE SELECCIONE
        txtnumero.setEnabled(true);//TEXTO DE ESCRITURA DE TEXTO ACTIVDAD
        txtnumero.requestFocus();//FOCO EN EL TEXTO 
        jDateChooser1.setEnabled(false);//BLOQUEAR EL JDATECHOOSER PARA EVITAR ERRORES
        jDateChooser1.setDate(null);//FECHA VACIA 
    }
}//GEN-LAST:event_rdbnnumeroActionPerformed

private void rdbbnfechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbbnfechaActionPerformed
//ACCION DEL RADIO BUTON DE BUSCAR POR FECHA 
    if (rdbbnfecha.isSelected() == true) {//EN CASO DE QUE SE SELCCIONE
        jDateChooser1.setEnabled(true);//ACTIVAR EL JDATECHOOSER
        txtnumero.setEnabled(false);//BLOQUEAR EL TEXTO PARA EVITAR ERRORES
        txtnumero.setText("");//TEXTO VACIO
    }
}//GEN-LAST:event_rdbbnfechaActionPerformed

private void rdbntodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbntodosActionPerformed
//ACCION DEL RADIO BUTON PARA MOSTRAR TODO 
    if (rdbntodos.isSelected() == true) {//EN CASO DE QUE SE SELCCIONE 
        jDateChooser1.setEnabled(false);//BLOQUEAR EL JDATECHOOSER PARA EVITAR ERRORES
        jDateChooser1.setDate(null);//FECHA VACIA
        txtnumero.setText("");//NUMERO VACIO
        txtnumero.setEnabled(false);//BLOQUEAR BUSQUEDA POR NUMERO PARA EVITAR ERRORES
        cargar_todos_recibos();//MOSTRAR TODOS LOS RECIBOS 
    }
}//GEN-LAST:event_rdbntodosActionPerformed

private void btnbuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscadorActionPerformed
//ACCION DEL BOTON BUSCAR TENIENDO EN CUENTA EL RADIO BUTON SELECCIONADO 
    String num = txtnumero.getText();//OBTENER EL NUMERO DE RECIBO PARA COMPARAR
    String consulta = "";//CONSUTLA SQL A REALIZAR
    if (rdbnnumero.isSelected() == true) {//SI HAY SELECCIONADA LA OPCION BUSCAR POR NUMERO 
        consulta = "SELECT * FROM recibo WHERE num_rec='" + num + "'";//CONSULTA SQL QUE BUSCA IGUALDAD POR NUMERO 
    }
    if (rdbbnfecha.isSelected() == true) {//SI HAY SELECCIONADA LA OPCION BUSCAR POR FECHA
        Date fecha = jDateChooser1.getDate();//OBTENER LA FECHA ACTUAL
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/YYYY");//SE LE DA FORMATO
        String fec = "" + formatofecha.format(fecha);//SE PASA A TEXTO
        consulta = "SELECT * FROM recibo WHERE fecha='" + fec + "'";//CONSUTL SQL QUE BUSCA POR FECHA 
    }
    if (rdbntodos.isSelected() == true) {//SI HAY SELECCIONADA LA OPCION MOSTRAR TODOS 
        consulta = "SELECT * FROM recibo ";//MUESTRA TODOS
    }
    DefaultTableModel tabla = new DefaultTableModel();//MODELO POR DEFECTO DE LA TABLA
    String[] titulos = {"NUMERO", "COD. CLIENTE", "TOTAL A PAGAR", "FECHA"};//TITULOS DE LA COLUMNAS 
    tabla.setColumnIdentifiers(titulos);//PONER A CADA COLUMNA SU TITULO 
    tbrecibos.setModel(tabla);//DARTOS DEL MODELO PASADOS A LA TABLA 
    String[] Datos = new String[4];//Nº DE COLUMNAS A MOSTRAR 
    try {
        Statement st = cn.createStatement();//CREAR LA SENTENCIA 
        ResultSet rs = st.executeQuery(consulta);//REALIZAR LA CONSULTA 
        while (rs.next()) {//MOSTRAR LOS DATOS EN LAS COLUMNAS, UNA VEZ FINALIZADA UNA, PASA A OTRA 
            Datos[0] = rs.getString("num_rec");
            Datos[1] = rs.getString("cod_cli");
            Datos[2] = rs.getString("pre_tot");
            Datos[3] = rs.getString("fecha");
            tabla.addRow(Datos);//METER TODOS LOS MODELS EN LAS COLUMNAS 
        }
    } catch (SQLException ex) {Logger.getLogger(ConsultasRecibos.class.getName()).log(Level.SEVERE, null, ex);}//MENSAJE DE ERROR 
}//GEN-LAST:event_btnbuscadorActionPerformed

private void mnverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnverActionPerformed
//OBTENER LOS DATOS DE LAS FILAS SELECCIONADAS PARA HACER LOS RECIBOS 
    int filasele = tbrecibos.getSelectedRow();//OBTENER LA FILA DEL RECIBO SELECCIONADA
    if (filasele == -1) {//EN CASO DE QUE NO SE SELECCIONE NINGUNA
        JOptionPane.showMessageDialog(null, "No Seleciono ninguna fila");//MENSAJE DE AVISO 
    } else {//SI HAY ALGUNA SELECCIONADA
        //OBTENER TODOS LOS DATOS COMO STRING DEL RECIBO SELECCIONADO, DE SU NUMERO DE COLUMNA CORRESPONDIENTE
        DetalleRecibo detalle = new DetalleRecibo();//NUEVO FORMULARIO PARA EL RECIBO 
        Principal.jdpescritorio.add(detalle);//AÑADIR DATOS AL ARRAYLIST
        detalle.toFront();//TAERLO AL FRENTE 
        detalle.setVisible(true);//VISIBILIDAD
        String numrec = tbrecibos.getValueAt(filasele, 0).toString();//OBTENER COMO STRING EL TEXTO DEL NUMERO DE RECIBO
        String cod = tbrecibos.getValueAt(filasele, 1).toString();//OBTENER COMO STRING EL TEXTO DEL CODIGO DEL RECIBO
        String total = tbrecibos.getValueAt(filasele, 2).toString();//OBTENER COMO STRING EL TEXTO DEL TOTAL A PAGAR POR LOS PRODUCTOS
        String fecha = tbrecibos.getValueAt(filasele, 3).toString();//OBTENER COMO STRING EL TEXTO DEL LA FECHA DE EXPEDICION
        DetalleRecibo.txtfac.setText(numrec);//INTRODUCIR 
        DetalleRecibo.txtcod.setText(cod);//INTRODUCIR 
        DetalleRecibo.txttot.setText(total);//INTRODUCIR 
        DetalleRecibo.txtfecha.setText(fecha);//INTRODUCIR 
        DefaultTableModel model = (DefaultTableModel) DetalleRecibo.tbdetalle.getModel();//MODELO POR DEFECTO DEL RECIBO
        String ver = "SELECT * FROM detallerecibo WHERE num_rec='" + numrec + "'";//CONSULTA SQL 
        String[] datos = new String[5];//Nº DE TABLAS CON DATOS 
        try {
            Statement st = cn.createStatement();//CREAR SENTENCIA SQL 
            ResultSet rs = st.executeQuery(ver);//REALIZAR LA CONSULTA 
            while (rs.next()) {//MOSTRAR LOS DATOS EN LAS COLUMNAS, UNA VEZ FINALIZADA UNA, PASA A OTRA 
                datos[0] = rs.getString("cod_pro");
                datos[1] = rs.getString("des_pro");
                datos[2] = rs.getString("cant_pro");
                datos[3] = rs.getString("pre_unit");
                datos[4] = rs.getString("pre_venta");
                model.addRow(datos);//METER TODOS LOS MODELOS EN LAS COLUMNAS 
            }
            DetalleFactura.tbdetalle.setModel(model);//MODELO DE LA TABLA POR DEFECTO 
        } catch (SQLException ex) {Logger.getLogger(ConsultasFacturas.class.getName()).log(Level.SEVERE, null, ex);}//MENSAJE ERROR 
    }
}//GEN-LAST:event_mnverActionPerformed

private void mneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mneliminarActionPerformed
//ACCION DE ELIMINAR UN RECIBO 
    int fila = tbrecibos.getSelectedRow();//OBTENER LA FILA SELECCIONADA 
    if (fila >= 0) {//EN CASO DE TENER FILA SELECIONADA 
        String cod = tbrecibos.getValueAt(fila, 0).toString();//OBTENER EL CODIGO DE FILA PARTIENDO DEL PRIMER ELEMENTO 
        try {
            PreparedStatement pst = cn.prepareStatement("DELETE FROM recibo WHERE num_rec='" + cod + "'");//CONSULTA SQL DE BORRADO POR EL CODIGO OBTENIDO 
            pst.executeUpdate();//EJECUTAR LA SENTENCIA
            cargar_todos_recibos();//ACTUALIZAR LA BASE DE DATOS 
        } catch (SQLException ex) {Logger.getLogger(ConsultasRecibos.class.getName()).log(Level.SEVERE, null, ex);}//MENSAJE DE ERROR 
    } else {JOptionPane.showMessageDialog(this, "Seleccione alguna fila");}//SI NO SE SELECCIONA NIGUNA FILA PEDIRLO 
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
    public static javax.swing.JTable tbrecibos;
    private javax.swing.JTextField txtnumero;
    // End of variables declaration//GEN-END:variables
//CONEXION CON LA BASE DE DATOS 
conectar cc = new conectar();
    Connection cn = cc.conexion();
}
