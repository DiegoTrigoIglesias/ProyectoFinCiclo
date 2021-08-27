package Ventanas_gestores;

import Conexion_BBDD.conectar;
import java.awt.Color;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author DIEGOT DATOS Y ELEMENTOS QUE VA A TENER LA FACTURA A LA HORA DE
 * CREARSE ACCIONES QUE PODREMOS REALIZAR CON ESA FACTURA
 * LA FECHA Y EL NUMERO DE RECIBO SE INCREMENTAN AUTOMATICAMENTE 
 * LOS DATOS DE Nombre DNI Codigo y Direccion SE AUTOCOMPLETAN CON UNA CONSULTA SQL REALIZADA MEDIANTE Clientes_factura 
 * A LA HORA DE SELECCIONAR LOS PRODUCTOS SE HACE MEIDANTE Productos_facturas 
 */
public class Factura extends javax.swing.JInternalFrame {

    //CREACION PARA EL NUEVO FORMULARIO DE LA FACTURA 
    public Factura() {
        initComponents();
        this.setLocation(25, 15);//LOCALIZACION EN LA PANTALLA 
        txtfac.setEnabled(false);//TEXTO AUTOCOMPLETABLE (NUNMERO AUTO INCREMENTAL DE FACTURA)
        txtfec.setEnabled(false);//TEXTO AUTOCOMPLETABLE (NUMERO AUTOINCREMENTAL DE FECHA)
        txtfec.setDisabledTextColor(Color.blue);//TEXTO PARA LA FECHA EN COLOR AZUL 
        txtfec.setText(fechaactual());//FECHA DEL DIA ACTUAL 
        numeros();//CONTADOR DE CADA FACTURA 
    }

