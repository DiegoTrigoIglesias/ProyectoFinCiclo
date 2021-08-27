package Ventanas_gestores;

import Conexion_BBDD.conectar;
import java.awt.Color;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author DIEGOT
 * CREACION DEL RECIBO PARA EL CLIENTE 
 * MISMA BASE QUE LA FACTURA 
 * LA FECHA Y EL NUMERO DE RECIBO SE INCREMENTAN AUTOMATICAMENTE 
 * LOS DATOS DE Nombre DNI Codigo y Direccion SE AUTOCOMPLETAN CON UNA CONSULTA SQL REALIZADA MEDIANTE Clientes_recibo
 * A LA HORA DE SELECCIONAR LOS PRODUCTOS SE HACE MEIDANTE Productos_recibo
 */
public class Recibo extends javax.swing.JInternalFrame {

    //NUEVO FORMULARIO PARA EL RECIBO 
    public Recibo() {
        initComponents();
        this.setLocation(15, 15);//LOCALIZACION EN PANTALLA 
        txtfecha.setDisabledTextColor(Color.blue);//FECHA DEL DIA ACTUAL EN AZUL 
        txtfecha.setText(fechaact());//FECHA AUTOCOMPLETABLE 
        txtnumbol.setDisabledTextColor(Color.red);//NUMERO DEL RECIBO EN ROJO
        txtcod.setDisabledTextColor(Color.blue);//CODGIO EN AZUK 
        txtdire.setDisabledTextColor(Color.blue);//DIRECCION EN AZUL
        txtdni.setDisabledTextColor(Color.blue);//DNI DEL CLIENTE EN AZUL
        txtnomape.setDisabledTextColor(Color.blue);//NOMBRE Y APELLIDOS EN AZUL 
        numeros();//NUMERO DEL RECIBO 
    }

    void descontarstock(String codi, String can) {//DESCONTAR PRODUCTOS DEL ALMACEN CADA VEZ QUE SE REALICE UNA VENTA 
        int des = Integer.parseInt(can);//CANTIDAD DE PRODUCTO A DESCONTAR 
        String cap = "";//CAPACIDAD DEL ALMACEN 
        int desfinal;//CAPACIDAD DE PRODUCTOS QUE QUEDAN DESPUES DE LA VENTA 
        String consul = "SELECT * FROM producto WHERE  cod_pro='" + codi + "'";//CONSULTA SQL PARA REALIZAR EL RECIBO 
        try {
            Statement st = cn.createStatement();//CREAR LA SENTENCIA SQL
            ResultSet rs = st.executeQuery(consul);//EJECUTAR LA SENTENCIA 
            while (rs.next()) {//MOSTRAR LOS DATOS EN LAS COLUMNAS 
                cap = rs.getString(4);
            }
        } catch (Exception e) {System.out.println(e);}//MANEJO DE LA EXCEPCION 
        desfinal = Integer.parseInt(cap) - des;//CANTIDAD FINAL DE PROUCTO DESPUES DE LA VENTA 
        String modi = "UPDATE producto SET Stock='" + desfinal + "' WHERE cod_pro = '" + codi + "'";//ACTUALIZACION DE LA BD DESPUES DE LA VENTA 
        try {
            PreparedStatement pst = cn.prepareStatement(modi);//CREAR LA SENTENCIA SQL 
            pst.executeUpdate();//ACTUALIZACION DE LA BASE DE DATOS 
        } catch (Exception e) {System.out.println(e);}//MANEJO DE LA EXCEPCION 
    }

    void numeros() {//IDENTIFICACODR DE CADA RECIVO 
        String c = "";//ID 
        String SQL = "select max(num_rec) from recibo";//CONSULTA SQL PARA OBTENER EL ID 
        try {
            Statement st = cn.createStatement();//CREAR LA SENTENCIA SQL
            ResultSet rs = st.executeQuery(SQL);//EJECUTAR LA SENTENCIA
            if (rs.next()) {//RECORRER LA LISTA DE RECIBOS GENERADOS 
                c = rs.getString(1);//RESULTADO EN STRING CON EL CODIGO DEL ULTIMO RECIBO 
            }
            if (c == null) {//EN CASO DE QUE NO HAYA 
                txtnumbol.setText("00000001");//SE CREA EL RECIBO UNO 
            } else {//EN CUALQUIER OTRO CASO 
                int j = Integer.parseInt(c);//SE PASA A NUMERO EL ID DEL RECIBO 
                GenerarNumero gen = new GenerarNumero();//SE GENERA UN NUEVO NUMERO DE RECIBO 
                gen.generar(j);//LLAMADA AL GENERADO 
                txtnumbol.setText(gen.serie());//SE PONE COMO TEXTO 
            }
        } catch (SQLException ex) {Logger.getLogger(Recibo.class.getName()).log(Level.SEVERE, null, ex);}
    }

