public class Prueba {

    private static void carta(int num, String palo){
        System.out.println("╭-----╮");
        System.out.println("|"+num+"    |");
        System.out.println("|  "+palo+"  |");
        System.out.println("|    "+num+"|");
        System.out.println("╰-----╯");
    }

    private static void imprimirCartas(int num, String palo){
        for(int i = 0; i < 3; i++){
            System.out.print("╭-----╮");
            System.out.print("   ");
        }

        System.out.println();

        for (int i = 0; i < 3; i ++) {
            System.out.print("|"+num+"    |");
            System.out.print("   ");
        }

        System.out.println();

        for (int i = 0; i < 3; i ++) {
            System.out.print("|  "+palo+"  |");
            System.out.print("   ");
        }

        System.out.println();

        for (int i = 0; i < 3; i ++) {
            System.out.print("|    "+num+"|");
            System.out.print("   ");
        }

        System.out.println();

        for (int i = 0; i < 3; i ++) {
            System.out.print("╰-----╯");
            System.out.print("   ");
        }
    }
    public static void main(String[] args) {
        System.out.println("Símbolos de cartas: ♥ ♠ ♦ ♣");
        carta(1, "♠");
        imprimirCartas(5, "♣");
    }
}
