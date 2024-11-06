public class Persona{
    private static String nombre;

    public Persona(String nombre){
        this.nombre = nombre;
    }

    public static String getNombre(){
        return nombre;
    }

    public String toString(){
        return nombre;
    }
}