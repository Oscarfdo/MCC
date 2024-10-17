
import java.util.ArrayList;

public class TridTicTacToe {

    private Jugador turnoActual;
    private Jugador jugador1, jugador2;
    private boolean hayGanador = false;

    ArrayList<Jugador> jugador = new ArrayList<>();

    String[][] tab1 = {
        {"-", "-", "-", "-"},
        {"-", "-", "-", "-"},
        {"-", "-", "-", "-"},
        {"-", "-", "-", "-"}
    };

    String[][] tab2 = {
        {"-", "-", "-", "-"},
        {"-", "-", "-", "-"},
        {"-", "-", "-", "-"},
        {"-", "-", "-", "-"}
    };

    String[][] tab3 = {
        {"-", "-", "-", "-"},
        {"-", "-", "-", "-"},
        {"-", "-", "-", "-"},
        {"-", "-", "-", "-"}
    };

    String[][] tab4 = {
        {"-", "-", "-", "-"},
        {"-", "-", "-", "-"},
        {"-", "-", "-", "-"},
        {"-", "-", "-", "-"}
    };

    public void crearJugador() {
        System.out.println("Introduce tu nombre: ");
        String n = Keyboard.readString();
        jugador.add(new Jugador(n));
    }

    public void intro() {
        System.out.println("Jugador 1...");
        crearJugador();
        System.out.println("Jugador 2...");
        crearJugador();

        if (jugador.size() >= 2) {
            jugador1 = jugador.get(0);
            jugador2 = jugador.get(1);
            turnoActual = jugador1;
        }
    }

    public boolean realizarJugada(int fila, int columna, int capa) {
        boolean jugExit = false;
        String[][] tableroSeleccionado;

        switch (capa) {
            case 0:
                tableroSeleccionado = tab1;
                break;
            case 1:
                tableroSeleccionado = tab2;
                break;
            case 2:
                tableroSeleccionado = tab3;
                break;
            case 3:
                tableroSeleccionado = tab4;
                break;
            default:
                System.out.println("Capa inválida. Debe ser un número entre 1 y 4.");
                return false;
        }

        if (tableroSeleccionado[fila][columna].equals("-")) {
            tableroSeleccionado[fila][columna] = (turnoActual == jugador1) ? "X" : "O";
            jugExit = true;
        } else {
            System.out.println("Casilla ocupada, elija otra.");
        }

        return jugExit;
    }

    public boolean verificarLinea(String[][] tab, int i, String marca) {
    return tab[i][0].equals(marca) &&
           tab[i][1].equals(marca) &&
           tab[i][2].equals(marca) &&
           tab[i][3].equals(marca);
    }


    public boolean verificarColumna(String[][] tab, int i, String marca) {
    return tab[0][i].equals(marca) &&
           tab[1][i].equals(marca) &&
           tab[2][i].equals(marca) &&
           tab[3][i].equals(marca);
    
    }   

    // Verifica la diagonal principal (de la esquina superior izquierda a la inferior derecha)
    public boolean verificarDiagonalPrincipal(String[][] tab, String marca) {
        return tab[0][0].equals(marca) &&
            tab[1][1].equals(marca) &&
            tab[2][2].equals(marca) &&
            tab[3][3].equals(marca);
    }

    // Verifica la diagonal secundaria (de la esquina superior derecha a la inferior izquierda)
    public boolean verificarDiagonalSecundaria(String[][] tab, String marca) {
        return tab[0][3].equals(marca) &&
            tab[1][2].equals(marca) &&
            tab[2][1].equals(marca) &&
            tab[3][0].equals(marca);
    }

    // Método para verificar si la misma posición en los cuatro tableros contiene la misma marca
    public boolean verificarFondo(int j, int k, String marca) {
        return tab1[j][k].equals(marca) &&
            tab2[j][k].equals(marca) &&
            tab3[j][k].equals(marca) &&
            tab4[j][k].equals(marca);
    }

    public boolean verificarFilaFondo( int k, String marca){
        return tab1[0][k].equals(marca) &&
            tab2[1][k].equals(marca) &&
            tab3[2][k].equals(marca) &&
            tab4[3][k].equals(marca);
    }

    public boolean verificarColumnaFondo( int j, String marca){
        return tab1[j][0].equals(marca) &&
            tab2[j][1].equals(marca) &&
            tab3[j][2].equals(marca) &&
            tab4[j][3].equals(marca);
    }

    public boolean verificarDiagonalPrincipalFondo(String marca) {
        return tab1[0][0].equals(marca) &&
            tab2[1][1].equals(marca) &&
            tab3[2][2].equals(marca) &&
            tab4[3][3].equals(marca);
    }

    public boolean verificarDiagonalSecundariaFondo(String marca) {
        return tab1[0][3].equals(marca) &&
            tab2[1][2].equals(marca) &&
            tab3[2][1].equals(marca) &&
            tab4[3][0].equals(marca);
    }

