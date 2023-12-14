package bo.gob.ruat.orquestador.infrastructure.adapter.secondary.repository.jpa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import bo.gob.ruat.orquestador.application.port.secondary.IMaquinaEstadoRepository;
import bo.gob.ruat.orquestador.domain.entity.MaquinaEstadoEntity;
import bo.gob.ruat.orquestador.infrastructure.adapter.secondary.model.jpa.MaquinaEstadoJPA;
import bo.gob.ruat.orquestador.infrastructure.converter.IMaquinaEstadoEntityConverter;
import bo.gob.ruat.orquestador.infrastructure.converter.IMaquinaEstadoJPAConverter;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@RequestScoped
public class MaquinaEstadoRepositoryImpl implements IMaquinaEstadoRepository, PanacheRepository<MaquinaEstadoJPA> {

    @Inject
    IMaquinaEstadoJPAConverter maquinaEstadoJPAConverter;

    @Inject
    IMaquinaEstadoEntityConverter maquinaEstadoEntityConverter;

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void registraMaquinaEstado(MaquinaEstadoEntity maquinaEstadoEntity) {
        MaquinaEstadoJPA maquinaEstadoJPA = maquinaEstadoJPAConverter.toMaquinaEstadoJPA(maquinaEstadoEntity);
        persist(maquinaEstadoJPA);
        maquinaEstadoEntity.setNumSec(maquinaEstadoJPA.getNumSec());
    }

    @Override
    @Transactional(value = TxType.MANDATORY)
    public void actualizaMaquinaEstado(MaquinaEstadoEntity maquinaEstadoEntity) {

        
        MaquinaEstadoJPA maquinaEstadoJPA =  findById(maquinaEstadoEntity.getNumSec());
        maquinaEstadoJPA.setEstadoPeticion(maquinaEstadoEntity.getEstadoPeticion());
        maquinaEstadoJPA.setIdRespuesta(maquinaEstadoEntity.getIdRespuesta());
       /* maquinaEstadoJPA.setEstado(maquinaEstadoEntity.getEstado();
        maquinaEstadoJPA.setEstadoPeticion(maquinaEstadoEntity.getEstadoPeticion());
        maquinaEstadoJPA.setIdObjetoPadre(maquinaEstadoEntity.getIdObjetoPadre());
        maquinaEstadoJPA.setIdPeticion(maquinaEstadoEntity.getIdPeticion());
        maquinaEstadoJPA.set
     */
        persistAndFlush(maquinaEstadoJPA);
    }

    @Override
    @Transactional(value = TxType.MANDATORY)
    public MaquinaEstadoEntity obtMaquinaEstadobyId(Long identificador) {
        System.out.println("dentro del repository obtmaquina estado" + identificador);
        MaquinaEstadoJPA resultado = findById(identificador);
        return maquinaEstadoEntityConverter.toMaquinaEstadoEntity(resultado);
    }

    @Override
    @Transactional(value = TxType.MANDATORY)
    public List<MaquinaEstadoEntity> lstMaquinaEstadoByIdPeticion(String idPeticion) {
        StringBuffer consulta = new StringBuffer();
        consulta.append("Select ma from MaquinaEstadoJPA ma");
        consulta.append(" where ma.idPeticion = :idPeticion ");
        //consulta.append("   and ma.estadoPeticion in ('TE')");

        var mapParametros = new HashMap<String, Object>();
			mapParametros.put("idPeticion", idPeticion);
        List<MaquinaEstadoEntity> lstMaquinaEstado = new ArrayList<>();
           

		List<MaquinaEstadoJPA> vListMaquinaEstadoJPA =  find(consulta.toString(), mapParametros).list();

        vListMaquinaEstadoJPA.stream().forEach(maquinaEstadoJPA->{
            MaquinaEstadoEntity maquinaEstadoEntity = new MaquinaEstadoEntity();
            maquinaEstadoEntity.setEstado(maquinaEstadoJPA.getEstado());
            maquinaEstadoEntity.setEstadoPeticion(maquinaEstadoJPA.getEstadoPeticion());
            maquinaEstadoEntity.setIdObjetoPadre(maquinaEstadoJPA.getIdObjetoPadre());
            maquinaEstadoEntity.setIdPeticion(maquinaEstadoJPA.getIdPeticion());
            maquinaEstadoEntity.setIdRespuesta(maquinaEstadoJPA.getIdRespuesta());
            maquinaEstadoEntity.setNumSec(maquinaEstadoJPA.getNumSec());
            maquinaEstadoEntity.setTopicCompensacion(maquinaEstadoJPA.getTopicCompensacion());
            maquinaEstadoEntity.setTopicDependiente(maquinaEstadoJPA.getTopicDependiente());
            maquinaEstadoEntity.setTopicPrincipal(maquinaEstadoJPA.getTopicPrincipal());

            //maquinaEstadoEntity = maquinaEstadoEntityConverter.toMaquinaEstadoEntity(maquinaEstadoJPA);
            lstMaquinaEstado.add(maquinaEstadoEntity);
        });
        return lstMaquinaEstado;
    }

    @Override
    //@Transactional(value = TxType.REQUIRED)
    @Transactional
    public boolean verificaMaquinaActualizada(Long secMaquinaEstado) {
        System.out.println("dentro de verificacion" + secMaquinaEstado);
        MaquinaEstadoEntity maquinaEstadoEntity = this.obtMaquinaEstadobyId(secMaquinaEstado);
        System.out.println(maquinaEstadoEntity.toString());
        if (maquinaEstadoEntity.getEstadoPeticion().equals("TE"))
            return true;
        else
            return false;    
    }
        
}
