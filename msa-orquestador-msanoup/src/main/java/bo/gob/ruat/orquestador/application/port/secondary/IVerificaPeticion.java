package bo.gob.ruat.orquestador.application.port.secondary;

import bo.gob.ruat.orquestador.domain.entity.MaquinaEstadoEntity;

public interface IVerificaPeticion {
    void verificaPeticionAtendia(MaquinaEstadoEntity maquinaEstadoEntity);
}
