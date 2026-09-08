public class SecuenciaLista implements SecuenciaEnteros {
    private Nodo cabeza;
    private int tamano;

    public SecuenciaLista(int[] valores) {
    this.tamano = valores.length;

    if (valores.length == 0) {
        cabeza = null;
        return;
    }

    cabeza = new Nodo(valores[0]);
    Nodo actual = cabeza;

    for (int i = 1; i < valores.length; i++) {
        actual.siguiente = new Nodo(valores[i]);
        actual = actual.siguiente;
    }
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
