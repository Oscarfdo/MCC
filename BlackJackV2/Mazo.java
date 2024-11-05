
import java.util.ArrayList;
import java.util.Collections;


public class Mazo{
    public ArrayList<Baraja> mazo; 

    public Mazo(){ //Metodo para generar un mazo a partir de la baraja

        mazo = new ArrayList<>();

        for(Baraja carta: Baraja.values()){
            mazo.add(carta);
        }
    }

    public void Barajear(){//Metodo para barajear las cartas del mazo
        Collections.shuffle(mazo);
    }


    /*public void partirMazo(){

    }*/

    public Baraja repartirCarta(){//Metodo para repartir una carta del mazo y luego eliminarla
        return mazo.remove(0);
    }

   
@Override
    public String toString() { // Método toString de la Clase
      
        String resultado = "Contenido del Mazo:\n"; // Inicializamos la cadena con un encabezado

        for (Baraja carta : mazo) {
            resultado += carta + "  "; // Concatenamos cada carta y un salto de línea
        }

        return resultado; // Devuelve la cadena completa
    }
}