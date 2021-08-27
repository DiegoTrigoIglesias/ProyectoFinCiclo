package Ventanas_gestores;

import Conexion_BBDD.conectar;

import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author DIEGOT CONTROLES PARA INTRODUCIR NUEVOS CLIENTES EN LA BASE DE DATOS
 * DATOS QUE SE VAN A INTRODUCIR DE ELLOS ACTUALIZACION DE CLIENTES BUSQUEDA DE
 * CLIENTES YA CREADOS ANTERIORMENTE
 */
public class IngresoCliente extends javax.swing.JInternalFrame {
    DefaultTableModel model;
    //CREAR UN NUEVO FORMULARIO PARA EL INGRESO DE LOS CLIENTES 
    public IngresoCliente() {
        initComponents();
        bloquear();
        cargar("");
    }

    void bloquear() {//DESACTIVA LOS ELEMENTOS DE INTRODUCCION DE DATOS 
        txtcod.setEnabled(false);
        txtnom.setEnabled(false);
        txtape.setEnabled(false);
        txtdir.setEnabled(false);
        txtemail.setEnabled(false);
        txttel.setEnabled(false);
        txtruc.setEnabled(false);
        txtdni.setEnabled(false);
        cbosexo.setEnabled(false);
        btnguardar.setEnabled(false);
        btnnuevo.setEnabled(true);
        btncancelar.setEnabled(false);
        btnactualizar.setEnabled(false);
    }

    void limpiar() {//VACIAR LOS DATOS DE LOS ELEMENTOS DE TEXTO 
        txtcod.setText("");
        txtnom.setText("");
        txtdir.setText("");
        txtdni.setText("");
        txtemail.setText("");
        txtruc.setText("");
        txttel.setText("");
        txtape.setText("");
    }

    void desbloquear() {//ACTIVA LOS ELEMENTOS DE INTRODUCCION DE DATOS 
        txtcod.setEnabled(true);
        txtnom.setEnabled(true);
        txtape.setEnabled(true);
        txtdir.setEnabled(true);
        txtemail.setEnabled(true);
        txttel.setEnabled(true);
        txtruc.setEnabled(true);
        txtdni.setEnabled(true);
        cbosexo.setEnabled(true);
        btnguardar.setEnabled(true);
        btnnuevo.setEnabled(false);
        btncancelar.setEnabled(true);
        btnactualizar.setEnabled(false);
    }

