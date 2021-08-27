package Conexion_BBDD;

import java.sql.*;
import javax.swing.*;

/**
 * @author DIEGOT 
 * REALIZAR LA CONEXION CON LA BASE DE DATOS PARA TENER GUARDADOS TODOS CLIENTES / PRODUCTOS / FACTURAS / RECIBOS 
 * USUARIO Y CONTRASEÑA DE XAMPP "root"/""
 */
public class conectar {
    Connection conect = null;
    public Connection conexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");//Cargamos el Driver MySQL
            conect = DriverManager.getConnection("jdbc:mysql://localhost/tienda", "root", "");//RUTA PARA LA CONEXION
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error " + e);//MANEJO DEL ERROR 
        }
        return conect;
    }
}
