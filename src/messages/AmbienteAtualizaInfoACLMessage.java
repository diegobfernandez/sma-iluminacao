package messages;

import agentes.AmbienteAtualizaInfoDFAgentDescription;
import models.AmbienteInfo;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class AmbienteAtualizaInfoACLMessage extends ACLMessage {

    private final Agent agent;

    public AmbienteAtualizaInfoACLMessage(Agent agent, AmbienteInfo infoAmbiente) {
        this.agent = agent;
        setAgentesInteressados();
        this.setPerformative(ACLMessage.PROPAGATE);
        this.setOntology("ambiente");
        this.setContent(infoAmbiente.toString());
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
