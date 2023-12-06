package bo.gob.ruat.orquestador.infrastructure.adapter.primary.kafka;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.eclipse.microprofile.reactive.messaging.Incoming;

import com.fasterxml.jackson.databind.ObjectMapper;

import bo.gob.ruat.orquestador.application.port.primary.IOrquestadorUseCase;
import bo.gob.ruat.orquestador.domain.entity.EnvioItemsEntity;
import bo.gob.ruat.orquestador.domain.entity.EnvioItemsListadoEntity;
import bo.gob.ruat.orquestador.domain.entity.RespuestaPeticion;

@RequestScoped
public class MensajesKafka {

   @Inject
   IOrquestadorUseCase orquestadorUseCase;
   

   @Incoming("crea-orden")
   @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = {Exception.class})
   public void topicCambiarClave(String mensaje) throws Exception{
	   ObjectMapper objectMapper = new ObjectMapper(); 
      EnvioItemsListadoEntity orquestadorEntity;
      orquestadorEntity = objectMapper.readValue(mensaje, EnvioItemsListadoEntity.class);
      orquestadorUseCase.direccionaOrdenes(orquestadorEntity); 
   }

   @Incoming("crea-factura")
   @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = {Exception.class})
    public void topicCreaFactura(String mensaje) throws Exception{
      
     System.out.println("llego el topic crea-factura con el siguiente mensaje :");
     System.out.println(mensaje); 
     orquestadorUseCase.direccionaFacturacion(mensaje);
  }

  @Incoming("confirma-operacion")
  @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = {Exception.class})
   public void confirmaOperacion(String mensaje) throws Exception{
      System.out.println("En el Topic confirma-operacion");
      ObjectMapper objectMapper = new ObjectMapper(); 
      RespuestaPeticion respuestaPeticion;
      respuestaPeticion = objectMapper.readValue(mensaje, RespuestaPeticion.class);
      orquestadorUseCase.confirmaOperacion(respuestaPeticion);  
 }


 @Incoming("debitar-items")
 @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = {Exception.class})
  public void debitarItemsInventario(String mensaje) throws Exception{
     System.out.println("En el Topic debitarItems");
     ObjectMapper objectMapper = new ObjectMapper(); 
     EnvioItemsEntity envioItemsEntity;
     envioItemsEntity = objectMapper.readValue(mensaje, EnvioItemsEntity.class);
     orquestadorUseCase.direccionaDebitarItems(envioItemsEntity); 
 }
  
}
