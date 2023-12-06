package bo.gob.ruat.ordenes.infrastructure.adapter.primary.rest.request;

import java.time.LocalDate;
import java.util.List;

public class OrdenRequest {
    
    private String idCliente;
    private LocalDate fecha;
    private Double monto;

    public static class ItemsNested {
        private String codigo;
        private int cantidad;

        public String getCodigo() {
            return codigo;
        }
        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }
        public int getCantidad() {
            return cantidad;
        }
        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }
        
    }

    private List<ItemsNested> lstProductos;

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public List<ItemsNested> getLstProductos() {
        return lstProductos;
    }

    public void setLstProductos(List<ItemsNested> lstProductos) {
        this.lstProductos = lstProductos;
    }
    
}
