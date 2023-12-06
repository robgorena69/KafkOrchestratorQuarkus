package bo.gob.ruat.ordenes.domain.entity;

import java.time.LocalDate;
import java.util.List;

public class OrdenEntity {

    private Long numSec;
    private String idCliente;
    private LocalDate fecha;
    private Double monto;
    private String estado;

    private List<ItemEntity> lstProductos;

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

    public List<ItemEntity> getLstProductos() {
        return lstProductos;
    }

    public void setLstProductos(List<ItemEntity> lstProductos) {
        this.lstProductos = lstProductos;
    }

    public Long getNumSec() {
        return numSec;
    }

    public void setNumSec(Long numSec) {
        this.numSec = numSec;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
   
    

}
