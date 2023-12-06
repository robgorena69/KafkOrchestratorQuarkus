package bo.gob.ruat.inventario.infrastructure.adapter.secondary.repository.jpa;


import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import bo.gob.ruat.inventario.application.port.secondary.IInventarioRepository;
import bo.gob.ruat.inventario.infrastructure.adapter.secondary.model.jpa.ItemInventarioJPA;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@RequestScoped
public class InventarioRepositoryImpl implements IInventarioRepository, PanacheRepository<ItemInventarioJPA>{

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void actualizarCantidadInventario(String codigoItem, int cantidad, String operacion) {

         ItemInventarioJPA itemInventarioJPA = find("item", codigoItem).singleResult();
         long qty = 0;
         switch (operacion){
            case "adicionar":
              qty = itemInventarioJPA.getCantidad().longValue() + (long)cantidad;
              break;
            case "disminuir":
              qty = itemInventarioJPA.getCantidad().longValue() - (long)cantidad; 
              break;
         }
         itemInventarioJPA.setCantidad(qty);
         persistAndFlush(itemInventarioJPA);
    }

    @Override
    @Transactional(value = TxType.MANDATORY)
    public boolean verificaExistenciaItem(String codigoItem, int cantidad) {
         ItemInventarioJPA itemInventarioJPA = find("item ",codigoItem).singleResult();
         if (itemInventarioJPA.getCantidad()>=cantidad)
                return true;
         else   
                return false;       
    }
    
    
}
