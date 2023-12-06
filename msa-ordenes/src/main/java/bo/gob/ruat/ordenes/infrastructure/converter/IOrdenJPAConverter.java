package bo.gob.ruat.ordenes.infrastructure.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import bo.gob.ruat.ordenes.domain.entity.OrdenEntity;
import bo.gob.ruat.ordenes.infrastructure.adapter.secondary.model.jpa.OrdenJPA;

@Mapper(componentModel = "cdi")
public interface IOrdenJPAConverter {

   	@Mapping(target = "idcliente", source = "ordenEntity.idCliente")
	@Mapping(target = "fecha", source = "ordenEntity.fecha")
	@Mapping(target = "monto", source = "ordenEntity.monto")
    @Mapping(target = "estado", constant = "AC")   
    OrdenJPA tOrdenJPA(OrdenEntity ordenEntity);
}

