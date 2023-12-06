package bo.gob.ruat.orquestador.application.port.secondary;

import java.util.List;

import bo.gob.ruat.orquestador.domain.entity.MaquinaEstadoEntity;

public interface IMaquinaEstadoRepository {

    void registraMaquinaEstado(MaquinaEstadoEntity maquinaEstadoEntity);
    void actualizaMaquinaEstado(MaquinaEstadoEntity maquinaEstadoEntity);
    MaquinaEstadoEntity obtMaquinaEstadobyId(Long identificador);
    List<MaquinaEstadoEntity> lstMaquinaEstadoByIdPeticion(String idPeticion);
}
