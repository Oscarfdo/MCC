/* Oscar Fernando Hernandez Lopez
30 de octubre del 2024
Deck.java
Clase para definir un mazo 
a partir de la clase baraja*/

import java.util.*;

public class Deck {
    private List<Cards> mazo;  // Mazo de cartas del juego 

    
    public Deck() {//COnstructor de la clase
        mazo = new ArrayList<>();//Se inicializa el juego con todas las cartas de la baraja
        for (Cards carta : Cards.values()) {
            mazo.add(carta);
        }
        mezclar();  
    }

  
    public void mezclar() {//Metodo para mezclar las cartas del mazo
        Collections.shuffle(mazo);
    }

  
    public Cards repartirCarta() {//Metodo para repartir una carta
        return mazo.remove(0);  
    }

    public String toString(){//Metodo to String
        return mazo.toString();
    }

   
}
