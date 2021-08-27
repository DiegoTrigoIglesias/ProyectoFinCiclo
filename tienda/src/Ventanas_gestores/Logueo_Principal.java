package Ventanas_gestores;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
/**
 * @author DIEGOT
 * CONTROL DE INICIO DE SESION EN LA APLICACION
 * USUARIO "admin"
 * PASS "diego"
 */
public class Logueo_Principal extends javax.swing.JFrame {
    //NUEVO FORMULARIO DE INGRESO AL SISTEMA 
    public Logueo_Principal() {
        initComponents();//INICIALIZAR COMPONENTES
        this.setSize(380, 200);//TAMAÑO DE LA PANTALLA
        this.setLocation(350, 300);//LOCALIZACION EN PANTALLA 
        this.setIconImage(new ImageIcon(getClass().getResource("/icono/logueo.png")).getImage());//ICONO DEL JFRAME
        txtusuario.requestFocus();//FOCO EN EL TEXTO DE ESCRITURA DEL USUARIO 
    }

// Este método se llama desde el constructor para inicializar el formulario
//ADVERTENCIA: NO modificar este código.generado por el Editor de formularios
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtusuario = new javax.swing.JTextField();
        txtcontra = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnaceptar = new javax.swing.JButton();
        btnsalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Acceso al Sistema");

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        txtusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusuarioActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Contraseña:");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuario:");

        btnaceptar.setText("Entrar");
        btnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarActionPerformed(evt);
            }
        });

        btnsalir.setText("Salir");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtusuario)
                    .addComponent(txtcontra, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
                .addContainerGap(170, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(106, 106, 106)
                    .addComponent(btnaceptar)
                    .addGap(18, 18, 18)
                    .addComponent(btnsalir)
                    .addContainerGap(147, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtusuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(txtcontra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(100, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(121, 121, 121)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnsalir)
                        .addComponent(btnaceptar))
                    .addContainerGap(52, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(395, 224));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
//ACCION DEL BOTON ACEPTAR EL INGRESO
    String usu = txtusuario.getText();//OBTENER EL TEXTO ESCRITO POR EL USUARIO
    String pas = new String(txtcontra.getPassword());//OBTENER LA PASS OBTENIDA POR EL USUARIO 
    if (usu.equals("admin") && pas.equals("diego")) {//COMPARAR QUE EL TEXTO Y LA PAS SEAN IGUALES A LAS CORRECTAS
        this.setVisible(false);//SE CIERRA VENTANA
        Principal ingreso = new Principal();//PASAMOS A LA VENTANA PRINCIPAL DEL PROGRAMA 
        ingreso.setVisible(true);//VISIBILIDAD
        ingreso.pack();//AJUSTAR EL TAMAÑO DEL MARCO PARA SU PROPIO CONTENIDO (Stack Overflow)
    } else {//EN CASO DE QUE NO SEAN CORRECTOS 
        JOptionPane.showMessageDialog(null, "Datos Incorrectos");//MENSAJE DE AVISO 
        txtusuario.requestFocus();//PREGUNTA DE NUEVO 
    }
}//GEN-LAST:event_btnaceptarActionPerformed

private void txtusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusuarioActionPerformed
    txtusuario.requestFocus();//FOCO EN EL TEXTO DEL USUARIO QUE VA A ESCRIBIR 
}//GEN-LAST:event_txtusuarioActionPerformed
 
//FUNCIN DE EJECCUCION MAIN NO MODIFICAR 
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            //EXCEPCIONES PARA EL LOGUEO
        } catch (ClassNotFoundException ex) {java.util.logging.Logger.getLogger(Logueo_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {java.util.logging.Logger.getLogger(Logueo_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {java.util.logging.Logger.getLogger(Logueo_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {java.util.logging.Logger.getLogger(Logueo_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() { //CORRER EL PROGRAMA 
                new Logueo_Principal().setVisible(true);//VISIBILIDAD DE LA VENTANA 
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaceptar;
    private javax.swing.JButton btnsalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField txtcontra;
    private javax.swing.JTextField txtusuario;
    // End of variables declaration//GEN-END:variables
}
