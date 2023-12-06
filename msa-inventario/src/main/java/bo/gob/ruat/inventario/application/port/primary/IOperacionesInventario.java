package bo.gob.ruat.inventario.application.port.primary;

import bo.gob.ruat.inventario.domain.entity.EnviotemsEntity;

public interface IOperacionesInventario {

    void debitaItems(EnviotemsEntity debitarItemsEntity) throws Exception;

    void adicionarItems(EnviotemsEntity adicionarItemsEntity) throws Exception;
    
}
