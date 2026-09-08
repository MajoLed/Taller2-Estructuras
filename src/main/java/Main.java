public class Main {

    public static void main(String[] args) {

        int[] datos1 = {5, 1, 4, 2, 3};

        SecuenciaEnteros arreglo = new SecuenciaArreglo(datos1);
        SecuenciaEnteros lista = new SecuenciaLista(datos1);

        // Algoritmo 1
        System.out.println("Inversiones: " + contarInversiones(arreglo, 5)
        );

        // Algoritmo 2
        int[] resultado = primerParConDiferencia(lista, 5);

        System.out.println("Primer par: (" +
                        resultado[0] + ", " +
                        resultado[1] + ")"
        );


        // Algoritmo 3
        int[] datos2 = {4, 7, 10, 3};

        SecuenciaEnteros secuencia2 =
                new SecuenciaArreglo(datos2);

        System.out.println("Pares de paridad distinta con producto alto: " + contarParesParidadProducto(secuencia2, 20));
    }

    // 1. Conteo de inversiones con corte temprano
    public static int contarInversiones(
        SecuenciaEnteros secuencia, int T) {

    int n = secuencia.tamano();
    int contador = 0;

    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {

            if (secuencia.obtener(i) > secuencia.obtener(j)) {
                contador++;

                if (contador >= T) {
                    return contador;
                }
            }
        }
    }

    return contador;
}
    // 2. Primer par con diferencia mayor a D
    public static int[] primerParConDiferencia(SecuenciaEnteros secuencia, int D) {

        int T = secuencia.tamano();

        for (int i = 0; i < T; i++) {
            for (int j = i + 1; j < T; j++) {

                if (Math.abs(secuencia.obtener(i) - secuencia.obtener(j)) > D) {
                    return new int[]{i, j};
                }
            }
        }

        return new int[]{-1, -1};
    }


    // 3. Conteo de pares de paridad distinta con producto alto
    public static int contarParesParidadProducto(SecuenciaEnteros secuencia, int M) {

        int T = secuencia.tamano(); //umbral
        int contador = 0;

        for (int i = 0; i < T; i++) {
            for (int j = i + 1; j < T; j++) {

                int primero = secuencia.obtener(i);
                int segundo = secuencia.obtener(j);

                boolean paridadDistinta = (primero % 2) != (segundo % 2);

                if (paridadDistinta && primero * segundo > M) {
                    contador++;
                }

            }
        }

        return contador;
    }
}
