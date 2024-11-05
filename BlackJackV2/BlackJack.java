public class BlackJack{

    Jugador jugador;
    Jugador croupier;
    private Mazo mazo;

    public BlackJack(){
        mazo = new Mazo();
        croupier = new Jugador("Croupier");

        System.out.println("Ingrese su nombre");
        jugador = new Jugador(Keyboard.readString());
    }


    public void inicio(){
        mazo.Barajear();
    }

    public void jugar(){
        

        croupier.agregarCarta(mazo.repartirCarta());
        jugador.agregarCarta(mazo.repartirCarta());
        

        
    }

    public static void main(String[] args){
        Mazo mazo = new Mazo();
   
        
        BlackJack juego = new BlackJack();

        System.out.println(mazo);
        
        mazo.Barajear();
        System.out.println("Mazo barajeado");

        System.out.println(mazo);

   
    }
}