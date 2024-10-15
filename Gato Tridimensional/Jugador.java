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

     public void incrementarAd(byte nAdversarios){
        adversarios += nAdversarios;
    }

    public void incrementarTDP(byte nTDP){
        tDP += nTDP;
    }

    public void incrementarPG(byte nPG){
        pG += nPG;
    }

    public void incerementarPP(byte nPP){
        pP += nPP;
    }

    public void incrementarPE(byte nPE){
        pE += nPE;
    }

    public void incrementarP(byte nP){
        p += nP;
    }

    public void decrementarP(byte nP){
        p -=nP;
    }

    public void reinicio(){

        tDP = 0;
        pG = 0;
        pP = 0;
        pE = 0;
        p = 0;
    }
    
    @Override
    public String toString(){
        return nombre + adversarios + tDP + pG + pP + pE + p;
    }

    public Jugador (String n){
        this.nombre = n;
        this.adversarios = 1;
        this.tDP = 0;
        this.pG = 0;
        this.pP = 0;
        this.pE = 0;
        this.p = 0;
    }
}