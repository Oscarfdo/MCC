/* Oscar Fernando Hernandez Lopez
14 de octubre del 2024
TicTacToe.java
Juego del gato */

import java.util.ArrayList;

public class TicTacToe{

    ArrayList<Jugador> jugador = new ArrayList<>();// Lista dinámica de jugadores

    public void crearJugador(){
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

    public void intro(){
        System.out.println("Jugador 1...");
        crearJugador();
        System.out.println("Jugador 2...");
        crearJugador();
    }

    public void imprimirTablero() {
 

        for (int i = 0; i < 15; i++) {
           if (i != 4 || i != 9){
            System.out.println("            *            *");
           } 
           if(i == 4 || i == 9){
                System.out.println("  * * * * * * * * * * * * * * * * * * ");
           }
        }
    }

    public static void main(String[] args) {
        TicTacToe gato = new TicTacToe();

        gato.intro();
        for (Jugador j : gato.jugador) {
        System.out.println(j); // Llama automáticamente al método toString() de Jugador
        }

        gato.imprimirTablero();
    }
}