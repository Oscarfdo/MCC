import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class SistemaDifuso {

    // Método para obtener el nivel de dificultad basado en las métricas del usuario
    public double obtenerNivel(int errores, int tiempo, int ayudas) {
        // Ruta del archivo FCL (asegúrate de que esté en la ubicación correcta)
        String archivoFCL = "STI.fcl";
        FIS fis = FIS.load(archivoFCL, true);

        // Validar si el archivo fue cargado correctamente
        if (fis == null) {
            System.err.println("No se pudo cargar el archivo FCL.");
            return 0; // Nivel básico por defecto en caso de error
        }

        // Establecer las variables de entrada
        try {
            fis.setVariable("time", tiempo);
            fis.setVariable("mistakes", errores);
            fis.setVariable("tips", ayudas);

            // Depuración: Mostrar valores de entrada
            System.out.println("Entradas al sistema difuso:");
            System.out.println("Tiempo: " + tiempo);
            System.out.println("Errores: " + errores);
            System.out.println("Ayudas: " + ayudas);
        } catch (Exception e) {
            System.err.println("Error al establecer las variables de entrada: " + e.getMessage());
            return 0; // Retornar nivel básico en caso de error
        }

        // Evaluar el sistema difuso
        try {
            fis.evaluate();
            System.out.println("Evaluación del sistema difuso completada.");
        } catch (Exception e) {
            System.err.println("Error durante la evaluación del sistema difuso: " + e.getMessage());
            return 0; // Retornar nivel básico en caso de error
        }

        // Obtener la variable de salida 'level'
        try {
            Variable nivel = fis.getVariable("level");
            if (nivel != null) {
                System.out.println("Nivel obtenido: " + nivel.getValue());
                return nivel.getValue(); // Retornar el nivel de dificultad
            } else {
                System.err.println("Variable de salida 'level' no encontrada.");
                return 0; // Nivel básico por defecto
            }
        } catch (Exception e) {
            System.err.println("Error al obtener la variable de salida: " + e.getMessage());
            return 0; // Nivel básico en caso de error
        }
    }

    public static void main(String[] args) {
        // Prueba del sistema difuso
        SistemaDifuso sistema = new SistemaDifuso();

        // Ejemplo de métricas de entrada
        int tiempo = 12;    // Segundos transcurridos
        int errores = 2;    // Cantidad de errores cometidos
        int ayudas = 1;     // Veces que pidió ayuda

        // Obtener el nivel de dificultad
        double nivel = sistema.obtenerNivel(errores, tiempo, ayudas);
        System.out.println("Nivel determinado por el sistema difuso: " + nivel);
    }
}
