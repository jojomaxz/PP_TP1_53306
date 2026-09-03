public class Sala {

    private int id;
    private String nombre;

    public Sala(int id, String nombre) {
        this.id = id;
        setNombre(nombre);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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