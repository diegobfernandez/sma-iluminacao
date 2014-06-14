package agentes;

import jade.core.Agent;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Ambiente extends Agent {
    public static final Map<Integer,Double> variacaoLuminosidadeNoTempo;
    
    static {
        Map<Integer,Double> vlt = new HashMap<>();
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
    
    @Override
    protected void setup() {
        Object[] args = getArguments();
        int hour = Integer.parseInt((String)args[0]);
        Clima clima = Clima.valueOf((String)args[1]);
    }
}