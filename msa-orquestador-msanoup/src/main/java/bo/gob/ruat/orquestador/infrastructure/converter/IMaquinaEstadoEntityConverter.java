package bo.gob.ruat.orquestador.infrastructure.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import bo.gob.ruat.orquestador.domain.entity.MaquinaEstadoEntity;
import bo.gob.ruat.orquestador.infrastructure.adapter.secondary.model.jpa.MaquinaEstadoJPA;

@Mapper(componentModel = "cdi")
public interface IMaquinaEstadoEntityConverter {
    
    @Mapping(target = "idPeticion", source = "maquinaEstadoJPA.idPeticion")
	@Mapping(target = "idObjetoPadre", source = "maquinaEstadoJPA.idObjetoPadre")
	@Mapping(target = "topicPrincipal", source = "maquinaEstadoJPA.topicPrincipal") 
    @Mapping(target = "idRespuesta", source = "maquinaEstadoJPA.idRespuesta") 
    @Mapping(target = "topicCompensacion", source = "maquinaEstadoJPA.topicCompensacion") 
    @Mapping(target = "estadoPeticion", source = "maquinaEstadoJPA.estadoPeticion") 
    @Mapping(target = "topicDependiente", source = "maquinaEstadoJPA.topicDependiente") 
    @Mapping(target = "estado", source = "maquinaEstadoJPA.estado") 
    MaquinaEstadoEntity toMaquinaEstadoEntity(MaquinaEstadoJPA maquinaEstadoJPA);
}
