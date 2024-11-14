/* Oscar Fernando Hernandez Lopez
30 de octubre del 2024
Hand.java
Clase para que cada jugador 
cuente con su mano*/

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private List<Cards> cartas; //Lista para las cartas de la mano

    public Hand() { //Constructor de la clase
        cartas = new ArrayList<>();
    }

   
    public List<Cards> getCartas() {//Lista para obtener la lista de cartas
    return cartas;
    }

    public void agregarCarta(Cards carta) {//Metodo para agregar un carta a la mano
        cartas.add(carta);
    }

    public int calcularValor() { //Metodo para calcular el valor de la mano
        int valor = 0;
        int ases = 0;

        for (Cards carta : cartas) {
            valor += carta.getValor();
            if (carta.getNombre().equals("As")) {
                ases++;
            }
        }

        while (valor > 21 && ases > 0) {//Se ajusta el valor en caso de haber una A
            valor -= 10;
            ases--;
        }

        return valor;
    }

 
    @Override
    public String toString() {//Metodo toString de la clase
        return cartas.toString();
    }
}
