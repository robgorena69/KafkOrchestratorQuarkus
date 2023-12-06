package bo.gob.ruat.items.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class DebitarItemsEntity {
   
    private Long numSec;
    private String idPeticion;
    private Long nsecMaquinaEstado;
    private List<ItemEntity> lstEntity = new ArrayList<>();

    public String getIdPeticion() {
        return idPeticion;
    }
    public void setIdPeticion(String idPeticion) {
        this.idPeticion = idPeticion;
    }
    public List<ItemEntity> getLstEntity() {
        return lstEntity;
    }
    public void setLstEntity(List<ItemEntity> lstEntity) {
        this.lstEntity = lstEntity;
    }
    public Long getNumSec() {
        return numSec;
    }
    public void setNumSec(Long numSec) {
        this.numSec = numSec;
    }
    public Long getNsecMaquinaEstado() {
        return nsecMaquinaEstado;
    }
    public void setNsecMaquinaEstado(Long nsecMaquinaEstado) {
        this.nsecMaquinaEstado = nsecMaquinaEstado;
    }

    
    
    
    
}
