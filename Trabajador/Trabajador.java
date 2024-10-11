public class Trabajador{
    private String nombre;
    private char sexo;
    private char categoria; 
    private double  horasTrabajadas;

    public String getNombre(){
        return nombre;
    } 

    public char getSexo(){
        return sexo;
    }

    public char getCategoria(){
        return categoria;
    }

    public double getHoras(){
        return horasTrabajadas;
    }

    public void setNombre(String nNombre){
        nombre = nNombre;
    }

    public void setSexo(char nSexo){
        sexo = nSexo;
    }

    public void setCategoria(char nCategoria){
        categoria = nCategoria;
    }

    public void setHoras(double nHoras){
        horasTrabajadas = nHoras;
    }

    public double sueldo (){

        double sueldo = 0;

        switch (categoria) {
            case  'a': 
            case 'A':
                sueldo = 50;              
                break;

            case  'b': 
            case 'B':
                sueldo = 60;              
                break;

            case  'c': 
            case 'C':
                sueldo = 75;              
                break;
            default:
                System.out.println("Esta categoria no existe");
        }
        return sueldo * horasTrabajadas ;
    }

    public double bono(){
        double bono = 0;
        if (horasTrabajadas >= 40) {
            bono = 500;
        }
        return bono;
    }

     public String toString(){
        double sueldo = sueldo();
        double bono = bono();
        String nombreTruncado = (nombre.length() > 20) ? nombre.substring(0, 17) + "..." : nombre;

        return String.format("%-20s %-5s %-10s %-10.2f %-10.2f %-10.2f %-10.2f",
          nombreTruncado, sexo, categoria, horasTrabajadas, bono(), sueldo(), (sueldo() + bono()));
    }

    public Trabajador (String n, char s, char c, double h){
        this.nombre = n;
        this.sexo = s;
        this.categoria = c;
        this.horasTrabajadas = h;
    }
}