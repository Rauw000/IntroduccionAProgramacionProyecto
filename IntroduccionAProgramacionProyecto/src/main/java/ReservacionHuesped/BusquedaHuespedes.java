package ReservacionHuesped;

import javax.swing.JOptionPane;

public class BusquedaHuespedes {

    // Menú principal de búsquedas
    public static void menuBusquedas() {

        boolean salir = false;

        while (!salir) {
            String menu = "═══════════════════════\n"
                    + "   BÚSQUEDA DE HUÉSPEDES\n"
                    + "═══════════════════════\n"
                    + "1. Buscar por nombre\n"
                    + "2. Buscar por número de habitación\n"
                    + "3. Buscar por fecha de ingreso\n"
                    + "4. Volver";

            String respuesta = JOptionPane.showInputDialog(menu);

            if (respuesta == null) { // Cancelar
                return;
            }

            int opcion;
            try {
                opcion = Integer.parseInt(respuesta);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingrese un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    buscarPorNombre();
                    break;
                case 2:
                    buscarPorHabitacion();
                    break;
                case 3:
                    buscarPorFechaIngreso();
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }

    // 1) Buscar huéspedes por nombre
    public static void buscarPorNombre() {

        if (RegistroHuespedes.cantidadHuespedes == 0) {
            JOptionPane.showMessageDialog(null, "No hay huéspedes registrados.");
            return;
        }

        String nombreBuscado = JOptionPane.showInputDialog(
                "Ingrese el nombre o parte del nombre a buscar:");

        if (nombreBuscado == null || nombreBuscado.trim().isEmpty()) {
            return;
        }

        nombreBuscado = nombreBuscado.trim().toLowerCase();

        String resultado = "Resultados de búsqueda por nombre:\n\n";
        boolean encontrado = false;

        for (int i = 0; i < RegistroHuespedes.cantidadHuespedes; i++) {
            String nombre = RegistroHuespedes.nombresHuespedes[i];
            if (nombre != null && nombre.toLowerCase().contains(nombreBuscado)) {
                encontrado = true;
                resultado += formatoHuesped(i);
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "No se encontraron huéspedes con ese nombre.");
        } else {
            JOptionPane.showMessageDialog(null, resultado);
        }
    }

    // 2) Buscar huéspedes por número de habitación
    public static void buscarPorHabitacion() {

        if (RegistroHuespedes.cantidadHuespedes == 0) {
            JOptionPane.showMessageDialog(null, "No hay huéspedes registrados.");
            return;
        }

        String entrada = JOptionPane.showInputDialog(
                "Ingrese el número de habitación (1-5):");

        if (entrada == null) {
            return;
        }

        int habitacion;
        try {
            habitacion = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Número de habitación inválido.");
            return;
        }

        if (!SistemaReservas.validarHabitacion(habitacion)) {
            JOptionPane.showMessageDialog(null, "Número de habitación fuera de rango.");
            return;
        }

        String resultado = "Huéspedes en la habitación " + habitacion + ":\n\n";
        boolean encontrado = false;

        for (int i = 0; i < RegistroHuespedes.cantidadHuespedes; i++) {
            if (RegistroHuespedes.habitacionesHuespedes[i] == habitacion) {
                encontrado = true;
                resultado += formatoHuesped(i);
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null,
                    "No hay huéspedes registrados en la habitación " + habitacion + ".");
        } else {
            JOptionPane.showMessageDialog(null, resultado);
        }
    }

    // 3) Buscar huéspedes por fecha de ingreso (día de entrada)
    public static void buscarPorFechaIngreso() {

        if (RegistroHuespedes.cantidadHuespedes == 0) {
            JOptionPane.showMessageDialog(null, "No hay huéspedes registrados.");
            return;
        }

        String entrada = JOptionPane.showInputDialog(
                "Ingrese el día de entrada (1-30):");

        if (entrada == null) {
            return;
        }

        int diaEntrada;
        try {
            diaEntrada = Integer.parseInt(entrada);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Día inválido.");
            return;
        }

        if (diaEntrada < 1 || diaEntrada > 30) {
            JOptionPane.showMessageDialog(null, "El día debe estar entre 1 y 30.");
            return;
        }

        String resultado = "Huéspedes con fecha de ingreso Día " + diaEntrada + ":\n\n";
        boolean encontrado = false;

        for (int i = 0; i < RegistroHuespedes.cantidadHuespedes; i++) {
            if (RegistroHuespedes.fechaEntradaHuespedes[i] == diaEntrada) {
                encontrado = true;
                resultado += formatoHuesped(i);
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null,
                    "No hay huéspedes con fecha de ingreso Día " + diaEntrada + ".");
        } else {
            JOptionPane.showMessageDialog(null, resultado);
        }
    }

    // Método de apoyo: arma el texto bonito de un huésped
    private static String formatoHuesped(int i) {
        String texto = "";
        texto += "Huésped #" + (i + 1) + "\n";
        texto += "Nombre: " + RegistroHuespedes.nombresHuespedes[i] + "\n";
        texto += "Habitación: " + RegistroHuespedes.habitacionesHuespedes[i] + "\n";
        texto += "Tipo: " + RegistroHuespedes.nombresTiposHabitacion[
                RegistroHuespedes.tipoHabitacionHuespedes[i]] + "\n";
        texto += "Entrada: Día " + RegistroHuespedes.fechaEntradaHuespedes[i] + "\n";
        texto += "Salida: Día " + RegistroHuespedes.fechaSalidaHuespedes[i] + "\n";
        texto += "Presupuesto: $" + RegistroHuespedes.presupuestoHuespedes[i] + "\n\n";
        return texto;
    }

    // OPCIONAL: para probar tú sola desde NetBeans
    public static void main(String[] args) {
        menuBusquedas();
    }
}
