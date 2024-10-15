Jugador jugadorGanador = null; // Para guardar el jugador ganador

// Recorrer la lista de jugadores para buscar al que ha alcanzado 10 puntos
for (Jugador j : gato.jugador) {
    if (j.getP() >= 10) {
        jugadorGanador = j; // El jugador ha ganado
        break; // Salir del bucle una vez encontrado el ganador
    }
}

// Si hay un ganador, preguntar si quieren seguir jugando
if (jugadorGanador != null) {
    System.out.println(jugadorGanador.getnombre() + " ha alcanzado 10 puntos!!!");
    seguirJugando = gato.transicion();

    if (seguirJugando) {
        // Reiniciar puntajes a 0 y registrar un nuevo jugador si continúan jugando
        gato.crearJugador(); // Registrar un nuevo jugador

        // Cambiar los jugadores para la nueva ronda
        gato.jugador1 = jugadorGanador; // El jugador ganador anterior
        gato.jugador2 = gato.jugador.get(gato.jugador.size() - 1); // El nuevo jugador
        gato.turnoActual = gato.jugador1; // Reiniciar el turno para el nuevo jugador

        gato.jugador1.incrementarAd((byte) 1);
        gato.jugador1.reinicio();
    }
}
