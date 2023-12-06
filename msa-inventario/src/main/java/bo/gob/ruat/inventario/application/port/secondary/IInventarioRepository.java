package bo.gob.ruat.inventario.application.port.secondary;

public interface IInventarioRepository {

    void actualizarCantidadInventario(String codigoItem, int cantidad, String Operacion);
    boolean verificaExistenciaItem(String codigoItem, int cantidad);
    
}
