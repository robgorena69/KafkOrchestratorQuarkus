package bo.gob.ruat.orquestador.application.port.primary;

import bo.gob.ruat.orquestador.domain.entity.EnvioItemsEntity;
import bo.gob.ruat.orquestador.domain.entity.EnvioItemsListadoEntity;
import bo.gob.ruat.orquestador.domain.entity.RespuestaPeticion;

public interface IOrquestadorUseCase {
    
    void direccionaFacturacion (String peticion);

    void direccionaOrdenes(EnvioItemsListadoEntity envioItems);

    void confirmaOperacion(RespuestaPeticion respuestaPeticion);

    void direccionaDebitarItems(EnvioItemsEntity envioItemsEntity);
}
