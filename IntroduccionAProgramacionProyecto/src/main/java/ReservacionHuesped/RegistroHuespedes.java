package ReservacionHuesped;

public class RegistroHuespedes {

    // Capacidad máxima de huéspedes
    public static int cantidadHuespedes = 0;

    public static String[] nombresHuespedes = new String[100];
    public static int[] habitacionesHuespedes = new int[100];
    public static int[] tipoHabitacionHuespedes = new int[100];  // 0=Económica, 1=Estándar, 2=Suite
    public static int[] fechaEntradaHuespedes = new int[100];
    public static int[] fechaSalidaHuespedes = new int[100];
    public static double[] presupuestoHuespedes = new double[100];

    public static String[] nombresTiposHabitacion = {
        "Económica", "Estándar", "Suite"
    };

    // Método para agregar un huésped al registro
    public static void registrarHuesped(
            String nombre,
            int habitacion,
            int tipo,
            int entrada,
            int salida,
            double presupuesto
    ) {
        nombresHuespedes[cantidadHuespedes] = nombre;
        habitacionesHuespedes[cantidadHuespedes] = habitacion;
        tipoHabitacionHuespedes[cantidadHuespedes] = tipo;
        fechaEntradaHuespedes[cantidadHuespedes] = entrada;
        fechaSalidaHuespedes[cantidadHuespedes] = salida;
        presupuestoHuespedes[cantidadHuespedes] = presupuesto;

        cantidadHuespedes++;
    }
}
