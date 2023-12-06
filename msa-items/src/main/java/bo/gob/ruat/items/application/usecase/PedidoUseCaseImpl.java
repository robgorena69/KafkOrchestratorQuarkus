package bo.gob.ruat.items.application.usecase;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bo.gob.ruat.items.application.port.primary.IPedidoUseCase;
import bo.gob.ruat.items.application.port.secondary.IEnvioMensaje;
import bo.gob.ruat.items.application.port.secondary.IPedidoRepository;
import bo.gob.ruat.items.domain.entity.CompensacionEntity;
import bo.gob.ruat.items.domain.entity.DebitarItemsEntity;
import bo.gob.ruat.items.domain.entity.EnvioItemsEntity;
import bo.gob.ruat.items.domain.entity.RespuestaPeticion;

@RequestScoped
public class PedidoUseCaseImpl  implements IPedidoUseCase{

    @Inject
    IPedidoRepository iPedidoRepository;

    @Inject
    IEnvioMensaje iEnvioMensaje;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void registraPedido(EnvioItemsEntity envioItemsEntity) {
        RespuestaPeticion respuestaPeticion = new RespuestaPeticion();
        String mensajePeticion = "";
        try {
        iPedidoRepository.registraPedido(envioItemsEntity);
        respuestaPeticion.setId(envioItemsEntity.getNsecMaquinaEstado());
        respuestaPeticion.setRespuesta(envioItemsEntity.getNumSec().toString());
        respuestaPeticion.setTipoPeticion("EXITO");
   
            mensajePeticion = objectMapper.writeValueAsString(respuestaPeticion);
            iEnvioMensaje.envioOperacionExitosa(mensajePeticion);

            // Solicita debitar los items del inventario
            DebitarItemsEntity debitarItemsEntity = new DebitarItemsEntity();
            debitarItemsEntity.setIdPeticion(envioItemsEntity.getIdPeticion());
            debitarItemsEntity.setLstEntity(envioItemsEntity.getLstEntity());
            mensajePeticion = objectMapper.writeValueAsString(debitarItemsEntity);    
            iEnvioMensaje.envioDebitarItems(mensajePeticion); 
            System.out.println("se envio a debitar items del inventario");    
            System.out.println(mensajePeticion);

        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e){
            System.out.print("Dentro la solicitud de fallo");
            respuestaPeticion.setId(envioItemsEntity.getNsecMaquinaEstado());
            respuestaPeticion.setRespuesta(envioItemsEntity.getNumSec().toString());
            respuestaPeticion.setIdPeticion(envioItemsEntity.getIdPeticion());
            respuestaPeticion.setTipoPeticion("FALLO");
            try{
                 mensajePeticion = objectMapper.writeValueAsString(respuestaPeticion);
            }catch (JsonProcessingException e2) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            iEnvioMensaje.envioOperacionExitosa(mensajePeticion);
        }   
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
    }

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void cancelaPedido(CompensacionEntity compensacionEntity) {
        iPedidoRepository.anulaPedido(Long.parseLong(compensacionEntity.getRespuesta()));
    }

    
}
