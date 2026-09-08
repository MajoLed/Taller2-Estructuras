import java.util.Random;

public class Experimentos {

    public static void main(String[] args) {

        int[] tamanos = {200, 400, 800, 1600, 3200};

        int mediciones = 10;

        System.out.println("ESCENARIO ALEATORIO");
        realizarExperimentos(tamanos, mediciones, "aleatorio");

        System.out.println("\nESCENARIO ASCENDENTE");
        realizarExperimentos(tamanos, mediciones, "ascendente");

        System.out.println("\nESCENARIO DESCENDENTE");
        realizarExperimentos(tamanos, mediciones, "descendente");
    }

    public static void realizarExperimentos(
            int[] tamanos,
            int mediciones,
            String escenario) {

        for (int n : tamanos) {

            long tiempoTotal = 0;

            for (int i = 0; i < mediciones; i++) {

                int[] datos = generarDatos(n, escenario);

                SecuenciaEnteros secuencia =
                        new SecuenciaArreglo(datos);

                long inicio = System.nanoTime();

                // Algoritmo seleccionado
                Main.contarInversiones(secuencia);

                long fin = System.nanoTime();

                tiempoTotal += (fin - inicio);
            }

            double promedio = (double) tiempoTotal / mediciones;

            System.out.println(
                    "N = " + n +
                    " | Promedio = " + promedio + " ns"
            );
        }
    }

    public static int[] generarDatos(int n, String escenario) {

        int[] datos = new int[n];

        switch (escenario) {

            case "aleatorio":
                Random random = new Random();

                for (int i = 0; i < n; i++) {
                    datos[i] = random.nextInt(100000);
                }
                break;

            case "ascendente":
                for (int i = 0; i < n; i++) {
                    datos[i] = i;
                }
                break;

            case "descendente":
                for (int i = 0; i < n; i++) {
                    datos[i] = n - i;
                }
                break;
        }

        return datos;
    }
}
