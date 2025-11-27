package ReservacionHuesped;

import javax.swing.JOptionPane;

public class ActividadesHotel {

    // Arreglo donde se guardan las actividades (máximo 100)
    public static String[] actividadesDiarias = new String[100];
    public static int cantidadActividades = 0;

    // Menú para gestionar actividades
    public static void menuActividades() {

        boolean salir = false;

        while (!salir) {
            String menu = "═══════════════════════\n"
                    + "   ACTIVIDADES DEL HOTEL\n"
                    + "═══════════════════════\n"
                    + "1. Registrar actividad\n"
                    + "2. Ver actividades de un día\n"
                    + "3. Ver todas las actividades\n"
                    + "4. Volver";

            String respuesta = JOptionPane.showInputDialog(menu);

            if (respuesta == null) { // Si cierran o cancelan
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
                    registrarActividad();
                    break;
                case 2:
                    verActividadesDeUnDia();
                    break;
                case 3:
                    verTodasLasActividades();
                    break;
                case 4:
                    salir = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }

    // 1) Registrar una nueva actividad
    public static void registrarActividad() {

        if (cantidadActividades >= actividadesDiarias.length) {
            JOptionPane.showMessageDialog(null, "Ya no se pueden registrar más actividades.");
            return;
        }

        String textoDia = JOptionPane.showInputDialog("Ingrese el día de la actividad (1-30):");

        if (textoDia == null) {
            return;
        }

        int dia;
        try {
            dia = Integer.parseInt(textoDia);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Día inválido.");
            return;
        }

        if (dia < 1 || dia > 30) {
            JOptionPane.showMessageDialog(null, "El día debe estar entre 1 y 30.");
            return;
        }

        String descripcion = JOptionPane.showInputDialog("Descripción de la actividad:");

        if (descripcion == null || descripcion.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "La descripción no puede estar vacía.");
            return;
        }

        // Guardamos con el formato "Día X: descripción"
        actividadesDiarias[cantidadActividades] = "Día " + dia + ": " + descripcion.trim();
        cantidadActividades++;

        JOptionPane.showMessageDialog(null, "Actividad registrada correctamente.");
    }

    // 2) Ver actividades para un día específico
    public static void verActividadesDeUnDia() {

        if (cantidadActividades == 0) {
            JOptionPane.showMessageDialog(null, "No hay actividades registradas.");
            return;
        }

        String textoDia = JOptionPane.showInputDialog("¿Qué día desea consultar? (1-30):");

        if (textoDia == null) {
            return;
        }

        int dia;
        try {
            dia = Integer.parseInt(textoDia);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Día inválido.");
            return;
        }

        if (dia < 1 || dia > 30) {
            JOptionPane.showMessageDialog(null, "El día debe estar entre 1 y 30.");
            return;
        }

        String reporte = "Actividades programadas para el Día " + dia + ":\n\n";
        boolean encontrado = false;

        for (int i = 0; i < cantidadActividades; i++) {
            String act = actividadesDiarias[i];
            if (act != null && act.startsWith("Día " + dia + ":")) {
                encontrado = true;
                reporte += "- " + act + "\n";
            }
        }

        if (!encontrado) {
            reporte += "No hay actividades para este día.";
        }

        JOptionPane.showMessageDialog(null, reporte);
    }

    // 3) Ver todas las actividades registradas
    public static void verTodasLasActividades() {

        if (cantidadActividades == 0) {
            JOptionPane.showMessageDialog(null, "No hay actividades registradas.");
            return;
        }

        String reporte = "Todas las actividades registradas:\n\n";

        for (int i = 0; i < cantidadActividades; i++) {
            if (actividadesDiarias[i] != null) {
                reporte += (i + 1) + ". " + actividadesDiarias[i] + "\n";
            }
        }

        JOptionPane.showMessageDialog(null, reporte);
    }

    // OPCIONAL: para probar tú sola desde NetBeans
    public static void main(String[] args) {
        menuActividades();
    }
}
