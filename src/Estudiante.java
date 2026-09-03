public class Estudiante {

    private String legajo;
    private String nombre;

    public Estudiante(String legajo, String nombre) {
        this.legajo = legajo;
        setNombre(nombre);
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {

        if (nombre != null && !nombre.isBlank()) {
            this.nombre = nombre;
        }
    }
}