package bo.gob.ruat.items.infrastructure.adapter.primary.kafka;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import com.fasterxml.jackson.databind.ObjectMapper;

import bo.gob.ruat.items.application.port.primary.IPedidoUseCase;
import bo.gob.ruat.items.domain.entity.CompensacionEntity;
import bo.gob.ruat.items.domain.entity.EnvioItemsEntity;

public class MensajesKafka {

    @Inject
    IPedidoUseCase iPedidoUseCase;

    @Incoming("solicita-preparar-items")
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = {Exception.class})
    public void topicPreparaItems(String mensaje) throws Exception{
      System.out.println("solicita preparar items");
      ObjectMapper objectMapper = new ObjectMapper(); 
      EnvioItemsEntity envioItemsPeticion = new EnvioItemsEntity();
      envioItemsPeticion = objectMapper.readValue(mensaje, EnvioItemsEntity.class);
      iPedidoUseCase.registraPedido(envioItemsPeticion);
   }

   @Incoming("cancela-preparar-items")
   @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = {Exception.class})
   public void topicCancelaItems(String mensaje) throws Exception{
     ObjectMapper objectMapper = new ObjectMapper(); 
     CompensacionEntity cancelaItemsEntity = new CompensacionEntity();
     cancelaItemsEntity = objectMapper.readValue(mensaje, CompensacionEntity.class);
     iPedidoUseCase.cancelaPedido(cancelaItemsEntity);
  }
    
}
