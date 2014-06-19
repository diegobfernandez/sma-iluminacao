package agentes;

import models.LampadaInfo;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import messages.LampadaRegistraACLMessage;

public class Lampada extends Agent {
    private boolean acesa;
    
    @Override
    protected void setup() {
        Object[] args = getArguments();
        final LampadaInfo lampadaInfo = new LampadaInfo(Double.parseDouble(args[1].toString()), Integer.parseInt(args[2].toString()));
        final AID aidComodo = new AID(args[0].toString(), AID.ISLOCALNAME);
        
        send(new LampadaRegistraACLMessage(aidComodo, lampadaInfo));
        
        this.addBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                myAgent.send(new LampadaRegistraACLMessage(aidComodo, lampadaInfo));
            }
        });
        
        this.addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage mensagem = myAgent.blockingReceive();
                if (mensagem == null || !"altera-estado-lampada".equals(mensagem.getOntology())) {
                    return;
                }

                acesa = Boolean.parseBoolean(mensagem.getContent());
                System.out.println(acesa);
            }   
        });
    }
}
