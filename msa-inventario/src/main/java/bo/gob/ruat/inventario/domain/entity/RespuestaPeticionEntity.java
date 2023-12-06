package bo.gob.ruat.inventario.domain.entity;

public class RespuestaPeticionEntity {
    
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
   
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTipoPeticion() {
        return tipoPeticion;
    }
    public void setTipoPeticion(String tipoPeticion) {
        this.tipoPeticion = tipoPeticion;
    }
    public String getIdPeticion() {
        return idPeticion;
    }
    public void setIdPeticion(String idPeticion) {
        this.idPeticion = idPeticion;
    }
    
}


