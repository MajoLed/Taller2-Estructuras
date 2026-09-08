/*
 * Implementación de SecuenciaEnteros usando un arreglo de enteros.
 * Autores:
 * Maria Jose Ledesma Cordoba - ID:000559241
 * Miguel Angel Puente Mejia - ID:000559418
 */
import java.util.Arrays;

public class SecuenciaArreglo implements SecuenciaEnteros {

    int[] datos;

    public SecuenciaArreglo(int[] arreglo) {
        if (arreglo == null) {
            throw new IllegalArgumentException("El arreglo no puede ser null");
        }
        this.datos = arreglo;
    }

    @Override
    public int obtener(int i) {
        if (i < 0 || i >= datos.length) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + i);
        }
        return datos[i];
    }

    @Override
    public int tamano() {
        return datos.length;
    }

    @Override
    public String toString() {
        return "SecuenciaArreglo{" +
                "datos=" + Arrays.toString(datos) +
                '}';
    }
}