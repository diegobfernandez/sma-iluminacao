package messages;

import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class ComodoAlteraEstadoLampadaACLMessage extends ACLMessage {
    public ComodoAlteraEstadoLampadaACLMessage(AID receiver, boolean acesa) {
        this.setPerformative(ACLMessage.INFORM);
        this.setOntology("altera-estado-lampada");
        this.setContent("" + acesa);
        this.addReceiver(receiver);
    }
}