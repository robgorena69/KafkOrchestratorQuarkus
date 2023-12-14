package bo.gob.ruat.orquestador.domain.constant;



public enum EstadoPeticion {

    ENVIADO("PE"),
    CONCLUIDO("TE"),
    ERROR("ER"),
    CANCELADO("CA");    
    
    private String descripcion;

    EstadoPeticion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