    void cargar(String valor) {//BUSQUEDA DE LOS ELEMENTOS EN LA BASE DE DATOS
        String mostrar = "SELECT * FROM cliente WHERE CONCAT(cod_cli,nom_cli,ape_cli,dni_cli,ruc_cli) LIKE '%" + valor + "%'";//CONSULTA SQL 
        String[] titulos = {"Codigo", "Nombres", "Apellidos", "Sexo", "DNI", "Telefono", "ID", "Email", "Direccion"};//TITULOS DE CADA COLUMNA 
        String[] Registros = new String[9];//NUMERO DE TABLAS A OBTENER 
        model = new DefaultTableModel(null, titulos);//MODELO POR DEFECTO DE LA TABLA 
        try {
            Statement st = cn.createStatement();//CREAR LA SENTENCIA SQL 
            ResultSet rs = st.executeQuery(mostrar);//REALIZAR LA CONSULTA SQL 
            while (rs.next()) {//MOSTRAR LOS DATOS EN LAS COLUMNAS, UNA VEZ FINALIZADA UNA, PASA A OTRA 
                Registros[0] = rs.getString("cod_cli");
                Registros[1] = rs.getString("nom_cli");
                Registros[2] = rs.getString("ape_cli");
                Registros[3] = rs.getString("sexo_cli");
                Registros[4] = rs.getString("dni_cli");
                Registros[5] = rs.getString("tel_cli");
                Registros[6] = rs.getString("ruc_cli");
                Registros[7] = rs.getString("email_cli");
                Registros[8] = rs.getString("dir_cli");
                model.addRow(Registros);//METER TODOS LOS MODELOS EN LAS COLUMNAS 
            }
            tbclientes.setModel(model);//CREAR MODELO DE LA TABLA CLIENTES 
        } catch (SQLException ex) {
            Logger.getLogger(IngresoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }//MANEJO DE LAS EXCEPCIONES
    }

    void codigosclientes() {//TENER UN CODIGO IDENTIFICADOR PARA CADA CLIENTE 
        int j;
        int cont = 1;//CONTADOR INTERNO 
        String num = "";//NUMERO DE CLIENTE 
        String c = "";//CODIGO
        String SQL = "select max(cod_cli) from cliente";//CONSULTA SQL 
        try {
            Statement st = cn.createStatement();//CREAR LA SENTENCIA SQL 
            ResultSet rs = st.executeQuery(SQL);//REALIZAR LA CONSULTA SQL
            if (rs.next()) {//MOSTRAR LOS DATOS EN LAS COLUMNAS, UNA VEZ FINALIZADA UNA, PASA A OTRA 
                c = rs.getString(1);//RECORRER LOS CODIGOS DE LOS CLIENTES 
            }
            if (c == null) {//EN CASO DE QUE EL CODIGO SEA NULO 
                txtcod.setText("CC0001");//DEFINIMOS UN PRIMER CODIGO 
            } else {//EN CASO DE QUE TODO VAYA BN ESCRIBE EL NUEVO CODGO
                char r1 = c.charAt(2);
                char r2 = c.charAt(3);
                char r3 = c.charAt(4);
                char r4 = c.charAt(5);
                String r = "";
                r = "" + r1 + r2 + r3 + r4;//CONJUNTO DE VALOES DEL CODIGO
                j = Integer.parseInt(r);//PASAMOS EL CODGIO A NUMERO
                GenerarCodigos gen = new GenerarCodigos();//LLAMAMOS A GENERAR CODIGO 
                gen.generar(j);// SE GENERA 
                txtcod.setText("CC" + gen.serie());//GENERAR NUMEROS EN SERIE 
            }
        } catch (SQLException ex) {
            Logger.getLogger(IngresoCliente.class.getName()).log(Level.SEVERE, null, ex);
        }//MANEJO DE LA EXCEPCION} 
    }
// Este método se llama desde el constructor para inicializar el formulario
//ADVERTENCIA: NO modificar este código.generado por el Editor de formularios
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        mnmodificar = new javax.swing.JMenuItem();
        mneliminar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtcod = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtnom = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtape = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtdni = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbosexo = new javax.swing.JComboBox();
        txttel = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtemail = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtdir = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtruc = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btnactualizar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbclientes = new javax.swing.JTable();
        txtbuscar = new javax.swing.JTextField();
        btnbuscar = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        mnmodificar.setText("Modificar");
        mnmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnmodificarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnmodificar);

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
        setTitle("REGISTRO DE CLIENTES");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle Cliente"));

        jLabel1.setText("Codigo:");

        jLabel2.setText("Nombres:");

        txtnom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnomKeyTyped(evt);
            }
        });

        jLabel3.setText("Apellidos:");

        txtape.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtapeKeyTyped(evt);
            }
        });

        jLabel4.setText("DNI:");

        txtdni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdniActionPerformed(evt);
            }
        });
        txtdni.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtdniKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtdniKeyTyped(evt);
            }
        });

        jLabel5.setText("Sexo:");

        cbosexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "M", "F" }));

        txttel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txttelKeyTyped(evt);
            }
        });

        jLabel6.setText("Telefono:");

        jLabel7.setText("Email:");

        jLabel8.setText("Direccion:");

        jLabel9.setText("ID:");

        txtruc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtrucKeyTyped(evt);
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
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbosexo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtnom, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel9))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtape, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(txttel, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtruc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtdir, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtemail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbosexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txttel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtdir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnnuevo.setText("Nuevo");
        btnnuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnuevoActionPerformed(evt);
            }
        });

        btnguardar.setText("Guardar");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnactualizar.setText("Actualizar");
        btnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnactualizarActionPerformed(evt);
            }
        });

        btncancelar.setText("Cancelar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnguardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btncancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnactualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnnuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(btnnuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnguardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnactualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnsalir)
                .addContainerGap())
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
        tbclientes.setComponentPopupMenu(jPopupMenu1);
        jScrollPane2.setViewportView(tbclientes);

        txtbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbuscarActionPerformed(evt);
            }
        });
        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        btnbuscar.setText("Mostrar Todos");

        jLabel10.setText("BUSCAR:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnbuscar)
                .addContainerGap(359, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 662, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnbuscar)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
////ACCION DEL BOTON DE ACTUALIZAR LOS DATOS DE ALGUN CLIENTE 
    try {
        PreparedStatement pst = cn.prepareStatement("UPDATE cliente SET nom_cli='" + txtnom.getText()//CONSULTA SQL CON LOS NUEVOS DATOS SEGUN EL NOMBRE  
                + "',ape_cli='" + txtape.getText()//OBTENEMOS EL NUEVO APELLIDO ESCRITO Y LO MODIFICAMOS
                + "',sexo_cli='" + cbosexo.getSelectedItem()//OBTENEMOS EL NUEVO SEXO ESCRITO Y LO MODIFICAMOS 
                + "',dni_cli='" + txtdni.getText()//OBTENEMOS EL NUEVO DNI ESCRITO Y LO MODIFICAMOS 
                + "',tel_cli='" + txttel.getText()//OBTENEMOS EL NUEVO TELEFONO ESCRITO Y LO MODIFICAMOS 
                + "',ruc_cli='" + txtruc.getText()//OBTENEMOS EL NUEVO CODIGO ESCRITO Y LO MODIFICAMOS 
                + "',email_cli='" + txtemail.getText()//OBTENEMOS EL NUEVO MAIL ESCRITO Y LO MODIFICAMOS
                + "',dir_cli='" + txtdir.getText()//OBTENEMOS LA NUEVA DIRECCON ESCRITA Y LO MODIFICAMOS 
                + "' WHERE cod_cli='" + txtcod.getText() + "'");//OBTENEMOS EL NUEVO CODIGO ESCRITO Y LO MODIFICAMOS 
       pst.executeUpdate();//SE EJECUTA LA ACTUALIZACION
        JOptionPane.showMessageDialog(null, "Actualizado");//MENSJAE DE ACTUALIZADO
        cargar("");//CARGAR LOS DATOS 
        bloquear();//LLAMADA A BLOQUEAR LOS CAMPOS 
    } catch (Exception e) {System.out.print(e.getMessage());}//MANEJO DE LA EXCEPCION 
}//GEN-LAST:event_btnactualizarActionPerformed

