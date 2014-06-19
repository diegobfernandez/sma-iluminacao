package models;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class VariacaoLuminosidadeNoTempo {
    private static final Map<Integer,Double> variacaoLuminosidadeNoTempo;
    
    static {
        Map<Integer,Double> vlt = new HashMap<>();
        vlt.put(0, 0.0);
        vlt.put(5, 0.1);
        vlt.put(6, 0.3);
        vlt.put(7, 0.6);
        vlt.put(8, 0.8);
        vlt.put(9, 0.9);
        vlt.put(10, 1.0);
        vlt.put(11, 1.0);
        vlt.put(12, 1.0);
        vlt.put(13, 1.0);
        vlt.put(14, 1.0);
        vlt.put(15, 0.8);
        vlt.put(16, 0.8);
        vlt.put(17, 0.5);
        vlt.put(18, 0.4);
        vlt.put(19, 0.2);
        vlt.put(20, 0.1);
        variacaoLuminosidadeNoTempo = Collections.unmodifiableMap(vlt);
    };
    
    public static double getLuminosidade(int hora) throws Exception {
        if (hora < 0 || hora > 23)
            throw new Exception("A hora deve estar entre 0 e 23. O valor passado foi " + hora);
        if (hora < 5 || hora > 20)
            hora = 0;
        return VariacaoLuminosidadeNoTempo.variacaoLuminosidadeNoTempo.get(hora);
    }
}
