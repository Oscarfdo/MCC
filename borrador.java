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
                    turnoActual.setP((byte) (turnoActual.getP() + 2));
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

public static void main(String[] args) {
    TicTacToe gato = new TicTacToe();

    gato.intro();

    boolean seguirJugando = true;
    Jugador jugadorGanador = null; // Para guardar el jugador ganador

    while (seguirJugando) {
        // Inicia una partida
        gato.jugar();

        // Verificar si algún jugador alcanzó 10 puntos
        if (gato.jugador.get(0).getP() >= 10) {
            jugadorGanador = gato.jugador.get(0); // Jugador 1 ganó
        } else if (gato.jugador.get(1).getP() >= 10) {
            jugadorGanador = gato.jugador.get(1); // Jugador 2 ganó
        }

        // Si hay un ganador, pregunta si quieren seguir jugando
        if (jugadorGanador != null) {
            System.out.println("¡El jugador " + jugadorGanador.getnombre() + " ha ganado!");
            seguirJugando = gato.transicion();

            if (seguirJugando) {
                gato.crearJugador(); // Registrar un nuevo jugador

                // Cambiar los jugadores para la nueva ronda
                jugador1 = jugadorGanador; // El jugador ganador anterior
                jugador2 = gato.jugador.get(gato.jugador.size() - 1); // El nuevo jugador
                gato.turnoActual = jugador1; // Reiniciar el turno para el nuevo jugador
            }
        }

        // Si deciden no continuar, termina el juego
        if (!seguirJugando) {
            System.out.println("El juego ha terminado.");
            for (Jugador j : gato.jugador) {
                System.out.println(j.getnombre() + " - Puntaje: " + j.getP() +
                    ", Partidas Ganadas: " + j.getPG() + ", Partidas Perdidas: " +
                    j.getPP() + ", Partidas Empatadas: " + j.getPE());
            }
        }
    }
}
