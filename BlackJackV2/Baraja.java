
public enum Baraja {
    
AS_CORAZONES("As", "♥", 11), // Corazones
DOS_CORAZONES("2", "♥", 2),
TRES_CORAZONES("3", "♥", 3),
CUATRO_CORAZONES("4", "♥", 4),
CINCO_CORAZONES("5", "♥", 5),
SEIS_CORAZONES("6", "♥", 6),
SIETE_CORAZONES("7", "♥", 7),
OCHO_CORAZONES("8", "♥", 8),
NUEVE_CORAZONES("9", "♥", 9),
DIEZ_CORAZONES("10", "♥", 10),
JOTA_CORAZONES("J", "♥", 10),
REINA_CORAZONES("Q", "♥", 10),
REY_CORAZONES("K", "♥", 10),


AS_PICAS("As", "♠", 11), // Picas
DOS_PICAS("2", "♠", 2),
TRES_PICAS("3", "♠", 3),
CUATRO_PICAS("4", "♠", 4),
CINCO_PICAS("5", "♠", 5),
SEIS_PICAS("6", "♠", 6),
SIETE_PICAS("7", "♠", 7),
OCHO_PICAS("8", "♠", 8),
NUEVE_PICAS("9", "♠", 9),
DIEZ_PICAS("10", "♠", 10),
JOTA_PICAS("J", "♠", 10),
REINA_PICAS("Q", "♠", 10),
REY_PICAS("K", "♠", 10),


AS_DIAMANTES("As", "♦", 11), // Diamantes
DOS_DIAMANTES("2", "♦", 2),
TRES_DIAMANTES("3", "♦", 3),
CUATRO_DIAMANTES("4", "♦", 4),
CINCO_DIAMANTES("5", "♦", 5),
SEIS_DIAMANTES("6", "♦", 6),
SIETE_DIAMANTES("7", "♦", 7),
OCHO_DIAMANTES("8", "♦", 8),
NUEVE_DIAMANTES("9", "♦", 9),
DIEZ_DIAMANTES("10", "♦", 10),
JOTA_DIAMANTES("J", "♦", 10),
REINA_DIAMANTES("Q", "♦", 10),
REY_DIAMANTES("K", "♦", 10),


AS_TREBOLES("As", "♣", 11), // Tréboles
DOS_TREBOLES("2", "♣", 2),
TRES_TREBOLES("3", "♣", 3),
CUATRO_TREBOLES("4", "♣", 4),
CINCO_TREBOLES("5", "♣", 5),
SEIS_TREBOLES("6", "♣", 6),
SIETE_TREBOLES("7", "♣", 7),
OCHO_TREBOLES("8", "♣", 8),
NUEVE_TREBOLES("9", "♣", 9),
DIEZ_TREBOLES("10", "♣", 10),
JOTA_TREBOLES("J", "♣", 10),
REINA_TREBOLES("Q", "♣", 10),
REY_TREBOLES("K", "♣", 10);

    
    private final String nombre; // Atributos de cada carta
    private final String tipo;
    private final int valor;

    
    private Baraja(String nombre, String tipo, int valor) { // Constructor de la clase
        this.nombre = nombre;
        this.tipo = tipo;
        this.valor = valor;
    }

   
    public String getNombre() { // Metodo para obtener el nombre de la carta
        return nombre;
    }

    public String getPalo() {// Metodo para obtener el tipo de la carta
        return tipo;
    }

    public int getValor() {// Metodo para obtener el valor de la carta
        return valor;
    }

    @Override
    public String toString(){// Metodo toString de la clase
        return nombre + tipo;
    }
}