    public boolean verificarGanador() {
        String marcaActual = (turnoActual == jugador1) ? "X" : "O";
        boolean ganador = false;
        // Verificación en las cuatro capas y combinaciones posibles
        for (int i = 0; i < 4; i++) {
            // Filas
            if (verificarLinea(tab1, i, marcaActual) ||
                verificarLinea(tab2, i, marcaActual) ||
                verificarLinea(tab3, i, marcaActual) ||
                verificarLinea(tab4, i, marcaActual)) {
                ganador = true;
            }
            
            // Columnas
            if (verificarColumna(tab1, i, marcaActual) ||
                verificarColumna(tab2, i, marcaActual) ||
                verificarColumna(tab3, i, marcaActual) ||
                verificarColumna(tab4, i, marcaActual)) {
                ganador = true;
                }
            //Diagonales
            if(verificarDiagonalPrincipal(tab1, marcaActual)||
               verificarDiagonalSecundaria(tab1, marcaActual)||
               verificarDiagonalPrincipal(tab2, marcaActual)||
               verificarDiagonalSecundaria(tab2, marcaActual)||
               verificarDiagonalPrincipal(tab3, marcaActual)||
               verificarDiagonalSecundaria(tab3, marcaActual)||
               verificarDiagonalPrincipal(tab4, marcaActual)||
               verificarDiagonalSecundaria(tab4, marcaActual)){
                ganador = true;
               }
        }

        //Fondo
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    if (verificarFondo(j, k, marcaActual)) {
                        ganador = true;
                    }
                }
            }

            //Columna de Fondo
            for(int j = 0; j < 4; j++){
                if(verificarColumnaFondo(j, marcaActual)){
                    ganador = true;
                }
            }

            //Fila de Fondo
            for(int k = 0; k < 4; k++){
                if(verificarFilaFondo(k, marcaActual)){
                    ganador = true;
                }
            }

        if(verificarDiagonalPrincipalFondo(marcaActual)){
            ganador = true;
        }

        if (verificarDiagonalSecundariaFondo(marcaActual)) {
            ganador = true;
        }
        // Aquí se pueden agregar más verificaciones para otras combinaciones en 3D
        return ganador;
    }

    public boolean verificarEmpate() {
        // Verificar si todas las casillas están llenas
        for (String[][] tab : new String[][][]{tab1, tab2, tab3, tab4}) {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (tab[i][j].equals("-")) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void cambiarTurno() {
        turnoActual = (turnoActual == jugador1) ? jugador2 : jugador1;
    }

    public void jugar() {
        intro();
        cuadricula();

        while (true) {
            System.out.println("Turno del jugador " + turnoActual.getnombre());
            System.out.print("Ingrese la fila (1-4): ");
            int fila = (Keyboard.readInt() - 1);
            System.out.print("Ingrese la columna (1-4): ");
            int columna = (Keyboard.readInt() - 1);
            System.out.print("Ingrese la capa (1-4): ");
            int capa = (Keyboard.readInt() - 1);
            

            if (realizarJugada(fila, columna, capa)) {
                cuadricula();

                if (verificarGanador()) {
                    System.out.println("¡El jugador " + turnoActual.getnombre() + " ha ganado!");
                    break;
                } else if (verificarEmpate()) {
                    System.out.println("¡Es un empate!");
                    break;
                } else {
                    cambiarTurno();
                }
            } else {
                System.out.println("Jugada no válida. Intenta nuevamente.");
            }
        }
    }

    public void cuadricula() {
        // Mantener el método cuadricula sin cambios
        // El código original se queda aquí
        System.out.print("   "); // Espacio para la esquina superior izquierda
        for(int k = 0; k < 4; k++){
            for (int i = 0; i < 4; i++) {
                System.out.print(String.format("%-5s", "  "+ (i + 1)));   
            }
            System.out.print("        ");
        }
        
        System.out.println();

        // Imprimir cuadrícula con el contenido
        for (int i = 0; i < 4; i++) {
            for(int k = 0; k <4; k++){
                System.out.print("  +");
                for (int j = 0; j < 4; j++) {
                    System.out.print("----+");
                }
                System.out.print("     ");
            }
      
            System.out.println();

            // Imprimir el índice de la fila y los valores
            for(int k = 0; k < 4; k++){
                System.out.print(i + 1 + " "); // Índice de la fila
                for (int j = 0; j < 4; j++) {
                
                    if(k == 0) System.out.print(String.format("| %2s ", tab1[i][j]));
                    if(k == 1) System.out.print(String.format("| %2s ", tab2[i][j]));
                    if(k == 2) System.out.print(String.format("| %2s ", tab3[i][j]));
                    if(k == 3) System.out.print(String.format("| %2s ", tab4[i][j]));
                }
                if(k < 3){
                    System.out.print("|");
                    System.out.print("     ");
                }else{
                    System.out.println("|");
                }
            
            }
        }

        // Imprimir borde inferior de la última fila
        for(int k = 0; k < 4; k++){
            System.out.print("  +");
            for (int j = 0; j < 4; j++) {
                System.out.print("----+");
            }
            System.out.print("     ");
        }
    
        System.out.println();
        
        System.out.print(String.format("%-18s", "         Fondo 1"));
        System.out.print("          ");
        System.out.print(String.format("%-18s", "         Fondo 2"));
        System.out.print("          ");
        System.out.print(String.format("%-18s", "         Fondo 3"));
        System.out.print("          ");
        System.out.println(String.format("%-18s", "         Fondo 4"));
    }

    public static void main(String[] args) {
        TridTicTacToe juego = new TridTicTacToe();
        juego.jugar();
    }
}
