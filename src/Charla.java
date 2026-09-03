public class Charla extends Actividad {

    private String disertante;

    public Charla(
            int id,
            String titulo,
            String disertante,
            int cupo
    ) {

        super(id, titulo, cupo);
        setDisertante(disertante);
    }

    public String getDisertante() {
        return disertante;
    }

    public void setDisertante(String disertante) {

        if (disertante != null && !disertante.isBlank()) {
            this.disertante = disertante;
        }
    }

    @Override
    public double calcularCostoMateriales() {
        return 0.0;
    }

    @Override
    public String getTipo() {
        return "Charla";
    }
}