    void descontarstock(String codi, String can) {//DESCONTAR PRODUCTOS DEL ALMACEN CADA VEZ QUE SE REALICE UNA VENTA 
        int des = Integer.parseInt(can);//CANTIDAD DE PRODUCTO A DESCONTAR 
        String cap = "";//CAPACIDAD DEL ALMACEN 
        int desfinal;//CANTIDAD FINAL CON EL DESCUENTO 
        String consul = "SELECT * FROM producto WHERE  cod_pro='" + codi + "'";//SENTENCIA SQL PARA OBTENER EL PRODUCTO 
        try {
            Statement st = cn.createStatement();//CREAR LA SENTENCIA 
            ResultSet rs = st.executeQuery(consul);//REALIZAR LA SENTENCIA 
            while (rs.next()) {//MOSTRAR LOS DATOS EN LAS COLUMNAS, UNA VEZ FINALIZADA UNA, PASA A OTRA 
                cap = rs.getString(4);//RESULTADOS EN STRING DE LOS DATOS DEL PRODUCTO 
            }
        } catch (Exception e) {System.out.println(e);}//MANEJO DE LA EXCEPCION 
        desfinal = Integer.parseInt(cap) - des;//REALIZAR EL CALCULO DE LA CAPACIDAD DE PRODUCTOS MENOS LA VENTA 
        String modi = "UPDATE producto SET Stock='" + desfinal + "' WHERE cod_pro = '" + codi + "'";//ACTUALIZAR EL STOCK CON LA CANTIDAD DESCONTADA 
        try {
            PreparedStatement pst = cn.prepareStatement(modi);//CREAR LA SENTENCIA CON LA MODIFICACION
            pst.executeUpdate();//ACTUALIZAR LA BASE DE DATPS 
        } catch (Exception e) {System.out.println(e);}//MANEJO DE LA EXCEPCION 
    }
    void numeros() {//CREAR UN IDENTIFICADOR PARA CADA FACTURA 
        int j;
        int cont = 1;
        String num = "";
        String c = "";
        String SQL = "select max(num_fac) from factura";//SELECCIONAR LA ULTIMA FACTURA GENERADA 
        try {
            Statement st = cn.createStatement();//CREAR LA SENTENCIA SQL 
            ResultSet rs = st.executeQuery(SQL);//REALIZAR LA SENTENCIA 
            if (rs.next()) {//MOSTRAR LOS DATOS EN LAS COLUMNAS, UNA VEZ FINALIZADA UNA, PASA A OTRA 
                c = rs.getString(1);//RESULTADOS A STRING DE LOS DATOS DE LA FACTURA 
            }
            if (c == null) {//EN CASO DE QUE NO HAYA UNA FACTURA ANTERIOR
                txtfac.setText("00000001");//SE CREA EL PRIMER NUMERO DE FACTURA 
            } else {//SI  NO 
                j = Integer.parseInt(c); //SE PASA EL ID DE LA FACTURA 
                GenerarNumero gen = new GenerarNumero();//SE CREA UN NUMRO Nº DE FACTURA 
                gen.generar(j);//LLAMADA A GENERAR
                txtfac.setText(gen.serie());//SE LE PONE A LA FACTURA ESE NUEVO NUMERO 
            }
        } catch (SQLException ex) {Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);} //MANEJO DE LA EXCEPCION
    }
    void calcular() {//FUNCION PARA CALCULAR EL VALOR TOTAL DEL IMPORTE 
        String pre;
        String can;
        double impuesto = 0;//impuesto
        double total = 0;//precio final 
        double subtotal = 0;//suma total del precio de productos 
        double precio;//precio unitario del producti
        int cantidad;//cantidad de producto que se va a comprar 
        double imp = 0.0;//impuesto 

        for (int i = 0; i < tbdet.getRowCount(); i++) {//RECORRER TODOS LOS ELEMENTOS DE LA FACTURA 
            pre = tbdet.getValueAt(i, 2).toString();//OBTENER EL PRECIO
            can = tbdet.getValueAt(i, 3).toString();//OBTENER LA CANTIDAD 
            precio = Double.parseDouble(pre);//PARSEAR DATO PARA EL CALCULO
            cantidad = Integer.parseInt(can);//PARSEAR DATO PARA EL CALCULO
            imp = precio * cantidad;//MULTIPLICAR PRECIO POR CANRTIDAD 
            subtotal = subtotal + imp;//SUMAR EL SUBTOTAL CON EL IMPORTE
            impuesto = subtotal * 0.21;//SUMAR EL IVA 21%
            total = subtotal + impuesto;//PRECIO FINAL DE LA FACTURA
            tbdet.setValueAt(Math.rint(imp * 100) / 100, i, 4);//PONER EN LA TABLA LOS CALCULOS REALIZADOS 
        }
        txtsubtotal.setText(Double.toString(subtotal));//PONER COMO STRING EL SUBTOTAL 
        txtigv.setText("" + Math.rint(impuesto * 100) / 100);//PONER EL IMPUESTO COMO DECIMAL DE DOS DIGITOS
        txttotal.setText("" + Math.rint(total * 100) / 100);//PONER EL TOTAL CON MAXIMO DOS DIGITOS 
    }

    void factura() {//CREACION DE LA FACTURA COMO TAL 
        String InsertarSQL = "INSERT INTO factura (num_fac,cod_cli,ruc_cli,subtotal,igv,total,fec_fac) VALUES (?,?,?,?,?,?,?)";//SENTENCIA SQL PARA INSERTAR LOS DATOS 
        //OBTENER LOS DATOS INTRODUCIDOS EN LAS ETIQUETAS DE TEXTO 
        String numfac = txtfac.getText();
        String codcli = txtcod.getText();
        String ruccli = txtruc.getText();
        String subtotal = txtsubtotal.getText();
        String igv = txtigv.getText();
        String total = txttotal.getText();
        String fecha = txtfec.getText();
        try {
            PreparedStatement pst = cn.prepareStatement(InsertarSQL);//SENTENCIA DE INGRESO 
            //INSERTAR LOS DATOS EN CADA COLUMNA DE LA RTABLA 
            pst.setString(1, numfac);
            pst.setString(2, codcli);
            pst.setString(3, ruccli);
            pst.setString(4, subtotal);
            pst.setString(5, igv);
            pst.setString(6, total);
            pst.setString(7, fecha);
            int n = pst.executeUpdate();//ACTUALIZAR LA TABLA 
            if (n > 0) {//SI NO QUEDA NINGUNA COLUMNA VACIA 
                JOptionPane.showMessageDialog(null, "Los datos se guardaron correctamente");//MENSAJE DE AVIOS 
            }
        } catch (SQLException ex) {Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);}//MANEJO DE LA EZCEPCION
    }

    void detallefactura() {//ELEMENTOS QUE VAN A FORMAR PARTE DE LA FACTURA 
        for (int i = 0; i < tbdet.getRowCount(); i++) {//RECORRER TODOS LOS ELEMENTOS DE LA TABLA
            String InsertarSQL = "INSERT INTO detallefactura(num_fac,cod_pro,des_pro,cant_pro,pre_unit,pre_tot) VALUES (?,?,?,?,?,?)";//SENCENCIA SQL 
            //VALORES DE LAS COLUMNAS A LA HORA DE LA FACTURA DE LOS PRODUCTOS 
            String numfac = txtfac.getText();
            String codpro = tbdet.getValueAt(i, 0).toString();
            String despro = tbdet.getValueAt(i, 1).toString();
            String cantpro = tbdet.getValueAt(i, 3).toString();
            String preunit = tbdet.getValueAt(i, 2).toString();
            String importe = tbdet.getValueAt(i, 4).toString();
            try {
                PreparedStatement pst = cn.prepareStatement(InsertarSQL);//CREAR LA SENTENCIA SQL 
                //INGRESAR CADA DATOS EN SU COLUMNQ CORRESPONDIENTE 
                pst.setString(1, numfac);
                pst.setString(2, codpro);
                pst.setString(3, despro);
                pst.setString(4, cantpro);
                pst.setString(5, preunit);
                pst.setString(6, importe);
                pst.executeUpdate();//REALIZAR EL ACTUALIZADO 
            } catch (SQLException ex) {
                Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);//MANEJO DE LA EXCEPCION
            }
        }
    }
