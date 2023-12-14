package bo.gob.ruat.orquestador.application.port.secondary;

import bo.gob.ruat.orquestador.domain.entity.MaquinaEstadoEntity;

public interface IMensaje {

    void enviaItems(String mensaje);
    
    void enviaFacturacion(String mensaje);

    void enviaDebitarItems(String mensaje);

    void enviaCompensacion(MaquinaEstadoEntity maquinaEstadoEntity);
}
