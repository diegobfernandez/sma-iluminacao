package agentes;

public enum Clima {
    CeuLimpo(1),
    ParcialmenteNublado(0.75),
    Nublado(0.5),
    NuvensCarregadas(0.25);
    
    private final double index;

    Clima(double index) {
        this.index = index;
    }

    public double fator() {
        return index; 
    }
}