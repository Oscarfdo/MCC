public void cuadricula() {
    // Imprimir encabezado con números de columna
    System.out.print("   "); // Espacio para la esquina superior izquierda
    for(int k = 0; k < 4; k++){
        for (int i = 0; i < 4; i++) {
            System.out.print(String.format("%-5s", "  "+i));   
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