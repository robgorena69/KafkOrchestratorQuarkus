package bo.gob.ruat.items.infrastructure.adapter.secondary.model.jpa;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "it_detalle_pedido", schema = "prepara_items",  catalog = "saga_orquestador")
public class DetallePedidoJPA {

    @Id
    @Column(name = "num_sec")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalle_pedido_sec")
	@SequenceGenerator(name = "detalle_pedido_sec", schema = "prepara_items", sequenceName = "detalle_pedido_sec", initialValue = 1000, allocationSize = 1)
	private Long numSec;

    @Column(name = "id_item")
    private String idItem;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "id_pedido",insertable = false, updatable = false)
    private Long idPedido;

    @Column(name = "estado")
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_pedido", referencedColumnName = "num_sec", nullable = false, insertable = true, updatable = true)
	private PedidoItemJPA detalleByNsecPedido;

    public Long getNumSec() {
        return numSec;
    }


    public void setNumSec(Long numSec) {
        this.numSec = numSec;
    }


    public String getIdItem() {
        return idItem;
    }


    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }


    public int getCantidad() {
        return cantidad;
    }


    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    public Long getIdPedido() {
        return idPedido;
    }


    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }


    public String getEstado() {
        return estado;
    }


    public void setEstado(String estado) {
        this.estado = estado;
    }


    public PedidoItemJPA getDetalleByNsecPedido() {
        return detalleByNsecPedido;
    }


    public void setDetalleByNsecPedido(PedidoItemJPA usuarioByNsecUsuario) {
        this.detalleByNsecPedido = usuarioByNsecUsuario;
    }

    
    
}
