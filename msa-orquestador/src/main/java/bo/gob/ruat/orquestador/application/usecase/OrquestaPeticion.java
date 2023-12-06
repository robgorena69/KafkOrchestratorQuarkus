package bo.gob.ruat.orquestador.application.usecase;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import bo.gob.ruat.orquestador.application.port.primary.IOrquestadorUseCase;
import bo.gob.ruat.orquestador.application.port.secondary.IMaquinaEstadoRepository;
import bo.gob.ruat.orquestador.application.port.secondary.IMensaje;
import bo.gob.ruat.orquestador.domain.constant.EstadoPeticion;
import bo.gob.ruat.orquestador.domain.entity.EnvioItemsEntity;
import bo.gob.ruat.orquestador.domain.entity.EnvioItemsListadoEntity;
import bo.gob.ruat.orquestador.domain.entity.MaquinaEstadoEntity;
import bo.gob.ruat.orquestador.domain.entity.RespuestaPeticion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestScoped
public class OrquestaPeticion implements IOrquestadorUseCase{


    ObjectMapper objectMapper = new ObjectMapper();


    @Inject
    IMensaje iMensaje;

    @Inject 
    IMaquinaEstadoRepository iMaquinaEstadoRepository;

    
 
    @Override
    public void direccionaFacturacion(String peticion) {
        String resultado[]= peticion.split(":");
        iMensaje.enviaFacturacion(resultado[1]);  
    }



    @Override
    @Transactional(value = TxType.MANDATORY)
    public void direccionaOrdenes(EnvioItemsListadoEntity envioItems) {
        String mensajePeticion = "";
        MaquinaEstadoEntity maquinaEstadoEntity = new MaquinaEstadoEntity();
        maquinaEstadoEntity.setEstadoPeticion(EstadoPeticion.CONCLUIDO.getDescripcion());
        maquinaEstadoEntity.setEstado("AC");
        maquinaEstadoEntity.setIdObjetoPadre(envioItems.getDatosPeticionEntity().getIdentificadorPadre());
        maquinaEstadoEntity.setIdPeticion(envioItems.getDatosPeticionEntity().getIdentificador());
        maquinaEstadoEntity.setTopicCompensacion(envioItems.getDatosPeticionEntity().getTopicCompensacion());
        maquinaEstadoEntity.setTopicDependiente(envioItems.getDatosPeticionEntity().getTopicDestino());
        maquinaEstadoEntity.setIdRespuesta(envioItems.getDatosPeticionEntity().getIdentificadorPadre());
        maquinaEstadoEntity.setTopicPrincipal(envioItems.getDatosPeticionEntity().getTopicOrigen());
        iMaquinaEstadoRepository.registraMaquinaEstado(maquinaEstadoEntity);

        //Se registra la maquina de estado para realizar la petición

        maquinaEstadoEntity = new MaquinaEstadoEntity();
        maquinaEstadoEntity.setEstadoPeticion(EstadoPeticion.ENVIADO.getDescripcion());
        maquinaEstadoEntity.setEstado("AC");
        maquinaEstadoEntity.setIdPeticion(envioItems.getDatosPeticionEntity().getIdentificador());
        maquinaEstadoEntity.setTopicCompensacion("cancela-preparar-items");
        maquinaEstadoEntity.setTopicDependiente("solicita-preparar-items");
        maquinaEstadoEntity.setTopicPrincipal("crea-orden");
        iMaquinaEstadoRepository.registraMaquinaEstado(maquinaEstadoEntity);


        EnvioItemsEntity envioItemsPeticion = new EnvioItemsEntity();
        envioItemsPeticion.setIdPeticion(envioItems.getDatosPeticionEntity().getIdentificador());
        envioItemsPeticion.setNsecMaquinaEstado(maquinaEstadoEntity.getNumSec());
        envioItemsPeticion.setLstEntity(envioItems.getLstProductos());

        try {
            mensajePeticion = objectMapper.writeValueAsString(envioItemsPeticion);
            iMensaje.enviaItems(mensajePeticion);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }



    @Override
    public void confirmaOperacion(RespuestaPeticion respuestaPeticion) {
        System.out.println("Dentro de confirma operacion");
        MaquinaEstadoEntity maquinaEstadoEntity = iMaquinaEstadoRepository.obtMaquinaEstadobyId(respuestaPeticion.getId());
        maquinaEstadoEntity.setIdRespuesta(respuestaPeticion.getRespuesta());
        switch (respuestaPeticion.getTipoPeticion()){
            case "EXITO":
               maquinaEstadoEntity.setEstadoPeticion("TE");
               iMaquinaEstadoRepository.actualizaMaquinaEstado(maquinaEstadoEntity);
            break;
            case "FALLO":
                List<MaquinaEstadoEntity> lstMaquinaEstadoEntigy = this.iMaquinaEstadoRepository.lstMaquinaEstadoByIdPeticion(respuestaPeticion.getIdPeticion());
                lstMaquinaEstadoEntigy.stream().forEach(maquinaEstado->{      
                    iMensaje.enviaCompensacion(maquinaEstado);
                    maquinaEstado.setEstadoPeticion("CA");
                    iMaquinaEstadoRepository.actualizaMaquinaEstado(maquinaEstado);
                });
                maquinaEstadoEntity.setEstadoPeticion("CA");
                iMaquinaEstadoRepository.actualizaMaquinaEstado(maquinaEstadoEntity);
                break;
            default:
            break;
        }
    }

    @Override
    public void direccionaDebitarItems(EnvioItemsEntity envioItemsEntity) {

        String mensajePeticion = "";
        MaquinaEstadoEntity maquinaEstadoEntity = new MaquinaEstadoEntity();
        maquinaEstadoEntity.setEstadoPeticion(EstadoPeticion.ENVIADO.getDescripcion());
        maquinaEstadoEntity.setEstado("AC");
        //maquinaEstadoEntity.setIdObjetoPadre(envioItemsEntity.getDatosPeticionEntity().getIdentificadorPadre());
        maquinaEstadoEntity.setIdPeticion(envioItemsEntity.getIdPeticion());
        maquinaEstadoEntity.setTopicCompensacion("cancelar-debitar-inventario");
        maquinaEstadoEntity.setTopicPrincipal("debitar-items-inventario");
        maquinaEstadoEntity.setTopicDependiente("debitar-items-inventario");


        iMaquinaEstadoRepository.registraMaquinaEstado(maquinaEstadoEntity);

        envioItemsEntity.setNsecMaquinaEstado(maquinaEstadoEntity.getNumSec());

        try {
            mensajePeticion = objectMapper.writeValueAsString(envioItemsEntity);
            iMensaje.enviaDebitarItems(mensajePeticion);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }  
    
}
