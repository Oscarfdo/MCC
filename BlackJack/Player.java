public class Player {
    private String nombre;
    private Hand mano;

    public Player(String nombre) {
        this.nombre = nombre;
        this.mano = new Hand();
    }

    public String getNombre() {
        return nombre;
    }

    public Hand getMano() {
        return mano;
    }

    public void recibirCarta(Cards carta) {
        mano.agregarCarta(carta);
    }

    public int obtenerValorMano() {
        return mano.calcularValor();
    }

    @Override
    public String toString() {
        return nombre + " tiene: " + mano.toString();
    }
}