private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
//ACCION DEL BOTON SALIR
    this.dispose();//SE ELIMINA LA VENTANA DEL JFRAME 
}//GEN-LAST:event_btnsalirActionPerformed

private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
//ACCION DEL BOTON AÑADIR UN NUEVO CLIENTE 
    desbloquear();//SE DES BLOQUEAN LOS CAMPOS ESCRITOS 
    limpiar();//SE DEJAN SIN TEXTO 
    codigosclientes();// SE AÑADE UN CODIGO AL NUEVO CLIENTE 
    txtcod.requestFocus();//PONER EL FOCO SIN PUNTERO 
}//GEN-LAST:event_btnnuevoActionPerformed

private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
//ACCION DE CANCELAR CUALQUIER ACCION 
    bloquear();// SE BLOQUEAN LOS CAMPOS ESCRITOS 
}//GEN-LAST:event_btncancelarActionPerformed

private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
//ACCION DE GUARDAR UN CLIENTE 
    String cod, dir, nom, ape, tel, sex, dni, email, ruc;//DATOS DEL CLIENTE A GUARDAR 
    String sql = "";//CONSULTA SQL 
    //OBTENER LOS DATOS ESCRITOS POR EL USUARIO 
    cod = txtcod.getText();
    nom = txtnom.getText();
    ape = txtape.getText();
    dir = txtdir.getText();
    tel = txttel.getText();
    sex = cbosexo.getSelectedItem().toString();
    ruc = txtruc.getText();
    email = txtemail.getText();
    dni = txtdni.getText();
    sql = "INSERT INTO cliente (cod_cli,nom_cli,ape_cli,sexo_cli,dni_cli,tel_cli,ruc_cli,email_cli,dir_cli) VALUES (?,?,?,?,?,?,?,?,?)";//SENTENCIA SQL 
    try {//INSERCION DE LOS DATOS EN SU COLUMNA CORRESPONDIENTE 
        PreparedStatement pst = cn.prepareStatement(sql);//CONSULTA SQL
        pst.setString(1, cod);
        pst.setString(2, nom);
        pst.setString(3, ape);
        pst.setString(4, sex);
        pst.setString(5, dni);
        pst.setString(6, tel);
        pst.setString(7, ruc);
        pst.setString(8, email);
        pst.setString(9, dir);

        int n = pst.executeUpdate();//ACTUALIZACION DE LA BBDD
        if (n > 0) {//SI NO QUEDA NINGUN CAMPO VACIO 
            JOptionPane.showMessageDialog(null, "Registro Guardado con Exito");//MENSAJE DE TODO BIEN
            bloquear();//BLOQUEAR CAMPOS DE ESCRITURA 
        }
        cargar("");///RECARGAR LA TABLA 
    }catch (SQLException ex) {Logger.getLogger(IngresoProductos.class.getName()).log(Level.SEVERE, null, ex);} //MANEJO DE EXCEPCION 
}//GEN-LAST:event_btnguardarActionPerformed

