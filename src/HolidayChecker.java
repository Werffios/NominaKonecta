import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

public class HolidayChecker {

    /**
     * Consulta el API de Konecta para determinar si la fecha es festiva.
     * Se hace una petición GET a: https://konecta.calendar.werffios.com/festivo/{fecha}
     *
     * @param fecha La fecha a consultar.
     * @return true si la fecha es festiva, false en caso contrario.
     */
    public static boolean isHoliday(LocalDate fecha) {
        try {
            String fechaStr = fecha.toString();
            String url = "https://konecta.calendar.werffios.com/festivo/" + fechaStr;

            // Crear un cliente HTTP.
            HttpClient client = HttpClient.newHttpClient();

            // Crear la solicitud GET.
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            // Enviar la solicitud y obtener la respuesta.
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String body = response.body();
            // Se interpreta la respuesta buscando si en el JSON aparece "festivo":true
            // Esta es una solución simple sin usar librerías JSON externas.
            return body.contains("\"festivo\":true");
        } catch (Exception e) {
            System.err.println("Error al consultar el API de festivos: " + e.getMessage());
            // En caso de error (por ejemplo, no se pudo conectar), se asume que la fecha NO es festiva.
            return false;
        }
    }
}
