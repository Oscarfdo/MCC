
import javax.swing.plaf.basic.BasicSplitPaneUI;

public class UsarTrabajador{

    public void determinar(){

        Trabajador[] trabajador = new Trabajador[1];

        for(int i = 0; i < trabajador.length; i++){
            System.out.println("Introduzca Nombre:");
            String n = Keyboard.readString();
            System.out.println("Introduzca sexo:");
            char s = Keyboard.readChar();
            System.out.println("Introduzca categoria:");
            char c = Keyboard.readChar();
            System.out.println("Introduzca numero de horas trabajadas:");
            double h = Keyboard.readDouble();

            trabajador[i] = new Trabajador(n,s,c,h);

            System.out.println(String.format("%-20s %-5s %-10s %-10s %-10s %-10s %-10s",
                                 "NOMBRE", "SEXO", "CATEGORÍA", "HORAS", "BONO", "SUELDO", "TOTAL"));

            System.out.println(trabajador[i].toString());
        }
    }

    public static void main(String[] args){
        UsarTrabajador modulo = new UsarTrabajador();
        modulo.determinar();
    }
}