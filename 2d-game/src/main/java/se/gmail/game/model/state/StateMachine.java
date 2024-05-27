package se.gmail.game.model.state;

import java.util.ArrayList;

public class StateMachine {

    private ArrayList<State> states = new ArrayList<>();
    private State currentState;

    public StateMachine(State s) {
        this.currentState = s;
    }

    public void update() {

        if(!currentState.doTransition()) {
            currentState.update();
            return;
        }

        String nextState = currentState.getNextStateName();
        for(State s : states) {
            if((s.getStateName().toLowerCase()).equals(nextState.toLowerCase())) {
                currentState.exit();
                s.enter();
                currentState = s;
            }
        }
    }
}
