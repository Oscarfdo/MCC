/* Oscar Fernando Hernandez Lopez
30 de octubre del 2024
BlackJack.java
Juego BlackJack 
para 1 jugador vs maquina*/
import java.util.List;


public class BlackJack {
    private Deck mazo;
    private Player jugador;
    private Player crupier;

    public BlackJack() {//Constructor de la clase
        mazo = new Deck();
        crupier = new Player("Crupier");

        System.out.print("Ingresa tu nombre: ");
        String nombreJugador = Keyboard.readString();
        jugador = new Player(nombreJugador);
    }

   public void jugar() {//Metodo para la estructura principal del juego
    
        jugador.recibirCarta(mazo.repartirCarta());// Se reparten dos cartas
        jugador.recibirCarta(mazo.repartirCarta());//para cada uno
        crupier.recibirCarta(mazo.repartirCarta());
        crupier.recibirCarta(mazo.repartirCarta());

        System.out.println("Cartas del Jugador:");//Se muestra mano del jugador
        imprimirCartas(jugador.getMano().getCartas());

        System.out.println("Cartas del Crupier:");//Solo se muestra la primera carta 
        imprimirCartasOcultas(crupier.getMano().getCartas());//del crupier

        
        while (jugador.obtenerValorMano() < 21) {//Ciclo para que el jugador arme su mano
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

        while (crupier.obtenerValorMano() < 17) {//Ciclo para que el Crupier arme su mano
            crupier.recibirCarta(mazo.repartirCarta());//hasta llegar a 17
        }

        System.out.println("Cartas del Crupier:");//Mostrar mazo completo de Crupier
        imprimirCartas(crupier.getMano().getCartas());
    }


    private void determinarGanador() {//Metodo para saber si hay ganador
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
    private void imprimirCartasOcultas(List<Cards> mano) {//Metodo para mostrar solo la 
        for (int i = 0; i < mano.size(); i++) {           //primera carta del crupier
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

   
    private void imprimirCartas(List<Cards> mano) {//Metodo para mostrar las cartas
        int numCartas = mano.size();               //de la mano de un jugador

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
        System.out.println("BlackJack...");
        BlackJack juego = new BlackJack();
        juego.jugar();
        juego.determinarGanador();
    }
}
