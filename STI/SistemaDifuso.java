import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class SistemaDifuso {

    public static void main(String[] args) {
        // Cargar el archivo .flc con la ruta absoluta
        String archivoFCL = "C:/Users/Reloa/Desktop/MCC/Repositorio/MCC/STI/STI.flc";
        FIS fis = FIS.load(archivoFCL, true);

        if (fis == null) {
            System.err.println("No se pudo cargar el archivo FCL.");
            return;
        }
        
        // Declarar y asignar valores a las variables de entrada
        double tiempo = 20.0;      // Ejemplo de valor para el tiempo
        double errores = 3.0;      // Ejemplo de valor para los errores
        double ayudas = 1.0;       // Ejemplo de valor para las veces que el usuario pidió ayuda

        // Enviar entradas al sistema difuso con mensajes de depuración
        try {
            fis.setVariable("time", tiempo);
            System.out.println("Variable 'time' establecida en: " + tiempo);

            fis.setVariable("mistakes", errores);
            System.out.println("Variable 'mistakes' establecida en: " + errores);

            fis.setVariable("tips", ayudas);
            System.out.println("Variable 'tips' establecida en: " + ayudas);
        } catch (Exception e) {
            System.err.println("Error al establecer las variables de entrada: " + e.getMessage());
            return;
        }

        // Evaluar el sistema difuso
        try {
            fis.evaluate();
            System.out.println("Evaluación del sistema difuso completada.");
        } catch (Exception e) {
            System.err.println("Error durante la evaluación del sistema difuso: " + e.getMessage());
            return;
        }

        // Obtener la salida con mensajes de depuración
        try {
            Variable nivel = fis.getVariable("level");
            if (nivel != null) {
                System.out.println("Valor de salida: " + nivel.getValue());
            } else {
                System.err.println("Variable de salida 'level' no encontrada.");
            }
        } catch (Exception e) {
            System.err.println("Error al obtener la variable de salida: " + e.getMessage());
        }
    }
}
