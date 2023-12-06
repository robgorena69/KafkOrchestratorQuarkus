package bo.gob.ruat.orquestador.infrastructure.adapter.secondary.kafka;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import bo.gob.ruat.orquestador.application.port.secondary.IMensaje;
import bo.gob.ruat.orquestador.domain.entity.CompensacionEntity;
import bo.gob.ruat.orquestador.domain.entity.MaquinaEstadoEntity;

@RequestScoped
public class EnviaMensaje implements IMensaje{


   ObjectMapper objectMapper = new ObjectMapper();

   
    @Inject
    @Channel("solicita-preparar-items") // Nombre de la corriente de salida definida en la clase KafkaProducer
    Emitter<String> emitterItems;

    @Inject
    @Channel("solicita-generar-facturacion") // Nombre de la corriente de salida definida en la clase KafkaProducer
    Emitter<String> emitterFacturacion;

    @Inject
    @Channel("debitar-items-inventario") // Nombre de la corriente de salida definida en la clase KafkaProducer
    Emitter<String> emitterDebitarItems;

    //compensaciones
    @Inject
    @Channel("cancelar-debitar-inventario") // Nombre de la corriente de salida definida en la clase KafkaProducer
    Emitter<String> emitterCancelarDebitarInventario;

    @Inject
    @Channel("cancela-orden") // Nombre de la corriente de salida definida en la clase KafkaProducer
    Emitter<String> emitterCancelaOrden;

    @Inject
    @Channel("cancela-preparar-items") // Nombre de la corriente de salida definida en la clase KafkaProducer
    Emitter<String> emitterCancelarPreparaItem;

    @Override
    public void enviaItems(String mensaje) {
       emitterItems.send(mensaje); // Enviar el mensaje utilizando el emisor  
    }

    @Override
    public void enviaFacturacion(String mensaje) {
       emitterFacturacion.send("Se envio a Facturación la solicitud :" + mensaje);
    }

    @Override
    public void enviaDebitarItems(String mensaje) {
      System.out.println("se envio a debitar items");
      emitterDebitarItems.send(mensaje);
    }

   @Override
   public void enviaCompensacion(MaquinaEstadoEntity maquinaEstadoEntity) {
        String mensajeRespuesta = "";
        CompensacionEntity compensacionEntity = new CompensacionEntity();
        compensacionEntity.setIdPeticion(maquinaEstadoEntity.getIdPeticion());
        compensacionEntity.setRespuesta(maquinaEstadoEntity.getIdRespuesta());
        System.out.println("dentro de envia compensacion");
        System.out.println(maquinaEstadoEntity.getTopicCompensacion());
        
        System.out.println(mensajeRespuesta);
        try {
         mensajeRespuesta = objectMapper.writeValueAsString(compensacionEntity);
        } catch (JsonProcessingException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
       }

        switch  (maquinaEstadoEntity.getTopicCompensacion()){ 
          case "cancelar-debitar-inventario":
            emitterCancelarDebitarInventario.send(mensajeRespuesta);
            break;
          case "cancela-orden":
            emitterCancelaOrden.send(mensajeRespuesta);
            break;
          case "cancela-preparar-items":
             emitterCancelarPreparaItem.send(mensajeRespuesta);
            break;    
      }
  }
} 
