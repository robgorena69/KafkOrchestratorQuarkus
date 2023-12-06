package bo.gob.ruat.inventario.infrastructure.adapter.secondary.model.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "inventario_item", schema = "inventario", catalog = "saga_orquestador")
public class ItemInventarioJPA {
        
    @Id
    @Column(name = "item", nullable = false)
    private String item;

    @Column(name = "cantidad")
    private Long cantidad;

    @Column(name = "estado")
    private String estado;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    
}
