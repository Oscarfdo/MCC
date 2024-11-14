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
    private int apuesta;

    public BlackJack() {//Constructor de la clase
        mazo = new Deck();
        crupier = new Player("Crupier");

        System.out.print("Ingresa tu nombre: ");
        String nombreJugador = Keyboard.readString();
        jugador = new Player(nombreJugador);
    }

  public void jugar() {//Metodo con la estructura principal del juego
        

        while (true) {
            System.out.println();
            System.out.println( jugador.getNombre() + " cuenta con: " +
             jugador.getFichas() + " fichas");
            System.out.println("El crupier cuenta con: " + crupier.getFichas() +
             " fichas");
            System.out.println();
            System.out.println("¿Cuántas fichas deseas apostar?");
            apuesta = Keyboard.readInt();
            System.out.println();

            if (!jugador.apostar(apuesta)) {
                System.out.println("Apuesta inválida, intenta nuevamente.");
                continue;
            }

            if (!crupier.apostar(apuesta)) {
                System.out.println("El crupier no tiene tantas fichas.");
                continue;
            }

            
            jugador.getMano().getCartas().clear(); // Se limpian las manos 
            crupier.getMano().getCartas().clear();
            jugador.recibirCarta(mazo.repartirCarta());//Se reparten 2 cartas
            jugador.recibirCarta(mazo.repartirCarta());
            crupier.recibirCarta(mazo.repartirCarta());
            crupier.recibirCarta(mazo.repartirCarta());

            System.out.println();
            System.out.println("Cartas del Jugador:");
            imprimirCartas(jugador.getMano().getCartas());
            System.out.println("Carta del Crupier:");
            imprimirCartasOcultas(crupier.getMano().getCartas());
            System.out.println();

            while (jugador.obtenerValorMano() < 21) {
                System.out.println("¿Quieres otra carta? (Si/No)");
                String respuesta = Keyboard.readString();

                if (respuesta.equalsIgnoreCase("si")) {
                    jugador.recibirCarta(mazo.repartirCarta());
                    System.out.println();
                    System.out.println("Cartas del Jugador:");
                    imprimirCartas(jugador.getMano().getCartas());
                } else {
                    break;
                }
            }

            while (crupier.obtenerValorMano() < 17) {
                crupier.recibirCarta(mazo.repartirCarta());
            }

            System.out.println("Cartas del Crupier:");
            imprimirCartas(crupier.getMano().getCartas());

            determinarGanador();

            if (jugador.getFichas() <= 0) {
                System.out.println("Te has quedado sin fichas. ¡El juego ha terminado!");
                break;
            } else if (crupier.getFichas() <= 0) { // Solo si implementamos fichas para el crupier
                System.out.println("El crupier se ha quedado sin fichas. ¡Has ganado el juego!");
            break;
            }

            System.out.println();
            System.out.println("¿Quieres seguir jugando? (sí/no)");
            String respuesta = Keyboard.readString();
            if (!respuesta.equalsIgnoreCase("si")) {
                System.out.println("Gracias por jugar. Fichas finales: " + jugador.getFichas());
                break;
            }
            
            // Mezclar el mazo nuevamente antes de la siguiente ronda
            mazo.mezclar();
        }
       
    }


    private void determinarGanador() {//Metodo para saber si hay ganador
        int valorJugador = jugador.obtenerValorMano();
        int valorCrupier = crupier.obtenerValorMano();

        System.out.println("Valor de la mano de "+ jugador.getNombre() + ": "+ valorJugador);
        System.out.println("Valor de la mano del Crupier: " + valorCrupier);
        System.out.println();

        if (valorJugador == valorCrupier) {
            
            System.out.println("Es un empate.");
            jugador.ganarApuesta(apuesta / 2); // Devolver la mitad de la apuesta
            crupier.ganarApuesta(apuesta / 2);
            
            
        } else if (valorJugador > 21) {
            System.out.println("¡Te pasaste de 21! El crupier gana.");
            crupier.ganarApuesta(apuesta);

        } else if (valorCrupier > 21) {
            System.out.println("El crupier se pasó de 21. ¡Ganaste!");
            jugador.ganarApuesta(apuesta);
            
        } else if (valorJugador > valorCrupier) {
            System.out.println("¡Felicidades, " + jugador.getNombre() + "! Ganaste.");
            jugador.ganarApuesta(apuesta);   
            
        }  else {
            System.out.println("El crupier gana.");
            
            crupier.ganarApuesta(apuesta);
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
                System.out.printf("|%-2s   |   ", mano.get(i).getNombre());
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
            String nombre = carta.getNombre();
            if (nombre.length() == 1) {
            System.out.printf("|%s    |   ", nombre); // Un solo carácter, añadir un espacio extra
            } else {
            System.out.printf("|%-2s   |   ", nombre); // Dos caracteres
        }
        }
        System.out.println();

        for (Cards carta : mano) {
            System.out.printf("|  %s  |   ", carta.getPalo());
        }
        System.out.println();

        for (Cards carta : mano) {
            String nombre = carta.getNombre();
            if (nombre.length() == 1) {
            System.out.printf("|    %s|   ", carta.getNombre());
            } else {
            System.out.printf("|   %2s|   ", carta.getNombre());
            }
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
    }
}
