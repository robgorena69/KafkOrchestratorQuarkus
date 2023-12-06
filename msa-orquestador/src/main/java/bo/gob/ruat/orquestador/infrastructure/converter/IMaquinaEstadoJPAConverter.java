package bo.gob.ruat.orquestador.infrastructure.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import bo.gob.ruat.orquestador.domain.entity.MaquinaEstadoEntity;
import bo.gob.ruat.orquestador.infrastructure.adapter.secondary.model.jpa.MaquinaEstadoJPA;

@Mapper(componentModel = "cdi")
public interface IMaquinaEstadoJPAConverter {
    
    @Mapping(target = "idPeticion", source = "maquinaEstadoEntity.idPeticion")
	@Mapping(target = "idObjetoPadre", source = "maquinaEstadoEntity.idObjetoPadre")
	@Mapping(target = "topicPrincipal", source = "maquinaEstadoEntity.topicPrincipal") 
    @Mapping(target = "idRespuesta", source = "maquinaEstadoEntity.idRespuesta") 
    @Mapping(target = "topicCompensacion", source = "maquinaEstadoEntity.topicCompensacion") 
    @Mapping(target = "estadoPeticion", source = "maquinaEstadoEntity.estadoPeticion") 
    @Mapping(target = "topicDependiente", source = "maquinaEstadoEntity.topicDependiente") 
    @Mapping(target = "estado", source = "maquinaEstadoEntity.estado")    
    MaquinaEstadoJPA toMaquinaEstadoJPA(MaquinaEstadoEntity maquinaEstadoEntity);


}
