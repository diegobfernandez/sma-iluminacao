package models;

public class VariacaoIncidenciaSolarNoTempo {
    
    public static IncidenciaSolar getIncidenciaSolar(int hora, Sentido sentido) throws Exception {
        if (hora < 0 || hora > 23) {
            throw new Exception("A hora deve estar entre 0 e 23. O valor passado foi " + hora);
        }
        
        if (hora < 5 || hora > 20) {
            return IncidenciaSolar.Nula;
        }
        
        if (sentido == Sentido.Norte || sentido == Sentido.Sul) {
            return IncidenciaSolar.Indireta;
        }
        
        if (sentido == Sentido.Leste) {
            if (hora >= 5 && hora <= 8) {
                return IncidenciaSolar.Direta;
            }
            
            if (hora >= 9 && hora <= 12) {
                return IncidenciaSolar.Indireta;
            }
            
            if (hora >= 13 && hora <= 20) {
                return IncidenciaSolar.Indireta;
            }
        }
        
        if (sentido == Sentido.Oeste) {
            if (hora >= 5 && hora <= 11) {
                return IncidenciaSolar.Oposta;
            }
            
            if (hora >= 12 && hora <= 15) {
                return IncidenciaSolar.Indireta;
            }
            
            if (hora >= 16 && hora <= 20) {
                return IncidenciaSolar.Direta;
            }
        }
        
        return IncidenciaSolar.Nula;
    }
}