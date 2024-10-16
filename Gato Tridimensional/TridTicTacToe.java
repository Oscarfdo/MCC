import java.util.ArrayList;

public class TridTicTacToe{

    // Inicializar el arreglo
    char[] tablero = new char[62];

    private Jugador turnoActual; // 'X' para jugador1, 'O' para jugador2
    private Jugador jugador1, jugador2;
    private boolean hayGanador = false;

    ArrayList<Jugador> jugador = new ArrayList<>(); // Lista dinámica de jugadores

    // Convertir los arreglos a String[][] en lugar de byte[][]
    String[][] tab1 = {
        {"1","2","3","4"},
        {"5","6","7","8"},
        {"9","10","11","12"},
        {"13","14","15","16"},
    };

    String[][] tab2 = {
        {"17","18","19","20"},
        {"21","22","23","24"},
        {"25","26","27","28"},
        {"29","30","31","32"},
    };

    String[][] tab3 = {
        {"33","34","35","36"},
        {"37","38","39","40"},
        {"41","42","43","44"},
        {"45","46","47","48"},
    };

    String[][] tab4 = {
        {"49","50","51","52"},
        {"53","54","55","56"},
        {"57","58","59","60"},
        {"61","62","63","64"},
    };

    public TridTicTacToe() {
        // Asignar '-' a cada posición del arreglo
        for (int i = 0; i < tablero.length; i++) {
            tablero[i] = '-';
        }
    }

    public void crearJugador() {
        System.out.println("Introduce tu nombre: ");
        String n = Keyboard.readString();
        jugador.add(new Jugador(n));
    }

    public void intro() {
        System.out.println("Jugador 1...");
        crearJugador();
        System.out.println("Jugador 2...");
        crearJugador();
        
        if (jugador.size() >= 2) {
            jugador1 = jugador.get(0);
            jugador2 = jugador.get(1);
            turnoActual = jugador1; // Asegúrate de inicializar el turnoActual
        }
    }

    public void formatoTabs(int i, String[][] tab){
        for (int j = 0; j < 4; j++) {
            if (j < 3) {
                System.out.print(String.format("%-3s", tab[i][j]));
            } else {
                System.out.print(String.format("%-9s", tab[i][j]));
            }
        }
    }

    public void imprimirTableros() {
        for (int i = 0; i < 4; i++) {
            formatoTabs(i, tab1);
            formatoTabs(i, tab2);
            formatoTabs(i, tab3);
            formatoTabs(i, tab4);
            System.out.println();
        }
        System.out.println();
        System.out.print(String.format("%-18s", "Fondo 1"));
        System.out.print(String.format("%-18s", "Fondo 2"));
        System.out.print(String.format("%-18s", "Fondo 3"));
        System.out.println(String.format("%-18s", "Fondo 4"));
    }

    public void cuadricula() {
    // Imprimir encabezado con números de columna
    System.out.print("   "); // Espacio para la esquina superior izquierda
    for(int k = 0; k < 4; k++){
        for (int i = 0; i < 4; i++) {
            System.out.print(String.format("%-5s", "  "+i));   
        }
        System.out.print("        ");
    }
        
    System.out.println();

    // Imprimir cuadrícula con el contenido
    for (int i = 0; i < 4; i++) {
        // Imprimir borde superior de cada fila
        System.out.print("  +");
        for (int j = 0; j < 4; j++) {
            System.out.print("----+");
        }
        System.out.println();

        // Imprimir el índice de la fila y los valores
        System.out.print(i + 1 + " "); // Índice de la fila
        for (int j = 0; j < 4; j++) {
            System.out.print(String.format("| %2s ", tab1[i][j])); // Asegura que cada celda tenga un ancho fijo
        }
        System.out.println("|");
    }

    // Imprimir borde inferior de la última fila
    System.out.print("  +");
    for (int j = 0; j < 4; j++) {
        System.out.print("----+");
    }
    System.out.println();
}


    public static void main(String[] args) {
        TridTicTacToe juego = new TridTicTacToe();

        juego.imprimirTableros();
        System.out.println();
        juego.cuadricula();
    }
}
