import java.util.Scanner;
import java.util.InputMismatchException;

public class Biseccion {

    /**
     * Encuentra una raíz de la función f(x) en el intervalo [a, b]
     * usando el Método de Bisección.
     *
     * @param a         El límite inferior del intervalo.
     * @param b         El límite superior del intervalo.
     * @param tolerancia La tolerancia deseada para la aproximación de la raíz.
     * @param maxIter   El número máximo de iteraciones.
     * @return La aproximación de la raíz, o Double.NaN si no se encontró una raíz.
     */
    public static double biseccion(double a, double b, double tolerancia, int maxIter) {
        // Verifica que el intervalo sea válido y que haya un cambio de signo
        if (a >= b || f(a) * f(b) >= 0) {
            System.out.println("Error: El intervalo dado no es válido.");
            return Double.NaN; // Retorna NaN (Not a Number) para indicar un error
        }

        double c = 0; // Inicializa el punto medio
        int iter = 0;

        System.out.println("\nIteración | a\t\t | b\t\t | c\t\t | f(c)"); // Encabezado de la tabla

        while ((b - a) / 2 > tolerancia && iter < maxIter) {
            c = (a + b) / 2; // Calcula el punto medio
            System.out.printf("%4d     | %8.6f | %8.6f | %8.6f | %8.6f\n", iter + 1, a, b, c, f(c)); // Imprime la iteración

            if (f(c) == 0) {
                return c; // Se encontró la raíz exacta
            } else if (f(a) * f(c) < 0) {
                b = c; // La raíz está en el subintervalo [a, c]
            } else {
                a = c; // La raíz está en el subintervalo [c, b]
            }
            iter++;
        }

        if (iter >= maxIter) {
            System.out.println("Advertencia: El método no convergió dentro del número máximo de iteraciones.");
            return c; // Retorna la mejor aproximación encontrada
        }
        return c; // Retorna la aproximación de la raíz
    }

    /**
     * La función de la cual queremos encontrar la raíz.
     * Puedes cambiar esta función para probar el método con diferentes ecuaciones.
     * Por ejemplo, f(x) = x*x - 2  para encontrar la raíz cuadrada de 2.
     *
     * @param x El valor de x.
     * @return El valor de la función en x.
     */
    public static double f(double x) {
        return x * x * x - 2 * x - 5; // Ejemplo: x^3 - 2x - 5 = 0
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = 0, b = 0, tolerancia = 0;
        int maxIter = 0;
        boolean entradaValida = false;

        // Obtención de datos de entrada con validación
        System.out.println("Bienvenido al programa del Método de Bisección");

        while (!entradaValida) {
            try {
                System.out.print("Ingrese el límite inferior del intervalo (a): ");
                a = scanner.nextDouble();
                System.out.print("Ingrese el límite superior del intervalo (b): ");
                b = scanner.nextDouble();
                System.out.print("Ingrese la tolerancia deseada (ej., 1e-6): ");
                tolerancia = scanner.nextDouble();
                System.out.print("Ingrese el número máximo de iteraciones: ");
                maxIter = scanner.nextInt();

                if (a < b && tolerancia > 0 && maxIter > 0) {
                    entradaValida = true; // Salir del bucle si todos los datos son válidos
                } else {
                    System.out.println("Error: Asegúrese de que a < b, tolerancia > 0 y maxIter > 0.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese valores numéricos válidos.");
                scanner.next(); // Limpiar el buffer del scanner para evitar un bucle infinito
            }
        }
        scanner.close();


        // Llamada al Método de Bisección
        double raiz = biseccion(a, b, tolerancia, maxIter);

        // Mostrar el resultado
        if (Double.isNaN(raiz)) {
            System.out.println("No se pudo encontrar una raíz en el intervalo dado.");
        } else {
            System.out.println("\nLa raíz aproximada es: " + raiz);
            System.out.println("f(" + raiz + ") = " + f(raiz));
        }
    }
}

