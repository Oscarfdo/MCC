/* Oscar Fernando Hernandez Lopez
14 de octubre del 2024
Clase Gato
Clase con metodos para dar el formato 
correcto al juego TicTacToe */
public class Gato {

    String[][] board = new String[14][14];

    String[][] empty = {
        {" ", " ", " ", " "},
        {" ", " ", " ", " "},
        {" ", " ", " ", " "},
        {" ", " ", " ", " "},
    };

    String[][] X = {
        {"X", " ", " ", "X"},
        {" ", "X", "X", " "},
        {" ", "X", "X", " "},
        {"X", " ", " ", "X"},
    };

    String[][] O = {
        {"O", "O", "O", "O"},
        {"O", " ", " ", "O"},
        {"O", " ", " ", "O"},
        {"O", "O", "O", "O"},
    };

    // Rellena el tablero 14x14 con submatrices y separadores usando la matriz tablero de TicTacToe
    public void fillBoard(String[][] board, TicTacToe ticTacToe) {
    
    char[][] tablero = ticTacToe.getTablero();

    // Para cada cuadrante del tablero, colocamos una submatriz (puede ser empty, X o O)
    for (int row = 0; row < 14; row++) {
        for (int col = 0; col < 14; col++) {
            // Determinamos si estamos en una línea divisoria (de asteriscos)
            if (row % 5 == 4 || col % 5 == 4) {
                board[row][col] = "*";  // Ponemos los asteriscos como separadores
            } else {
                // Determinamos en qué submatriz estamos
                int subRow = row / 5;
                int subCol = col / 5;

                // Seleccionamos qué submatriz poner en esa posición
                String[][] subMatrix;
                if (tablero[subRow][subCol] == 'X') {
                    subMatrix = X;
                } else if (tablero[subRow][subCol] == 'O') {
                    subMatrix = O;
                } else {
                    subMatrix = empty;
                }

                // Rellenamos la submatriz correspondiente en la posición correcta
                int localRow = row % 5;
                int localCol = col % 5;
                if (localRow < 4 && localCol < 4) {
                    board[row][col] = subMatrix[localRow][localCol];
                }
            }
        }
    }
}
    // Imprime el tablero completo
    public void printBoard(String[][] board) {
        for (int i = 0; i < 14; i++) {
            StringBuilder row = new StringBuilder();
            for (int j = 0; j < 14; j++) {
                row.append(String.format("%-2s", board[i][j])); 
            }
            System.out.println(row.toString());
        }
    }
}
