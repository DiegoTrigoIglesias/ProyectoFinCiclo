
package Ventanas_gestores;

import Conexion_BBDD.conectar;
import java.sql.*;
import java.util.logging.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author DIEGOT 
 * CONTROLES PARA INTRODUCIR NUEVOS PRODUCTOS EN LA BASE DE DATOS
 * DATOS QUE SE VAN A INTRODUCIR DE ELLOS ACTUALIZACION DE PRODUCTOS BUSQUEDA DE
 * PRODUCTOS YA CREADOS ANTERIORMENTE
 */
public class IngresoProductos extends javax.swing.JInternalFrame {

    DefaultTableModel model;

    //CREACION DEL NUEVO FORMULARIO PARA EL INGRESO DE PRODUCTOS 
    public IngresoProductos() {
        initComponents();
        this.setLocation(150, 15);
        bloquear();
        cargar("");
    }

    void bloquear() {//DESACTIVA LOS ELEMENTOS DE INTRODUCCION DE DATOS 
        txtcod.setEnabled(false);
        txtdes.setEnabled(false);
        txtpre.setEnabled(false);
        txtstock.setEnabled(false);
        btnguardar.setEnabled(false);
        btnnuevo.setEnabled(true);
        btncancelar.setEnabled(false);
        btnactualizar.setEnabled(false);
    }

    void limpiar() {//VACIAR LOS DATOS DE LAS ETIQUETAS DE SEXO 
        txtcod.setText("");
        txtdes.setText("");
        txtpre.setText("");
        txtstock.setText("");
    }

    void desbloquear() {//ACTIVAR LOS ELEMENTOS DE INTRODUCCION DE DATOS 
        txtcod.setEnabled(true);
        txtdes.setEnabled(true);
        txtpre.setEnabled(true);
        txtstock.setEnabled(true);
        btnguardar.setEnabled(true);
        btnnuevo.setEnabled(false);
        btncancelar.setEnabled(true);
    }

    void cargar(String valor) {//BUSQUEDA DE ELEMENTOS EN LA BASE DE DATOS 
        try {
            String[] titulos = {"Codigo", "Descripcion", "Precio", "Stock"};
            String[] registros = new String[4];//REGISTRO DE CADA PROPIEDAD DEL PRODUCTO 
            model = new DefaultTableModel(null, titulos);
            String cons = "select * from producto WHERE CONCAT (descripcion,'',precio) LIKE '%" + valor + "%'";//CONSULTA SQL 
            Statement st = cn.createStatement();//CREAR LA SENTENCIA 
            ResultSet rs = st.executeQuery(cons);//REALIZAR LA CONSULTA 
            while (rs.next()) {//MOSTRAR LOS DATOS EN LA COLUMNA, UNA VEZ FINALIZADA UNA PASA A OTRA 
                registros[0] = rs.getString(1);
                registros[1] = rs.getString(2);
                registros[2] = rs.getString(3);
                registros[3] = rs.getString(4);
                model.addRow(registros);//MTER LOS MODELOS EN LAS COLUMNAS 
            }
            tbproductos.setModel(model);
            tbproductos.getColumnModel().getColumn(0).setPreferredWidth(150);//SE ESTABLE ANCHO PARA LA COLUMNA UNO 
            tbproductos.getColumnModel().getColumn(1).setPreferredWidth(300);//SE ESTABLE ANCHO PARA LA COLUMNA DOS 
            tbproductos.getColumnModel().getColumn(2).setPreferredWidth(100);//SE ESTABLE ANCHO PARA LA COLUMNA TRES 
        } catch (Exception e) {System.out.println(e.getMessage());} //MANEJO DE LA EXCEPCION 
    }

       void BuscarProductoEditar(String cod) {//BUSCAR UN PRODUCTO MEDIANTE SU CODIGO 
        try {
            String codi = "", desc = "", prec = "", stock = "";//PROPIEDADES DEL PRODUCTO A MMOSTRAR 
            String cons = "select * from producto WHERE cod_pro='" + cod + "'";//SENTENCIA SQL 
            Statement st = cn.createStatement();//CREAR LA SENTENCIA 
            ResultSet rs = st.executeQuery(cons);//REALIZAR LA CONSULTA 
            while (rs.next()) {//MOSTRAR DATOS EN COLUMNAS 
                codi = rs.getString(1);
                desc = rs.getString(2);
                prec = rs.getString(3);
                stock = rs.getString(4);
            }//PONER LOS DATOS DONDE CORRESPONDAN 
            txtcod.setText(codi);//PONER EL CODGIO 
            txtdes.setText(desc);//PONER LA DESCRIPCION
            txtpre.setText(prec);//PONER EL PRECIO 
            txtstock.setText(stock);//PONER LA DESCRIPCION 
        } catch (Exception e) {System.out.println(e.getMessage());}//MANEJO DE LA EXCEPCION 
    }

