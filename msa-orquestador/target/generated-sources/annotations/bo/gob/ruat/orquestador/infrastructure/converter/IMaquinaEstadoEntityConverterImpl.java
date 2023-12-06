package bo.gob.ruat.orquestador.infrastructure.converter;

import bo.gob.ruat.orquestador.domain.entity.MaquinaEstadoEntity;
import bo.gob.ruat.orquestador.infrastructure.adapter.secondary.model.jpa.MaquinaEstadoJPA;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-04T08:43:57-0400",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20230213-1046, environment: Java 17.0.6 (Eclipse Adoptium)"
)
@ApplicationScoped
public class IMaquinaEstadoEntityConverterImpl implements IMaquinaEstadoEntityConverter {

    @Override
    public MaquinaEstadoEntity toMaquinaEstadoEntity(MaquinaEstadoJPA maquinaEstadoJPA) {
        if ( maquinaEstadoJPA == null ) {
            return null;
        }

        MaquinaEstadoEntity maquinaEstadoEntity = new MaquinaEstadoEntity();

        maquinaEstadoEntity.setIdPeticion( maquinaEstadoJPA.getIdPeticion() );
        maquinaEstadoEntity.setIdObjetoPadre( maquinaEstadoJPA.getIdObjetoPadre() );
        maquinaEstadoEntity.setTopicPrincipal( maquinaEstadoJPA.getTopicPrincipal() );
        maquinaEstadoEntity.setIdRespuesta( maquinaEstadoJPA.getIdRespuesta() );
        maquinaEstadoEntity.setTopicCompensacion( maquinaEstadoJPA.getTopicCompensacion() );
        maquinaEstadoEntity.setEstadoPeticion( maquinaEstadoJPA.getEstadoPeticion() );
        maquinaEstadoEntity.setTopicDependiente( maquinaEstadoJPA.getTopicDependiente() );
        maquinaEstadoEntity.setEstado( maquinaEstadoJPA.getEstado() );
        maquinaEstadoEntity.setNumSec( maquinaEstadoJPA.getNumSec() );

        return maquinaEstadoEntity;
    }
}