    void calcular() {//FUNCION PARA CALCULAR EL VALOR TOTAL DEL IMPORTE 
        String pre;
        String can;
        double impuesto = 0; //impuesto 
        double total = 0;//precio final 
        double subtotal = 0;//suma del precio de productos
        double precio;
        int cantidad;
        double imp = 0.0;//impuestos
        for (int i = 0; i < tbdetbol.getRowCount(); i++) {//RECORRER TODOS LOS ELEMENTOS DEL RECIBO 
            pre = tbdetbol.getValueAt(i, 2).toString();//OBTENER EL PRECIO 
            can = tbdetbol.getValueAt(i, 3).toString();//OBTENER LA CANTIDAD
            precio = Double.parseDouble(pre);//PARSEAR PARA CALCULAR
            cantidad = Integer.parseInt(can);//PARSEAR PARA CALCULAR 
            imp = precio * cantidad;//CANTIDAD TOTAL POR EL RECIO UNITARIO
            subtotal = subtotal + imp;//CALCULO DEL RESULTADO 
            tbdetbol.setValueAt(Math.rint(imp * 100) / 100, i, 4);//CALCLO DEL IMPORTE 
        }
        txttotal.setText("" + Math.rint(subtotal * 100) / 100);//TENER EN CUENTA LOS DECIMALES 
    }