// Este método se llama desde el constructor para inicializar el formulario
//ADVERTENCIA: NO modificar este código.generado por el Editor de formularios
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        txtnomape = new javax.swing.JTextField();
        btnclientes = new javax.swing.JButton();
        txtruc = new javax.swing.JTextField();
        btnproductos = new javax.swing.JButton();
        txtcod = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtdni = new javax.swing.JTextField();
        txtdir = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtfec = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtfac = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txtsubtotal = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtigv = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        txttotal = new javax.swing.JTextField();
        tbdetalle = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbdet = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        btnguardar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        btncalcular = new javax.swing.JButton();
        btneliminar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

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

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("FACTURA");
        setPreferredSize(new java.awt.Dimension(0, 0));

        jLabel8.setText("Señor(a):");

        txtnomape.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtnomape.setForeground(new java.awt.Color(0, 51, 204));
        txtnomape.setEnabled(false);
        txtnomape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnomapeActionPerformed(evt);
            }
        });

        btnclientes.setText("Buscar");
        btnclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclientesActionPerformed(evt);
            }
        });

        txtruc.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtruc.setEnabled(false);

        btnproductos.setText("Buscar");
        btnproductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproductosActionPerformed(evt);
            }
        });

        txtcod.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtcod.setEnabled(false);

        jLabel11.setText("Cod. Cliente:");

        txtdni.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtdni.setForeground(new java.awt.Color(51, 51, 255));
        txtdni.setEnabled(false);

        txtdir.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtdir.setEnabled(false);

        jLabel10.setText("Direccion:");

        jLabel9.setText("DNI:");

        jLabel13.setText("ID:");

        jLabel12.setText("Fecha:");

        jLabel14.setText("Articulo:");

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel15.setText("FACTURA DE VENTA");

        jLabel17.setText("Nº");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(txtfac))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel15)))
                .addContainerGap(36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel15)
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtfac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtdir, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnproductos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12)
                        .addGap(18, 18, 18))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtnomape, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnclientes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfec, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtnomape)
                            .addComponent(btnclientes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtruc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtdir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(btnproductos))))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel18.setText("SubTotal:");

        jLabel19.setText("Impuesto:");

        jLabel20.setText("Total:");

        tbdetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));

        tbdet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Precio", "Cantidad", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbdet);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 66, Short.MAX_VALUE)
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtigv, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 11, Short.MAX_VALUE)
                    .addComponent(tbdetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 11, Short.MAX_VALUE)))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtsubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(txtigv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(0, 88, Short.MAX_VALUE)
                    .addComponent(tbdetalle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 87, Short.MAX_VALUE)))
        );

        btnguardar.setText("Realizar Venta");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });

        btnsalir.setText("Salir");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        btncalcular.setText("Realizar Calculo");
        btncalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncalcularActionPerformed(evt);
            }
        });

        btneliminar.setText("Eliminar");
        btneliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnsalir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btneliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btncalcular, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                            .addComponent(btnguardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btncalcular)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnguardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(btneliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsalir)
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel3.setText("    GESTCAR");
        jLabel3.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40))
            .addGroup(layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        setBounds(0, 0, 705, 479);
    }// </editor-fold>//GEN-END:initComponents
public static String fechaactual() {//OBTENER LA FECHA ACTUAL PARA LA CREACION DE LA FATURA 
        Date fecha = new Date();//NUEVA FECHA
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/YYYY");//FORMARTO DE LA FECHA 
        return formatofecha.format(fecha);//MOSTRAR AL USUARIO 
    }
private void btnclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclientesActionPerformed
//ACCION DEL BOTON CLIENTES
    Clientes_factura cli = new Clientes_factura();//MOSTRAR UNA LISTA CON LOS CLIENTES ACTUALES 
    Principal.jdpescritorio.add(cli);//SE AÑADE EL CLIENTE SELECCIONADO
    cli.toFront();//SE PASA AL FRENTE LA LISTA
    cli.setVisible(true);
}//GEN-LAST:event_btnclientesActionPerformed

private void btnproductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnproductosActionPerformed
//ACCION DEL BTON PRODUCTOS 
    try {
        Productos_facturas pro = new Productos_facturas();//MOSTRAR UNA LISTA CON LOS PRODUCTOS ACTUALES 
        Principal.jdpescritorio.add(pro);//SE AÑADE EL PRODUCTO SELECCIONADO
        pro.toFront();//SE PASA AL FRENTE 
        pro.setVisible(true);
    } catch (Exception e) {System.out.println(e);}//MANEJO DE EXCEPCION
}//GEN-LAST:event_btnproductosActionPerformed

