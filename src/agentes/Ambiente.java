package agentes;

import jade.core.Agent;

public class Ambiente extends Agent {

    public double luminosidade;

    @Override
    protected void setup() {
        this.addBehaviour(new AtualizaAmbienteBehaviour(this, 5000));
    }
}