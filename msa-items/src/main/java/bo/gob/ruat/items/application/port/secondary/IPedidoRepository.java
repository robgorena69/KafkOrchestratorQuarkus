package bo.gob.ruat.items.application.port.secondary;

import bo.gob.ruat.items.domain.entity.EnvioItemsEntity;

public interface IPedidoRepository {
    void registraPedido(EnvioItemsEntity envioItemsEntity) throws Exception;

    void anulaPedido(Long idPedido);
}
