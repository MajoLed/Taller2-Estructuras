/**
 * Pruebas unitarias del ADT SecuenciaEnteros, ambas representaciones.
 *
 * Autores:
 * Maria Jose Ledesma Cordoba - ID:000559241
 * Miguel Angel Puente Mejia - ID:000559418
 * Ejecutar con: java -ea PruebasSecuenciaEnteros.java
 */

public class Main {

    public static void main(String[] args) {
        probarADT();
        probarConteoInversionesConCorte();
        probarPrimerParDiferenciaMayor();
        probarConteoParidadDistintaProductoAlto();
        probarConAmbasRepresentaciones();

        System.out.println("TODAS LAS PRUEBAS PASARON");
    }

    // ---------- Pruebas del ADT SecuenciaEnteros ----------

    private static void probarADT() {
        int[] datos = {5, 1, 4, 2, 3};

        SecuenciaEnteros arreglo = new SecuenciaArreglo(datos);
        SecuenciaEnteros lista = new SecuenciaLista(datos);

        // tamano()
        assert arreglo.tamano() == 5 : "tamano() arreglo incorrecto";
        assert lista.tamano() == 5 : "tamano() lista incorrecta";

        // obtener(i) para todas las posiciones, en ambas representaciones
        for (int i = 0; i < datos.length; i++) {
            assert arreglo.obtener(i) == datos[i] : "obtener(" + i + ") arreglo incorrecto";
            assert lista.obtener(i) == datos[i] : "obtener(" + i + ") lista incorrecta";
        }

        // toString()
        assert arreglo.toString().equals("[5, 1, 4, 2, 3]") : "toString() arreglo incorrecto";
        assert lista.toString().equals("[5, 1, 4, 2, 3]") : "toString() lista incorrecta";

        // Índice fuera de rango debe lanzar excepción en ambas representaciones
        boolean lanzoArreglo = false;
        try {
            arreglo.obtener(10);
        } catch (IndexOutOfBoundsException e) {
            lanzoArreglo = true;
        }
        assert lanzoArreglo : "arreglo.obtener(10) debía lanzar excepción";

        boolean lanzoLista = false;
        try {
            lista.obtener(-1);
        } catch (IndexOutOfBoundsException e) {
            lanzoLista = true;
        }
        assert lanzoLista : "lista.obtener(-1) debía lanzar excepción";

        // Caso borde: secuencia de un solo elemento
        SecuenciaEnteros unElemento = new SecuenciaArreglo(new int[]{42});
        assert unElemento.tamano() == 1;
        assert unElemento.obtener(0) == 42;

        System.out.println("ADT SecuenciaEnteros: OK");
    }

    // ---------- Algoritmo 0: conteo de inversiones con corte ----------

    private static void probarConteoInversionesConCorte() {
        int[] datos = {5, 1, 4, 2, 3};
        SecuenciaEnteros s = new SecuenciaArreglo(datos);

        // Caso del enunciado: T=5 -> corte antes de revisar todos los pares (total real es 6)
        int resultadoConCorte = Algoritmos.contarInversiones(s, 5);
        assert resultadoConCorte == 5 : "Se esperaba 5, se obtuvo " + resultadoConCorte;

        // Caso de corte inmediato: T=1, se corta en el primer par que es inversión
        int resultadoCorteInmediato = Algoritmos.contarInversiones(s, 1);
        assert resultadoCorteInmediato == 1 : "Se esperaba 1, se obtuvo " + resultadoCorteInmediato;

        // Caso de no-corte: umbral inalcanzable, recorre todos los pares (6 inversiones reales)
        int resultadoSinCorte = Algoritmos.contarInversiones(s, Integer.MAX_VALUE);
        assert resultadoSinCorte == 6 : "Se esperaba 6, se obtuvo " + resultadoSinCorte;

        // Caso sin inversiones (secuencia ordenada ascendente): nunca se corta, conteo final 0
        SecuenciaEnteros ordenada = new SecuenciaArreglo(new int[]{1, 2, 3, 4, 5});
        int resultadoOrdenada = Algoritmos.contarInversiones(ordenada, 3);
        assert resultadoOrdenada == 0 : "Se esperaba 0, se obtuvo " + resultadoOrdenada;

        System.out.println("Algoritmo 0 (conteo inversiones con corte): OK");
    }