    void codigosproductos() {//ESTABLECER UN ID UNICO PARA CADA PRODUCTO 
        int j;//VARIABLE DE OPERACIONES
        int cont = 1;//CONTADOR INTERNO DE PRODUCTOS 
        String num = "";//NUMERO DE CADA PRODUCTO 
        String c = "";//CODIGO
        String SQL = "select max(cod_pro) from producto";//CONSULTA SQL PAR OBTENER EL COD DEL PRODUCTO 
        try {
            Statement st = cn.createStatement();//CREACION DE LA SENTENCIA 
            ResultSet rs = st.executeQuery(SQL);//REALIZAR LA CONSULTA 
            if (rs.next()) {//MOSTRAR LOS DATOS OBTENIDOS
                c = rs.getString(1);//CAPTURAMOS EL CODIGO DEL PRODUCTO OBTENIDO 
            }
            if (c == null) {//EN CASO DE QUE NO HAYA UN CODIGO CREADO, SE DEFINE EL PRIMERO 
                txtcod.setText("CP0001");//VALOR INICIAL(Cp es codigo de producto)
            } else {//OBTENEMOS LOS CARACTERES POR SU POSICION SE IGNORAN LAS LETRAS 
                char r1 = c.charAt(2);//OBTENER EL VALOR NUMERICO 1
                char r2 = c.charAt(3);//OBTENER EL VALOR NUMERICO 2
                char r3 = c.charAt(4);//OBTENER EL VALOR NUMERICO 3
                char r4 = c.charAt(5);//OBTENER EL VALOR NUMERICO 4
                String valor = "";//NUMERO A OBTENER CON EL CONJUNTO DE VALORES 
                valor = "" + r1 + r2 + r3 + r4;//CREAMOS EL NUEVO CODIGO DEL CLIENTE MEDIANTE LOS NUMEROS OBTENIDOS 
                j = Integer.parseInt(valor);//SE CREA EL NUEVO NUMERO (NO SE TIENEN EN CUENTA LAS LETRAS)
                GenerarCodigos gen = new GenerarCodigos();//SE GENERA EL CODIGO 
                gen.generar(j);//SE OBTIENE EL NUMERO CON SU CODIGO 
                txtcod.setText("CP" + gen.serie());//NUEVO ID DEL PRODUCTO UNICO CREADO (Cp es codigo de producto)
            }
        } catch (SQLException ex) {Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);}//MANEJO DE LA EXCEPCION 
    }
// Este método se llama desde el constructor para inicializar el formulario
//ADVERTENCIA: NO modificar este código.generado por el Editor de formularios
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        mnactualizar = new javax.swing.JMenuItem();
        mneliminar = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtcod = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtdes = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtpre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtstock = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnnuevo = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        btnactualizar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbproductos = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtbuscar = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

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

        mnactualizar.setText("Modificar");
        mnactualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnactualizarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(mnactualizar);

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
        setTitle("REGISTRO DE PRODUCTOS");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle de Producto"));

        jLabel1.setText("Codigo:");

        txtcod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcodActionPerformed(evt);
            }
        });

        jLabel2.setText("Descripcion:");

        txtdes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdesActionPerformed(evt);
            }
        });

        jLabel3.setText("Precio:");

        txtpre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpreActionPerformed(evt);
            }
        });

        jLabel5.setText("Stock:");

        txtstock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtstockActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtpre, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdes, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(txtdes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtpre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtstock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnactualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnnuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnguardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btncancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnnuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnguardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnactualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsalir)
                .addContainerGap(43, Short.MAX_VALUE))
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
        tbproductos.setComponentPopupMenu(jPopupMenu1);
        jScrollPane2.setViewportView(tbproductos);

        jLabel4.setText("Buscar:");

        txtbuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbuscarKeyReleased(evt);
            }
        });

        jButton1.setText("Mostrar Todo");
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
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void txtcodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcodActionPerformed
//ACION DEL EVENTO DE ESCRITURA
    txtcod.transferFocus();//MENTENER EL FOCO EN LO QUE ESCRIBE EL CLIENTE PARA HACER LA BUSQUEDA POR CODIGO 
}//GEN-LAST:event_txtcodActionPerformed

