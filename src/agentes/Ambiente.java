package agentes;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.AmbienteAtualizaInfoACLMessage;
import models.Clima;
import models.VariacaoLuminosidadeNoTempo;

public class Ambiente extends Agent {

    public double luminosidade;

    @Override
    protected void setup() {
        this.addBehaviour(new TickerBehaviour(this, 5000) {

            private final Random random = new Random();

            @Override
            protected void onTick() {
                AmbienteInfo infoAmbiente = calculaInfoAmbiente();
                sendMessage(infoAmbiente);
            }

            private Clima calculaClima() {
                int climaIndex = random.nextInt(3);
                return Clima.values()[climaIndex];
            }

            private int calculaHora() {
                return random.nextInt(15 + 5);
            }

            private double calculaLuminosidade(int hora) {
                Clima clima = calculaClima();
                try {
                    return VariacaoLuminosidadeNoTempo.getLuminosidade(hora) * clima.fator();
                } catch (Exception ex) {
                    Logger.getLogger(Ambiente.class.getName()).log(Level.SEVERE, null, ex);
                }
                return -1;
            }

            private AmbienteInfo calculaInfoAmbiente() {
                int hora = calculaHora();
                double luminosidade = calculaLuminosidade(hora);

                return new AmbienteInfo(hora, luminosidade);
            }

            private void sendMessage(AmbienteInfo infoAmbiente) {
                try {
                    ACLMessage msg = new ACLMessage();
                    DFAgentDescription[] dfds = DFService.search(Ambiente.this, new AmbienteAtualizaInfoDFAgentDescription());
                    for (DFAgentDescription dfd : dfds) {
                        AID aid = dfd.getName();
                        if (aid != null) {
                            msg.addReceiver(aid);
                        }
                    }
                    msg.setPerformative(ACLMessage.PROPAGATE);
                    msg.setOntology("ambiente");
                    msg.setContent(infoAmbiente.toString());
                    
                    myAgent.send(new AmbienteAtualizaInfoACLMessage(myAgent, infoAmbiente));
                } catch (FIPAException ex) {
                    Logger.getLogger(Ambiente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}