    // ---------- Algoritmo 1: primer par con diferencia mayor a D ----------

    private static void probarPrimerParDiferenciaMayor() {
        int[] datos = {3, 9, 1, 8};
        SecuenciaEnteros s = new SecuenciaArreglo(datos);

        // Caso del enunciado: corte inmediato en el primer par evaluado (0,1)
        int[] par = Algoritmos.primerParConDiferencia(s, 5);
        assert par[0] == 0 && par[1] == 1 : "Se esperaba (0,1), se obtuvo (" + par[0] + "," + par[1] + ")";

        // Caso de no-corte: D tan grande que ningún par lo supera
        SecuenciaEnteros s2 = new SecuenciaArreglo(new int[]{1, 2, 3, 4});
        int[] parNulo = Algoritmos.primerParConDiferencia(s2, 100);
        assert parNulo[0] == -1 && parNulo[1] == -1 : "Se esperaba (-1,-1)";

        // Caso de corte solo al final: el único par válido es el último evaluado
        SecuenciaEnteros s3 = new SecuenciaArreglo(new int[]{1, 1, 1, 10});
        int[] parFinal = Algoritmos.primerParConDiferencia(s3, 5);
        assert parFinal[0] == 0 && parFinal[1] == 3 : "Se esperaba (0,3)";

        System.out.println("Algoritmo 1 (primer par diferencia > D): OK");
    }

    // ---------- Algoritmo 2: conteo de pares de paridad distinta con producto alto ----------

    private static void probarConteoParidadDistintaProductoAlto() {
        int[] datos = {4, 7, 10, 3};
        SecuenciaEnteros s = new SecuenciaArreglo(datos);

        // Caso del enunciado: 3 de los 6 pares cumplen la condición
        int conteo = Algoritmos.contarParesParidadProducto(s, 20);
        assert conteo == 3 : "Se esperaba 3, se obtuvo " + conteo;

        // Caso "nunca se cumple": M muy alto, ningún producto lo supera
        int conteoCero = Algoritmos.contarParesParidadProducto(s, 1000);
        assert conteoCero == 0 : "Se esperaba 0, se obtuvo " + conteoCero;

        // Caso "todos cumplen": secuencia alternando par/impar con producto alto
        SecuenciaEnteros todosCumplen = new SecuenciaArreglo(new int[]{100, 101});
        int conteoTodos = Algoritmos.contarParesParidadProducto(todosCumplen, 10);
        assert conteoTodos == 1 : "Se esperaba 1, se obtuvo " + conteoTodos;

        System.out.println("Algoritmo 2 (paridad distinta producto alto): OK");
    }

    // ---------- Consistencia entre representaciones ----------


    private static void probarConAmbasRepresentaciones() {
        int[] datos = {5, 1, 4, 2, 3, 9, 0, 7};

        SecuenciaEnteros arreglo = new SecuenciaArreglo(datos);
        SecuenciaEnteros lista = new SecuenciaLista(datos);

        int r1a = Algoritmos.contarInversiones(arreglo, 4);
        int r1l = Algoritmos.contarInversiones(lista, 4);
        assert r1a == r1l : "Resultados distintos entre arreglo y lista (inversiones)";

        int[] r2a = Algoritmos.primerParConDiferencia(arreglo, 3);
        int[] r2l = Algoritmos.primerParConDiferencia(lista, 3);
        assert r2a[0] == r2l[0] && r2a[1] == r2l[1] : "Resultados distintos entre arreglo y lista (diferencia)";

        int r3a = Algoritmos.contarParesParidadProducto(arreglo, 15);
        int r3l = Algoritmos.contarParesParidadProducto(lista, 15);
        assert r3a == r3l : "Resultados distintos entre arreglo y lista (paridad)";

        System.out.println("Consistencia arreglo/lista: OK");
    }
}
