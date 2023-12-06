package bo.gob.ruat.ordenes.infrastructure.adapter.secondary.kafka;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import bo.gob.ruat.ordenes.application.port.secondary.IEnvioMensaje;

@RequestScoped
public class MensajeriaKafkaEmitter implements IEnvioMensaje{

    @Inject
    @Channel("crea-orden") // Nombre de la corriente de salida definida en la clase KafkaProducer
    Emitter<String> emitterOrden;


    @Inject
    @Channel("crea-factura") // Nombre de la corriente de salida definida en la clase KafkaProducer
    Emitter<String> emitterFactura;

    @Override
    public void enviaCrearOrden(String mensaje) {
        emitterOrden.send(mensaje); // Enviar el mensaje utilizando el emisor
    }

    @Override
    public void enviaCreaFactura(String mensaje) {
        emitterFactura.send(mensaje); // Enviar el mensaje utilizando el emisor
    }   
    
    
}