private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
//ACCION DEL BOTON SALIR 
    this.dispose();//SE ELIMINA LA VENTANA 
}//GEN-LAST:event_btnsalirActionPerformed

private void btnnuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnuevoActionPerformed
//ACCION DEL BOTON AÑADIR UN NUEVO PRODUCTO 
    desbloquear();//DESBLOQUEAR LOS BOTONES DE ESCRITURA 
    limpiar();//SE DEJAN SIN TEXTO 
    txtcod.requestFocus();//PONER EL FOCO SIN PUNTERO 
    codigosproductos();//INTRODUCIR CODIGO A ESE PRODUCTO 
}//GEN-LAST:event_btnnuevoActionPerformed

private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
//ACCION DE CANCELAR CUALQUIER ACCION 
    bloquear();// SE BLOQUEAN LOS CAMPOS ESCRITOS 
}//GEN-LAST:event_btncancelarActionPerformed

private void txtdesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdesActionPerformed
//ACCION DE DESCRIPCION DEL PRODUCTO 
    txtdes.transferFocus();//SE MANRIENE EL FOCO EN LO QUE SE ESCRIBE PARA BUSCAR POR DESCRIPCION
}//GEN-LAST:event_txtdesActionPerformed

private void txtpreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpreActionPerformed
// TODO add your handling code here:
    txtpre.transferFocus();//MOVER EL CURSOR CON <ctrl-tab>
}//GEN-LAST:event_txtpreActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
// ACCOIN DEL BOTON DE MOESTRAR LOS RESULTADOS 
    cargar("");//MOSTRAR TODOS LOS PRODUCTOS EN STOCK 
}//GEN-LAST:event_jButton1ActionPerformed

private void txtbuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbuscarKeyReleased
//ACCON DE BUSCAR UN PRODUCTO DETERMINADO
    cargar(txtbuscar.getText());//BUSCAR POR SIMILITUD DE TEXTO 
}//GEN-LAST:event_txtbuscarKeyReleased

private void mneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mneliminarActionPerformed
//ACCION DE ELIMINAR UN PRODUCTO 
    int filasel = tbproductos.getSelectedRow();//OBTENER LA FILA DEL PRODUCTO SELECCIONAO 
    try {
        if (filasel == -1) {//EN CASO DE NO TENER FILA SELECCIONADA
            JOptionPane.showMessageDialog(null, "Seleccione algun dato");//MENSAJE DE AVISO 
        } else {//SI HAY UNA SELECCIONADA 
            String cod = (String) tbproductos.getValueAt(filasel, 0);//OBTEMER EL CODIGO DE ESE PRODUCTO 
            String eliminarSQL = "DELETE FROM producto WHERE cod_pro = '" + cod + "'";//CONSULTA SQL PARA ELIMINAR POR CODIGO 
            try {
                PreparedStatement pst = cn.prepareStatement(eliminarSQL);//SE CREA LA CONSULTA 
                pst.executeUpdate();//SE EJECUTA EL BORRADO
                JOptionPane.showMessageDialog(null, "Borrado");//MENSAJE DE AVISO PARA EL USUARIO 
                cargar("");//RECARGA LA TABLA 
            } catch (Exception e) {JOptionPane.showMessageDialog(null, e);}//MANEJO DE LA EXCEPCION 
        }
    } catch (Exception e) {System.out.println(e);}//si algo falla mensaje de error 
}//GEN-LAST:event_mneliminarActionPerformed

private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
//GUARDAR LOS DATOS DE UN NUEVO PRODUCTO 
    String cod, des, pre, stock;//DATOS QUE SE VAN A GUARDAR 
    String sql = "";//CONSULTA SQL
    //OBTENER LOS DATOS ESCRITOS POR EL USUARIO 
    cod = txtcod.getText();//OBTENER EL CODIGO DEL PRODUCTO NUEVO
    des = txtdes.getText();//OBTENER LA DESCRIPCION DEL PRODUCTO NUEVO 
    pre = txtpre.getText();//OBTENER EL PRECIO DEL PRODUCTO NUEVO
    stock = txtstock.getText();//OBTENER EL STOCK DEL PRODUCTO NUEVO
    sql = "INSERT INTO producto (cod_pro,descripcion,precio,Stock) VALUES (?,?,?,?)";//CONSULTA SQL PARA GUARDAR LOS DATOS 
    try {//INSERCION DE LOS DATOS EN LA COLUMNA CORRESPONDIENTE 
        PreparedStatement pst = cn.prepareStatement(sql);//SE CREA LA SENTENCIA 
        pst.setString(1, cod);//INTRODUCIR EN LA BBDD EL CODIGO DEL PRODUCTO NUEVO 
        pst.setString(2, des);//INTRODUCIR EN LA BBDD LA DESCRIPCION DEL PRODUCTO NUEVO 
        pst.setString(3, pre);//INTRODUCIR EN LA BBDD EL PRECIO DEL PRODUCTO NUEVO 
        pst.setString(4, stock);//INTRODUCIR EN LA BBDD EL STOCK DEL PRODUCTO NUEVO 
        int n = pst.executeUpdate();//ACTUALIZAR LA BBDD
        if (n > 0) {//SI  NO QUEDA NINGUN CAMPO VACIO 
            JOptionPane.showMessageDialog(null, "Registro Guardado con Exito");//TODO CORRECTO MENSAJE DE AVISO
            bloquear();//BLOQUEAR LAS OPCIONES DE ESCRITURA 
        }
        cargar("");//RECARGAR LA TABLA 
    } catch (SQLException ex) {Logger.getLogger(IngresoProductos.class.getName()).log(Level.SEVERE, null, ex);}//MANEJO DE LA EXCEPCION 
}//GEN-LAST:event_btnguardarActionPerformed

