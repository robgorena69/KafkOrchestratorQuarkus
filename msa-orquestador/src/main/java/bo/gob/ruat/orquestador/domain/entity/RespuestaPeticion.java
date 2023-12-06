package bo.gob.ruat.orquestador.domain.entity;

public class RespuestaPeticion {
    
    private Long id;
    private String respuesta;
    private String tipoPeticion;
    private String idPeticion;
    
    public String getRespuesta() {
        return respuesta;
    }
    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }
    public String getTipoPeticion() {
        return tipoPeticion;
    }
    public void setTipoPeticion(String tipoPeticion) {
        this.tipoPeticion = tipoPeticion;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getIdPeticion() {
        return idPeticion;
    }
    
}


