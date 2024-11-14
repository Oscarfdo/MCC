/* Oscar Fernando Hernandez Lopez
30 de octubre del 2024
Player.java
Clase para crear jugadores 
de Blackjack*/

public class Player {
    private String nombre; //Atributos de la clase
    private Hand mano;
    private int fichas;

    public Player(String nombre) { //Constructor de de la clase
        this.nombre = nombre;
        this.mano = new Hand();
        this.fichas = 100;
    }

    public String getNombre() { //Metodo para obtener el nombre del jugador
        return nombre;
    }

    public Hand getMano() { //Metodo para obtener la mano del jugador
        return mano;
    }

    public int getFichas() {//Metodo para obtener el numero de fichas del jugador
        return fichas;
    }

    public void recibirCarta(Cards carta) { //Metodo para recibir una carta
        mano.agregarCarta(carta);
    }

    public int obtenerValorMano() { //Metodo para sumar el valor 
        return mano.calcularValor();//de todas las cartas de la mano
    }

    public boolean apostar(int apuesta) {//Metodo para realizar un apuesta
        boolean state;
        if (apuesta <= fichas) {
            fichas -= apuesta;
            System.out.println(nombre + " ha apostado " + apuesta + " fichas. Fichas restantes: " + fichas);
            state = true;
        } else {
            System.out.println("No tienes suficientes fichas para esa apuesta.");
            state = false;
        }
        return state;
    }

    public void ganarApuesta(int apuesta) {//Metodo para recibir ganancias
        fichas += apuesta * 2;
        System.out.println(nombre + " ha ganado " + (apuesta * 2) + " fichas. Fichas actuales: " + fichas);
    }

    @Override
    public String toString() { //Metodo toString de la mano 
        return nombre + " tiene: " + mano.toString() + "y cuenta con:" + fichas;
    }
}
