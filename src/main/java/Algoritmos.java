/**
 * Tres algoritmos que operan sobre pares (i, j) con i < j del ADT SecuenciaEnteros
 *
 * Autores:
 * Maria Jose Ledesma Cordoba - ID:000559241
 * Miguel Angel Puente Mejia - ID:000559418
 */

public class Algoritmos {
    // 1. Conteo de inversiones con corte temprano
    public static int contarInversiones(SecuenciaEnteros secuencia, int umbral) {

        int T = secuencia.tamano();
        int contador = 0;

        for (int i = 0; i < T; i++) {
            for (int j = i + 1; j < T; j++) {

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
