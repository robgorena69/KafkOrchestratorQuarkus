package bo.gob.ruat.items.application.port.primary;

import bo.gob.ruat.items.domain.entity.CancelaItemsEntity;
import bo.gob.ruat.items.domain.entity.CompensacionEntity;
import bo.gob.ruat.items.domain.entity.EnvioItemsEntity;

public interface IPedidoUseCase {
    
    void registraPedido(EnvioItemsEntity envioItemsEntity);

    void cancelaPedido(CompensacionEntity compensacionEntity);
}
