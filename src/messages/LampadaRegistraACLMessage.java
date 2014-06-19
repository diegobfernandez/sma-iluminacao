package messages;

import models.LampadaInfo;
import jade.core.AID;
import jade.lang.acl.ACLMessage;

public class LampadaRegistraACLMessage extends ACLMessage {

    public LampadaRegistraACLMessage(AID receiver, LampadaInfo lampadaInfo) {
        this.setPerformative(ACLMessage.INFORM);
        this.setOntology("lampada-info");
        this.setContent(lampadaInfo.toString());
        this.addReceiver(receiver);
    }
}