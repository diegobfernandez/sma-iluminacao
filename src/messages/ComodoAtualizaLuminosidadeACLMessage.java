package messages;

import agentes.AmbienteAtualizaInfoDFAgentDescription;
import agentes.Comodo;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComodoAtualizaLuminosidadeACLMessage extends ACLMessage {
    private final Comodo agent;
    public ComodoAtualizaLuminosidadeACLMessage(Comodo agent, double luminosidade) {
        this.agent = agent;
        setAgentesInteressados();
        this.setOntology("luminosidade-interna");
        this.setContent("" + luminosidade);
    }
    
    private void setAgentesInteressados() {
        try {
            DFAgentDescription[] dfds = DFService.search(agent, new AmbienteAtualizaInfoDFAgentDescription());
            for (DFAgentDescription dfd : dfds) {
                AID aid = dfd.getName();
                
                if (aid != null) {
                    this.addReceiver(aid);
                }
            }
        } catch (FIPAException ex) {
            Logger.getLogger(AmbienteAtualizaInfoACLMessage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}