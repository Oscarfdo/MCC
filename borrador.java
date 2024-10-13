/* Oscar Fernando Hernandez Lopez
14 de octubre del 2024
borrador.java
Juego del gato */

import java.util.ArrayList;

public class borrador {

    ArrayList<Jugador> jugador = new ArrayList<>(); // Lista dinámica de jugadores
    boolean hayGanador = false;
    private char[][] tablero;
    private Jugador turnoActual; // 'X' para jugador1, 'O' para jugador2
    private Jugador jugador1, jugador2;

    // Constructor vacío
    public borrador() {
        tablero = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = '-'; // Inicializa todas las casillas vacías
            }
        }
    }

    public void crearJugador() {
        System.out.println("Introduce tu nombre: ");
        String n = Keyboard.readString();
        byte a = 1;
        byte t = 0;
        byte g = 0;
        byte d = 0;
        byte e = 0;
        byte s = 0;

        jugador.add(new Jugador(n, a, t, g, d, e, s));
    }

    public void agregarJugadores() {
        System.out.println("¿Quieres agregar más jugadores? (si/no)");
        String respuesta = Keyboard.readString();

        while (respuesta.equalsIgnoreCase("si")) {
            crearJugador();
            System.out.println("¿Agregar otro jugador? (si/no)");
            respuesta = Keyboard.readString();
        }
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

    public void imprimirVacio() {
        for (int i = 0; i < 15; i++) {
            if (i != 4 || i != 9) {
                System.out.print("            *");
                System.out.print("            *");
                System.out.println("             ");
            }
            if (i == 4 || i == 9) {
                System.out.println("  * * * * * * * * * * * * * * * * * * ");
            }
        }
    }

    public void imprimirTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean realizarJugada(int casilla) {
        int fila = (casilla - 1) / 3;
        int columna = (casilla - 1) % 3;

        if (tablero[fila][columna] == '-') {
            tablero[fila][columna] = (turnoActual == jugador1) ? 'X' : 'O';
            return true;
        }
        return false; // Casilla ya ocupada
    }

    private void cambiarTurno() {
        turnoActual = (turnoActual == jugador1) ? jugador2 : jugador1; // Cambia el turno entre jugadores
    }

    public boolean verificarGanador() {
        char marcaActual = (turnoActual == jugador1) ? 'X' : 'O';

        // Revisar filas
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == marcaActual && tablero[i][1] == marcaActual && tablero[i][2] == marcaActual) {
                return true;
            }
        }
        // Revisar columnas
        for (int i = 0; i < 3; i++) {
            if (tablero[0][i] == marcaActual && tablero[1][i] == marcaActual && tablero[2][i] == marcaActual) {
                return true;
            }
        }
        // Revisar diagonales
        if (tablero[0][0] == marcaActual && tablero[1][1] == marcaActual && tablero[2][2] == marcaActual) {
            return true;
        }
        if (tablero[0][2] == marcaActual && tablero[1][1] == marcaActual && tablero[2][0] == marcaActual) {
            return true;
        }
        return false;
    }

    public boolean verificarEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j] == '-') {
                    return false; // Hay casillas vacías, no hay empate
                }
            }
        }
        return true; // Todas las casillas llenas
    }

    public void jugar() {
        while (!hayGanador) {
            imprimirTablero();
            System.out.println("Turno del jugador " + turnoActual.getnombre());
            System.out.print("Ingrese el número de casilla (1-9): ");
            int casilla = Keyboard.readInt();

            if (realizarJugada(casilla)) {
                if (verificarGanador()) {
                    imprimirTablero();
                    System.out.println("¡El jugador " + turnoActual.getnombre() + " ha ganado!");
                    hayGanador = true;
                } else if (verificarEmpate()) {
                    imprimirTablero();
                    System.out.println("¡Es un empate!");
                    break;
                } else {
                    cambiarTurno();
                }
            } else {
                System.out.println("Casilla ocupada, elija otra.");
            }
        }
    }

    public boolean transicion() {
        String seguir;
        boolean continuar = false;

        if (hayGanador) {
            System.out.println("¿Seguir jugando?");
            seguir = Keyboard.readString();

            if (seguir.equalsIgnoreCase("si")) {
                continuar = true; // Continuar el juego si la respuesta es "si"
                crearJugador(); // Puedes modificar esto si necesitas agregar jugadores de nuevo
            } else if (seguir.equalsIgnoreCase("no")) {
                continuar = false;
            }
        }
        return continuar;
    }

    public static void main(String[] args) {
        borrador gato = new borrador(); // Se inicializa con el constructor vacío
        gato.intro(); // Inicializa los jugadores

        gato.imprimirVacio();
        gato.jugar(); // Inicia el juego

        for (Jugador j : gato.jugador) {
            if (j.getP() == 10) {
                gato.hayGanador = true;
                System.out.println(j.getnombre() + " es el ganador, ¡felicidades!");
                break; // Romper el bucle si se encuentra un ganador
            }
        }

        gato.transicion();

        for (Jugador j : gato.jugador) {
            System.out.println(j.getnombre() + " - Puntaje: " + j.getP() +
                ", Partidas Ganadas: " + j.getPG() + ", Partidas Perdidas: " +
                j.getPP() + ", Partidas Empatadas: " + j.getPE());
        }
    }
}
