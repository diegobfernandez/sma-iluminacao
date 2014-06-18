package agentes;

import utils.MapSorting;
import messages.ComodoAlteraEstadoLampadaACLMessage;
import behaviours.ComodoRegistraLampadaBehaviour;
import jade.core.AID;
import jade.core.Agent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Comodo extends Agent {
    public static final double LUMINOSIDADE_IDEAL = 1.0;
    
    private double luminosidade = 0;
    private final Map<AID, LampadaInfo> lampadas = new HashMap<>();
    
    public void setLuminosidade(double luminosidade) {
        this.luminosidade = luminosidade;
        
        if (this.luminosidade < LUMINOSIDADE_IDEAL) {
            acendeLampadas();
        }
    }
    
    @Override
    protected void setup() {
        this.addBehaviour(new ComodoAtualizaLuminosidade(this));
        this.addBehaviour(new ComodoRegistraLampadaBehaviour(this));
    }

    private void acendeLampadas() {
        Map<AID, Double> lampadasEficiencia = new HashMap<>();
        for(AID key :lampadas.keySet()) {
            LampadaInfo li = lampadas.get(key);
            lampadasEficiencia.put(key, li.getLuminosidade() / li.getConsumo());
        }
        
        lampadasEficiencia = MapSorting.sortByValues(lampadasEficiencia);
        Collection<AID> lampadasAcessas = new ArrayList<>();
        Collection<AID> lampadasApagadas = new ArrayList<>(lampadasEficiencia.keySet());
        
        for (Map.Entry<AID,Double> entry : lampadasEficiencia.entrySet()) {
            LampadaInfo li = lampadas.get(entry.getKey());
            if(luminosidade + li.getLuminosidade() < LUMINOSIDADE_IDEAL) {
                lampadasAcessas.add(entry.getKey());
                lampadasApagadas.remove(entry.getKey());
            }
        }
        if (getState() != Agent.AP_ACTIVE && getState() != Agent.AP_DELETED)
            doActivate();
        
        for (AID aid : lampadasAcessas) {
            this.send(new ComodoAlteraEstadoLampadaACLMessage(aid, true));
        }
        
        for (AID aid : lampadasApagadas) {
            this.send(new ComodoAlteraEstadoLampadaACLMessage(aid, false));
        }
    }

    public void registraLampada(AID sender, LampadaInfo li) {
        lampadas.put(sender, li);
    }
}