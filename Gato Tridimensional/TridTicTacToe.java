/* Oscar Fernando Hernandez Lopez
14 de octubre del 2024
TridTicTacToe.java
Juego del gato tridimensional
para dos jugadores */

import java.util.ArrayList;

public class TridTicTacToe{

    // Inicializar el arreglo
    char[] tablero = new char[62];

    private Jugador turnoActual; // 'X' para jugador1, 'O' para jugador2
    private Jugador jugador1, jugador2;
    private boolean hayGanador = false;

     ArrayList<Jugador> jugador = new ArrayList<>(); // Lista dinámica de jugadores


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

    public void imprimirTablero() {
        for (int i = 0; i < 3; i++) {
            // Se imprimen cuatro líneas por cada fila del tablero
            for (int j = 0; j < 4; j++) {
             // Primera columna
                if (tablero[i][0] == 'X' || tablero[i][0] == 'O') {
                    System.out.print(String.format("%-1s %-1s %-1s %-1s %-1s",
                    tablero[i][0], " ", " ", tablero[i][0], "*"));
                } else {
                System.out.print(String.format("%-1s %-1s %-1s %-1s %-1s",
                    " ", " ", " ", " ", "*"));
                }

                // Segunda columna
                if (tablero[i][1] == 'X' || tablero[i][1] == 'O') {
                    System.out.print(String.format("%-1s %-1s %-1s %-1s %-1s",
                    tablero[i][1], " ", " ", tablero[i][1], "*"));
                } else {
                System.out.print(String.format("%-1s %-1s %-1s %-1s %-1s",
                    " ", " ", " ", " ", "*"));
                }

                // Tercera columna
                if (tablero[i][2] == 'X' || tablero[i][2] == 'O') {
                    System.out.print(String.format("%-1s %-1s %-1s %-1s %-1s",
                    tablero[i][2], " ", " ", tablero[i][2], " "));
                } else {
                    System.out.print(String.format("%-1s %-1s %-1s %-1s %-1s",
                    " ", " ", " ", " ", " "));
                }
            
                // Nueva línea para imprimir las siguientes filas vacías o jugadas
                System.out.println();
            }
        
            // Imprimir línea separadora de asteriscos entre filas
            if (i < 2) {
                for (int k = 0; k < 3; k++) {
                    System.out.print(String.format("%-1s %-1s %-1s %-1s %-1s", "*", "*", "*", "*", "*"));
                }
                System.out.println();
            }
        }
    }
}