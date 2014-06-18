package agentes;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sensor extends Agent {
    @Override
    protected void setup() {
        try {
            DFService.register(this, new AtualizaAmbienteDFAgentDescription(getAID()));
            this.addBehaviour(new AtualizaSensorBehaviour());
        } catch (FIPAException ex) {
            Logger.getLogger(Sensor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}