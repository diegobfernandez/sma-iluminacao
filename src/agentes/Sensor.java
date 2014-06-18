package agentes;

import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ReceiverBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.Property;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Sensor extends Agent {

    private void setupServicesDescription() {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("interessado-iluminacao");
        sd.addProperties(new Property("is-sensor", true));
        sd.setName("sensor");
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }

    @Override
    protected void setup() {
        setupServicesDescription();
        
        this.addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();
                if (msg == null)
                    block();
                String content = msg.getContent();
                String[] partes = content.split(":");
                int hora = Integer.parseInt(partes[0]);
                Clima clima = Enum.valueOf(Clima.class, partes[1]);
            }
        });
        
        this.doWait();
    }
}
