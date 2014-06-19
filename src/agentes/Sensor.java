package agentes;

import models.Sentido;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.SensorAtualizaLuminosidadeACLMessage;
import models.AmbienteInfo;
import models.IncidenciaSolar;
import models.VariacaoIncidenciaSolarNoTempo;

public class Sensor extends Agent {
    private double luminosidadeExterna = 0;
    private double luminosidadeInterna = 0;
    private AID aidComodo;
    private Sentido sentido;
    
    public void setLuminosidadeExterna(double luminosidadeExterna) {
        this.luminosidadeExterna = luminosidadeExterna;
    }
    
    public void setLuminosidadeInterna(double luminosidadeInterna) {
        this.luminosidadeInterna = luminosidadeInterna;
    }
    
    public double getLuminosidade() {
        return luminosidadeExterna + luminosidadeInterna;
    }
    
    public Sentido getSentido() {
        return sentido;
    }
    
    @Override
    protected void setup() {
        try {
            Object[] args = getArguments();
            if (args[0] == null)
                throw new Exception("É necessário informar o comodo no qual o sensor está alocado");
            
            if(args[1] == null)
                throw new Exception("É necessário informar o sentido no qual o sensor está alocado");
            
            sentido = Sentido.valueOf(args[1].toString());
            aidComodo = new AID(args[0].toString(), AID.ISLOCALNAME);
            
            DFService.register(this, new AmbienteAtualizaInfoDFAgentDescription(getAID()));
            
            this.addBehaviour(new CyclicBehaviour() {
                @Override
                public void action() {
                    try {
                        ACLMessage mensagem = myAgent.blockingReceive();
                        if (mensagem == null) {
                            return;
                        }

                        if ("ambiente".equals(mensagem.getOntology())) {
                            System.out.println("recebeu luminosidade externa");
                            AmbienteInfo infoAmbiente = new AmbienteInfo(mensagem.getContent());
                            IncidenciaSolar incidenciaSolar = VariacaoIncidenciaSolarNoTempo.getIncidenciaSolar(infoAmbiente.getHora(), getSentido());
                            setLuminosidadeExterna(infoAmbiente.getLuminosidade() * incidenciaSolar.fator());
                            myAgent.send(new SensorAtualizaLuminosidadeACLMessage(getComodo(), getLuminosidade()));
                        }

                        if ("luminosidade-interna".equals(mensagem.getOntology())) {
                            setLuminosidadeInterna(Double.parseDouble(mensagem.getContent()));
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(Sensor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        } catch (FIPAException ex) {
            Logger.getLogger(Sensor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Sensor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public AID getComodo() {
        return aidComodo;
    }
}