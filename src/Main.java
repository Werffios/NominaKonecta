import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Ingrese la fecha (formato yyyy-MM-dd): ");
            String input = scanner.nextLine();

            // Convertir el string a LocalDate (asegurarse de que el formato sea correcto)
            LocalDate inputDate = LocalDate.parse(input);

            // Calcular la próxima fecha de pago según las reglas definidas
            LocalDate proximoPago = PayrollCalculator.getProximoPago(inputDate);

            System.out.println("La próxima fecha de pago es: " + proximoPago);
        } catch (Exception e) {
            System.err.println("Ocurrió un error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
