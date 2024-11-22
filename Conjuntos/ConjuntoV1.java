/* Oscar Fernando Hernandez Lopez
11 de noviembre del 2024
ConjuntoV1.java
Proyecto ConjuntoV1
diferentes metodos para conjuntoV1*/

import java.util.HashSet;
import java.util.Set;

public class ConjuntoV1{

    private Set<Integer> conjUni = new HashSet<Integer>();

    private Set<Integer> conj;

    public ConjuntoV1(){
        this.conj = new HashSet<>();
    }

    public ConjuntoV1(Set<Integer> conj) {
        this.conj = conj;
    }

    public ConjuntoV1(Integer... elementos){
        this.conj = new HashSet<>();
        for(Integer elemento : elementos){
            this.conj.add(elemento);
        }
    }

    public boolean isEmpty(){
        return this.conj.isEmpty();
    }

    public boolean contains(int a){
        return this.conj.contains(a);
    }

    public boolean subSet(ConjuntoV1 subConjunto) {
        return conj.containsAll(subConjunto.conj);
    }

    public boolean subSetP(ConjuntoV1 subConjunto){
        boolean state = false;

        conj.containsAll(subConjunto.conj);

        for(Integer elemento: conj){
            if(!subConjunto.contains(elemento)){
                state = true;
            }
        }
        return state;
    }

    public ConjuntoV1 union(ConjuntoV1 conjunto2) {
        Set<Integer> union = new HashSet<>(conj); 
        for (Integer elemento : conjunto2.conj) {
            if (!union.contains(elemento)) { 
                union.add(elemento);    
            }
        }
        return new ConjuntoV1(union);
    }

    public ConjuntoV1 interseccion(ConjuntoV1 conjunto2) {
        Set<Integer> interseccion = new HashSet<>();

        for (Integer elemento : conj) {
            if (conjunto2.contains(elemento)) {
                interseccion.add(elemento);
            }
        }
        return new ConjuntoV1(interseccion);
    }

    public ConjuntoV1 diferencia(ConjuntoV1 conjunto2){
        Set<Integer> diferencia = new HashSet<>();

        for(Integer elemento : conj){
            if(!conjunto2.contains(elemento)){
                diferencia.add(elemento);
            }
        }
        return new ConjuntoV1(diferencia);
    }

    public ConjuntoV1 complemento(ConjuntoV1 conjUni){
        Set<Integer> complemento = new HashSet<>();

        for(Integer elemento : conjUni.conj){
            if (!conj.contains(elemento)) {
                complemento.add(elemento);
            }
        }
        return new ConjuntoV1(complemento);
    }

    public ConjuntoV1 clonacion(){
        Set<Integer> clon = new HashSet<>();

        for(Integer elemento : conj){
            clon.add(elemento);
        }
        return new ConjuntoV1(clon);
    }

    public boolean iguales( ConjuntoV1 conjuntoB) {
    return conj.equals(conjuntoB.conj);
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int size = this.conj.size();
        int count = 0;
        for (Integer elemento : this.conj){
            sb.append(elemento);
            count++;
            if (count < size) {
                sb.append(", ");
            }
        }
        sb.append("}");
        return sb.toString();
    }
}