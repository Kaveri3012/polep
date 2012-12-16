package polep.domain.agent;

import agentspring.agent.AbstractAgent;

public  class PolepAgent extends AbstractAgent {

    private double cash;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    } 
    @Override
    public String toString() {
        return getName();
    }

}
