package ReservacionHuesped;

import javax.swing.JOptionPane;

public class SistemaReservas {

    static int[][] calendario = new int[5][30];

    // ------------------- MENU PRINCIPAL -------------------
    public static void menu() {
        boolean salir = false;

        while (!salir) {
            String menu = "SISTEMA DE RESERVAS DE HOTEL\n"
                    + "1. Reservar habitación\n"
                    + "2. Ver calendario\n"
                    + "3. Cancelar reserva\n"
                    + "4. Salir";

            int opcion = Integer.parseInt(JOptionPane.showInputDialog(menu));

            switch (opcion) {
                case 1:
                    reservar();
                    break;
                case 2:
                    mostrarCalendario();
                    break;
                case 3:
                    cancelarReserva();
                    break;
                case 4:
                    salir = true;
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }
    }

    // ---------------------- RESERVAR ----------------------
    public static void reservar() {
        int habitacion = Integer.parseInt(JOptionPane.showInputDialog("Seleccione habitación (1-5):"));
        int entrada = Integer.parseInt(JOptionPane.showInputDialog("Ingrese día de entrada (1-29):"));
        int salida = Integer.parseInt(JOptionPane.showInputDialog("Ingrese día de salida (1-30):"));

        if (!validarFechas(entrada, salida)) {
            return;
        }
        if (!validarHabitacion(habitacion)) {
            return;
        }

        for (int i = entrada; i < salida; i++) {
            if (calendario[habitacion][i] == 1) {
                JOptionPane.showMessageDialog(null, "No disponible en ese rango.");
                return;
            }
        }

        for (int i = entrada; i < salida; i++) {
            calendario[habitacion][i] = 1;
        }

        JOptionPane.showMessageDialog(null, "Reserva hecha en habitación " + habitacion);
    }

    // ---------------------- CANCELAR ----------------------
    public static void cancelarReserva() {
        int habitacion = Integer.parseInt(JOptionPane.showInputDialog("Habitación a cancelar (1-5):"));
        int entrada = Integer.parseInt(JOptionPane.showInputDialog("Día de entrada (1"
                + ""
                + ""
                + "-29):"));
        int salida = Integer.parseInt(JOptionPane.showInputDialog("Día de salida (1-30):"));

        if (!validarFechas(entrada, salida)) {
            return;
        }
        if (!validarHabitacion(habitacion)) {
            return;
        }

        for (int i = entrada; i < salida; i++) {
            calendario[habitacion][i] = 0;
        }

        JOptionPane.showMessageDialog(null, "Reserva cancelada.");
    }

    // ---------------------- CALENDARIO ----------------------
    public static void mostrarCalendario() {
        String texto
                = "==============================\n"
                + "   CALENDARIO DE RESERVAS\n"
                + "   0 = Libre   |   1 = Ocupado\n"
                + "==============================\n\n"
                + "Día:   ";

        for (int d = 1; d < 30; d++) {
            texto += (d < 10 ? "0" + d : d) + " ";
        }

        texto += "\n-------------------------------------------------------------\n";

        for (int h = 0; h < 5; h++) {
            texto += "Hab " + (h + 1) + ": ";
            for (int d = 0; d < 30; d++) {
                texto += " " + calendario[h][d] + " ";
            }
            texto += "\n";
        }

        texto += "-------------------------------------------------------------";

        JOptionPane.showMessageDialog(null, texto);
    }

    // ---------------------- VALIDACIONES ----------------------
    public static boolean validarFechas(int entrada, int salida) {
        if (entrada < 0 || salida > 30 || entrada >= salida) {
            JOptionPane.showMessageDialog(null, "Fechas inválidas.");
            return false;
        }
        return true;
    }

    public static boolean validarHabitacion(int h) {
        if (h < 0 || h >= 5) {
            JOptionPane.showMessageDialog(null, "Habitación inválida.");
            return false;
        }
        return true;
    }
}
//Raul aca coloque sistema de reportes
public static void menuReportes() {
        boolean salir = false;

        while (!salir) {
            String menu = "GENERAR REPORTES\n"
                    + "1. Lista de huéspedes registrados\n"
                    + "2. Habitaciones ocupadas y disponibles\n"
                    + "3. Actividades programadas para el día\n"
                    + "4. Volver al menú principal";

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
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }
    }

    public static void reporteHuespedes() {
        if (cantidadHuespedes == 0) {
            JOptionPane.showMessageDialog(null, "No hay huéspedes registrados.");
            return;
        }

        String reporte = "═══════════════════════════════════════\n"
                + "   LISTA DE HUÉSPEDES REGISTRADOS\n"
                + "═══════════════════════════════════════\n\n";

        for (int i = 0; i < cantidadHuespedes; i++) {
            reporte += "Huésped #" + (i + 1) + ":\n"
                    + "  Nombre: " + nombresHuespedes[i] + "\n"
                    + "  Habitación: " + habitacionesHuespedes[i] + "\n"
                    + "  Tipo: " + nombresTiposHabitacion[tipoHabitacionHuespedes[i]] + "\n"
                    + "  Entrada: Día " + fechaEntradaHuespedes[i] + "\n"
                    + "  Salida: Día " + fechaSalidaHuespedes[i] + "\n"
                    + "  Presupuesto: $" + presupuestoHuespedes[i] + "\n\n";
        }

        JOptionPane.showMessageDialog(null, reporte);
    }

    public static void reporteHabitaciones() {
        String reporte = "═══════════════════════════════════════\n"
                + "   HABITACIONES OCUPADAS Y DISPONIBLES\n"
                + "═══════════════════════════════════════\n\n";

        // Verificar estado de cada habitación
        for (int h = 0; h < 5; h++) {
            boolean ocupada = false;
            String huespedNombre = "";

            // Buscar si hay algún huésped en esta habitación
            for (int i = 0; i < cantidadHuespedes; i++) {
                if (habitacionesHuespedes[i] == (h + 1)) {
                    ocupada = true;
                    huespedNombre = nombresHuespedes[i];
                    break;
                }
            }

            String tipoHabitacion = "";
            if (h < 2) {
                tipoHabitacion = "Económica";
            } else if (h < 4) {
                tipoHabitacion = "Estándar";
            } else {
                tipoHabitacion = "Suite";
            }

            reporte += "Habitación " + (h + 1) + " (" + tipoHabitacion + "): ";
            if (ocupada) {
                reporte += "OCUPADA - Huésped: " + huespedNombre + "\n";
            } else {
                reporte += "DISPONIBLE\n";
            }
        }

        JOptionPane.showMessageDialog(null, reporte);
    }

    public static void reporteActividadesDia() {
        int dia = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el día para el reporte (1-30):"));
        if (dia < 1 || dia > 30) {
            JOptionPane.showMessageDialog(null, "Día inválido.");
            return;
        }

        String reporte = "═══════════════════════════════════════\n"
                + "   ACTIVIDADES PROGRAMADAS\n"
                + "   DÍA " + dia + "\n"
                + "═══════════════════════════════════════\n\n";

        boolean encontrado = false;
        for (int i = 0; i < cantidadActividades; i++) {
            if (actividadesDiarias[i] != null && actividadesDiarias[i].startsWith("Día " + dia + ":")) {
                encontrado = true;
                reporte += actividadesDiarias[i] + "\n";
            }
        }

        if (!encontrado) {
            reporte += "No hay actividades programadas para este día.";
        }

        JOptionPane.showMessageDialog(null, reporte);
    }
