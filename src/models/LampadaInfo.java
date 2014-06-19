package models;

public class LampadaInfo {
    private final double luminosidade;
    private final int consumo;

    public LampadaInfo(double luminosidade, int consumo) {
        this.luminosidade = luminosidade;
        this.consumo = consumo;
    }

    public LampadaInfo(String lampadaInfo) {
        String[] partes = lampadaInfo.split(":");
        luminosidade = Double.parseDouble(partes[0]);
        consumo = Integer.parseInt(partes[1]);
    }
    
    public double getLuminosidade() {
        return luminosidade;
    }
    
    public int getConsumo() {
        return consumo;
    }
    
    @Override
    public String toString() {
        return getLuminosidade() + ":" + getConsumo();
    }
}