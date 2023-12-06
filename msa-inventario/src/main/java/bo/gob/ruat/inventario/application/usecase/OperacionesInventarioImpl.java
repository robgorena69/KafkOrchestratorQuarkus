package bo.gob.ruat.inventario.application.usecase;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import bo.gob.ruat.inventario.application.port.primary.IOperacionesInventario;
import bo.gob.ruat.inventario.application.port.secondary.IEnvioMensajes;
import bo.gob.ruat.inventario.application.port.secondary.IInventarioRepository;
import bo.gob.ruat.inventario.domain.entity.EnviotemsEntity;
import bo.gob.ruat.inventario.domain.entity.ItemEntity;
import bo.gob.ruat.inventario.domain.entity.RespuestaPeticionEntity;

@RequestScoped
public class OperacionesInventarioImpl implements IOperacionesInventario {

    @Inject
    IInventarioRepository inventarioRepository;

    @Inject
    IEnvioMensajes envioMensajes;

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void debitaItems(EnviotemsEntity debitarItemsEntity) throws Exception {
       boolean fallo = false;
       RespuestaPeticionEntity respuestaPeticion = new RespuestaPeticionEntity();  
       respuestaPeticion.setId(debitarItemsEntity.getNsecMaquinaEstado());
       respuestaPeticion.setIdPeticion(debitarItemsEntity.getIdPeticion());
        for (ItemEntity item : debitarItemsEntity.getLstEntity()){ //verifica si existen items a
            if (!inventarioRepository.verificaExistenciaItem(item.getCodigo(), item.getCantidad())){
                fallo = true;
                break;
            } 
        }
        if (fallo){
                System.out.print("En Fallo");  
                respuestaPeticion.setTipoPeticion("FALLO");
                envioMensajes.envioOperacion(respuestaPeticion);
              //  throw new Exception("No existen items suficientes para el item ");
        } else {
            for (ItemEntity item : debitarItemsEntity.getLstEntity()){ //verifica si existen items a
                inventarioRepository.actualizarCantidadInventario(item.getCodigo(), item.getCantidad(), "disminuir");
            }
            try {
                String mensaje = objectMapper.writeValueAsString(debitarItemsEntity.getLstEntity());
                respuestaPeticion.setTipoPeticion("EXITO");
                respuestaPeticion.setRespuesta(mensaje);
    
                envioMensajes.envioOperacion(respuestaPeticion);
            } catch (JsonProcessingException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }
            
        }
    }

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void adicionarItems(EnviotemsEntity adicionarItemsEntity) throws Exception {
         for (ItemEntity item : adicionarItemsEntity.getLstEntity()){
           inventarioRepository.actualizarCantidadInventario(item.getCodigo(), item.getCantidad(), "adicionar");
           
        }
    }

    
}
