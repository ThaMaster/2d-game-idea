package se.gmail.game.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

public class KeyHandler implements KeyListener {

    HashMap<Integer, Boolean> keyMap;
    HashMap<Integer, Boolean> keyToggleMap;

    int keysActive = 0;

    public KeyHandler() {
        this.keyMap = new HashMap<>();
        this.keyToggleMap = new HashMap<>();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(keyToggleMap.containsKey(e.getKeyCode())) {
            boolean keyStatus = !keyToggleMap.get(e.getKeyCode());
            keyToggleMap.put(e.getKeyCode(), !keyStatus);
        } else {
            keyMap.put(e.getKeyCode(), true);
        }
        keysActive++;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(!keyToggleMap.containsKey(e.getKeyCode())) {
            keyMap.put(e.getKeyCode(), false);
        }
        keysActive--;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public boolean isKeyActive(int keyCode) {
        boolean keyStatus;
        if(keyToggleMap.containsKey(keyCode)) {
            keyStatus = keyToggleMap.get(keyCode);
        } else if(keyMap.containsKey(keyCode)) {
            keyStatus = keyMap.get(keyCode);
        } else {
            keyStatus = false;
        }
        return keyStatus;
    }

    public void setToggleKeys(ArrayList<Integer> keyCodes) {
        for (int keyCode : keyCodes) {
            setToggleKey(keyCode);
        }
    }

    public void setToggleKey(int keyCode) {
        if(!keyToggleMap.containsKey(keyCode)) {
            keyToggleMap.put(keyCode, false);
        }
    }

    public void removeToggleKeys(ArrayList<Integer> keyCodes) {
        for(int keyCode : keyCodes) {
            removeToggleKey(keyCode);
        }
    }

    public void removeToggleKey(int keyCode) {
        if(keyToggleMap.containsKey(keyCode)) {
            keyToggleMap.remove(keyCode);
        }
    }

    public boolean keysActive() {
        return keysActive != 0;
    }

    public boolean movementKeysActive() {
        if(keysActive()) {
            for (int keyCode : keyMap.keySet()) {
                if(keyMap.get(keyCode)) {
                    return true;
                }
            }
        } 
        return false;
    }
}