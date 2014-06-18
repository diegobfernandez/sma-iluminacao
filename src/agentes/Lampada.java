package agentes;

import jade.core.AID;
import jade.core.Agent;

public class Lampada extends Agent {
    private LampadaInfo lampadaInfo;
    private boolean acesa;
    
    public boolean estaAcesa() {
        return acesa;
    }
    
    @Override
    protected void setup() {
        Object[] args = getArguments();
        lampadaInfo = new LampadaInfo(Double.parseDouble(args[1].toString()), Integer.parseInt(args[2].toString()));
        AID aidComodo = new AID(args[0].toString(), AID.ISLOCALNAME);
        
        this.addBehaviour(new NotificaLampadaAdicionadaBehaviour(aidComodo, this));
        this.addBehaviour(new AlteraEstadoLampadaBehaviour(this));
    }

    LampadaInfo getLampadaInfo() {
        return lampadaInfo;
    }

    public void setEstaAcesa(boolean acesa) {
        this.acesa = acesa;
        System.out.println("Lampada " + getName() + " está " + acesa);
    }
}
