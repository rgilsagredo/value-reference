package com.daw.proramacion;

import java.util.Objects;

public class ValueReference {
    public static void main(String[] args) throws CloneNotSupportedException {
        /*
         * cuando declaro una variable, esta puede ser de tipo primitivo (byte, int,
         * char, boolean..: https://www.baeldung.com/java-primitives)
         * o puede ser un objeto (su tipo es alguna clase). Estas últimas se llaman de
         * tipo referenciado.
         */

        // variable de tipo primitivo
        int n = 5;
        // si pregunto qué vale, dice 5
        System.out.println(n);

        // variable referenciada
        Perro kira = new Perro(1, "Kira");

        // si pregunto qué vale, dice una dirección de memoria (ojo, es porque no he
        // implementado un .toString())
        System.out.println(kira);

        // si creo otra variable primitiva y le paso el valor de n, lo que le paso es
        // eso, el valor
        int m = n;
        System.out.println(m);

        // de tal manera que si n cambia, m NO
        n = 100;
        System.out.println("n: " + n);
        System.out.println("m: " + m);

        // este comportamiento no es el mismo con variables referenciadas. Si creo un
        // nuevo perro y le asisno el valor de kira, son la misma referencia
        Perro kira2 = kira;
        System.out.println("kira: " + kira);
        System.out.println("kira2: " + kira2);

        // y por tanto, un cambio en uno afecta al otro (son el mismo objeto)
        System.out.println("nombre: " + kira.getNombre());
        kira2.setNombre("Taz");
        System.out.println("nombre: " + kira.getNombre());

        // con variables de tipo primitivo, el operador == compara los valores
        System.out.println(n == m); // false

        // con variables refernciadas, compara la direccion de memoria
        System.out.println(kira == kira2); // true

        // borramos kira y kira2, creamos nuevos objetos independientes
        kira = null;
        kira2 = null;

        kira = new Perro(1, "Kira");
        kira2 = new Perro(1, "Kira");

        System.out.println(kira);
        System.out.println(kira2);
        System.out.println(kira == kira2); // false

        // sin embargo, a efectos kira y kira2 sí son iguales (tienen el mismo "estado",
        // ie, sus atributos son los mismos)
        // el metodo estático equals no funciona (aún), puesto que hace lo mismo que ==
        // (mira si son la misma refercnia)
        System.out.println(Objects.equals(kira, kira2)); // false si no tengo el método equals en Perro, true en otro
                                                         // caso

        // para que tengamos un true, tenemos que crear en la clase el método equals
        System.out.println(Objects.equals(kira, kira2)); // true, he creado el método equals
        System.out.println(kira == kira2); // false
        // también lo puedes hacer así
        System.out.println(kira.equals(kira2)); // true

        /*
         * Finalmente, cuando invocamos un método, java siempre hace un paso por valor.
         * Cuando la variable es de tipo primitivo, funciona como se espera: un cambio
         * en el método no afecta al valor de la variable fuera del método
         */

        System.out.println(n);
        metodo1(n);
        System.out.println(n); // no ha cambiado

        /*
         * Pero si le pasamos una variable referenciada, lo que pasamos por valor es
         * la dirección de memoria. Por tanto, cambios en la variable, se reflejan en
         * cambios fuera
         * 
         */

        System.out.println(kira.getNombre());
        metodo2(kira);
        System.out.println(kira.getNombre()); // ha cambiado

        /**
         * Si machaco, en el método, la variable con un nuevo valor,
         * se crea una nueva referencia a la que apunta perro en el método,
         * y por tanto cambios en esa referencia no afectan a lo que he pasado
         * por valor (que está fuera en main)
         */

        System.out.println(kira.getNombre());
        metodo3(kira);
        System.out.println(kira.getNombre()); // no ha cambiado

        // si quiero "clonar" un objeto en otro pero que sean distintas refercnias, hay
        // que usar el método .clone()
        // hay que implementarlo, añadir la interfaz Clonable a la clase y los throws a
        // main
        Perro taz = (Perro) kira.clone();
        // una copia es que
        System.out.println(taz == kira); // false, son distintas direcciones de memoria

        // pero
        System.out.println(kira.equals(taz)); // true, son "iguales"

    } // main

    public static void metodo1(int n) {
        System.out.println(n);
        n = 55;
        System.out.println(n);
    }

    public static void metodo2(Perro perro) {

        System.out.println(perro.getNombre());
        perro.setNombre("mordisquitos");
        System.out.println(perro.getNombre());

    }

    public static void metodo3(Perro perro) {

        System.out.println(perro.getNombre());
        perro = new Perro(2, "Taz");
        System.out.println(perro.getNombre());

    }

} // ValueReference
