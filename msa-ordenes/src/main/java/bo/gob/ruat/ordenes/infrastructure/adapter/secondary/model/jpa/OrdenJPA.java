package bo.gob.ruat.ordenes.infrastructure.adapter.secondary.model.jpa;


import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "ort_orden", schema = "ordenes", catalog = "saga_orquestador")
public class OrdenJPA {

   
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orden_sec")
	@SequenceGenerator(name = "orden_sec", schema = "ordenes", sequenceName = "orden_sec", initialValue = 1000, allocationSize = 1)
    @Id
    @Column(name = "num_sec", nullable = false)
	private Long numSec;

	@Column(name = "idcliente", nullable = false)
	private String idcliente;

	@Column(name = "fecha", nullable = false)
	private LocalDateTime fecha;

    @Column(name = "monto", nullable = false)
	private Long monto;

    @Column(name = "estado", nullable = false)
	private String estado;

    public Long getNumSec() {
        return numSec;
    }

    public void setNumSec(Long numSec) {
        this.numSec = numSec;
    }

    public String getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(String idcliente) {
        this.idcliente = idcliente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Long getMonto() {
        return monto;
    }

    public void setMonto(Long monto) {
        this.monto = monto;
    }

    



}
