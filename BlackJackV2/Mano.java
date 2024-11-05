
import java.util.ArrayList;

public class Mano{
    private ArrayList<Baraja> mano;

    public Mano(){
        mano = new ArrayList<>();
    }

    public void agregarCarta(Baraja carta){
        mano.add(carta);
    }

    public int calcularValorMano(){
        int total = 0;

        for(Baraja valor: mano){
            total += valor.getValor(); 
        }
        return total;
    }

    public String toString(){
        return mano.toString();
    }
}