package bo.gob.ruat.orquestador.domain.entity;

public class MaquinaEstadoEntity {
    
    private Long numSec;
    private String idPeticion;
    private String idObjetoPadre;
    private String topicPrincipal;
    private String idRespuesta;
    private String topicDependiente;
    private String topicCompensacion;
    private String estadoPeticion;
    private String estado;  

    public String getIdObjetoPadre() {
        return idObjetoPadre;
    }
    public void setIdObjetoPadre(String idObjetoPadre) {
        this.idObjetoPadre = idObjetoPadre;
    }
    public String getTopicPrincipal() {
        return topicPrincipal;
    }
    public void setTopicPrincipal(String topicPrincipal) {
        this.topicPrincipal = topicPrincipal;
    }
    public String getIdRespuesta() {
        return idRespuesta;
    }
    public void setIdRespuesta(String idRespuesta) {
        this.idRespuesta = idRespuesta;
    }
    public String getTopicDependiente() {
        return topicDependiente;
    }
    public void setTopicDependiente(String topicDependiente) {
        this.topicDependiente = topicDependiente;
    }
    public String getTopicCompensacion() {
        return topicCompensacion;
    }
    public void setTopicCompensacion(String topicCompensacion) {
        this.topicCompensacion = topicCompensacion;
    }
    public String getEstadoPeticion() {
        return estadoPeticion;
    }
    public void setEstadoPeticion(String estadoPeticion) {
        this.estadoPeticion = estadoPeticion;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Long getNumSec() {
        return numSec;
    }
    public void setNumSec(Long numSec) {
        this.numSec = numSec;
    }
    public String getIdPeticion() {
        return idPeticion;
    }
    public void setIdPeticion(String idPeticion) {
        this.idPeticion = idPeticion;
    }

    
    
}
