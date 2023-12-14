package bo.gob.ruat.orquestador.infrastructure.adapter.secondary.Timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import bo.gob.ruat.orquestador.application.port.primary.IOrquestadorUseCase;
import bo.gob.ruat.orquestador.application.port.secondary.IMaquinaEstadoRepository;
import bo.gob.ruat.orquestador.application.port.secondary.IVerificaPeticion;
import bo.gob.ruat.orquestador.domain.entity.MaquinaEstadoEntity;
import bo.gob.ruat.orquestador.domain.entity.RespuestaPeticion;

@ApplicationScoped
public class TareaProgramaVerificacion implements IVerificaPeticion {

    @Inject
    IMaquinaEstadoRepository maquinaEstadoRepository;

    @Inject
    IOrquestadorUseCase orquestadorUseCase;

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    private final AtomicInteger intentos = new AtomicInteger(0);
    private boolean detenerEjecucion = false;
    private Long secMaquinaEstado;
    private MaquinaEstadoEntity maquinaEstado;

    

    public void iniciarTareaProgramada() {
        System.out.println("dentro de iniciar tarea programada");
        executorService.scheduleAtFixedRate(this::verificarRutina, 1, 1, TimeUnit.SECONDS);
    }

    public void detenerTareaProgramada() {
        executorService.shutdown();
    }    

     private void verificarRutina() {
        // Lógica de la tarea programada
        System.out.println("Verifica si se actualizo la maquina de estado con la secuencia:" + this.secMaquinaEstado);
        boolean maquinaActualizada =  maquinaEstadoRepository.verificaMaquinaActualizada(this.secMaquinaEstado);
        System.out.println("El valor de la respuesta es:" + maquinaActualizada);
        // Incrementar el número de intentos antes de la lógica específica
      
        // Lógica a ejecutar cada 2 segundos
        //if (maquinaActualizada){
        //    detenerTareaProgramada();
       // }

        int numeroIntentoActual = intentos.getAndIncrement();
        // Verificar si se ha alcanzado el número máximo de intentos
        System.out.println("Intentos realizados");
        System.out.println(numeroIntentoActual);
        if (numeroIntentoActual >= 4) {
            detenerEjecucion = true;
            System.out.println("Número máximo de intentos alcanzado. Deteniendo la ejecución de la tarea programada.");
        }

        if (detenerEjecucion) {
            // Realizar acciones antes de detener
         //   enviarPeticionRollback();
            System.out.println("Deteniendo la ejecución de la tarea programada, y llamando a la compensación");
            detenerTareaProgramada();
            return;
        }

        // Resto de la lógica de la tarea programada
    }

    @Transactional(value = TxType.REQUIRES_NEW)
    public void verificaDatos(MaquinaEstadoEntity maquinaEstadoEntity) {
        // Llamada explícita al método que realiza la tarea programada
        this.maquinaEstado = maquinaEstadoEntity;
      
        this.secMaquinaEstado = maquinaEstadoEntity.getNumSec();
        System.out.println("asignando el valor de la maquina de estado:" + this.secMaquinaEstado);
        iniciarTareaProgramada();
        //verificarRutina();
    }

    private void enviarPeticionRollback(){
        System.out.println("asignando valores de la peticion");
        RespuestaPeticion respuestaPeticion = new RespuestaPeticion();
        respuestaPeticion.setId(maquinaEstado.getNumSec());
        respuestaPeticion.setTipoPeticion("FALLO");
        respuestaPeticion.setIdPeticion(maquinaEstado.getIdPeticion());
        System.out.println("asignando valores de la peticion:" + respuestaPeticion.toString());
        orquestadorUseCase.confirmaOperacion(respuestaPeticion);

    }

    @Override
    public void verificaPeticionAtendia(MaquinaEstadoEntity maquinaEstadoEntity) {
        this.verificaDatos(maquinaEstadoEntity);
    }

           

    
}

