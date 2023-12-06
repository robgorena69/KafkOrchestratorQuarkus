package bo.gob.ruat.orquestador.domain.entity;

import java.util.List;

public class EnvioItemsEntity {
    
    List<ItemEntity> lstEntity;

    private Long numSec;
    private String idPeticion;
    private Long nsecMaquinaEstado;
    private String topicPrincipal;

    public Long getNsecMaquinaEstado() {
        return nsecMaquinaEstado;
    }
    public void setNsecMaquinaEstado(Long nsecMaquinaEstado) {
        this.nsecMaquinaEstado = nsecMaquinaEstado;
    }
    public String getIdPeticion() {
        return idPeticion;
    }
    public void setIdPeticion(String idPeticion) {
        this.idPeticion = idPeticion;
    }
    public Long getNumSec() {
        return numSec;
    }
    public void setNumSec(Long numSec) {
        this.numSec = numSec;
    }
    public List<ItemEntity> getLstEntity() {
        return lstEntity;
    }
    public void setLstEntity(List<ItemEntity> lstEntity) {
        this.lstEntity = lstEntity;
    }
    public String getTopicPrincipal() {
        return topicPrincipal;
    }
    public void setTopicPrincipal(String topicPrincipal) {
        this.topicPrincipal = topicPrincipal;
    }
    
}


