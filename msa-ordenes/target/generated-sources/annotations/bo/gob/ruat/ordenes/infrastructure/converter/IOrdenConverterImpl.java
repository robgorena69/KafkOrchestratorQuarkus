package bo.gob.ruat.ordenes.infrastructure.converter;

import bo.gob.ruat.ordenes.domain.entity.ItemEntity;
import bo.gob.ruat.ordenes.domain.entity.OrdenEntity;
import bo.gob.ruat.ordenes.infrastructure.adapter.primary.rest.request.OrdenRequest;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-04T08:43:57-0400",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20230213-1046, environment: Java 17.0.6 (Eclipse Adoptium)"
)
@ApplicationScoped
public class IOrdenConverterImpl implements IOrdenConverter {

    @Override
    public OrdenEntity toOrdenEntity(OrdenRequest pOrdenRequest) {
        if ( pOrdenRequest == null ) {
            return null;
        }

        OrdenEntity ordenEntity = new OrdenEntity();

        ordenEntity.setIdCliente( pOrdenRequest.getIdCliente() );
        ordenEntity.setFecha( pOrdenRequest.getFecha() );
        ordenEntity.setMonto( pOrdenRequest.getMonto() );
        ordenEntity.setLstProductos( itemsNestedListToItemEntityList( pOrdenRequest.getLstProductos() ) );

        ordenEntity.setNumSec( (long) 0L );

        return ordenEntity;
    }

    protected ItemEntity itemsNestedToItemEntity(OrdenRequest.ItemsNested itemsNested) {
        if ( itemsNested == null ) {
            return null;
        }

        ItemEntity itemEntity = new ItemEntity();

        itemEntity.setCodigo( itemsNested.getCodigo() );
        itemEntity.setCantidad( itemsNested.getCantidad() );

        return itemEntity;
    }

    protected List<ItemEntity> itemsNestedListToItemEntityList(List<OrdenRequest.ItemsNested> list) {
        if ( list == null ) {
            return null;
        }

        List<ItemEntity> list1 = new ArrayList<ItemEntity>( list.size() );
        for ( OrdenRequest.ItemsNested itemsNested : list ) {
            list1.add( itemsNestedToItemEntity( itemsNested ) );
        }

        return list1;
    }
}
