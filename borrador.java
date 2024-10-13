  public boolean transicion() {
        String seguir;
        boolean continuar = false;

        if (hayGanador) {
            System.out.println("¿Seguir jugando?");
            seguir = Keyboard.readString();

            if (seguir.equalsIgnoreCase("si")) {
                continuar = true; // Continuar el juego si la respuesta es "si"
                crearJugador(); // Puedes modificar esto si necesitas agregar jugadores de nuevo
            } else if (seguir.equalsIgnoreCase("no")) {
                continuar = false;
            }
        }
        return continuar;
    }