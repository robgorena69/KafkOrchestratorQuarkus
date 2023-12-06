package bo.gob.ruat.ordenes.infrastructure.adapter.secondary.repository.jpa;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import bo.gob.ruat.ordenes.application.port.secondary.IOrdenRepository;
import bo.gob.ruat.ordenes.domain.entity.OrdenEntity;
import bo.gob.ruat.ordenes.infrastructure.adapter.secondary.model.jpa.OrdenJPA;
import bo.gob.ruat.ordenes.infrastructure.converter.IOrdenConverter;
import bo.gob.ruat.ordenes.infrastructure.converter.IOrdenJPAConverter;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@RequestScoped
public class OrdenRepositoryImpl  implements IOrdenRepository,PanacheRepository<OrdenJPA>{

    @Inject
    IOrdenJPAConverter ordenJPAConverter;

    @Inject
    IOrdenConverter ordenConverter;

    @Override
	@Transactional(Transactional.TxType.MANDATORY)
    public void registraOrden(OrdenEntity ordenEntity) {

        OrdenJPA ordenJPA = new OrdenJPA();
        ordenJPA.setEstado("AC");
        ordenJPA.setFecha(ordenEntity.getFecha().atStartOfDay());
        ordenJPA.setIdcliente(ordenEntity.getIdCliente());
        ordenJPA.setMonto(ordenEntity.getMonto().longValue());
        //ordenJPAConverter.tOrdenJPA(ordenEntity);
        persist(ordenJPA);
        ordenEntity.setNumSec(ordenJPA.getNumSec());
    }

    @Override
    @Transactional(Transactional.TxType.MANDATORY)
    public void actualizaOrden(OrdenEntity ordenEntity) {
    
        OrdenJPA ordenJPA = findById(ordenEntity.getNumSec());
        ordenJPA.setEstado(ordenEntity.getEstado());
        persistAndFlush(ordenJPA);
        System.out.println("se actualizo la orden");
    }

}
