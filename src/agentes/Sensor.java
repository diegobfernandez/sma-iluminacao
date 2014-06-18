package agentes;

import models.Sentido;
import behaviours.SensorRecebeLuminosidadeExternaBehaviour;
import behaviours.SensorRecebeLuminosidadeInternaBehaviour;
import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            
            this.addBehaviour(new SensorRecebeLuminosidadeExternaBehaviour(this));
            //this.addBehaviour(new SensorRecebeLuminosidadeInternaBehaviour(this));
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