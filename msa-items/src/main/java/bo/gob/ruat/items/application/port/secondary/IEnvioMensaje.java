package bo.gob.ruat.items.application.port.secondary;


public interface IEnvioMensaje {

    void envioOperacionExitosa(String mensaje);

    void envioDebitarItems(String mensaje);
    
}
