package Ventanas_gestores;

import Conexion_BBDD.conectar;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 * @author DIEGOT 
 * AQUI SE PODRAN HACER LAS BUSQUEDAS DE TODOS LOS CLIENTES QUE
 * VAMOS A TENER DE VARIOS MODOS SELECCIONADOS POR RADIO BUTON BUSQUEDA POR
 *      GENERO (H/M) 
 *      BUSQUEDA POR DNI (escribiendolo y viendo los reusltadossimilares)
 *      MOSTRAR DIRECTAMENTE TODOS LOS CLIENTES
 * INICIALMENTE ESTARÁ MARCADA LA OPCION POR DEFECTO PARA SELECCIONAR GENERO 
 */
public class ConsultasClientes extends javax.swing.JInternalFrame {
//REALIZAR LAS BUSQUEDAS EN LOS CLIENTES 
    public ConsultasClientes() {
        initComponents();
        txtdni.setEnabled(false);//LA OPCION DE ESCRITURA DE DNI BLOQUEADA PARA QUE NO FALLE AL INICIO DE CARGAR LOS DATOS 
        Cargar_lista_clientes();
    }

    void Cargar_lista_clientes() {//MOSTRAR TODOS LOS CLIENTES DE LA LISTA CLIENTES 
        DefaultTableModel modelo = new DefaultTableModel();//MODELO DE CLIENTE CON SUS DATOS 
        String[] Titulos = {"CODIGO", "NOMBRES", "APELLIDOS", "SEXO", "DNI", "TELEFONO", "ID", "EMAIL", "DIRECCION"};
        modelo.setColumnIdentifiers(Titulos);//TITULO A CADA COLUMNA 
        this.tbclientes.setModel(modelo);//DATOS DEL MODELO CLIENTES PASADO A LA TABLA CLIENTES 
        try {
            String ConsultaSQL="SELECT * FROM cliente";//CONSULTA SQL A REALIZAR 
            String []registros= new String[9];//TABLAS QUE SE VAN A MOSTRAR
            Statement st = cn.createStatement();//CREAR LA SENTENCIA SQL 
            ResultSet rs = st.executeQuery(ConsultaSQL);//REALIZAR LA CONSULTA SQL 
            while(rs.next()){//MOSTRAR LOS DATOS EN LAS COLUMNAS, UNA VEZ FINALIZADA UNA, PASA A OTRA 
                registros[0] = rs.getString("cod_cli");
                registros[1] = rs.getString("nom_cli");
                registros[2] = rs.getString("ape_cli");
                registros[3] = rs.getString("sexo_cli");
                registros[4] = rs.getString("dni_cli");
                registros[5] = rs.getString("tel_cli");
                registros[6] = rs.getString("ruc_cli");//REGISTRO UNICO DE CLIENTE, REALMENTE ES UN ID INTERNO
                registros[7] = rs.getString("email_cli");
                registros[8] = rs.getString("dir_cli");
                modelo.addRow(registros);//METER LOS MODELOS EN LAS COLUMNAS 
            }
            tbclientes.setModel(modelo);//MODELO DE LA TABA CLIENTES 
            txtcant.setText("" + tbclientes.getRowCount());//LLENAR DE RESULTADOS 
        } catch (SQLException ex) {Logger.getLogger(ConsultasClientes.class.getName()).log(Level.SEVERE, null, ex);}//CONTROL DE EXCEPCIONES 
    }
// Este método se llama desde el constructor para inicializar el formulario
//ADVERTENCIA: NO modificar este código.generado por el Editor de formularios
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        rdgenero = new javax.swing.JRadioButton();
        rddni = new javax.swing.JRadioButton();
        rdtodo = new javax.swing.JRadioButton();
        cbogenero = new javax.swing.JComboBox();
        txtdni = new javax.swing.JTextField();
        btnbuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbclientes = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtcant = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("CONSULTA DE CLIENTES");

