/* Oscar Fernando Hernandez Lopez
11 de noviembre del 2024
ConjuntoV2<T>.java
Proyecto ConjuntoV2<T>
diferentes metodos para conjuntoV2ConjuntoV2<T>*/

import java.util.HashSet;
import java.util.Set;

public class ConjuntoV2<T> {

    private Set<T> conjUni = new HashSet<>();

    private Set<T> conj;

    public ConjuntoV2() {
        this.conj = new HashSet<>();
    }

    public ConjuntoV2(Set<T> conj) {
        this.conj = conj;
    }

    @SafeVarargs
    public ConjuntoV2(T... elementos) {
        this.conj = new HashSet<>();
        for (T elemento : elementos) {
            this.conj.add(elemento);
        }
    }

    public boolean isEmpty() {
        return this.conj.isEmpty();
    }

    public boolean contains(T a) {
        return this.conj.contains(a);
    }

    public boolean subSet(ConjuntoV2<T> subConjunto) {
        return conj.containsAll(subConjunto.conj);
    }

    public boolean subSetP(ConjuntoV2<T> subConjunto) {
        return conj.containsAll(subConjunto.conj) && !this.equals(subConjunto);
    }

    public ConjuntoV2<T> union(ConjuntoV2<T> conjunto2) {
        Set<T> union = new HashSet<>(conj); 
        for (T elemento : conjunto2.conj) {
            if (!union.contains(elemento)) { 
                union.add(elemento);    
            }
        }
        return new ConjuntoV2<>(union);
    }

    public ConjuntoV2<T> interseccion(ConjuntoV2<T> conjunto2) {
        Set<T> interseccion = new HashSet<>();

        for (T elemento : conj) {
            if (conjunto2.contains(elemento)) {
                interseccion.add(elemento);
            }
        }
        return new ConjuntoV2<>(interseccion);
    }

    public ConjuntoV2<T> diferencia(ConjuntoV2<T> conjunto2) {
        Set<T> diferencia = new HashSet<>();

        for (T elemento : conj) {
            if (!conjunto2.contains(elemento)) {
                diferencia.add(elemento);
            }
        }
        return new ConjuntoV2<>(diferencia);
    }

    public ConjuntoV2<T> complemento(ConjuntoV2<T> conjUni) {
        Set<T> complemento = new HashSet<>();

        for (T elemento : conjUni.conj) {
            if (!conj.contains(elemento)) {
                complemento.add(elemento);
            }
        }
        return new ConjuntoV2<>(complemento);
    }

    public ConjuntoV2<T> clonacion() {
        Set<T> clon = new HashSet<>();

        for (T elemento : conj) {
            clon.add(elemento);
        }
        return new ConjuntoV2<>(clon);
    }

    public boolean iguales(ConjuntoV2<T> conjuntoB) {
        return conj.equals(conjuntoB.conj);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int size = this.conj.size();
        int count = 0;
        for (T elemento : this.conj) {
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