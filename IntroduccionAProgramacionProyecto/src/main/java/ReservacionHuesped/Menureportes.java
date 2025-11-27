package ReservacionHuesped;

import javax.swing.JOptionPane;

public class Menureportes {

    public static void menuReportes() {

        boolean salir = false;

        while (!salir) {
            String menu = "═══════════════════════\n"
                    + "    MENÚ DE REPORTES\n"
                    + "═══════════════════════\n"
                    + "1. Lista de huéspedes\n"
                    + "2. Habitaciones ocupadas\n"
                    + "3. Actividades del día\n"
                    + "4. Volver";

            int opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcion) {
                case 1:
                    reporteHuespedes();
                    break;
                case 2:
                    reporteHabitaciones();
                    break;
                case 3:
                    reporteActividadesDia();
                    break;
                case 4:
                    salir = true;
                    break;
            }
        }
    }

    public static void reporteHuespedes() {

        if (RegistroHuespedes.cantidadHuespedes == 0) {
            JOptionPane.showMessageDialog(null, "No hay huéspedes registrados.");
            return;
        }

        String r = "LISTA DE HUÉSPEDES\n\n";

        for (int i = 0; i < RegistroHuespedes.cantidadHuespedes; i++) {

            r += "Huésped #" + (i + 1) + "\n";
            r += "Nombre: " + RegistroHuespedes.nombresHuespedes[i] + "\n";
            r += "Habitación: " + RegistroHuespedes.habitacionesHuespedes[i] + "\n";
            r += "Tipo: " + RegistroHuespedes.nombresTiposHabitacion[RegistroHuespedes.tipoHabitacionHuespedes[i]] + "\n";
            r += "Entrada: Día " + RegistroHuespedes.fechaEntradaHuespedes[i] + "\n";
            r += "Salida: Día " + RegistroHuespedes.fechaSalidaHuespedes[i] + "\n";
            r += "Presupuesto: $" + RegistroHuespedes.presupuestoHuespedes[i] + "\n\n";
        }

        JOptionPane.showMessageDialog(null, r);
    }

    public static void reporteHabitaciones() {

        String r = "ESTADO DE HABITACIONES\n\n";

        for (int h = 0; h < 5; h++) {

            boolean ocupada = false;
            String nombre = "";

            for (int i = 0; i < RegistroHuespedes.cantidadHuespedes; i++) {
                if (RegistroHuespedes.habitacionesHuespedes[i] == (h + 1)) {
                    ocupada = true;
                    nombre = RegistroHuespedes.nombresHuespedes[i];
                }
            }

            r += "Habitación " + (h + 1)
                    + (ocupada ? " OCUPADA por " + nombre : " DISPONIBLE")
                    + "\n";
        }

        r += "\nCalendario detallado:\n";

        for (int h = 0; h < 5; h++) {

            int dias = 0;

            for (int d = 0; d < 30; d++) {
                if (SistemaReservas.calendario[h][d] == 1) {
                    dias++;
                }
            }

            r += "Hab " + (h + 1) + ": " + dias + " días ocupados.\n";
        }

        JOptionPane.showMessageDialog(null, r);
    }

    public static void reporteActividadesDia() {
        JOptionPane.showMessageDialog(null, "Aún no implementado.");
    }
}
