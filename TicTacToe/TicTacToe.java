import java.util.ArrayList;

public class TicTacToe {

    ArrayList<Jugador> jugador = new ArrayList<>(); // Lista dinámica de jugadores
    boolean hayGanador = false;
    private char[][] tablero;
    private Jugador turnoActual; // 'X' para jugador1, 'O' para jugador2
    private Jugador jugador1, jugador2;

    // Constructor vacío
    public TicTacToe() {
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
        byte a = 1; // Puntos ganados
        byte t = 0; // Total jugados
        byte g = 0; // Ganados
        byte d = 0; // Derrotas
        byte e = 0; // Empates
        byte s = 0; // puntaje
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

    public boolean realizarJugada(int fila, int columna) {
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
        while (jugador1.getP() < 10 && jugador2.getP() < 10) {
            hayGanador = false; // Resetear el estado de ganador para cada nueva partida
            turnoActual = jugador1; // Reiniciar el turno para empezar siempre con el jugador 1

            // Inicializar un tablero vacío para cada partida
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    tablero[i][j] = '-'; // Inicializa todas las casillas vacías
                }
            }

            while (!hayGanador) {
                imprimirTablero();
                System.out.println("Turno del jugador " + turnoActual.getnombre());
                System.out.print("Ingrese el número de la casilla (1-9): ");
                int casilla = Keyboard.readInt();
                int fila = (casilla - 1) / 3;
                int columna = (casilla - 1) % 3;

                if (realizarJugada(fila, columna)) {
                    if (verificarGanador()) {
                        imprimirTablero();
                        System.out.println("¡El jugador " + turnoActual.getnombre() + " ha ganado esta partida!");

                        // Sumar 2 al ganador
                        turnoActual.setP((byte) (turnoActual.getP() + 5));
                        turnoActual.setPG((byte) (turnoActual.getPG() + 1));

                        // Restar 1 al perdedor
                        Jugador perdedor = (turnoActual == jugador1) ? jugador2 : jugador1;
                        perdedor.setP((byte) (perdedor.getP() - 1));
                        perdedor.setPP((byte) (perdedor.getPP() + 1));

                        hayGanador = true;

                        // Verificar si alguno de los jugadores ha alcanzado 10 puntos en p
                        if (jugador1.getP() >= 10 || jugador2.getP() >= 10) {
                            return; // Interrumpe el método jugar
                        }
                    } else if (verificarEmpate()) {
                        imprimirTablero();
                        System.out.println("¡Es un empate!");

                        // Incrementar el contador de empates para ambos jugadores
                        jugador1.setPE((byte) (jugador1.getPE() + 1));
                        jugador2.setPE((byte) (jugador2.getPE() + 1));

                        hayGanador = true;
                    } else {
                        cambiarTurno();
                    }
                } else {
                    System.out.println("Casilla ocupada, elija otra.");
                }
            }

            // Mostrar los puntajes actuales después de cada partida
            System.out.println(jugador1.getnombre() + " - Puntaje: " + jugador1.getP());
            System.out.println(jugador2.getnombre() + " - Puntaje: " + jugador2.getP());
        }
    }

    public boolean transicion() {
        String seguir;
        boolean continuar = false;

        System.out.println("¿Seguir jugando?");
        seguir = Keyboard.readString();

        if (seguir.equalsIgnoreCase("si")) {
            continuar = true; // Continuar el juego si la respuesta es "si"
            crearJugador();
        } else if (seguir.equalsIgnoreCase("no")) {
            continuar = false;
        }
        return continuar;
    }

    public static void main(String[] args) {
        TicTacToe gato = new TicTacToe();

        gato.intro();

        // Obtener los jugadores desde el ArrayList
        Jugador jugador1 = gato.jugador.get(0);
        Jugador jugador2 = gato.jugador.get(1);

        // Bucle para jugar hasta que decidan no continuar
        boolean seguirJugando = true;
        while (seguirJugando) {
            // Inicia una partida
            gato.jugar();

            // Verificar si algún jugador alcanzó 10 puntos
            if (jugador1.getP() >= 10 || jugador2.getP() >= 10) {
                // Ejecución de la transición cuando alguno alcanza 10 puntos
                seguirJugando = gato.transicion();

                // Resetear los puntajes de ambos jugadores si deciden continuar
                if (seguirJugando) {
                    jugador1.setP((byte) 0);
                    jugador2.setP((byte) 0);
                }
            }

            // Si deciden no continuar, termina el juego
            if (!seguirJugando) {
                jugador1.setP((byte) 0);
                jugador2.setP((byte) 0);
                System.out.println("El juego ha terminado.");
                for (Jugador j : gato.jugador) {
                System.out.println(j.getnombre() + " - Puntaje: " + j.getP() +
                ", Partidas Ganadas: " + j.getPG() + ", Partidas Perdidas: " +
                j.getPP() + ", Partidas Empatadas: " + j.getPE());
            }

            
    }
        }
    }
}
