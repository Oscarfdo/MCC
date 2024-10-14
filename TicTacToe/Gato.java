public class Gato {

    char[][] tablero = {
        {'X', 'O', ' '},
        {' ', 'X', 'O'},
        {'O', ' ', 'X'}
    };

    // Método para imprimir la "X"
    public void imprimirX(int linea) {
        switch (linea) {
            case 0: System.out.print("X  X "); break;
            case 1: System.out.print(" XX  "); break;
            case 2: System.out.print(" XX  "); break;
            case 3: System.out.print("X  X "); break;
        }
    }

    // Método para imprimir la "O"
    public void imprimirO(int linea) {
        switch (linea) {
            case 0: System.out.print("oooo "); break;
            case 1: System.out.print("o  o "); break;
            case 2: System.out.print("o  o "); break;
            case 3: System.out.print("oooo "); break;
        }
    }

    // Método para imprimir una celda vacía
    public void imprimirVacio(int linea) {
        System.out.print("     "); // Espacio en blanco para celdas vacías
    }

    // Método para imprimir el tablero
    public void imprimirTablero() {
        for (int i = 0; i < 3; i++) { // Recorre las filas del tablero
            for (int linea = 0; linea < 4; linea++) { // Imprimir cada una de las 4 líneas de las celdas

                // Imprimir la primera columna
                if (tablero[i][0] == 'X') {
                    imprimirX(linea); // Imprime la "X"
                } else if (tablero[i][0] == 'O') {
                    imprimirO(linea); // Imprime la "O"
                } else {
                    imprimirVacio(linea); // Imprime espacio vacío
                }

                // Separador de columna
                System.out.print("*");

                // Imprimir la segunda columna
                if (tablero[i][1] == 'X') {
                    imprimirX(linea); // Imprime la "X"
                } else if (tablero[i][1] == 'O') {
                    imprimirO(linea); // Imprime la "O"
                } else {
                    imprimirVacio(linea); // Imprime espacio vacío
                }

                // Separador de columna
                System.out.print("*");

                // Imprimir la tercera columna
                if (tablero[i][2] == 'X') {
                    imprimirX(linea); // Imprime la "X"
                } else if (tablero[i][2] == 'O') {
                    imprimirO(linea); // Imprime la "O"
                } else {
                    imprimirVacio(linea); // Imprime espacio vacío
                }

                // Salta a la siguiente línea
                System.out.println();
            }

            // Imprimir la línea de separación entre filas
            if (i < 2) {
                System.out.println("* * * * * * *"); // Línea de separación entre filas
            }
        }
    }

    public static void main(String[] args) {
        Gato juego = new Gato();
        juego.imprimirTablero(); // Prueba la impresión del tablero
    }
}
