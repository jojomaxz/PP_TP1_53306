import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Actividad {

    private int id;
    private String titulo;
    private int cupoMaximo;

    private List<Inscripcion> inscripciones;

    public static final int CUPO_MINIMO;

    static {
        CUPO_MINIMO = 2;
        System.out.println(
                "Carga estática: la clase Actividad ha sido inicializada."
        );
    }

    public Actividad(int id, String titulo, int cupo) {

        this.id = id;
        setTitulo(titulo);

        this.cupoMaximo =
                cupo >= CUPO_MINIMO
                        ? cupo
                        : CUPO_MINIMO;

        this.inscripciones = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {

        if (titulo != null && !titulo.isBlank()) {
            this.titulo = titulo;
        }
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupo) {

        this.cupoMaximo =
                cupo >= CUPO_MINIMO
                        ? cupo
                        : CUPO_MINIMO;
    }

    public Inscripcion inscribir(Estudiante estudiante) {

        if (estudiante == null) {
            System.out.println("No se puede inscribir un estudiante nulo.");
            return null;
        }

        if (inscripciones.size() >= cupoMaximo) {
            System.out.println(
                    "No hay cupo disponible en la actividad "
                            + titulo + "."
            );
            return null;
        }

        Inscripcion inscripcion =
                new Inscripcion(
                        this,
                        estudiante,
                        LocalDate.now(),
                        "REGISTRADA"
                );

        inscripciones.add(inscripcion);

        return inscripcion;
    }

    public List<Inscripcion> getInscripciones() {
        return Collections.unmodifiableList(inscripciones);
    }

    public void mostrarInscripciones() {

        if (inscripciones.isEmpty()) {
            System.out.println("  No hay inscripciones cargadas.");
            return;
        }

        System.out.println("  Listado de inscripciones:");

        for (Inscripcion inscripcion : inscripciones) {

            System.out.println(
                    "  "
                            + inscripcion.getFecha()
                            + " - "
                            + inscripcion.getEstado()
                            + " - "
                            + inscripcion.getEstudiante().getNombre()
                            + " (Legajo: "
                            + inscripcion.getEstudiante().getLegajo()
                            + ")"
            );
        }
    }

    public final void mostrarIdentificacion() {

        System.out.println(
                "- "
                        + getTipo()
                        + ": "
                        + titulo
                        + " (identificador="
                        + id
                        + ")"
                        + " - Capacidad máxima: "
                        + cupoMaximo
        );
    }

    public abstract double calcularCostoMateriales();

    public abstract String getTipo();
}