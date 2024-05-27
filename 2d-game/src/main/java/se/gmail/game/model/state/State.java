package se.gmail.game.model.state;

public abstract class State {

    private boolean transition = false;
    private String nextState = "";
    private String stateName;

    public State(String stateName) {
        this.stateName = stateName;
    }

    public abstract void enter();
    public abstract void exit();
    public abstract void update();

    public boolean doTransition() {
        return this.transition;
    }

    public String getStateName() {
        return this.stateName;
    }

    public String getNextStateName() {
        return this.nextState;
    }
}
