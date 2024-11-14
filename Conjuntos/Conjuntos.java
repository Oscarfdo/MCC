/* Oscar Fernando Hernandez Lopez
11 de noviembre del 2024
Conjuntos.java
Proyecto Conjuntos
diferentes metodos para conjuntos*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Conjuntos{

    private Set<Integer> conjUni = new HashSet<Integer>();

    private Set<Integer> conjunto1 = Set.of(1, 2, 3, 4);

    private Set<Integer> conjunto2 = Set.of(5, 6);

    private Set<Integer> conjunto3 = Set.of(1, 2);

    private Set<Integer> conjunto35 = Set.of(1, 2);

    private Set<Integer> conjunto4 = Set.of(1, 3, 5);

    private Set<Integer> conjuntoVacio = Set.of();

    public Conjuntos(){
        for(int i = 0; i < 20; i++){
            conjUni.add(i);
        }

    }

    public Conjuntos(Set conjunto){

    }

    public boolean isEmpty(Set conjunto){
        return conjunto.isEmpty();
    }

    public boolean contains(int a, Set conjunto){
        return conjunto.contains(a);
    }

    public boolean subSet(Set<Integer> conjunto, Set<Integer> subConjunto) {
        return conjunto.containsAll(subConjunto);
    }

    public boolean subSetP(Set<Integer> conjunto, Set<Integer> subConjunto){
        boolean state = false;

        conjunto.containsAll(subConjunto);

        for(Integer elemento: conjunto){
            if(!subConjunto.contains(elemento)){
                state = true;
            }
        }
        return state;
    }

    public Set<Integer> union(Set<Integer> conjunto1,Set<Integer> conjunto2) {
        Set<Integer> union = new HashSet<Integer>(conjunto1); 
        for (Integer elemento : conjunto2) {
            if (!union.contains(elemento)) { 
                union.add(elemento);    
            }
        }
        return union;
    }

    public Set<Integer> interseccion(Set<Integer> conjunto1, Set<Integer> conjunto2) {
        Set<Integer> interseccion = new HashSet<Integer>();

        for (Integer elemento : conjunto1) {
            if (conjunto2.contains(elemento)) {
                interseccion.add(elemento);
            }
        }
        return interseccion;
    }

    public Set<Integer> diferencia(Set<Integer> conjunto1, Set<Integer> conjunto2){
        Set<Integer> diferencia = new HashSet<Integer>();

        for(Integer elemento : conjunto1){
            if(!conjunto2.contains(elemento)){
                diferencia.add(elemento);
            }
        }
        return diferencia;
    }

    public Set<Integer> complemento(Set<Integer> conjunto){
        Set<Integer> complemento = new HashSet<Integer>();

        for(Integer elemento : this.conjUni){
            if (!conjunto.contains(elemento)) {
                complemento.add(elemento);
            }
        }
        return complemento;
    }

    public Set<Integer> clonacion(Set<Integer> conjunto){
        Set<Integer> clon = new HashSet<Integer>();

        for(Integer elemento : conjunto){
            clon.add(elemento);
        }
        return clon;
    }

    public boolean iguales(Set<Integer> conjuntoA, Set<Integer> conjuntoB) {
    
    Set<Integer> setA = new HashSet<Integer>(conjuntoA);
    Set<Integer> setB = new HashSet<Integer>(conjuntoB);
    
    return setA.equals(setB);
    }

    public String toString(){
        return conjUni.toString();
    }

    public static void main(String[] args){
        Conjuntos conjuntos = new Conjuntos();

        //Conjunto vacío es un conjunto sin elementos
        System.out.println("El conjunto "+ conjuntos.conjunto1.toString() +
         " esta vacio? " + conjuntos.isEmpty(conjuntos.conjunto1));
        System.out.println();

        //Pertenencia (de un elemento de un conjunto)
        System.out.println("El conjunto " + conjuntos.conjunto1 + " contiene 1? " +
         conjuntos.contains(1, conjuntos.conjunto1) );
        System.out.println();

        //Subconjunto
        System.out.println("El conjunto " + conjuntos.conjunto2 + " es subconjunto de " +
         conjuntos.conjunto1 + "? " + conjuntos.subSet(conjuntos.conjunto1, conjuntos.conjunto2));
        System.out.println();

        //Subconjunto Propio
        System.out.println("El conjunto " + conjuntos.conjunto1 + " es subconjunto propio de " +
         conjuntos.conjunto2 + "? " + conjuntos.subSetP(conjuntos.conjunto2, conjuntos.conjunto1));
        System.out.println();

        //Union de conjuntos
        System.out.println("La union entre el conjunto " + conjuntos.conjunto1 + " y el conjunto " +
         conjuntos.conjunto2 + " nos da:  " + conjuntos.union(conjuntos.conjunto2, conjuntos.conjunto1));
        System.out.println();

        //Intersección de conjuntos
        System.out.println("La interseccion entre el conjunto " + conjuntos.conjunto1 + " y el conjunto " +
         conjuntos.conjunto4 + " nos da:  " + conjuntos.interseccion(conjuntos.conjunto1, conjuntos.conjunto4));
        System.out.println();

        //Diferencia de conjuntos
        System.out.println("La diferencia entre el conjunto " + conjuntos.conjunto1 + " y el conjunto " +
         conjuntos.conjunto4 + " nos da:  " + conjuntos.diferencia(conjuntos.conjunto1, conjuntos.conjunto4));
        System.out.println();

        //Complemento de conjuntos
        System.out.println("El complemento del conjunto " + conjuntos.conjunto4 + " y el conjunto universo es: "  +
         conjuntos.complemento(conjuntos.conjunto4));
        System.out.println();

        //Clonacion de conjunto
        System.out.println("El clon del conjunto " + conjuntos.conjunto4 + " es: "  +
         conjuntos.clonacion(conjuntos.conjunto4));
        System.out.println();

        //Listar los elementos de un conjunto
        System.out.println("El toString del conjunto " + conjuntos.conjunto4 + " nos da: "  +
         conjuntos.conjunto4.toString());
        System.out.println();

        //Igualdad de conjuntos
        System.out.println("El conjunto " + conjuntos.conjunto3 + " es igual al conjunto "  +
         conjuntos.conjunto35 + " ? " + conjuntos.iguales(conjuntos.conjunto35, conjuntos.conjunto3));
        System.out.println();  
    }
}