private void txtbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbuscarActionPerformed
//ACCION DEL BOTON BUSCAR CLIENTE 
}//GEN-LAST:event_txtbuscarActionPerformed

private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
//BUSCAR CLIENTE MEDIANTE DATOS INTRDUCIDOS 
    cargar(txtbuscar.getText());//BUSCAR POR SIMILITUD DE TEXTO 
}//GEN-LAST:event_txtbuscarKeyReleased

private void txtdniKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdniKeyPressed
//ASOCIACION DEL DNI CON EL CLIENTE
}//GEN-LAST:event_txtdniKeyPressed

private void txtdniKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtdniKeyTyped
//CONTROL DE LOS DATOS DEL DNI SON INTRODUCIDOS CORRESTAMENTE 
    char car = evt.getKeyChar();
    if (txtdni.getText().length() >= 8) {//COMPRBAR MAXIMMO Nº DE CARACTERES
        evt.consume();//CAPTURA DE TECLAS DE DATOS INTRODUCIDOS 
    }
    if ((car < '0' || car > '9')) {//AQUI SI SE TIENE EN CUENTA LA LETRA 
        evt.consume();//CAPTURADO DE LETRA 
    }
}//GEN-LAST:event_txtdniKeyTyped

private void txttelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttelKeyTyped
//CONTROL DEL TELEFONO INTRODUCIDO 
    char car = evt.getKeyChar();
    if (txttel.getText().length() >= 9) {//COMPRBAR MAXIMMO Nº DE CARACTERES
        evt.consume();//CAPTURA DE TECLAS DE DATOS INTRODUCIDOS 
    }
    if ((car < '0' || car > '9')) {//TENEMOS EN CUENTA LA LOGITUD MINIMA 
        evt.consume();//CAPTURADO DE ELEMENTOS 
    }
}//GEN-LAST:event_txttelKeyTyped

private void txtrucKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtrucKeyTyped
//CONTROL DEL IDENTIFICACOR INTRODUCIDO 
    char car = evt.getKeyChar();
    if (txtruc.getText().length() >= 11) {//COMPRBAR MAXIMMO Nº DE CARACTERES
        evt.consume();//CAPTURAR LAS TELCAS DE LOS DATOS INTRODUCIDOS 
    }
    if ((car < '0' || car > '9')) {//TENER EN CUENTA LA LONGITUD 
        evt.consume();//CAPTURADO TECLAS 
    }
}//GEN-LAST:event_txtrucKeyTyped

private void txtnomKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnomKeyTyped
//ACCION DE OBTENER LOS DATOS DEL NOMBRE 
    char car = evt.getKeyChar();//OBTENER LOS CARACTERES COMO TEXTO 
    if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') && (car != (char) KeyEvent.VK_SPACE)) {// TENER EN CUENTA (mayus-minus-espacio)
        evt.consume();//CAPTURADO DE ELEMENTOS 
    }
}//GEN-LAST:event_txtnomKeyTyped

private void txtapeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapeKeyTyped
// ACCION DE OBTENER LOS DATOS DEL APELLIDO 
    char car = evt.getKeyChar();//OBTENER CARACTERES COMO TEXTO 
    if ((car < 'a' || car > 'z') && (car < 'A' || car > 'Z') && (car != (char) KeyEvent.VK_SPACE)) {// TENER EN CUENTA (mayus-minus-espacio)
        evt.consume();//CAPTURADO DE ELEMENTOS 
    }
}//GEN-LAST:event_txtapeKeyTyped

private void txtdniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdniActionPerformed
//DNI ESCRITO POR EL USUARIO 
}//GEN-LAST:event_txtdniActionPerformed

private void mneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mneliminarActionPerformed
//ACCCION DE ELIMINAR UN CLIENTE
    int fila = tbclientes.getSelectedRow();//OBTENER EL CLIENTE SLECCIONADO 
    String cod = "";
    cod = tbclientes.getValueAt(fila, 0).toString();//VALORES DE LA FILA DE ESE CLIENTE 
    if (fila >= 0) {
        try {
            PreparedStatement pst = cn.prepareStatement("DELETE FROM cliente WHERE cod_cli='" + cod + "'");//SENTENCIA SQL DE BORRADO
            pst.executeUpdate();//SE EJECUTA LA SENTENCIA 
            JOptionPane.showMessageDialog(null, "Borrado");//MENSAJE DE CONFIRMACION DEL BORRADO 
            cargar("");//RECARGA LA TABLA DE CLIENTES 
        } catch (SQLException ex) {Logger.getLogger(IngresoCliente.class.getName()).log(Level.SEVERE, null, ex);}//MANEJO DE LA EXCEPCION
    } else {//SI ALGO FALA MENSAJE DE AVISO 
        JOptionPane.showMessageDialog(this, "No ha selecionada ninguna fila");
    }
}//GEN-LAST:event_mneliminarActionPerformed

