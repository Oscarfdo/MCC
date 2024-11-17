public class BlackJack{

    private static Jugador jugador;
    private static Jugador croupier;
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
        
        croupier.recibirCarta(mazo.repartirCarta());
        croupier.recibirCarta(mazo.repartirCarta());
        jugador.recibirCarta(mazo.repartirCarta());
        jugador.recibirCarta(mazo.repartirCarta());
        
    }

    public static void main(String[] args){
        Mazo mazo = new Mazo();

        BlackJack juego = new BlackJack();

        mazo.Barajear();
        System.out.println("Mazo barajeado");

        System.out.println(croupier);
        System.out.println(jugador);

   
    }
}