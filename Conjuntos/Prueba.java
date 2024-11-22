public class Prueba{
    public static void main(String[] args) {

        //Versión 1 del Programa
        ConjuntoV1 conjuntoUno = new ConjuntoV1(1,2,3,4);
        ConjuntoV1 conjuntoDos = new ConjuntoV1(1,2,3,4);
        ConjuntoV1 conjuntoVacío = new ConjuntoV1();
        ConjuntoV1 conjuntoUni = new ConjuntoV1(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16);

        System.out.println("El conjunto " + conjuntoUno + " esta vacio? " + conjuntoUno.isEmpty());
        System.out.println("El conjunto " + conjuntoDos + " esta vacio? " + conjuntoDos.isEmpty());
        System.out.println("El conjunto " + conjuntoVacío + " esta vacio? " + conjuntoVacío.isEmpty());

        System.out.println(conjuntoUno.toString());
        System.out.println(conjuntoDos.toString());
        System.out.println(conjuntoVacío.toString());

        //Conjunto vacío es un conjunto sin elementos
        System.out.println("El conjunto "+ conjuntoUno.toString() +
         " esta vacio? " + conjuntoUno.isEmpty());
        System.out.println();

        
        //Pertenencia (de un elemento de un conjunto)
        System.out.println("El conjunto " + conjuntoUno + " contiene 1? " +
         conjuntoUno.contains(1) );
        System.out.println();

        
        //Subconjunto
        System.out.println("El conjunto " + conjuntoDos.toString() + " es subconjunto de " +
         conjuntoUno.toString() + "? " + conjuntoUno.subSet(conjuntoDos));
        System.out.println();

        
        //Subconjunto Propio
        System.out.println("El conjunto " + conjuntoDos + " es subconjunto propio de " +
         conjuntoUno.toString() + "? " + conjuntoUno.subSetP(conjuntoDos));
        System.out.println();

     
        //Union de conjuntoV1ConjuntoV1
        System.out.println("La union entre el conjunto " + conjuntoUno.toString()+ " y el conjunto " +
         conjuntoDos.toString() + " nos da:  " + conjuntoUno.union(conjuntoDos));
        System.out.println();

        

        //Intersección de conjuntoV1ConjuntoV1
        System.out.println("La interseccion entre el conjunto " + conjuntoUno.toString() + " y el conjunto " +
         conjuntoDos.toString() + " nos da:  " + conjuntoUno.interseccion(conjuntoDos));
        System.out.println();

        
        //Diferencia de conjuntoV1ConjuntoV1
        System.out.println("La diferencia entre el conjunto " + conjuntoUno + " y el conjunto " +
         conjuntoDos + " nos da:  " + conjuntoUno.diferencia(conjuntoDos));
        System.out.println();


        //Complemento de conjuntoV1ConjuntoV1
        System.out.println("El complemento del conjunto " + conjuntoUno.toString() + " y el conjunto universo es: "  +
         conjuntoUno.complemento(conjuntoUni));
        System.out.println();

        
        //Clonacion de conjunto
        System.out.println("El clon del conjunto " + conjuntoUno + " es: "  +
         conjuntoUno.clonacion());
        System.out.println();

       
        //Listar los elementos de un conjunto
        System.out.println("El toString del conjunto " + conjuntoUno + " nos da: "  +
         conjuntoUno.toString());
        System.out.println();

        //Igualdad de conjuntoV1ConjuntoV1
        System.out.println("El conjunto " + conjuntoUno + " es igual al conjunto "  +
         conjuntoDos + " ? " + conjuntoUno.iguales(conjuntoDos));
        System.out.println();  

        ConjuntoV2 PrimerConjunto = new ConjuntoV2<>("Hola");
        ConjuntoV2 SegundoConjunto = new ConjuntoV2<>("Adios");

        System.out.println("El conjunto " + PrimerConjunto + " esta vacio? " + PrimerConjunto.isEmpty());
        System.out.println("El conjunto " + SegundoConjunto + " esta vacio? " + SegundoConjunto.isEmpty());

        System.out.println(PrimerConjunto.toString());
        System.out.println(SegundoConjunto.toString());

        System.out.println("El conjunto " + PrimerConjunto + " contiene Hola? " + PrimerConjunto.contains("Hola") );
        System.out.println("El conjunto " + SegundoConjunto + " contiene Adios? " + SegundoConjunto.contains("Adios") );
        System.out.println("El conunto " + PrimerConjunto + "contiene Adios? " + PrimerConjunto.contains("Adios"));
        System.out.println("El conjunto " + SegundoConjunto + " contiene Hola? " + SegundoConjunto.contains("Hola") );
    }
}