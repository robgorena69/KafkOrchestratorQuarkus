package bo.gob.ruat.ordenes.domain.entity;

public class DatosPeticionEntity {
    
    private String identificador;
    private String topicOrigen;
    private String topicDestino ;
    private String topicCompensacion;
    private String identificadorPadre;

    public String getIdentificador() {
        return identificador;
    }
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }
    public String getTopicOrigen() {
        return topicOrigen;
    }
    public void setTopicOrigen(String topicOrigen) {
        this.topicOrigen = topicOrigen;
    }
    public String getTopicDestino() {
        return topicDestino;
    }
    public void setTopicDestino(String topicDestino) {
        this.topicDestino = topicDestino;
    }
    public String getIdentificadorPadre() {
        return identificadorPadre;
    }
    public void setIdentificadorPadre(String identificadorPadre) {
        this.identificadorPadre = identificadorPadre;
    }
    public String getTopicCompensacion() {
        return topicCompensacion;
    }
    public void setTopicCompensacion(String topicCompensacion) {
        this.topicCompensacion = topicCompensacion;
    }
    

    
}
