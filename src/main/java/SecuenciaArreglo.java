import java.util.Arrays;

public class SecuenciaArreglo implements SecuenciaEnteros {

    int[] datos;

    public SecuenciaArreglo(int[] valores) {
        this.datos = valores;
    }

    @Override
    public int obtener(int i) {
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