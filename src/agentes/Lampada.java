package agentes;

import comportamentos.Acender;
import jade.core.Agent;

public class Lampada extends Agent {
    @Override
    protected void setup() {
        //Fazer a verificação de quando esse comportamento deve ser disparado;
        addBehaviour(new Acender(this));
    }
}
