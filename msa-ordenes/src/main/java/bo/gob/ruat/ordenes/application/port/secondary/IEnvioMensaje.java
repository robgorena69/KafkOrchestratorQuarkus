package bo.gob.ruat.ordenes.application.port.secondary;

public interface IEnvioMensaje {
   
    void enviaCrearOrden(String mensaje);

    void enviaCreaFactura(String mensaje);


}