    void recibo() {//CREACION DEL RECIBO COMO TAL 
        String InsertarSQL = "INSERT INTO recibo(num_rec,cod_cli,pre_tot,fecha) VALUES (?,?,?,?)";//SENTENCIA SQL CON EL RECIBO 
        String numbol = txtnumbol.getText();//OBTENER LOS DATOS 
        String codcli = txtcod.getText();//OBTENER LOS DATOS 
        String total = txttotal.getText();//OBTENER LOS DATOS 
        String fecha = txtfecha.getText();//OBTENER LOS DATOS 
        try {
            PreparedStatement pst = cn.prepareStatement(InsertarSQL);//CREAR LA SENTENCIA 
            //INGRESAR CADA DATO EN SU COLUMNA CORRESPONDIENTE 
            pst.setString(1, numbol);
            pst.setString(2, codcli);
            pst.setString(3, total);
            pst.setString(4, fecha);
            int n = pst.executeUpdate();//ACTUALIAR LA BBDD
            if (n > 0) {//SI NO HAY COLUMNAS VACIAS 
                JOptionPane.showMessageDialog(null, "Los datos se guardaron correctamente");//MENSAJE DE AVISO 
            }
        } catch (SQLException ex) {Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);} //MANEJO DE LA EXCEPCION 
    }
    void detallerecibo() {//ELEMENTOS QUE VAN A FORMAR PARTE DEL RECIBO 
        for (int i = 0; i < tbdetbol.getRowCount(); i++) {//RECORRER TODOS LO ELEMENTOS DE LA TABLA 
            String InsertarSQL = "INSERT INTO detallerecibo(num_rec,cod_pro,des_pro,cant_pro,pre_unit,pre_venta) VALUES (?,?,?,?,?,?)";//SENCENCIA SQL 
            //VALORES DE LAS COLUMNAS A LA HORA DE LA FACTURA DE LOS PRODUCTOS 
            String numrec = txtnumbol.getText();
            String codpro = tbdetbol.getValueAt(i, 0).toString();
            String despro = tbdetbol.getValueAt(i, 1).toString();
            String cantpro = tbdetbol.getValueAt(i, 3).toString();
            String preunit = tbdetbol.getValueAt(i, 2).toString();
            String importe = tbdetbol.getValueAt(i, 4).toString();
            try {
                PreparedStatement pst = cn.prepareStatement(InsertarSQL);//CREAR LA SENTENCIA SQL
                //INGRESAR LOS DATOS DE LOS PRODUCTOS EN SU COLUMNA CORRESPONDIENTE 
                pst.setString(1, numrec);
                pst.setString(2, codpro);
                pst.setString(3, despro);
                pst.setString(4, cantpro);
                pst.setString(5, preunit);
                pst.setString(6, importe);
                pst.executeUpdate();
            } catch (SQLException ex) {Logger.getLogger(Factura.class.getName()).log(Level.SEVERE, null, ex);} //MANEJO DE LA EXCEPCION 
        }
    }
    
    @SuppressWarnings("unchecked")
    public static String fechaact() {//OBTENER LA FECHA MEDIANTE EL JDATECHOOSER 
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/YYYY");//FORMATO DE LA FECHA 
        return formatofecha.format(fecha);//OBTENER LA FECHA ACTUAL 
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtnumbol = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtnomape = new javax.swing.JTextField();
        txtdni = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtcod = new javax.swing.JTextField();
        txtfecha = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtdire = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        btnclientes = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        btnproductos = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbdetbol = new javax.swing.JTable();
        btncalcular = new javax.swing.JButton();
        btnven = new javax.swing.JButton();
        btneli = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();
        txttotal = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();

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
        setTitle("RECIBO");

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 48)); // NOI18N
        jLabel3.setText("    GESTCAR");
        jLabel3.setToolTipText("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setBackground(new java.awt.Color(51, 51, 255));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("       Recibo de Venta");
        jLabel6.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 153, 153)));

        jLabel8.setText("Nº");

        txtnumbol.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtnumbol, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                        .addGap(20, 20, 20))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtnumbol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel9.setText("Señor(es):");

        txtnomape.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtnomape.setEnabled(false);

        txtdni.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtdni.setEnabled(false);
        txtdni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdniActionPerformed(evt);
            }
        });

        jLabel10.setText("DNI:");

        jLabel11.setText("Cod.Cliente");

        txtcod.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtcod.setEnabled(false);

        txtfecha.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtfecha.setEnabled(false);
        txtfecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtfechaActionPerformed(evt);
            }
        });

        jLabel12.setText("Fecha:");

        txtdire.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        txtdire.setEnabled(false);
        txtdire.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdireActionPerformed(evt);
            }
        });

        jLabel13.setText("Direccion:");

        btnclientes.setText("Buscar");
        btnclientes.setToolTipText("");
        btnclientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnclientesActionPerformed(evt);
            }
        });

        jLabel14.setText("Productos:");

        btnproductos.setText("Buscar");
        btnproductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnproductosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtnomape, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnclientes, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txtdire, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnproductos)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtnomape, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnclientes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtdni, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtcod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txtfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtdire, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(btnproductos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbdetbol.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CODIGO", "DESCRIPCION", "PRECIO UNITARIO", "CANTIDAD", "PRECIO VENTA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbdetbol);

        btncalcular.setText("REALIZAR CALCULO");
        btncalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncalcularActionPerformed(evt);
            }
        });

        btnven.setText("REALIZAR VENTA");
        btnven.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnvenActionPerformed(evt);
            }
        });

        btneli.setText("ELIMINAR");
        btneli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliActionPerformed(evt);
            }
        });

        btnsalir.setText("SALIR");
        btnsalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsalirActionPerformed(evt);
            }
        });

        jLabel20.setText("Total:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnsalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btncalcular, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnven, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btneli, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btncalcular)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btneli)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnven)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnsalir)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txttotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void txtfechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfechaActionPerformed
//TEXTO DONDE SE PONE LA FECHA UNA VEZ SELECCIONADS 
}//GEN-LAST:event_txtfechaActionPerformed

private void txtdireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdireActionPerformed
//TEXTO DONDE SE PONE LA DIRECCION DEL CLIENTE 
}//GEN-LAST:event_txtdireActionPerformed

private void txtdniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdniActionPerformed
//TEXTO DONDE SE PONE EL DNI DEL CLIENTE 
}//GEN-LAST:event_txtdniActionPerformed

private void btncalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncalcularActionPerformed
//CALCULAE EL IMPORTE TOTAL 
    if (tbdetbol.getRowCount() < 1) {//EN CASO DE QUE NO HAYA PRODUCTOS 
        JOptionPane.showMessageDialog(this, "ingrese algun producto");//MENSAJE DE AVISO
    } else {//SI HAY ALGUN PRODUCTO 
        calcular();// SE CALCULA EL IMPORTE 
    }
}//GEN-LAST:event_btncalcularActionPerformed

