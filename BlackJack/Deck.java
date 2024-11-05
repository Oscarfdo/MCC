import java.util.*;

public class Deck {
    private List<Cards> mazo;  // Mazo de cartas

    // Constructor: inicializa el mazo con todas las cartas
    public Deck() {
        mazo = new ArrayList<>();
        for (Cards carta : Cards.values()) {
            mazo.add(carta);
        }
        mezclar();  // Mezcla el mazo al inicio
    }

    // Mezcla las cartas del mazo
    public void mezclar() {
        Collections.shuffle(mazo);
    }

    // Reparte una carta y la elimina del mazo
    public Cards repartirCarta() {
        if (mazo.isEmpty()) {
            throw new IllegalStateException("No hay más cartas en el mazo.");
        }
        return mazo.remove(0);  // Elimina y devuelve la primera carta del mazo
    }

    // Verifica si quedan cartas en el mazo
    public boolean hayCartas() {
        return !mazo.isEmpty();
    }
}
