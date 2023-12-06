package bo.gob.ruat.inventario.infrastructure.adapter.primary.kafka;

import javax.inject.Inject;
import javax.transaction.*;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import com.fasterxml.jackson.databind.ObjectMapper;

import bo.gob.ruat.inventario.application.port.primary.IOperacionesInventario;
import bo.gob.ruat.inventario.domain.entity.EnviotemsEntity;
import io.smallrye.common.annotation.Blocking;

public class Mensaje {

   @Inject
   IOperacionesInventario operacionesInventario;

    @Incoming("debitar-items-inventario")
    @Blocking
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = {Exception.class})
     public void topicCDebitarInventario(String mensaje) throws Exception{

    

        System.out.println("En el Topic debitar-inventario");
        ObjectMapper objectMapper = new ObjectMapper(); 
        EnviotemsEntity respuestaPeticion;
        respuestaPeticion = objectMapper.readValue(mensaje, EnviotemsEntity.class);
        operacionesInventario.debitaItems(respuestaPeticion);
     }
    

   @Incoming("cancelar-debitar-inventario")
   @Blocking
   @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = {Exception.class})
    public void topicCancelarInventario(String mensaje) throws Exception{
       System.out.println("En el Topic cancelar-debitar-inventario");
       ObjectMapper objectMapper = new ObjectMapper(); 
       EnviotemsEntity respuestaPeticion;
       respuestaPeticion = objectMapper.readValue(mensaje, EnviotemsEntity.class);
       operacionesInventario.adicionarItems(respuestaPeticion);
    }
    
}
