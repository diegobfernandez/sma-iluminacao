package models;

public class AmbienteInfo {
    private final int hora;
    private final double luminosidade;
    
    public AmbienteInfo(int hora, double luminosidade) {
        this.hora = hora;
        this.luminosidade = luminosidade;
    }
    
    public AmbienteInfo(String messageContent) {
        String[] partes = messageContent.split(":");
        this.hora = Integer.parseInt(partes[0]);
        this.luminosidade = Double.parseDouble(partes[1]);
    }
    
    public int getHora() {
        return hora;
    }
    
    public double getLuminosidade() {
        return luminosidade;
    }
    
    @Override
    public String toString() {
        return getHora() + ":" + getLuminosidade();
    }
}