/* Oscar Fernando Hernandez Lopez
14 de octubre del 2024
TicTacToe.java
Juego del gato con sistema de puntuacion
para varios jugadores */

import java.util.ArrayList;

public class TicTacToe {
    
    ArrayList<Jugador> jugador = new ArrayList<>(); // Lista dinámica de jugadores
    private boolean hayGanador = false;
    public char[][] tablero;
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

    public char[][] getTablero() { // Para la clase Gato
        return tablero;
    }

    public void vaciarTablero(){
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    tablero[i][j] = '-'; // Inicializa todas las casillas vacías
                }
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
            turnoActual = jugador1; 
        }
    }

    public boolean realizarJugada (int fila, int columna) {
        boolean jugExit = false;

        if (tablero[fila][columna] == '-') {
            tablero[fila][columna] = (turnoActual == jugador1) ? 'X' : 'O';
            jugExit =  true;
        }
        return jugExit; // Casilla ya ocupada
    }

    private void cambiarTurno() {
        turnoActual = (turnoActual == jugador1) ? jugador2 : jugador1; // Cambia el turno entre jugadores
    }

    public boolean verificarGanador() {
        char marcaActual = (turnoActual == jugador1) ? 'X' : 'O';
        boolean ver = false;

        // Revisar filas
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0] == marcaActual && tablero[i][1] == marcaActual && tablero[i][2] == marcaActual) {
                ver = true;
            }
        }
        // Revisar columnas
        for (int i = 0; i < 3; i++) {
            if (tablero[0][i] == marcaActual && tablero[1][i] == marcaActual && tablero[2][i] == marcaActual) {
                ver = true;
            }
        }
        // Revisar diagonales
        if (tablero[0][0] == marcaActual && tablero[1][1] == marcaActual && tablero[2][2] == marcaActual) {
            ver = true;
        }
        if (tablero[0][2] == marcaActual && tablero[1][1] == marcaActual && tablero[2][0] == marcaActual) {
            ver = true;
        }
        return ver;
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
        Gato gato = new Gato(); 

        gato.formaInicial(gato.board, this);
        gato.printBoard(gato.board);

        while (jugador1.getP() < 10 && jugador2.getP() < 10) {
            hayGanador = false; 
            turnoActual = jugador1; // Reiniciar el turno para empezar siempre con el jugador 1

            vaciarTablero();

            while (!hayGanador) {
               
                
                
                System.out.println("Turno del jugador " + turnoActual.getnombre());
                System.out.print("Ingrese el número de la casilla (1-9): ");
                int casilla = Keyboard.readInt();
                int fila = (casilla - 1) / 3;
                int columna = (casilla - 1) % 3;

                if (realizarJugada(fila, columna)) {
                    if (verificarGanador()) {
                        gato.fillBoard(gato.board, this); // O el método que uses para llenar el tablero
                        gato.printBoard(gato.board);
                        System.out.println();
                        System.out.println("¡El jugador " +
                        turnoActual.getnombre() + " ha ganado esta partida!");

                        // Sumar 2 al ganador
                        turnoActual.incrementarP((byte) 5);
                        turnoActual.incrementarPG((byte) 1);
                        turnoActual.incrementarTDP((byte) 1);

                        // Restar 1 al perdedor
                        Jugador perdedor = (turnoActual == jugador1) ? jugador2 : jugador1;
                        perdedor.decrementarP((byte) 1);
                        perdedor.incerementarPP((byte) 1);
                        perdedor.incrementarTDP((byte) 1);


                        hayGanador = true;

                        // Verificar si alguno de los jugadores ha alcanzado 10 puntos en p
                        if (jugador1.getP() >= 10 || jugador2.getP() >= 10) {
                            return; // Interrumpe el método jugar
                        }
                    } else if (verificarEmpate()) {
                        
                        gato.fillBoard(gato.board, this); // O el método que uses para llenar el tablero
                        gato.printBoard(gato.board);
                        System.out.println("¡Es un empate!");

                        // Incrementar el contador de empates para ambos jugadores
                        jugador1.incrementarPE((byte) 1);
                        jugador2.incrementarPE((byte) 1);

                        hayGanador = true;
                    } else {
                        cambiarTurno();
                        gato.fillBoard(gato.board, this);
                        gato.printBoard(gato.board);
                    }
                } else {
                    System.out.println("Casilla ocupada, elija otra.");
                }
            }

            // Mostrar los puntajes actuales después de cada partida
            System.out.println(jugador1.getnombre() + " - Puntaje: " + jugador1.getP());
            System.out.println(jugador2.getnombre() + " - Puntaje: " + jugador2.getP());

             // Inicializar un tablero vacío para cada partida
            vaciarTablero();
            gato.fillBoard(gato.board, this);
            gato.printBoard(gato.board);
        }
    }

    public boolean transicion() {
        String seguir;
        boolean continuar = false;

        System.out.println("¿Seguir jugando?");
        seguir = Keyboard.readString();

        if (seguir.equalsIgnoreCase("si")) {
            continuar = true; // Continuar el juego si la respuesta es "si"
        } else if (seguir.equalsIgnoreCase("no")) {
            continuar = false;
        }
        return continuar;
    }

    public static void main(String[] args) {
    TicTacToe gato = new TicTacToe();

    gato.intro();

    // Bucle para jugar hasta que decidan no continuar
    boolean seguirJugando = true;
    while (seguirJugando) {
        // Inicia una partida
        gato.jugar();

        // Verificar si algún jugador alcanzó 10 puntos
        Jugador jugadorGanador = null; // Para guardar el jugador ganador
        for (Jugador j : gato.jugador) {
            if (j.getP() >= 10) {
            jugadorGanador = j; // El jugador ha ganado
            break; // Salir del bucle una vez encontrado el ganador
    }
}

        // Si hay un ganador, pregunta si quieren seguir jugando
        if (jugadorGanador != null) {
            System.out.println( jugadorGanador.getnombre() + " ha alcanzado 10 puntos!!!");
            seguirJugando = gato.transicion();

            if (seguirJugando) {
               
                gato.crearJugador(); // Registrar un nuevo jugador

                // Cambiar los jugadores para la nueva ronda
                gato.jugador1 = jugadorGanador; // El jugador ganador anterior
                gato.jugador2 = gato.jugador.get(gato.jugador.size() - 1); // El nuevo jugador
                gato.turnoActual = gato.jugador1; // Reiniciar el turno para el nuevo jugador
                gato.jugador1.incrementarAd((byte) 1);
                gato.jugador1.reinicio();
            }
        }
    }

    // Fin del juego
    System.out.println("El juego ha terminado.");
    System.out.println(String.format("%-20s %-11S %-17s %-16s %-17s %-18s %-8s",
    "NOMBRE","ADVERSARIOS", "TOTAL DE PARTIDAS", "PARTIDAS GANADAS", "PARTIDAS PERDIDAS",
    "PARTIDAS EMPATADAS", "PUNTAJE"));
    for (Jugador j : gato.jugador) {
        System.out.println(String.format("%-20s %-11S %-17s %-16s %-17s %-18s %-8s",
         j.getnombre(),j.getAdversarios(),j.getTDP(), j.getPG(), j.getPP(),  j.getPE(),j.getP()));
    }
}
}
