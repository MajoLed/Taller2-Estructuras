public class SecuenciaLista implements SecuenciaEnteros {
    private Nodo cabeza;
    private int tamano;

    public SecuenciaLista(int[] valores) {
        this.tamano = valores.length;
    }

    @Override
    public int obtener(int i) {
        Nodo actual = cabeza;
        for (int k = 0; k < i; k++) {
            actual = actual.siguiente;
        }
        return actual.valor;
    }

    public int tamano() {
        return tamano;
    }

    // Clase nodo
    private class Nodo {
        int valor;
        Nodo siguiente;
        Nodo(int valor) { this.valor = valor; }
    }

    @Override
    public String toString() {
        return "SecuenciaLista{" +
                "cabeza=" + cabeza +
                ", tamano=" + tamano +
                '}';
    }

}