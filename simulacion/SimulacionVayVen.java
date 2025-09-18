import java.util.Random;

class Bus {
    private int id;
    private int capacidad;
    private int ocupacion;
    private double tiempoRecorrido;
    private double retraso;

    public Bus(int id, int capacidad) {
        this.id = id;
        this.capacidad = capacidad;
        this.ocupacion = 0;
        this.tiempoRecorrido = 0;
        this.retraso = 0;
    }

    public void simularRecorrido(double factorTrafico, int[] pasajerosPorParada, String escenario) {
        Random rand = new Random();
        double tiempodelViaje = 10;
        tiempoRecorrido = 0;
        retraso = 0;

        System.out.println("\n--- VayVen " + id + " (Escenario: " + escenario + ") ---");

        for (int i = 0; i < pasajerosPorParada.length; i++) {

            double retrasoTramo = tiempodelViaje * factorTrafico;
            tiempoRecorrido += tiempodelViaje + retrasoTramo;
            retraso += retrasoTramo;

            int bajan = rand.nextInt(Math.min(5, ocupacion) + 1);
            ocupacion -= bajan;

            int suben = pasajerosPorParada[i];
            int asientosDisponibles = capacidad - ocupacion;
            int realmenteSuben = Math.min(suben, asientosDisponibles);
            ocupacion += realmenteSuben;

            System.out.println("Parada " + (i + 1) + ": " + bajan + " bajan, "
                    + realmenteSuben + " suben. Ocupación: "
                    + ocupacion + "/" + capacidad);
        }

        System.out.println("Tiempo total recorrido: " + tiempoRecorrido + " minutos");
        System.out.println("Retraso total: " + retraso + " minutos");
        System.out.println("Ocupación final: " + ocupacion + "/" + capacidad);
    }

    public double getTiempoRecorrido() {
        return tiempoRecorrido;
    }

    public double getRetraso() {
        return retraso;
    }

    public int getOcupacion() {
        return ocupacion;
    }

    public int getId() {
        return id;
    }
}

public class SimulacionVayVen {
    public static void main(String[] args) {

        Bus vayven1 = new Bus(1, 40);
        Bus vayven2 = new Bus(2, 40);

        double[] factoresTrafico = { 0.1, 0.5 };
        String[] escenarios = { "Bajo", "Alto" };

        int[] pasajerosBus1 = { 5, 8, 0, 4, 7 }; // Escenario bajo
        int[] pasajerosBus2 = { 20, 5, 15, 0, 20 }; // Escenario alto

        vayven1.simularRecorrido(factoresTrafico[0], pasajerosBus1, escenarios[0]);
        vayven2.simularRecorrido(factoresTrafico[1], pasajerosBus2, escenarios[1]);

    }
}
