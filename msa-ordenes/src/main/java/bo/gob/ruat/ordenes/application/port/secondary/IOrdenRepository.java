package bo.gob.ruat.ordenes.application.port.secondary;

import bo.gob.ruat.ordenes.domain.entity.OrdenEntity;

public interface IOrdenRepository {

    void registraOrden(OrdenEntity ordenEntity);

    void actualizaOrden(OrdenEntity ordenEntity);

    

    //OrdenEntity obtOrdenById(Long id);
    
}
