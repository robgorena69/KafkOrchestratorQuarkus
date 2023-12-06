package bo.gob.ruat.ordenes.infrastructure.adapter.primary.kafka;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import com.fasterxml.jackson.databind.ObjectMapper;

import bo.gob.ruat.ordenes.application.port.primary.IOrden;
import bo.gob.ruat.ordenes.domain.entity.CompensacionEntity;

public class MensajesRecepcion {
    
    @Inject
    IOrden iOrden;
    
    @Incoming("cancela-orden")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = {Exception.class})
    public void cancelarOrden(String mensaje) throws Exception{
        System.out.println("cancela orden");
        System.out.println(mensaje);
       ObjectMapper objectMapper = new ObjectMapper(); 
       CompensacionEntity compensacionEntity;
       compensacionEntity = objectMapper.readValue(mensaje, CompensacionEntity.class);
       iOrden.cancelaPedido(Long.parseLong(compensacionEntity.getRespuesta()));
    }
}
