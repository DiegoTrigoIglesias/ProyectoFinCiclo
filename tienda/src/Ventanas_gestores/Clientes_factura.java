package Ventanas_gestores;

import Conexion_BBDD.conectar;
import java.awt.Color;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author DIEGOT
 * LISTADO DE CLIENTES QUE SE VAN A ENVIAR A LA FATURA
 * ESTE LISTADO DE LOS CLIENTES SE LLAMA DESDE LA FACTURA PARA MOSTRAR TODOS LOS ELEMENTOS DE LA BASE DE DATOS 
 * EL FORMULARIO CLIENTES_RECIBO Y CLIENTES FACTURA ES IGUAL SOLO CAMBIA EL SITIO DESDE DONDE SE LLAMA PARA QUE NO HAYA CRUCE DE DATOS 
 */
public class Clientes_factura extends javax.swing.JInternalFrame {
    DefaultTableModel modelo;
    public Clientes_factura() {//CREACION DEL NUEVO FURMULARIO PARA LOS CLIENTES
        initComponents();
        mostrar_clientes("");//MOSTRAR TODOS LOS CLIENTES 
    }

    void mostrar_clientes(String valor) {//LISTADO DE LOS DATOS DEL MODELO CLIENTES
        String[] titulos = {"Codigo", "Nombres", "Apellidos", "Sexo", "DNI", "Telefono", "ID", "Email", "Direccion"};//COLUMNAS DE LOS DATOS 
        String[] Registros = new String[9];//NUMERO DE REGISTROS QUE HAY 
        modelo = new DefaultTableModel(null, titulos);//MODELO CON LOS TITULLOS DE CADA COLUMNA A MOSTRAR 
        String Sql = "SELECT * FROM cliente WHERE CONCAT(cod_cli,nom_cli,ape_cli,dni_cli) LIKE '%" + valor + "%'";//CONSULTA SQL 
        try {
            Statement st = cn.createStatement();//CREAR EL OBJETO PARA LA SENTENCIA SQL 
            ResultSet rs = st.executeQuery(Sql);//EJECUCION DE LA CONSULTA 
            while (rs.next()) {//PASO A LA SIGUIENTE TABLA Y DATOS A MOTRAR 
                Registros[0] = rs.getString("cod_cli");
                Registros[1] = rs.getString("nom_cli");
                Registros[2] = rs.getString("ape_cli");
                Registros[3] = rs.getString("sexo_cli");
                Registros[4] = rs.getString("dni_cli");
                Registros[5] = rs.getString("tel_cli");
                Registros[6] = rs.getString("ruc_cli");
                Registros[7] = rs.getString("email_cli");
                Registros[8] = rs.getString("dir_cli");
              modelo.addRow(Registros);//AÑADIR LOS RESULTADOS A LA TABLA 
            }
            tbclientes.setModel(modelo);//CREAR MODELO DE LA TABLA CLIENTES 
        } catch (SQLException ex) {Logger.getLogger(Clientes_factura.class.getName()).log(Level.SEVERE, null, ex);} //GESTION DE LA EXCEPCION //GESTION DE LA EXCEPCION
    }
// Este método se llama desde el constructor para inicializar el formulario
//ADVERTENCIA: NO modificar este código.generado por el Editor de formularios
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        mnenviar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnbus = new javax.swing.JButton();
        txtbus = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbclientes = new javax.swing.JTable();
        Btnregistrar = new javax.swing.JButton();

        mnenviar.setText("Enviar Datos");
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
        setTitle("CLIENTES");

        jLabel1.setText("Buscar Cliente:");

