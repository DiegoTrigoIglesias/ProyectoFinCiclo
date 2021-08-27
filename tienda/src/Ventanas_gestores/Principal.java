package Ventanas_gestores;

import Conexion_BBDD.conectar;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

/**
 * @author DIEGOT 
 * VENTANA PRINCIPAL DEL PROGRAMA EN ELLA SE HARA LA REDIRECCION A LOS DEMAS APARTADOS 
 *      SESION --> CERRAR SESION / SALIR 
 *      MANTENIMIENTO --> PRODUCTOS / CLIENTES 
 *      PAGOS --> FACTURA / RECIBO 
 *      CONSULTAS --> CLIENTES/ PRODUCTOS / FACTURAS / RECIBOS 
 *      REPORTES --> CLIENTES / PRODUCTOS / FACTURAS / RECIBOS
 */
public class Principal extends javax.swing.JFrame {
    //CREACION DEL NUEVO FORMULARIO 
    public Principal() {
        initComponents();//INICIAR
        this.setLocation(200, 50);//LOCALIZACION EN LA PANTALLA 
        this.setIconImage(new ImageIcon(getClass().getResource("/icono/icono.png")).getImage());//ICONO DEL JFRAME
    }

// Este método se llama desde el constructor para inicializar el formulario
//ADVERTENCIA: NO modificar este código.generado por el Editor de formularios
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jdpescritorio = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestor comercial");

        jdpescritorio.setBackground(new java.awt.Color(0, 102, 102));

        jMenu1.setText("Sesión");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        jMenuItem1.setText("Cerrar Sesion");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Salir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Mantenimiento");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        jMenuItem3.setText("Productos");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Clientes");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Pagos");
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });

        jMenuItem5.setText("Factura");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuItem10.setText("Recibo");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem10);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Consultas");
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });

        jMenuItem7.setText("Clientes");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem7);

        jMenuItem8.setText("Productos");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem8);

        jMenuItem9.setText("Facturas");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem9);

        jMenuItem11.setText("Recibos");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Reportes");

        jMenuItem6.setText("Clientes");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem6);

        jMenuItem12.setText("Productos");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem12);

        jMenuItem13.setText("Facturas");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem13);

        jMenuItem14.setText("Recibos");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem14);

        jMenuBar1.add(jMenu5);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jdpescritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jdpescritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
//VENTANA SUPERIOR ELEMENTO DONDE IRAN TODAS LAS OPCIONES DE INTRODUCCION DE DATOS
}//GEN-LAST:event_jMenu1ActionPerformed

private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
//ACCCION DEL ELEMENTO CERRAR SESION EL MENU DESPLEGABLE ARCHIVO
    Logueo_Principal principal = new Logueo_Principal();//REDIRECCION AL LOGIN
    principal.setVisible(true);
    principal.pack();//AJUSTE DE TAMAÑO
    this.setVisible(false);
}//GEN-LAST:event_jMenuItem1ActionPerformed

private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
//ACCCION DEL ELEMENTO SALIR EL MENU DESPLEGABLE 1
    this.dispose();//SE ELIMINA LA VENTANA DEL JFRAME 
}//GEN-LAST:event_jMenuItem2ActionPerformed

private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
//ACCCION DEL ELEMENTO PRODUCTOS EL MENU DESPLEGABLE MANTENIMINETO
    IngresoProductos ip = new IngresoProductos();//LLEVA AL FORMULARIO DE INGRESO DE PRODUCTOS 
    jdpescritorio.add(ip);//AÑADIR ELEMETNO AL SUBMENU
    ip.show();//PRODUCIR EL DIALOG
}//GEN-LAST:event_jMenuItem3ActionPerformed

private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
//ACCION DEL ELEMENTO MANTENIMIENTO DEL MENU    
}//GEN-LAST:event_jMenu2ActionPerformed

private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
//ACCCION DEL ELEMENTO CLIENTES EL MENU DESPLEGABLE MANTENIMIENTO
    IngresoCliente cli = new IngresoCliente();//LLEVA AL FORMULARIO DE INGRESO DE CLIENTE 
    jdpescritorio.add(cli);//AÑADIR ELEMENTO AL SUBMENU
    cli.show();//PRODUCIR EL DIALOG;
}//GEN-LAST:event_jMenuItem4ActionPerformed

private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
//ACCION DEL ELEMENTO MOVIMIENTO DEL MENU
}//GEN-LAST:event_jMenu3ActionPerformed

private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
//ACCION DEL ELEMENTO FACTURA EN EL SUBMENU MOVIMIENTO 
    Factura fac = new Factura();// CRACION DE NUEVA FACTURA
    jdpescritorio.add(fac);//AÑADIR ELEMETNO AL SUBMENU 
    fac.show();
}//GEN-LAST:event_jMenuItem5ActionPerformed