private void btnclientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnclientesActionPerformed
//BOTON DE SELECCION DE CLIENTES 
    Clientes_recibo cli = new Clientes_recibo();//MOSTRAR UNA LISTA CON LOS CLIENTES ACTUALES 
    Principal.jdpescritorio.add(cli);//SE AÑADE EL CLIENTE SELECCIONADO 
    cli.toFront();//SE PASA AL FRENRTE LA LSITA 
    cli.setVisible(true);
}//GEN-LAST:event_btnclientesActionPerformed

private void btnproductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnproductosActionPerformed
//BOTON DE SELECCION DE PRODUCTOS 
    try {
        Productos_recibo pro = new Productos_recibo();//MOSTRAR UNA LISTA DE LOS PRODUCTOS EN STOCK 
        Principal.jdpescritorio.add(pro);//SE AÑADE EL PRODUCTO SELECCIONADO
        pro.toFront();//SE PASA AL FRENRE LA LISTA 
        pro.setVisible(true);
    } catch (Exception e){System.out.println(e);}//MANEJO DE EXCEPCION
}//GEN-LAST:event_btnproductosActionPerformed

private void btnvenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnvenActionPerformed
//BOTON PARA REALIZAR LOS CALCULOS Y CREAR EL RECIBO 
    if ((txtcod.getText().equals("")) || (txttotal.getText().equals(""))) {
        JOptionPane.showMessageDialog(this, "Ingrese cliente, producto o realice operacion");
    } else {
        String capcod = "", capcan = "";
        for (int i = 0; i < Recibo.tbdetbol.getRowCount(); i++) {//RECORRER LOS DATOS DE LA FACTURA
            capcod = Recibo.tbdetbol.getValueAt(i, 0).toString();//OBTENER EL CODIGO
            capcan = Recibo.tbdetbol.getValueAt(i, 3).toString();//OBTENER LA CANTIDAD
            descontarstock(capcod, capcan);//QUITAR DEL STOCK LA CANTIDAD DEL PRODUCTO DEL CODIGO
        }
        recibo();//SE CREA UN NUEVO RECIBO
        detallerecibo();///SE CREA EL NUEVO DERALLE DE ESE RECIBO 
        txtcod.setText("");
        txtnomape.setText("");
        txtdni.setText("");
        txtdire.setText("");
        txttotal.setText("");
        DefaultTableModel modelo = (DefaultTableModel) tbdetbol.getModel();//NUEVO MODELO 
        int a = tbdetbol.getRowCount() - 1;//RECORRER LAS FLAS DE LA TABLA 
        int i;
        for (i = a; i >= 0; i--) {//RECORRIDOS TODOS 
            modelo.removeRow(i);//ELIMINAR LOS DATOS DEL RECIBO VIEJO 
        }
        numeros();//LLAMADA AL NUEVO NUMERO DEL RECIBO SIGUIENTE  
    }
}//GEN-LAST:event_btnvenActionPerformed

private void btnsalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsalirActionPerformed
//ACCION DEL BOTON SALIR
    this.dispose();//SE CIERRA LA VENTANA 
}//GEN-LAST:event_btnsalirActionPerformed

private void btneliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliActionPerformed
//ACCION PARA ELIMINAR EL ELEMENTO SELECCIONADO 
    DefaultTableModel model = (DefaultTableModel) tbdetbol.getModel();//MODELO POR DEFECTO 
    int fila = tbdetbol.getSelectedRow();//OBTENER LA FILA SELECCIONADA
    if (fila >= 0) {//ASEGURAR QUE SE SELECCIONO ALGNA FILA
        model.removeRow(fila);//SE ELIMINA ESA FILA 
    } else {//EN CASO DE QUE NO SE SELECCIONE NINGUNA
        JOptionPane.showMessageDialog(null, "Tabla vacia o no seleccione ninguna fila");//MENSAJE DE AVISO
    }
}//GEN-LAST:event_btneliActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncalcular;
    private javax.swing.JButton btnclientes;
    private javax.swing.JButton btneli;
    private javax.swing.JButton btnproductos;
    private javax.swing.JButton btnsalir;
    private javax.swing.JButton btnven;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    public static javax.swing.JTable tbdetbol;
    public static javax.swing.JTextField txtcod;
    public static javax.swing.JTextField txtdire;
    public static javax.swing.JTextField txtdni;
    public static javax.swing.JTextField txtfecha;
    public static javax.swing.JTextField txtnomape;
    private javax.swing.JTextField txtnumbol;
    private javax.swing.JTextField txttotal;
    // End of variables declaration//GEN-END:variables
//REALIZAR LA CONEXION CON LA BBDD
conectar cc = new conectar();
    Connection cn = cc.conexion();
}