        buttonGroup1.add(rdgenero);
        rdgenero.setSelected(true);
        rdgenero.setText("Mostrar Clientes por Genero:");
        rdgenero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdgeneroActionPerformed(evt);
            }
        });

        buttonGroup1.add(rddni);
        rddni.setText("Buscar Clientes por DNI:");
        rddni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rddniActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdtodo);
        rdtodo.setText("Mostrar todos los clientes");
        rdtodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdtodoActionPerformed(evt);
            }
        });

        cbogenero.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione sexo" }));

        btnbuscar.setText("BUSCAR");
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
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rddni)
                        .addGap(24, 24, 24)
                        .addComponent(txtdni))
                    .addComponent(rdtodo)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rdgenero)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbogenero, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(54, 54, 54)
                .addComponent(btnbuscar)
                .addGap(198, 198, 198))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdgenero)
                            .addComponent(cbogenero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rddni)
                            .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(rdtodo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
        jScrollPane1.setViewportView(tbclientes);

        jLabel1.setText("Cantidad de Registros");

        txtcant.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(198, 198, 198)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtcant, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtcant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void rdgeneroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdgeneroActionPerformed
//RADIO BUTON DE ELECCION DE GENERO
    if(rdgenero.isSelected()==true){//OBTENER RESPUESTA SELECCIONADA 
        cbogenero.setSelectedItem(0);//INICIALMENTE CERO 
        cbogenero.setEnabled(true);//FUNCIONA 
        txtdni.setEnabled(false);//SE BLOQUEA PARA EVITAR FALLOS 
        txtdni.setText("");//INCIALMENTE VACIO 
    }
}//GEN-LAST:event_rdgeneroActionPerformed

private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
//BUSCAR LOS DATOS INTRODUCIDOS EN LA BASE DE DATOS 
    String sexo = cbogenero.getSelectedItem().toString();//OBTENER EL GENERO Y PASARLO A STRING
    String dni = txtdni.getText();//OBTENER EL Nº DEL DNI Y PASARLO A TEXTO
    if (rdgenero.isSelected() == true) {//SI HAY SELCCIONADA LA OPCION DE BUSCAR POR GENERO
        DefaultTableModel modelo = new DefaultTableModel();//MODELO DEL CLIENTE
        String[] Titulos = {"CODIGO", "NOMBRES", "APELLIDOS", "SEXO", "DNI", "TELEFONO", "ID", "EMAIL", "DIRECCION"};//COLUMNAS CON SUS TITULOS
        modelo.setColumnIdentifiers(Titulos);//SE CARGAN 
        this.tbclientes.setModel(modelo);//DATOS DEL MODEO 
        try {
            String ConsultaSQL="SELECT * FROM cliente WHERE sexo_cli='"+sexo+"'";//CONSULTA SQL A REALIZAR 
            String []registros= new String[9];//TABLAS QUE SE VAN A MOSTRAR
            Statement st = cn.createStatement();//CREAR LA SENTENCIA SQL 
            ResultSet rs = st.executeQuery(ConsultaSQL);//REALIZAR LA CONSULTA SQL 
            while(rs.next()){//MOSTRAR LOS DATOS EN LAS COLUMNAS, UNA VEZ FINALIZADA UNA, PASA A OTRA 
                registros[0] = rs.getString("cod_cli");
                registros[1] = rs.getString("nom_cli");
                registros[2] = rs.getString("ape_cli");
                registros[3] = rs.getString("sexo_cli");
                registros[4] = rs.getString("dni_cli");
                registros[5] = rs.getString("tel_cli");
                registros[6] = rs.getString("ruc_cli");//REGISTRO UNICO DE CLIENTE, REALMENTE ES UN ID INTERNOs
                registros[7] = rs.getString("email_cli");
                registros[8] = rs.getString("dir_cli");
                modelo.addRow(registros);//METER TODOS LOS MODELOS EN LAS COLUMNAS                        
            }
            tbclientes.setModel(modelo);//MODELO DE LA TABLA CLIENTES
            txtcant.setText(""+tbclientes.getRowCount());//LLENAR CON RESULTADOS 
        } catch (SQLException ex) {Logger.getLogger(ConsultasClientes.class.getName()).log(Level.SEVERE, null, ex);}//MENSAJE ERROR 
   }

    if (rddni.isSelected() == true) {//SI HAY SELECCIONADA LA OPCION DE BUSCAR POR DNI 
        DefaultTableModel modelo = new DefaultTableModel();//SE CARGA EL MODELO DE CLIENTE
        String[] Titulos = {"CODIGO", "NOMBRES", "APELLIDOS", "SEXO", "DNI", "TELEFONO", "ID", "EMAIL", "DIRECCION"};//NOMBRE DE LAS COLUMNAS 
        modelo.setColumnIdentifiers(Titulos);//SE CARGAN 
        this.tbclientes.setModel(modelo);//MOSTRAR LOS DATOS EN LAS COLUMNAS, UNA VEZ FINALIAZA UNA, PASA A OTRA 
        try {
            String ConsultaSQL = "SELECT * FROM cliente WHERE dni_cli='" + dni + "'";//CONSULTA SQL A REALIZAR
            String[] registros = new String[9];//TABLAS QUE SE VAN A MOSTRAR
            Statement st = cn.createStatement();//CREAR LA SENTENCIA SQL 
            ResultSet rs = st.executeQuery(ConsultaSQL);//REALIZAR LA CONSULTA 
            while (rs.next()) {//MOSTRAR LOS DATOS EN LAS COLUMNAS, UNA VEZ FINALIZADA UNA, PASA A OTRA 
                registros[0] = rs.getString("cod_cli");
                registros[1] = rs.getString("nom_cli");
                registros[2] = rs.getString("ape_cli");
                registros[3] = rs.getString("sexo_cli");
                registros[4] = rs.getString("dni_cli");
                registros[5] = rs.getString("tel_cli");
                registros[6] = rs.getString("ruc_cli");
                registros[7] = rs.getString("email_cli");
                registros[8] = rs.getString("dir_cli");
                modelo.addRow(registros);//METER LOS MODELOS EN LS COLUMNAS 
            }
            tbclientes.setModel(modelo);//MODELO DE LA TABLA CLIENTES
            txtcant.setText(""+tbclientes.getRowCount());//LLENAR CON RESULTADOS 
        } catch (SQLException ex) {Logger.getLogger(ConsultasClientes.class.getName()).log(Level.SEVERE, null, ex);}//MENSAJE ERROR 
   }
    if (rdtodo.isSelected() == true) {//SI HAY SELECCIONADA LA OOPCION TODO
        Cargar_lista_clientes();//MOSTRAR TODA LA LISTA DE LOS CLIENTES 
    }
}//GEN-LAST:event_btnbuscarActionPerformed