private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
//ACCION DE CLIENTES SUBMENU DE REPORTES
    try {
        conectar cc = new conectar();
        JasperReport reportes = JasperCompileManager.compileReport("reportesClientes.jrxml");//LLAMADA AL REPORTE DE PRODUCTOS
        JasperPrint print = JasperFillManager.fillReport(reportes, null, cc.conexion());//METER DATOS EN EL REPORTE 
        JasperViewer.viewReport(print); //PINTAR EL REPORTE
    } catch (Exception e) {System.out.printf(e.getMessage());}//MANEJO DE LA EXCEPCION  
}//GEN-LAST:event_jMenuItem6ActionPerformed

private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
//ACCION DEL ELEMENTO CLIENTES DEL SUBMENU CONSULTAS 
    ConsultasClientes clientes = new ConsultasClientes();//LLAMADA A LA COSULTA DE CLIENTES
    jdpescritorio.add(clientes);//AÑADIR 
    clientes.show();
}//GEN-LAST:event_jMenuItem7ActionPerformed

private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
///ACCION DEL ELEMENTO PRODUCTOS DEL SUBMENU CONSULTAS 
    ConsultasProductos productos = new ConsultasProductos();//LLAMADA A LA COSULTA DE PRODUCTOS
    jdpescritorio.add(productos);//AÑADIR 
    productos.show();
}//GEN-LAST:event_jMenuItem8ActionPerformed

private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
///ACCION DEL ELEMENTO FACTURAS DEL SUBMENU CONSULTAS 
    ConsultasFacturas Facturas = new ConsultasFacturas();//LLAMADA A LA COSULTA DE FACTURAS
    jdpescritorio.add(Facturas);//AÑADIR 
    Facturas.show();
}//GEN-LAST:event_jMenuItem9ActionPerformed

private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
// /ACCION DEL ELEMENTO RECIBO DEL SUBMENU CONSULTAS 
    Recibo bol = new Recibo();//LLAMADA
    jdpescritorio.add(bol);//AÑADIR 
    bol.show();
}//GEN-LAST:event_jMenuItem10ActionPerformed

private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
//ACCION DEL ELEMENTO CONSULTAS DEL MENU
}//GEN-LAST:event_jMenu4ActionPerformed

private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
//ACCION DE CONSULTA DE LOS RECIBOS GENERADOS 
    ConsultasRecibos Recibos = new ConsultasRecibos();
    jdpescritorio.add(Recibos);//AÑADIR 
    Recibos.show();//MOSTRAR RECIBOS
}//GEN-LAST:event_jMenuItem11ActionPerformed

private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
//ACCION PRODUCTOS DEL SUBMENU DE REPORTES 
    try {
        conectar cc = new conectar();
        JasperReport reportes = JasperCompileManager.compileReport("reportesProductos.jrxml");//LLAMADA AL REPORTE DE PRODUCTOS
        JasperPrint print = JasperFillManager.fillReport(reportes, null, cc.conexion());//METER DATOS EN EL REPORTE 
        JasperViewer.viewReport(print); //PINTAR EL REPORTE
    } catch (Exception e) {
        System.out.printf(e.getMessage());
    }//MANEJO DE LA EXCEPCION
}//GEN-LAST:event_jMenuItem12ActionPerformed

private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
//ACCION FACTURAS DEL SUBMENU DE REPORTES 
    try {
        conectar cc = new conectar();
        JasperReport reportes = JasperCompileManager.compileReport("reportesFacturas.jrxml");//LLAMADA AL REPORTE DE FACTURAS 
        JasperPrint print = JasperFillManager.fillReport(reportes, null, cc.conexion());//METER DATOS EN EL REPORTE 
        JasperViewer.viewReport(print); //PINTAR EL REPORTE
    } catch (Exception e) {
        System.out.printf(e.getMessage());
    }//MANEJO DE LA EXCEPCION
}//GEN-LAST:event_jMenuItem13ActionPerformed

private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
//ACCION RECIBOS DEL SUBMENU DE REPORTES 
    try {
        conectar cc = new conectar();//CONEXION CON LA BD 
        JasperReport reportes = JasperCompileManager.compileReport("reportesRecibos.jrxml");//LLAMADA AL REPORTE DE RECIBOS 
        JasperPrint print = JasperFillManager.fillReport(reportes, null, cc.conexion());//METER DATOS EN EL REPORTE 
        JasperViewer.viewReport(print); //PINTAR EL REPORTE
    } catch (Exception e) {
        System.out.printf(e.getMessage());
    }//MANEJO DE LA EXCEPCION
}//GEN-LAST:event_jMenuItem14ActionPerformed

    //FUNCION DE EJECUCION MAIN 
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    public static javax.swing.JDesktopPane jdpescritorio;
    // End of variables declaration//GEN-END:variables
}
