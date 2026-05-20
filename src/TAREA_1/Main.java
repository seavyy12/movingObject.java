package TAREA_1;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        movingObject object = new movingObject(18);

        object.insertar(5, "5.0,8.0");
        object.insertar(1, "3.0,2.0");
        object.insertar(10, "15.0,6.0");
        object.insertar(7, "10.0,5.0");

        String[] rectangulo = {"4.0,5.0", "8.0,4.0"};
        int opcion = 0;

        System.out.println("-----iniciando pruebas de ejecucion del codigo-----");
        System.out.println("nodos insertados correctamente.");

        while (opcion != 5) {
            System.out.println("\n--- menu de prueba ---");
            System.out.println("1. probar calcular distancia total");
            System.out.println("2. probar metodo intersectaRangeST (caso 1: T[1, 6])");
            System.out.println("3. probar metodo intersectaRangeST (caso 2: T[8, 10])");
            System.out.println("4. probar eliminar (T=5) y recalcular distancia");
            System.out.println("5. salir");
            System.out.print("elija una opción: ");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    float distancia = object.distancia();
                    System.out.println("distancia total recorrida: " + distancia);
                    break;

                case 2:
                    int[] tiempo1 = {1, 6};
                    boolean cruce1 = object.IntersectaRangeST(rectangulo, tiempo1);
                    System.out.print("intervalo T [1, 6] con R: ");

                    if (cruce1 == true) {
                        System.out.println("true (correcto, cruzó el área)");
                    } else {
                        System.out.println("false (error)");
                    }
                    break;

                case 3:
                    int[] tiempo2 = {8, 10};
                    boolean cruce2 = object.IntersectaRangeST(rectangulo, tiempo2);
                    System.out.print("intervalo T [8, 10] con R: ");

                    if (cruce2 == false) {
                        System.out.println("false (correcto, pasó lejos)");
                    } else {
                        System.out.println("true (error)");
                    }
                    break;

                case 4:
                    System.out.println("se eliminó el nodo en T=5. volviendo a calcular distancia...");
                    object.Eliminar(5);
                    float nueva_distancia = object.distancia();
                    System.out.println("nueva distancia total: " + nueva_distancia);
                    break;

                case 5:
                    System.out.println("saliendooo");
                    break;

                default:
                    System.out.println("opción inválida , por favor intentar de nuevo.");
                    break;
            }
        }
        scanner.close();
    }
}