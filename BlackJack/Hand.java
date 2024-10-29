import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Cards> cartas;

    public Hand() {
        cartas = new ArrayList<>();
    }

    // Añadir una carta a la mano
    public void agregarCarta(Cards carta) {
        cartas.add(carta);
    }

    public List<Cards> getCartas() {
    return cartas;
    }


    // Calcular el valor total de la mano (considerando la regla del As)
    public int calcularValor() {
        int valor = 0;
        int ases = 0;

        for (Cards carta : cartas) {
            valor += carta.getValor();
            if (carta.getNombre().equals("As")) {
                ases++;
            }
        }

        // Ajustar el valor si hay Ases para no pasarse de 21
        while (valor > 21 && ases > 0) {
            valor -= 10;
            ases--;
        }

        return valor;
    }

    // Mostrar las cartas de la mano
    @Override
    public String toString() {
        return cartas.toString();
    }
}
