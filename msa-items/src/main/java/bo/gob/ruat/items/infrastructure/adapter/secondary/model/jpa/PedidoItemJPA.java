package bo.gob.ruat.items.infrastructure.adapter.secondary.model.jpa;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "it_pedido_items", schema ="prepara_items",  catalog = "saga_orquestador")
public class PedidoItemJPA {

    @Id
    @Column(name = "num_sec")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_sec")
	@SequenceGenerator(name = "pedido_sec", schema = "prepara_items", sequenceName = "pedido_sec", initialValue = 1000, allocationSize = 1)
	private Long numSec;

	@Column(name = "id_pedido_origen", nullable = false)
	private String idPedidoOrigen;

    @Column(name = "estado", nullable = false)
	private String estado;
 
    @OneToMany(mappedBy = "detalleByNsecPedido", cascade = CascadeType.ALL)
	private Collection<DetallePedidoJPA> detalePedidoByNumSec;

    public Long getNumSec() {
        return numSec;
    }

    public void setNumSec(Long numSec) {
        this.numSec = numSec;
    }

    public String getIdPedidoOrigen() {
        return idPedidoOrigen;
    }

    public void setIdPedidoOrigen(String idPedidoOrigen) {
        this.idPedidoOrigen = idPedidoOrigen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Collection<DetallePedidoJPA> getDetalePedidoByNumSec() {
        return detalePedidoByNumSec;
    }

    public void setDetalePedidoByNumSec(Collection<DetallePedidoJPA> detalePedidoByNumSec) {
        this.detalePedidoByNumSec = detalePedidoByNumSec;
    }

    




    
}
