import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class EventoUniversitario {

    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;

    private Sala sala;
    private List<Actividad> actividades;

    private static int cantidadEventos;

    static {
        cantidadEventos = 0;
        System.out.println("Carga estática: la clase EventoUniversitario ha sido inicializada.");
    }

    // Constructor
    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito) {
        this.id = id;
        setTitulo(titulo);
        this.costoBase = costoBase;
        this.gratuito = gratuito;

        this.actividades = new ArrayList<>();

        cantidadEventos++;
    }

    // Constructor de copia
    public EventoUniversitario(EventoUniversitario otroEvento) {
        this(
                otroEvento.id,
                otroEvento.titulo,
                otroEvento.costoBase,
                otroEvento.gratuito
        );
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo != null && !titulo.isBlank()) {
            this.titulo = titulo;
        }
    }

    public double calcularCostoEstimado() {

        if (gratuito) {
            return 0.0;
        }

        double costoTotal = costoBase;

        for (Actividad actividad : actividades) {
            costoTotal += actividad.calcularCostoMateriales();
        }

        return costoTotal * 1.21;
    }

    public Sala getSala() {
        return sala;
    }

    public void asignarSala(Sala sala) {
        this.sala = sala;
    }

    public void crearActividad(int id, String titulo, int cupo, String tipoActividad) {

        Scanner scanner = new Scanner(System.in);

        switch (tipoActividad.toLowerCase()) {

            case "charla":

                System.out.print(
                        "Indique quién será el disertante de la charla "
                                + titulo + ": "
                );

                String disertante = scanner.nextLine();

                Actividad charla =
                        new Charla(id, titulo, disertante, cupo);

                actividades.add(charla);

                break;

            case "taller":

                System.out.print(
                        "¿El taller " + titulo
                                + " necesita utilizar una Notebook? (S/N): "
                );

                String respuesta =
                        scanner.nextLine().trim().toLowerCase();

                boolean requiereNotebook =
                        respuesta.equals("s")
                                || respuesta.equals("si")
                                || respuesta.equals("sí");

                Actividad taller =
                        new Taller(id, titulo, requiereNotebook, cupo);

                actividades.add(taller);

                break;

            default:
                System.out.println(
                        "Error: no se reconoce el tipo de actividad indicado."
                );
        }
    }

    public List<Actividad> getActividades() {
        return Collections.unmodifiableList(actividades);
    }

    public void mostrarDatos() {

        System.out.println("===============================================");
        System.out.println("Código del evento: " + id);
        System.out.println("Nombre del evento: " + titulo);
        System.out.println("Costo estimado: $" + calcularCostoEstimado());

        if (sala != null) {
            System.out.println("Sala asignada: " + sala.getNombre());
        } else {
            System.out.println("Sala asignada: No se asignó ninguna sala");
        }

        System.out.println();
        System.out.println("Actividades disponibles:");
        System.out.println("------------------------");

        if (actividades.isEmpty()) {
            System.out.println("No hay actividades cargadas.");
        } else {

            for (Actividad actividad : actividades) {
                actividad.mostrarIdentificacion();
                actividad.mostrarInscripciones();
                System.out.println();
            }
        }

        System.out.println("===============================================");
    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }
}