package bo.gob.ruat.ordenes.application.port.primary;

import bo.gob.ruat.ordenes.domain.entity.OrdenEntity;

public interface IOrden {
    
    void realizaPedido(OrdenEntity ordenEntity);

    void cancelaPedido(Long idOrden);

    
}
