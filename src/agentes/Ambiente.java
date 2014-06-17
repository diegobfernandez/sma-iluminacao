package agentes;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ambiente extends Agent {

    public double luminosidade;

    @Override
    protected void setup() {
        Object[] args = getArguments();
        int hora = Integer.parseInt((String) args[0]);
        Clima clima = Clima.valueOf((String) args[1]);
        luminosidade = VariacaoLuminosidadeNoTempo.getLuminosidade(hora) * clima.fator();
        Collection<AID> aids = encontreAgentesInteressadosIluminacao();
        ACLMessage msg = new ACLMessage();
        msg.setContent(hora + ":" + clima.toString());
        for(AID aid : aids) {
            msg.addReceiver(aid);
        }
        
        this.send(msg);
    }

    private Collection<AID> encontreAgentesInteressadosIluminacao() {
        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType("interessado-iluminacao");
        template.addServices(sd);
        try {
            DFAgentDescription[] agentServices = DFService.search(this, template);
            List<AID> aids = new ArrayList<>();
            for (DFAgentDescription dfd : agentServices) {
                aids.add(dfd.getName());
            }
            
            return aids;
        } catch (FIPAException ex) {
            Logger.getLogger(Ambiente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new ArrayList<>(0);
    }
}
