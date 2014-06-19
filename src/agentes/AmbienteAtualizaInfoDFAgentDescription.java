package agentes;

import jade.core.AID;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class AmbienteAtualizaInfoDFAgentDescription extends DFAgentDescription {
    public AmbienteAtualizaInfoDFAgentDescription() {
        ServiceDescription sd = new ServiceDescription();
        sd.setType("ambiente-atualizado");
        this.addServices(sd);
    }
    
    public AmbienteAtualizaInfoDFAgentDescription(AID aid) {
        ServiceDescription sd = new ServiceDescription();
        sd.setType("ambiente-atualizado");
        sd.setName("ambiente-atualizado");
        this.addServices(sd);
        this.setName(aid);
    }
}