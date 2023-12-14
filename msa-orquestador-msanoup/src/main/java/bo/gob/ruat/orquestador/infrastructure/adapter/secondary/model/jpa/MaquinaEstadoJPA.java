package bo.gob.ruat.orquestador.infrastructure.adapter.secondary.model.jpa;

import javax.persistence.*;

@Entity
@Table(name = "maquina_estados_orden", schema = "orquestador_pedidos", catalog = "saga_orquestador")
public class MaquinaEstadoJPA {


	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sec_orquestador")
	@SequenceGenerator(name = "sec_orquestador", schema = "orquestador_pedidos", sequenceName = "maquina_estados_orden_num_sec_seq", initialValue = 1000, allocationSize = 1)
	@Column(name = "num_sec", nullable = false)
	private Long numSec;

	@Column(name = " id_peticion", nullable = false)
	private String idPeticion;

	@Column(name = "id_objeto_padre", nullable = false)
	private String idObjetoPadre;
    
    @Column(name = "topic_principal", nullable = false)
	private String topicPrincipal;

    @Column(name = "id_respuesta", nullable = false)
	private String idRespuesta;
   
    @Column(name = "topic_compensacion", nullable = false)
	private String topicCompensacion;

	@Column(name = "topic_dependiente", nullable = false)
	private String topicDependiente;

    @Column(name = "estado_peticion", nullable = false)
	private String estadoPeticion;

    @Column(name = "estado", nullable = false)
	private String estado;

	public Long getNumSec() {
		return numSec;
	}

	public void setNumSec(Long numSec) {
		this.numSec = numSec;
	}

	public String getIdPeticion() {
		return idPeticion;
	}

	public void setIdPeticion(String idPeticion) {
		this.idPeticion = idPeticion;
	}

	public String getIdObjetoPadre() {
		return idObjetoPadre;
	}

	public void setIdObjetoPadre(String idObjetoPadre) {
		this.idObjetoPadre = idObjetoPadre;
	}

	public String getTopicPrincipal() {
		return topicPrincipal;
	}

	public void setTopicPrincipal(String topicPrincipal) {
		this.topicPrincipal = topicPrincipal;
	}

	public String getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(String idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public String getTopicCompensacion() {
		return topicCompensacion;
	}

	public void setTopicCompensacion(String topicCompensacion) {
		this.topicCompensacion = topicCompensacion;
	}

	public String getEstadoPeticion() {
		return estadoPeticion;
	}

	public void setEstadoPeticion(String estadoPeticion) {
		this.estadoPeticion = estadoPeticion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTopicDependiente() {
		return topicDependiente;
	}

	public void setTopicDependiente(String topicDependiente) {
		this.topicDependiente = topicDependiente;
	}

	
	


}
