/* Oscar Fernando Hernandez Lopez
30 de octubre del 2024
Player.java
Clase para crear jugadores 
de Blackjack*/

public class Player {
    private String nombre; //Atributos de la clase
    private Hand mano;

    public Player(String nombre) { //Constructor de de la clase
        this.nombre = nombre;
        this.mano = new Hand();
    }

    public String getNombre() { //Metodo para obtener el nombre del jugador
        return nombre;
    }

    public Hand getMano() { //Metodo para obtener la mano del jugador
        return mano;
    }

    public void recibirCarta(Cards carta) { //Metodo para recibir una carta
        mano.agregarCarta(carta);
    }

    public int obtenerValorMano() { //Metodo para sumar el valor 
        return mano.calcularValor();//de todas las cartas de la mano
    }

    @Override
    public String toString() { //Metodo toString de la mano 
        return nombre + " tiene: " + mano.toString();
    }
}
