/* Oscar Fernando Hernandez Lopez
14 de octubre del 2024
Clase Jugador
Clase con metodos y objeto "Jugador" */

public class Jugador{
    private String nombre;
    private byte adversarios;
    private byte tDP; // Total de Partidas
    private byte pG; // Partidas Ganadas
    private byte pP; // Partidas Perdidas
    private byte pE; // Partidas Empatadas
    private byte p; //Puntaje

    public String getnombre(){
        return nombre;
    }
    
    public byte getAdversarios(){
        return adversarios;
    }

    public byte getTDP(){
        return tDP;
    }

    public byte getPG(){
        return pG;
    } 

    public byte getPP(){
        return pP;
    }

    public byte getPE(){
        return pE;
    } 

    public byte getP(){
        return p;
    }

    public void setNombre(String nNombre){
        nombre = nNombre;
    }

    public void setAdversarios(byte nAdversarios){
        adversarios = nAdversarios;
    }

    public void setTDP(byte nTDP){
        tDP = nTDP;
    }

    public void setPG(byte nPG){
        pG = nPG;
    }

    public void setPP(byte nPP){
        pP = nPP;
    }

    public void setPE(byte nPE){
        pE = nPE;
    }

    public void setP(byte nP){
        p = nP;
    }
    
    @Override
    public String toString(){
        return nombre + adversarios + tDP + pG + pP + pE + p;
    }

    public Jugador (String n, byte a, byte t, byte g, byte d, byte e, byte s ){
        this.nombre = n;
        this.adversarios = a;
        this.tDP = t;
        this.pG = g;
        this.pP = d;
        this.pE = e;
        this.p = s;
    }
}