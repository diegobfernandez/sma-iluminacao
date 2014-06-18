package agentes;

import behaviours.AmbienteAtualizaInfoBehaviour;
import jade.core.Agent;

public class Ambiente extends Agent {

    public double luminosidade;

    @Override
    protected void setup() {
        this.addBehaviour(new AmbienteAtualizaInfoBehaviour(this, 10000));
    }
}