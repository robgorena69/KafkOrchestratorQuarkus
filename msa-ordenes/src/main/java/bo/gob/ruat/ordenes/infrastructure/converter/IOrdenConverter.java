package bo.gob.ruat.ordenes.infrastructure.converter;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import bo.gob.ruat.ordenes.domain.entity.OrdenEntity;
import bo.gob.ruat.ordenes.infrastructure.adapter.primary.rest.request.OrdenRequest;


@Mapper(componentModel = "cdi")
public interface IOrdenConverter {
   
    @Mapping(target = "numSec", constant = "0L")
	@Mapping(target = "idCliente", source = "pOrdenRequest.idCliente")
	@Mapping(target = "fecha", source = "pOrdenRequest.fecha") 
    @Mapping(target = "monto", source = "pOrdenRequest.monto") 
    OrdenEntity toOrdenEntity(OrdenRequest  pOrdenRequest);
/*
    @Mapping(target = "numSec", source = "pJpa.numSec")
    @Mapping(target = "idCliente", source = "pJpa.idcliente")
	@Mapping(target = "fecha", source = "pJpa.fecha") 
    @Mapping(target = "monto", source = "pJpa.monto") 
    OrdenEntity tOrdenEntity(OrdenJPA pJpa);
*/
    
}
