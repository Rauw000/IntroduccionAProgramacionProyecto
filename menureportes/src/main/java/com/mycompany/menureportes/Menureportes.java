/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.introduccionaprogramacionproyecto;

import ReservacionHuesped.SistemaReservas;
import javax.swing.JOptionPane;

public class Menureportes {

    public static void menuReportes() {
        boolean salir = false;

        while (!salir) {
            String menu = "═══════════════════════════════════════\n"
                    + "        Generar reporestes: \n"
                    + "═══════════════════════════════════════\n"
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
        if (IntroduccionAProgramacionProyecto.cantidadHuespedes == 0) {
            JOptionPane.showMessageDialog(null, "No hay huéspedes registrados.");
            return;
        }

        String reporte = "═══════════════════════════════════════\n"
                + "   LISTA DE HUÉSPEDES REGISTRADOS\n"
                + "═══════════════════════════════════════\n\n";

        for (int i = 0; i < IntroduccionAProgramacionProyecto.cantidadHuespedes; i++) {
            reporte += "Huésped #" + (i + 1) + ":\n"
                    + "  Nombre: " + IntroduccionAProgramacionProyecto.nombresHuespedes[i] + "\n"
                    + "  Habitación: " + IntroduccionAProgramacionProyecto.habitacionesHuespedes[i] + "\n"
                    + "  Tipo: " + IntroduccionAProgramacionProyecto.nombresTiposHabitacion[IntroduccionAProgramacionProyecto.tipoHabitacionHuespedes[i]] + "\n"
                    + "  Entrada: Día " + IntroduccionAProgramacionProyecto.fechaEntradaHuespedes[i] + "\n"
                    + "  Salida: Día " + IntroduccionAProgramacionProyecto.fechaSalidaHuespedes[i] + "\n"
                    + "  Presupuesto: $" + IntroduccionAProgramacionProyecto.presupuestoHuespedes[i] + "\n\n";
        }

        JOptionPane.showMessageDialog(null, reporte);
    }

    public static void reporteHabitaciones() {
        String reporte = "═══════════════════════════════════════\n"
                + "   Habitaciones disponibles\n"
                + "═══════════════════════════════════════\n\n";

        for (int h = 0; h < 5; h++) {
            boolean ocupada = false;
            String huespedNombre = "";

            for (int i = 0; i < IntroduccionAProgramacionProyecto.cantidadHuespedes; i++) {
                if (IntroduccionAProgramacionProyecto.habitacionesHuespedes[i] == (h + 1)) {
                    ocupada = true;
                    huespedNombre = IntroduccionAProgramacionProyecto.nombresHuespedes[i];
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

        reporte += "\n═══════════════════════════════════════\n";
        reporte += "Estado detallado del calendario:\n";
        reporte += "═══════════════════════════════════════\n";

        for (int h = 0; h < 5; h++) {
            int diasOcupados = 0;
            for (int d = 0; d < 30; d++) {
                if (SistemaReservas.calendario[h][d] == 1) {
                    diasOcupados++;
                }
            }
            reporte += "Hab " + (h + 1) + ": " + diasOcupados + " días ocupados de 30\n";
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
                + "   Actividades programadas\n"
                + "   DÍA " + dia + "\n"
                + "═══════════════════════════════════════\n\n";

        boolean encontrado = false;
        for (int i = 0; i < IntroduccionAProgramacionProyecto.cantidadActividades; i++) {
            if (IntroduccionAProgramacionProyecto.actividadesDiarias[i] != null
                    && IntroduccionAProgramacionProyecto.actividadesDiarias[i].startsWith("Día " + dia + ":")) {
                encontrado = true;
                reporte += IntroduccionAProgramacionProyecto.actividadesDiarias[i] + "\n";
            }
        }

        if (!encontrado) {
            reporte += "No hay actividades programadas para este día.";
        }

        JOptionPane.showMessageDialog(null, reporte);
    }
}
