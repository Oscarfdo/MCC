/* Oscar Fernando Hernandez Lopez
30 de octubre del 2024
BlackJack.java
Juego BlackJack 
para 1 jugadores vs maquina*/
import java.util.List;


public class BlackJack {
    private Deck mazo;
    private Player jugador;
    private Player crupier;

    public BlackJack() {
        mazo = new Deck();
        crupier = new Player("Crupier");

        System.out.print("Ingresa tu nombre: ");
        String nombreJugador = Keyboard.readString();
        jugador = new Player(nombreJugador);
    }

   public void jugar() {
    

    // Repartir dos cartas al jugador y al crupier al comienzo
        jugador.recibirCarta(mazo.repartirCarta());
        jugador.recibirCarta(mazo.repartirCarta());
        crupier.recibirCarta(mazo.repartirCarta());
        crupier.recibirCarta(mazo.repartirCarta());

        System.out.println("Cartas del Jugador:");
        imprimirCartas(jugador.getMano().getCartas());

        System.out.println("Cartas del Crupier:");
        imprimirCartasCrupierOcultas(crupier.getMano().getCartas());

        // Turno del jugador
        while (jugador.obtenerValorMano() < 21) {
            System.out.println("¿Quieres otra carta? (Si/No)");
            String respuesta = Keyboard.readString();

            if (respuesta.equalsIgnoreCase("si")) {
                jugador.recibirCarta(mazo.repartirCarta());
                System.out.println("Cartas del Jugador:");
                imprimirCartas(jugador.getMano().getCartas());
            } else {
                break;
            }
        }

        // Turno del crupier: Pedir cartas hasta llegar a 17 o más
        while (crupier.obtenerValorMano() < 17) {
            crupier.recibirCarta(mazo.repartirCarta());
        }

        // Revelar todas las cartas del crupier al final del juego
        System.out.println("Cartas del Crupier:");
        imprimirCartas(crupier.getMano().getCartas());

        // Determinar el ganador
        determinarGanador();
    }


    private void determinarGanador() {
        int valorJugador = jugador.obtenerValorMano();
        int valorCrupier = crupier.obtenerValorMano();

        System.out.println("Valor del Jugador: " + valorJugador);
        System.out.println("Valor del Crupier: " + valorCrupier);

        if (valorJugador > 21) {
            System.out.println("¡Te pasaste de 21! El crupier gana.");
        } else if (valorCrupier > 21) {
            System.out.println("El crupier se pasó de 21. ¡Ganaste!");
        } else if (valorJugador > valorCrupier) {
            System.out.println("¡Felicidades, " + jugador.getNombre() + "! Ganaste.");
        } else if (valorJugador == valorCrupier) {
            System.out.println("Es un empate.");
        } else {
            System.out.println("El crupier gana.");
        }
    }


//Empieza codigo de formato

// Mostrar solo la primera carta del crupier, ocultando las demás.
    private void imprimirCartasCrupierOcultas(List<Cards> mano) {
        for (int i = 0; i < mano.size(); i++) {
            System.out.print("╭-----╮   ");
        }
        System.out.println();

        for (int i = 0; i < mano.size(); i++) {
            if (i == 0) { // Mostrar la primera carta
                System.out.printf("|%-2d   |   ", mano.get(i).getValor());
            } else { // Ocultar las demás
                System.out.print("|  ?  |   ");
            }
        }
        System.out.println();

        for (int i = 0; i < mano.size(); i++) {
            if (i == 0) {
                System.out.printf("|  %s  |   ", mano.get(i).getPalo());
            } else {
                System.out.print("|  ?  |   ");
            }
        }
        System.out.println();

        for (int i = 0; i < mano.size(); i++) {
            if (i == 0) {
                System.out.printf("|   %-2d|   ", mano.get(i).getValor());
            } else {
                System.out.print("|  ?  |   ");
            }
        }
        System.out.println();

        for (int i = 0; i < mano.size(); i++) {
            System.out.print("╰-----╯   ");
        }
        System.out.println();
    }

    // Mostrar todas las cartas del crupier al final del juego.
    private void imprimirCartas(List<Cards> mano) {
        int numCartas = mano.size();

        for (int i = 0; i < numCartas; i++) {
            System.out.print("╭-----╮   ");
        }
        System.out.println();

        for (Cards carta : mano) {
            System.out.printf("|%-2d   |   ", carta.getValor());
        }
        System.out.println();

        for (Cards carta : mano) {
            System.out.printf("|  %s  |   ", carta.getPalo());
        }
        System.out.println();

        for (Cards carta : mano) {
            System.out.printf("|   %-2d|   ", carta.getValor());
        }
        System.out.println();

        for (int i = 0; i < numCartas; i++) {
            System.out.print("╰-----╯   ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        System.out.println("¡Bienvenido al juego de BlackJack!");
        BlackJack juego = new BlackJack();
        juego.jugar();
    }
}
