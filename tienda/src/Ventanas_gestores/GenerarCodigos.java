package Ventanas_gestores;
/**
 * @author DIEGOT 
 * GESTION DE LOS CONTADORES INTERNOS DEL PROGRAMA 
 * CONTROL FACTURAS CODIGOS DE CLIENTES
 * CONTROL DE CODIGOS DE FACTRUAS 
 * LLEVAR EL CONTROL DE CADA CANTIDAD DE LOS DNIs, ID, TELEFONOS Y TODOS LOS DATOS CON TAMAÑO DE TEXTO 
 * ES UN METODO ALGO CHAPUZA PERO PREVIENE ERRORES DE ESCRIBIR DATOS DE MAS PARA LA BASE DE DATOS 
 */
public class GenerarCodigos {
    private int dato;
    private int cont = 1;
    private String num = "";
    public void generar(int dato) {
        this.dato = dato;
        if ((this.dato >= 1000) || (this.dato < 10000)) {
            int can = cont + this.dato;
            num = "" + can;
        }
        if ((this.dato >= 100) || (this.dato < 1000)) {
            int can = cont + this.dato;
            num = "0" + can;
        }
        if ((this.dato >= 9) || (this.dato < 100)) {
            int can = cont + this.dato;
            num = "00" + can;
        }
        if (this.dato < 9) {
            int can = cont + this.dato;
            num = "000" + can;
        }
    }
    public String serie() {
        return this.num;
    }
}
