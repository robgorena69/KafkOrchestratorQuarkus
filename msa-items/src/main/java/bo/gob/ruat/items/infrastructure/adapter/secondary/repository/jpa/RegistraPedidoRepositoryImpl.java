package bo.gob.ruat.items.infrastructure.adapter.secondary.repository.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import bo.gob.ruat.items.application.port.secondary.IPedidoRepository;
import bo.gob.ruat.items.domain.entity.EnvioItemsEntity;
import bo.gob.ruat.items.domain.entity.ItemEntity;
import bo.gob.ruat.items.infrastructure.adapter.secondary.model.jpa.DetallePedidoJPA;
import bo.gob.ruat.items.infrastructure.adapter.secondary.model.jpa.PedidoItemJPA;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@RequestScoped
public class RegistraPedidoRepositoryImpl implements IPedidoRepository, PanacheRepository<PedidoItemJPA>{

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void registraPedido(EnvioItemsEntity envioItemsEntity) throws Exception{

        PedidoItemJPA pedidoItemJPA = new PedidoItemJPA();
        pedidoItemJPA.setEstado("AC");
        pedidoItemJPA.setIdPedidoOrigen(envioItemsEntity.getIdPeticion());

        List<DetallePedidoJPA> lstDetalle = new ArrayList<>();
        for (ItemEntity item : envioItemsEntity.getLstEntity()){
            DetallePedidoJPA detalle = new DetallePedidoJPA();
            detalle.setCantidad(item.getCantidad());
            detalle.setEstado("AC");
            detalle.setIdItem(item.getCodigo());
            detalle.setIdPedido(pedidoItemJPA.getNumSec());
            detalle.setDetalleByNsecPedido(pedidoItemJPA);
            lstDetalle.add(detalle);
        }
        pedidoItemJPA.setDetalePedidoByNumSec(lstDetalle);
        //se genera un error randomico
    
        persist(pedidoItemJPA);
        envioItemsEntity.setNumSec(pedidoItemJPA.getNumSec());

        // Random rnd = new Random();
        // int valor = rnd.nextInt(1000);
        // System.out.println(valor);
        // if (valor > 10){
        //     System.out.println("se inducira la exepcion");
        //     throw new Exception("Se genero un error no controlado");
        // }
    }

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void anulaPedido(Long idPedido) {
        PedidoItemJPA pedidoItemJPA = findById(idPedido);
        pedidoItemJPA.setEstado("AN");
        pedidoItemJPA.getDetalePedidoByNumSec().forEach(itemPedido -> {
            itemPedido.setEstado("AN");
            
        });
        persistAndFlush(pedidoItemJPA);    
    }
}
