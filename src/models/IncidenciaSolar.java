package models;

public enum IncidenciaSolar {
    Direta(1),
    Indireta(0.95),
    Oposta(0.8),
    Nula(0);
    
    private final double index;

    IncidenciaSolar(double index) {
        this.index = index;
    }

    public double fator() {
        return index; 
    }
}