package agentes;

import jade.core.Agent;

public class Ambiente extends Agent {
    public double luminosidade;
    
    @Override
    protected void setup() {
        Object[] args = getArguments();
        int hora = Integer.parseInt((String)args[0]);
        Clima clima = Clima.valueOf((String)args[1]);
        luminosidade = VariacaoLuminosidadeNoTempo.getLuminosidade(hora) * clima.fator();
    }
}