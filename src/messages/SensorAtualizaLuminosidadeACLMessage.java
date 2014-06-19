package messages;

import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class SensorAtualizaLuminosidadeACLMessage extends ACLMessage {

    public SensorAtualizaLuminosidadeACLMessage(AID aid, double luminosidade) {
        this.setPerformative(ACLMessage.INFORM);
        this.setOntology("luminosidade");
        this.setContent("" + luminosidade);
        this.addReceiver(aid);
    }
}