package bo.gob.ruat.orquestador.domain.entity;

import java.util.List;

public class EnvioItemsListadoEntity {

    private DatosPeticionEntity datosPeticionEntity;

    private List<ItemEntity> lstProductos;
    
       public List<ItemEntity> getLstProductos() {
        return lstProductos;
    }
    public void setLstProductos(List<ItemEntity> lstProductos) {
        this.lstProductos = lstProductos;
    }
    public DatosPeticionEntity getDatosPeticionEntity() {
        return datosPeticionEntity;
    }
    public void setDatosPeticionEntity(DatosPeticionEntity datosPeticionEntity) {
        this.datosPeticionEntity = datosPeticionEntity;
    }

    
}
