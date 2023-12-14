package bo.gob.ruat.orquestador.infrastructure.converter;

import bo.gob.ruat.orquestador.domain.entity.MaquinaEstadoEntity;
import bo.gob.ruat.orquestador.infrastructure.adapter.secondary.model.jpa.MaquinaEstadoJPA;
import javax.annotation.processing.Generated;
import javax.enterprise.context.ApplicationScoped;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-14T15:19:05-0400",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 13.0.1 (Oracle Corporation)"
)
@ApplicationScoped
public class IMaquinaEstadoJPAConverterImpl implements IMaquinaEstadoJPAConverter {

    @Override
    public MaquinaEstadoJPA toMaquinaEstadoJPA(MaquinaEstadoEntity maquinaEstadoEntity) {
        if ( maquinaEstadoEntity == null ) {
            return null;
        }

        MaquinaEstadoJPA maquinaEstadoJPA = new MaquinaEstadoJPA();

        maquinaEstadoJPA.setIdPeticion( maquinaEstadoEntity.getIdPeticion() );
        maquinaEstadoJPA.setIdObjetoPadre( maquinaEstadoEntity.getIdObjetoPadre() );
        maquinaEstadoJPA.setTopicPrincipal( maquinaEstadoEntity.getTopicPrincipal() );
        maquinaEstadoJPA.setIdRespuesta( maquinaEstadoEntity.getIdRespuesta() );
        maquinaEstadoJPA.setTopicCompensacion( maquinaEstadoEntity.getTopicCompensacion() );
        maquinaEstadoJPA.setEstadoPeticion( maquinaEstadoEntity.getEstadoPeticion() );
        maquinaEstadoJPA.setTopicDependiente( maquinaEstadoEntity.getTopicDependiente() );
        maquinaEstadoJPA.setEstado( maquinaEstadoEntity.getEstado() );
        maquinaEstadoJPA.setNumSec( maquinaEstadoEntity.getNumSec() );

        return maquinaEstadoJPA;
    }
}