private void mnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnactualizarActionPerformed
//ACTUALIZAR LOS DATOS DE ALGUN PRODUCTO 
    try {
        int filaMod = tbproductos.getSelectedRow();//OBTENER LA FILA DEL PRODUCTO SELECCIONADO 
        if (filaMod == -1) {//SI NO HAY NINGUNA SELECCIONADA 
            JOptionPane.showMessageDialog(null, "Seleccione alguna fila");//MENSAJE DE AVISO 
        } else {//SI HAY ALGUNA SELECCIONADA
            desbloquear();//SE DESBLOQUEAN LAS OPCIONES DE ESCRITURA 
            btnactualizar.setEnabled(true);//BOTON DE ACTUALIZAR ACTIVADO 
            btnguardar.setEnabled(false);//DESCATIVAR EL BOTON GUARDAR PARA QUE NO FALLE
            String cod = (String) tbproductos.getValueAt(filaMod, 0);//OBTENER EL CODIGO DE ESE PRODUCTO 
            BuscarProductoEditar(cod);//SE MODIFICICAN LOS DATOS DEL PRODUCTO MEDIANTE SU CODIGO IDENTIFICADOR 
        }
    } catch (Exception e)  {System.out.println(e);}//si algo falla mensaje de error
}//GEN-LAST:event_mnactualizarActionPerformed

private void btnactualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnactualizarActionPerformed
//ACCION DEL BOTON ACTUALIZAR 
//CONSULTA SQL PARA HACER LA ACTUALIZACION MEDIANTE LOS NUEVOS DATOS INTRODUCIDOS 
    String sql = "UPDATE producto SET precio = '" + txtpre.getText() //ACTUALIZAR EL PRECIO
        + "',descripcion ='" + txtdes.getText() //ACTUALIZAR LA DESCRIPCION
        + "',Stock = '" + txtstock.getText() //ACTUALIZAR EL STOCK
        + "' WHERE cod_pro = '" + txtcod.getText() + "'";//CUANDO EL CODIGO DEL PRODUCTO COINCIDA
    try {
        PreparedStatement pst = cn.prepareStatement(sql);//CREACION DE LA SENTENCIA 
        pst.executeUpdate();//ACTUALIZACION DE LA BBDD
        JOptionPane.showMessageDialog(null, "Actualizado"); //MENSAJE DE AVISO 
        cargar("");//CARGAR LOS DATOS 
        bloquear();//BLOQUEAR LA ESCRITURA
        limpiar();//BORRR CONTENIDO DE LOS CAMPOS DE ESCRITURA 
    } catch (Exception e) {JOptionPane.showMessageDialog(null, e);}//MANEJO DE LA EXCEPCION 
}//GEN-LAST:event_btnactualizarActionPerformed

private void txtstockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtstockActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtstockActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnactualizar;
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnnuevo;
    private javax.swing.JButton btnsalir;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenuItem mnactualizar;
    private javax.swing.JMenuItem mneliminar;
    private javax.swing.JTable tbproductos;
    private javax.swing.JTextField txtbuscar;
    private javax.swing.JTextField txtcod;
    private javax.swing.JTextField txtdes;
    private javax.swing.JTextField txtpre;
    private javax.swing.JTextField txtstock;
    // End of variables declaration//GEN-END:variables
//CONEXION CON LA BASE DE DATOS 
conectar cc = new conectar();
    Connection cn = cc.conexion();
}
