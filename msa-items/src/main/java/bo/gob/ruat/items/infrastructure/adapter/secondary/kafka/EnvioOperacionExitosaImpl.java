package bo.gob.ruat.items.infrastructure.adapter.secondary.kafka;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import bo.gob.ruat.items.application.port.secondary.IEnvioMensaje;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@RequestScoped
public class EnvioOperacionExitosaImpl implements IEnvioMensaje{

    @Inject
    @Channel("confirma-operacion") // Nombre de la corriente de salida definida en la clase KafkaProducer
    Emitter<String> emitterOperacionExitosa;

    @Inject
    @Channel("debitar-items") // Nombre de la corriente de salida definida en la clase KafkaProducer
    Emitter<String> emitterDebitarItems;
    
    @Override
    public void envioOperacionExitosa(String mensaje) {
        System.out.println("operacion exitosa");
        emitterOperacionExitosa.send(mensaje);
    }
  
    @Override
    public void envioDebitarItems(String mensaje) {
        System.out.println("debitar items");
        emitterDebitarItems.send(mensaje);
    }
    
}
