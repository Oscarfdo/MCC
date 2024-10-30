import java.util.*;

public class Deck {
    private List<Cards> mazo;
    private Set<Cards> cartasRepartidas;  // Nuevo Set para controlar las cartas repartidas

    // Constructor: inicializa el mazo con todas las cartas
    public Deck() {
        mazo = new ArrayList<>();
        cartasRepartidas = new HashSet<>();  // Inicializa el Set vacío
        for (Cards carta : Cards.values()) {
            mazo.add(carta);
        }
        mezclar();  // Mezcla el mazo al inicio
    }

    // Mezcla las cartas del mazo
    public void mezclar() {
        Collections.shuffle(mazo);
    }

    // Reparte una carta que no haya sido repartida antes
    public Cards repartirCarta() {
        if (mazo.isEmpty()) {
            throw new IllegalStateException("No hay más cartas en el mazo.");
        }

        Cards carta;
        do {
            carta = mazo.remove(0);  // Saca la primera carta del mazo
        } while (cartasRepartidas.contains(carta));  // Repite si ya fue repartida

        cartasRepartidas.add(carta);  // Marca la carta como repartida
        return carta;
    }

    // Verifica si quedan cartas en el mazo
    public boolean hayCartas() {
        return !mazo.isEmpty();
    }
}
