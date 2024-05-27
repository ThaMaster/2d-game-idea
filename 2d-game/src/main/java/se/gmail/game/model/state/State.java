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

    public boolean shouldTransition() {
        return this.transition;
    }

    public void setTransition(boolean b) {
        this.transition = b;
    }

    public String getStateName() {
        return this.stateName;
    }

    public String getNextStateName() {
        return this.nextState;
    }

    public void setNextStateName(String nextState) {
        this.nextState = nextState;
    }
}