private void mnmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnmodificarActionPerformed
//ACCION DE MODIFICAR LOS DATOS DE UN CLIENTE 
    desbloquear();//SE DESBLOQUEAN LOS CAMPOS DE ESCRITURA 
    btnactualizar.setEnabled(true);//SE ACTIVA EL BOTON ACTUALIZAR 
    btnguardar.setEnabled(false);//DESCATIVAR EL BOTON GUARDAR PARA QUE NO FALLE 
    int filamodificar = tbclientes.getSelectedRow();//RECORRE LAS FILAS Y ACTUALIZA LOS DATOS NECESARIOS 
    if (filamodificar >= 0) {//EN CASO DE QUE HAYA UNA FILA SELECCIONADA MODIFICAMOS VALORES
        //METODO MAS FACIL QUE IR ACTUALIZANDO A¡LA BASE DE DARTOS CADA VEZ QUE SE HACE UNA MODIFICACION DE LOS DATOS DEL CLIENTE 
        txtcod.setText(tbclientes.getValueAt(filamodificar, 0).toString());//OBTENER EL NUEVO VALOR DEL CODIGO DEL CLIENTE Y MODIFICAR EL ELEMENTO 1 DE LA TABLA 
        txtnom.setText(tbclientes.getValueAt(filamodificar, 1).toString());//OBTENER EL NUEVO VALOR DEL NOMBRE DEL CLIENTE Y MODIFICAR EL ELEMENTO 2 DE LA TABLA 
        txtape.setText(tbclientes.getValueAt(filamodificar, 2).toString());//OBTENER EL NUEVO VALOR DEL APELLIDO DEL CLIENTE Y MODIFICAR EL ELEMENTO 3 DE LA TABLA 
        cbosexo.setSelectedItem(tbclientes.getValueAt(filamodificar, 3).toString());//OBTENER EL NUEVO VALOR DEL SEXO DEL CLIENTE Y MODIFICAR EL ELEMENTO 3 DE LA TABLA 
        txtdni.setText(tbclientes.getValueAt(filamodificar, 4).toString());//OBTENER EL NUEVO VALOR DEL DNI DEL CLIENTE Y MODIFICAR EL ELEMENTO 4 DE LA TABLA 
        txttel.setText(tbclientes.getValueAt(filamodificar, 5).toString());//OBTENER EL NUEVO VALOR DEL TELEFONO DEL CLIENTE Y MODIFICAR EL ELEMENTO 5 DE LA TABLA 
        txtruc.setText(tbclientes.getValueAt(filamodificar, 6).toString());//OBTENER EL NUEVO VALOR DEL ID DEL CLIENTE Y MODIFICAR EL ELEMENTO 6 DE LA TABLA 
        txtemail.setText(tbclientes.getValueAt(filamodificar, 7).toString());//OBTENER EL NUEVO VALOR DEL EMAIL DEL CLIENTE Y MODIFICAR EL ELEMENTO 7 DE LA TABLA 
        txtdir.setText(tbclientes.getValueAt(filamodificar, 8).toString());//OBTENER EL NUEVO VALOR DE LA DIRECCION DEL CLIENTE Y MODIFICAR EL ELEMENTO 8 DE LA TABLA 
    } else {//SI NO HAY NINGUNA FILA SELECCIONADA
        JOptionPane.showMessageDialog(this, "No ha seleccionado ");//MENSAJE DE AVISO 
    }
}//GEN-LAST:event_mnmodificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JComboBox cbosexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem mneliminar;
    private javax.swing.JMenuItem mnmodificar;
    private javax.swing.JTable tbclientes;
    private javax.swing.JTextField txtape;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtcod;
    private javax.swing.JTextField txtdir;
    private javax.swing.JTextField txtdni;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtnom;
    private javax.swing.JTextField txtruc;
    private javax.swing.JTextField txttel;
    // End of variables declaration//GEN-END:variables
//CONEXION CON LA BASE DE DATOS 
 conectar cc = new conectar();
    Connection cn = cc.conexion();
}
