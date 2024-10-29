import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Cards> mazo;

    // Constructor: inicializa el mazo con todas las cartas
    public Deck() {
        mazo = new ArrayList<>();
        for (Cards carta : Cards.values()) {
            mazo.add(carta);
        }
        mezclar();
    }

    // Mezcla las cartas del mazo
    public void mezclar() {
        Collections.shuffle(mazo);
    }

    // Reparte una carta
    public Cards repartirCarta() {
        return mazo.remove(0); // Retira la primera carta del mazo
    }

    // Verifica si quedan cartas en el mazo
    public boolean hayCartas() {
        return !mazo.isEmpty();
    }
}
