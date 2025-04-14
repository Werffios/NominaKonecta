import java.time.DayOfWeek;
import java.time.LocalDate;

public class PayrollCalculator {

    /**
     * Devuelve la próxima fecha de pago según la fecha de entrada.
     * Las fechas programadas son el día 15 y 30 de cada mes.
     * Si la fecha programada no es hábil (fin de semana o festivo), se retrocede al día hábil anterior.
     *
     * @param inputDate La fecha ingresada por el usuario en formato yyyy-MM-dd.
     * @return La fecha de pago ajustada.
     * @throws Exception En caso de error al consultar el API de festivos.
     */
    public static LocalDate getProximoPago(LocalDate inputDate) throws Exception {
        int dia = inputDate.getDayOfMonth();
        LocalDate fechaProgramada;

        // Determinar la fecha programada según la fecha de entrada.
        if (dia <= 15) {
            fechaProgramada = LocalDate.of(inputDate.getYear(), inputDate.getMonth(), 15);
        } else if (dia > 15 && dia <= 31) {
            // Se asume que si el día es 30 o 31, la fecha programada es el 30, los días de pago son 30 y 15.
            fechaProgramada = LocalDate.of(inputDate.getYear(), inputDate.getMonth(), 30);
        } else {
            // Si el día es mayor a 30, se considera el 15 del siguiente mes.
            fechaProgramada = LocalDate.of(inputDate.getYear(), inputDate.getMonth(), 1)
                    .plusMonths(1)
                    .withDayOfMonth(15);
        }

        // Si la fecha programada es el mismo día que ingresa el usuario, se asume que es la de pago; de lo contrario,
        // se ajusta la fecha si es que corresponde.
        // Se ajusta la fecha programada retrocediendo hasta encontrar un día hábil.
        while (esNoHabil(fechaProgramada)) {
            fechaProgramada = fechaProgramada.minusDays(1);
        }

        return fechaProgramada;
    }

    /**
     * Determina si la fecha indicada no es hábil.
     * Una fecha no es hábil si es sábado, domingo o un festivo (según el API).
     *
     * @param fecha La fecha a verificar.
     * @return true si la fecha no es hábil, false en caso contrario.
     */
    public static boolean esNoHabil(LocalDate fecha) {
        // Verificar fines de semana.
        DayOfWeek diaSemana = fecha.getDayOfWeek();
        if (diaSemana == DayOfWeek.SATURDAY || diaSemana == DayOfWeek.SUNDAY) {
            return true;
        }

        // Verificar si es festivo mediante el API.
        if (HolidayChecker.isHoliday(fecha)) {
            return true;
        }

        return false;
    }
}