        btnbus.setText("Mostrar Todo");
        btnbus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbusActionPerformed(evt);
            }
        });

        txtbus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbusActionPerformed(evt);
            }
        });
        txtbus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbusKeyReleased(evt);
            }
        });

        tbclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tbclientes.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(tbclientes);

        Btnregistrar.setText("Registrar Clientes");
        Btnregistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnregistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtbus, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnbus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Btnregistrar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtbus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbus)
                    .addComponent(Btnregistrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnbusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbusActionPerformed
//ACCION DE BUSCAR CLIENTES 
    mostrar_clientes("");
}//GEN-LAST:event_btnbusActionPerformed

private void txtbusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbusActionPerformed
//TEXTO PARA BUSCAR CLIENTES 
}//GEN-LAST:event_txtbusActionPerformed

private void txtbusKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbusKeyReleased
//OBTENER EL TEXTO POR EL CUAL SE VAN A OBTENER LOS CLIENTES 
    mostrar_clientes(txtbus.getText());
}//GEN-LAST:event_txtbusKeyReleased

private void mnenviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnenviarActionPerformed
//ACCION DEL BOTON DE ENVIO DE CLIENTE 
    String cod = "", nom = "", ape = "", dni = "", dir = "", ruc = "";//DATOS A ENVIAR 
    int fila = tbclientes.getSelectedRow();//SELECCIONAR EL ELEMENTO MARCADO 
    try {
        if (fila == -1) {//EN CASO DE QUE NO SE HAYA SELECCIONADO UNA FILA 
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningun dato"); //MENSAJE DE AVISO 
        } else { //SI SE SELECCIONA, SE OBTIENEN LOS DATOS EN LA TABLA 
            cod = (String) tbclientes.getValueAt(fila, 0);
            nom = (String) tbclientes.getValueAt(fila, 1);
            ape = (String) tbclientes.getValueAt(fila, 2);
            dni = (String) tbclientes.getValueAt(fila, 4);
            ruc = (String) tbclientes.getValueAt(fila, 6);
            dir = (String) tbclientes.getValueAt(fila, 8);
            //SE ENVIAN LOS DATOS DEL CLIENTE A LA FACTURA EN TEXTO AZUL
            //con la propiedad color."" se le dá el color que queramos a los datos autocompletables
            Factura.txtcod.setDisabledTextColor(Color.blue);
            Factura.txtcod.setText(cod);
            Factura.txtnomape.setDisabledTextColor(Color.blue);
            Factura.txtnomape.setText(nom + "   " + ape);
            Factura.txtdir.setDisabledTextColor(Color.blue);
            Factura.txtdir.setText(dir);
            Factura.txtdni.setDisabledTextColor(Color.blue);
            Factura.txtdni.setText(dni);
            Factura.txtruc.setDisabledTextColor(Color.blue);
            Factura.txtruc.setText(ruc);
            this.dispose();//BORRAR VENTANA DEL JFRAME UNA VEZ FINALIZADO 
        }
    } catch (Exception e){System.out.println(e);}//EXCEPCION
}//GEN-LAST:event_mnenviarActionPerformed

private void BtnregistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnregistrarActionPerformed
//ACCION DE BOTON DE REGISTRO DE NUEVOS CLIENTES
    try {
        IngresoCliente ingreso_client = new IngresoCliente();//LLAMADA AL FORMULARIO PARA REGISTRAR NUEVOS CLIENTES
        Principal.jdpescritorio.add(ingreso_client);//AÑADIR EL ELEMENTO AL ARRAY 
        ingreso_client.toFront();//SE TRAE AL FRENTR EL INGRESO DE LOS CLIENTES 
        ingreso_client.setVisible(true);//FORMULARIO VISIBLE 
        this.dispose();//SE CIERRA EL JFRAME 
    } catch (Exception e) {System.out.println(e);}//EXCEPCION

}//GEN-LAST:event_BtnregistrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btnregistrar;
    private javax.swing.JButton btnbus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem mnenviar;
    private javax.swing.JTable tbclientes;
    private javax.swing.JTextField txtbus;
    // End of variables declaration//GEN-END:variables
    conectar cc = new conectar();
    Connection cn = cc.conexion();
}