private void txtnomapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnomapeActionPerformed
// TEXTO QUE SE VA A ESCRIBIR POR EL USUARIO 
}//GEN-LAST:event_txtnomapeActionPerformed

private void btncalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncalcularActionPerformed
//ACCION DE CALCULAR EL PRECIO TOTAL 
    if (tbdet.getRowCount() < 1) {//EN CASO DE QUE NO HAYA FILAS CON PRODUCTOS 
        JOptionPane.showMessageDialog(this, "No hay ningun producto");
    } else {//si no 
        calcular();//se calcula el precio de la factura 
    }
}//GEN-LAST:event_btncalcularActionPerformed

private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
//ACCION DEL BOTON SALIR
    this.dispose();//SE CIERRA LA VENTANA 
}//GEN-LAST:event_btnsalirActionPerformed

private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
//GUARDAR LA FACTURA CON LOS CAMBIOS REALIZADOS 
    if ((txtcod.getText().equals("")) || (txtsubtotal.getText().equals(""))) {//EN CASO DE QUE FALTE ALGUN DATO  ESENCIAL (PRECIO/ COD_PRODUCTO)
        JOptionPane.showMessageDialog(this, "No ingreso cliente,productos o realice operacion");//MENSAJE DE AVISO
    } else {//SI NO 
        String capcod = "", capcan = "";
        for (int i = 0; i < Factura.tbdet.getRowCount(); i++) {//RECORRER DATOS DE LA FACTURA 
            capcod = Factura.tbdet.getValueAt(i, 0).toString();//OBTENER CODIGO
            capcan = Factura.tbdet.getValueAt(i, 3).toString();//OBTENER CANTIDAD
            descontarstock(capcod, capcan);//QUITAR DEL STOCK LA CANTIDAD DEL PRODUCTO DEL CODIGIO 
        }
        factura();//SE CREA UNA NUEVA FACTURA 
        detallefactura();//SE CREA EL NUEVO DETALLE DE ESA FACTURA 
        //SE PASAN LAS ETIQUETAS DE TEXTO A VACIAS 
        txtcod.setText("");
        txtnomape.setText("");
        txtdir.setText("");
        txtdni.setText("");
        txtigv.setText("");
        txtsubtotal.setText("");
        txtruc.setText("");
        txttotal.setText("");
        DefaultTableModel modelo = (DefaultTableModel) tbdet.getModel();//MODELO NUEVO 
        int a = tbdet.getRowCount() - 1;//RECORRER LAS FILAS DE LA TABLA 
        int i;
        for (i = a; i >= 0; i--) {//RECORRIDOS TODOS
            modelo.removeRow(i);//ELIMINAR LOS DATOS DE LA FACTURA VIEJA 
        }
        numeros();//LLAMADA EL NUEVO NUMERO DE LA FACTURA SIGUIENTE 
    }
}//GEN-LAST:event_btnguardarActionPerformed

private void btneliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminarActionPerformed
// ACCION PARA ELIMINAR EL ELEMENTO SELECCIONADO 
    DefaultTableModel modelo = (DefaultTableModel) tbdet.getModel();//MODELO POR DEFECTO 
    int fila = tbdet.getSelectedRow();//OBTENER LA FILA SELECCIONADA
    if (fila >= 0) {//ASEGURAR QUE SE SELECCIONO ALGUNA FILA
        modelo.removeRow(fila);//SE ELIMINA ESA FILA 
    } else {//EN CASO DE QUE NO SE SELECCIONE NIGUNA 
        JOptionPane.showMessageDialog(null, "No Selecciono ninguna fila");//MENSAJE DE AVISO 
    }
}//GEN-LAST:event_btneliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncalcular;
    private javax.swing.JButton btnclientes;
    private javax.swing.JButton btneliminar;
    private javax.swing.JButton btnguardar;
    private javax.swing.JButton btnproductos;
    private javax.swing.JButton btnsalir;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    public static javax.swing.JTable tbdet;
    private javax.swing.JTable tbdetalle;
    public static javax.swing.JTextField txtcod;
    public static javax.swing.JTextField txtdir;
    public static javax.swing.JTextField txtdni;
    private javax.swing.JTextField txtfac;
    private javax.swing.JTextField txtfec;
    private javax.swing.JTextField txtigv;
    public static javax.swing.JTextField txtnomape;
    public static javax.swing.JTextField txtruc;
    private javax.swing.JTextField txtsubtotal;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables

//REALIZAR LA CONEXION CON LA BBDD
conectar cc = new conectar();
    Connection cn = cc.conexion();
}