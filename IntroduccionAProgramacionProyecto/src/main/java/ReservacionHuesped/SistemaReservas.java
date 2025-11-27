package ReservacionHuesped;

import javax.swing.JOptionPane;

public class SistemaReservas {

    public static int[][] calendario = new int[5][30];

    public static void menu() {

        boolean salir = false;

        while (!salir) {

            String menu = "SISTEMA DE RESERVAS DE HOTEL\n"
                    + "1. Reservar habitación\n"
                    + "2. Ver calendario\n"
                    + "3. Cancelar reserva\n"
                    + "4. Reportes\n"
                    + "5. Salir";

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
                    Menureportes.menuReportes();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }
    }

    public static void reservar() {

        String nombre = JOptionPane.showInputDialog("Nombre del huésped:");
        double presupuesto = Double.parseDouble(JOptionPane.showInputDialog("Presupuesto del huésped:"));

        int habitacion = Integer.parseInt(JOptionPane.showInputDialog("Seleccione habitación (1-5):"));
        int tipoHabitacion = habitacion <= 2 ? 0 : habitacion <= 4 ? 1 : 2;

        int entrada = Integer.parseInt(JOptionPane.showInputDialog("Día de entrada (1-29):"));
        int salida = Integer.parseInt(JOptionPane.showInputDialog("Día de salida (1-30):"));

        if (!validarHabitacion(habitacion) || !validarFechas(entrada, salida)) {
            return;
        }

        // Validar disponibilidad
        for (int i = entrada; i < salida; i++) {
            if (calendario[habitacion - 1][i] == 1) {
                JOptionPane.showMessageDialog(null, "Habitación ocupada en ese rango.");
                return;
            }
        }

        // Registrar en calendario
        for (int i = entrada; i < salida; i++) {
            calendario[habitacion - 1][i] = 1;
        }

        // Registrar huésped
        RegistroHuespedes.registrarHuesped(
                nombre,
                habitacion,
                tipoHabitacion,
                entrada,
                salida,
                presupuesto
        );

        JOptionPane.showMessageDialog(null,
                "Reserva completada para " + nombre
                + "\nHabitación " + habitacion);
    }

    public static void cancelarReserva() {

        int habitacion = Integer.parseInt(JOptionPane.showInputDialog("Habitación (1-5):"));
        int entrada = Integer.parseInt(JOptionPane.showInputDialog("Día entrada:"));
        int salida = Integer.parseInt(JOptionPane.showInputDialog("Día salida:"));

        if (!validarHabitacion(habitacion) || !validarFechas(entrada, salida)) {
            return;
        }

        for (int i = entrada; i < salida; i++) {
            calendario[habitacion - 1][i] = 0;
        }

        JOptionPane.showMessageDialog(null, "Reserva cancelada.");
    }

    public static void mostrarCalendario() {

        String texto = "CALENDARIO DE RESERVAS\nDía:   ";

        for (int d = 1; d <= 30; d++) {
            texto += (d < 10 ? "0" + d : d) + " ";
        }

        texto += "\n----------------------------------------\n";

        for (int h = 0; h < 5; h++) {
            texto += "Hab " + (h + 1) + ": ";
            for (int d = 0; d < 30; d++) {
                texto += calendario[h][d] + " ";
            }
            texto += "\n";
        }

        JOptionPane.showMessageDialog(null, texto);
    }

    public static boolean validarHabitacion(int h) {
        return h >= 1 && h <= 5;
    }

    public static boolean validarFechas(int e, int s) {
        return e >= 1 && s <= 30 && e < s;
    }
}