private void rddniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rddniActionPerformed
//RADIO BUTON PARA BUSCAR POR DNI 
    if (rddni.isSelected() == true) {//OBTENER SELECCIONADO 
        cbogenero.setSelectedIndex(0);//INICIALMENTE CERO
        cbogenero.setEnabled(false);//BOTON BUSCAR POR GENERO DESACTIVADO
        txtdni.setEnabled(true);//TEXTO DEL DNI A BUSCAR ACTIVO
        txtdni.requestFocus();//FOCO EN EL TEXTO DEL DNI A BUSCAR 
    }
}//GEN-LAST:event_rddniActionPerformed

private void rdtodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdtodoActionPerformed
//RADIO BUTON PARA BUSCAR TODO
    Cargar_lista_clientes();//CARGA TODOS LOS CLIENTES 
    txtdni.setText("");//TEXTO DEL DNI VACIO 
    txtdni.setEnabled(false);//BOTON DEL DNI DESACTIVADO 
}//GEN-LAST:event_rdtodoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnbuscar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox cbogenero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rddni;
    private javax.swing.JRadioButton rdgenero;
    private javax.swing.JRadioButton rdtodo;
    private javax.swing.JTable tbclientes;
    private javax.swing.JTextField txtcant;
    private javax.swing.JTextField txtdni;
    // End of variables declaration//GEN-END:variables
//LLAMADA A LA CONEXION CON LA BASE DE DATOS 
conectar cc = new conectar();
    Connection cn = cc.conexion();

}
