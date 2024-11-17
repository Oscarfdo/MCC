

public class Jugador{

    private String nombre; //Atributos de la clase
    private int fichas;
    private int puntaje;
    private Mano mano;

    public Jugador(){
        
    }

    public Jugador (String nombre){ //Construtor de la clase
        this.nombre = nombre;
        fichas = 100;
        puntaje = 0;
        this.mano = new Mano();
    }

    public String getNombre(){ //Metodo para obtener el nombre del jugador
        return nombre;
    }

    public int getFichas(){ //Metodo para obtener el total de fichas
        return fichas;
    }

    public int getPuntaje(){ //Metodo para obtener el puntaje del jugador
        return puntaje;
    }

    public int apostar(int apuesta){ //Metodo para apostar fichas
        return this.fichas - apuesta;
    }

    public int ganar(int monto){ //Metodo 
        return fichas + monto;
    }

    /* public int puntajeActual(ArrayList<> mazo){
        return 
    } */

   public void recibirCarta(Baraja carta){
    mano.agregarCarta(carta);
   }

    public int reiniciarPuntaje(){ //Metodo para reiniciar el puntaje a 0
        return puntaje = 0;
    }

    public String toString(){ //Metodo toString de la clase
        return nombre + fichas + puntaje + mano.toString();
    }
}