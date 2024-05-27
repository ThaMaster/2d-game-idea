package se.gmail.game.model.state;

import java.util.ArrayList;

public class StateMachine {

    private ArrayList<State> states = new ArrayList<>();
    private State currentState;

    public StateMachine(State s) {
        states.add(s);
        this.currentState = s;
    }

    public void addState(State s) {
        states.add(s);
    }

    public void update() {
        if(!currentState.shouldTransition()) {
            currentState.update();
            return;
        }

        String nextState = currentState.getNextStateName();
        for(State s : states) {
            if(s.getStateName().equals(nextState)) {
                currentState.exit();
                s.enter();
                currentState = s;
            }
        }
    }

    public String getCurrentStateName() {
        return this.currentState.getStateName();
    }
}
