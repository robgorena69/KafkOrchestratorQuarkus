package bo.gob.ruat.inventario.infrastructure.adapter.secondary.kafka;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bo.gob.ruat.inventario.application.port.secondary.IEnvioMensajes;
import bo.gob.ruat.inventario.domain.entity.RespuestaPeticionEntity;

@RequestScoped
public class EnvioMensajesImpl implements IEnvioMensajes{

    ObjectMapper objectMapper = new ObjectMapper();

    @Inject
    @Channel("confirma-operacion") // Nombre de la corriente de salida definida en la clase KafkaProducer
    Emitter<String> emitterOperacion;

    @Override
    public void envioOperacion(RespuestaPeticionEntity respuestaPeticionEntity) { 
        System.out.println("envio operacion");   
        String mensaje = "";
         try {
              mensaje = objectMapper.writeValueAsString(respuestaPeticionEntity);
              System.out.println("En Inventario envio a confirmar la operacion");
              System.out.println(mensaje);
            
          } catch (JsonProcessingException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
        emitterOperacion.send(mensaje);
    }
    
}
