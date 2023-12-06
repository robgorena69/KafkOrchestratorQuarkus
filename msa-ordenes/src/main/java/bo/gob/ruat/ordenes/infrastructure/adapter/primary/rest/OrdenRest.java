package bo.gob.ruat.ordenes.infrastructure.adapter.primary.rest;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import bo.gob.ruat.ordenes.application.port.primary.IOrden;
import bo.gob.ruat.ordenes.domain.entity.OrdenEntity;
import bo.gob.ruat.ordenes.infrastructure.adapter.primary.rest.request.OrdenRequest;
import bo.gob.ruat.ordenes.infrastructure.converter.IOrdenConverter;

@Path("/prueba-saga/orden")
public class OrdenRest {

    @Inject
    IOrdenConverter ordenConverter;

    @Inject
    IOrden orden;

    @POST
    @Transactional(value = Transactional.TxType.REQUIRES_NEW, rollbackOn = {Exception.class})
	public Response realizarPedido(OrdenRequest  pRequest) {
        OrdenEntity resultado = ordenConverter.toOrdenEntity(pRequest);
        orden.realizaPedido(resultado);
        Response vResponseRest = Response.ok("Se envio el mensaje").build();
        return vResponseRest;
    }

    
}
