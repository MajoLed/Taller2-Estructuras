
 /**
 * Implementación de SecuenciaEnteros usando una lista simplemente enlazada.
 * * Autores:
 *  * Maria Jose Ledesma Cordoba - ID:000559241
 *  * Miguel Angel Puente Mejia - ID:000559418
 *
 * Costo de obtener(i): O(i) en el peor caso, pues hay que recorrer la
 * lista desde la cabeza hasta la posición i.
 * Costo de espacio: O(N), pero con una constante mayor que el arreglo,
 * ya que cada nodo guarda además una referencia "siguiente".
 */

public class SecuenciaLista implements SecuenciaEnteros {

    // Clase interna nodo (static porque no necesita referencia a la instancia externa)
    private static class Nodo {
        int valor;
        Nodo siguiente;

        Nodo(int valor) {
            this.valor = valor;
        }
    }

    private Nodo cabeza;
    private final int n;

    public SecuenciaLista(int[] arreglo) {
        if (arreglo == null) {
            throw new IllegalArgumentException("El arreglo no puede ser null");
        }
        Nodo actual = null;
        for (int valor : arreglo) {
            Nodo nuevo = new Nodo(valor);
            if (cabeza == null) {
                cabeza = nuevo;
            } else {
                actual.siguiente = nuevo;
            }
            actual = nuevo;
        }
        this.n = arreglo.length;
    }

    @Override
    public int obtener(int i) {
        if (i < 0 || i >= n) {
            throw new IndexOutOfBoundsException("Índice fuera de rango: " + i);
        }
        Nodo actual = cabeza;
        for (int k = 0; k < i; k++) {
            actual = actual.siguiente;
        }
        return actual.valor;
    }

    @Override
    public int tamano() {
        return n;
    }

    //Se recurre a string builder para evitar doble implementación porque el
    // ToString imprime imprimir el hash a falta de metodo ToString de la clase privada NO
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Nodo actual = cabeza;
        while (actual != null) {
            sb.append(actual.valor);
            if (actual.siguiente != null) {
                sb.append(", ");
            }
            actual = actual.siguiente;
        }
        return sb.append("]").toString();
    }
}