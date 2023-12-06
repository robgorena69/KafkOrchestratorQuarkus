package bo.gob.ruat.ordenes.application.usecase;


import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bo.gob.ruat.ordenes.application.port.primary.IOrden;
import bo.gob.ruat.ordenes.application.port.secondary.IEnvioMensaje;
import bo.gob.ruat.ordenes.application.port.secondary.IOrdenRepository;
import bo.gob.ruat.ordenes.domain.entity.DatosPeticionEntity;
import bo.gob.ruat.ordenes.domain.entity.EnvioItemsOrquestadorEntity;
import bo.gob.ruat.ordenes.domain.entity.OrdenEntity;

@RequestScoped
public class OrdenUseCase implements IOrden{

    @Inject
    IEnvioMensaje envioMensaje;

    @Inject
    IOrdenRepository ordenRepository;



    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void realizaPedido(OrdenEntity ordenEntity) {
        ObjectMapper objectMapper = new ObjectMapper();
        String serializedEvent = "";
        ordenRepository.registraOrden(ordenEntity);

        DatosPeticionEntity datosPeticionEntity = new DatosPeticionEntity();
        EnvioItemsOrquestadorEntity envioItemsOrquestadorEntity = new EnvioItemsOrquestadorEntity();
        datosPeticionEntity.setIdentificadorPadre(ordenEntity.getNumSec().toString());
        datosPeticionEntity.setIdentificador("crea-orden-"+ordenEntity.getNumSec().toString());
        datosPeticionEntity.setTopicOrigen("crea-orden");
        datosPeticionEntity.setTopicDestino("solicita-preparar-items");
        datosPeticionEntity.setTopicCompensacion("cancela-orden");
    
        
        envioItemsOrquestadorEntity.setDatosPeticionEntity(datosPeticionEntity);
        envioItemsOrquestadorEntity.setLstProductos(ordenEntity.getLstProductos());
   
        System.out.println("Se creo la Orden:" + ordenEntity.getNumSec());
        try {
            serializedEvent = objectMapper.writeValueAsString(envioItemsOrquestadorEntity);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        envioMensaje.enviaCrearOrden(serializedEvent);     
    }



    @Override
    @Transactional(value = Transactional.TxType.MANDATORY)
    public void cancelaPedido(Long idOrden) {
        OrdenEntity ordenEntity = new OrdenEntity();
        ordenEntity.setNumSec(idOrden);
        ordenEntity.setEstado("AN");

        ordenRepository.actualizaOrden(ordenEntity);
    }  
    
}
