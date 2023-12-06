package bo.gob.ruat.inventario.application.port.secondary;

import bo.gob.ruat.inventario.domain.entity.RespuestaPeticionEntity;

public interface IEnvioMensajes {

    void envioOperacion(RespuestaPeticionEntity respuestaPeticionEntity);
    
}
