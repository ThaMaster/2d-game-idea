package se.gmail.game.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;


/**
 * 
 */
public class KeyHandler implements KeyListener {

    HashMap<Character, Boolean> keyMap;
    HashMap<Character, Boolean> keyToggleMap;

    int keysActive = 0;

    public KeyHandler() {
        this.keyMap = new HashMap<>();
        this.keyToggleMap = new HashMap<>();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(keyToggleMap.containsKey(e.getKeyChar())) {
            boolean keyStatus = keyToggleMap.get(e.getKeyChar());
            keyToggleMap.put(e.getKeyChar(), !keyStatus);
        } else {
            keyMap.put(e.getKeyChar(), true);
        }
        keysActive++;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(!keyToggleMap.containsKey(e.getKeyChar())) {
            keyMap.put(e.getKeyChar(), false);
        }
        keysActive--;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public boolean isKeyActive(char key) {
        boolean keyStatus;
        if(keyToggleMap.containsKey(key)) {
            keyStatus = keyToggleMap.get(key);
        } else if(keyMap.containsKey(key)) {
            keyStatus = keyMap.get(key);
        } else {
            keyStatus = false;
        }
        return keyStatus;
    }

    public void setToggleKeys(ArrayList<Character> keys) {
        for (char key : keys) {
            setToggleKey(key);
        }
    }

    public void setToggleKey(char key) {
        if(!keyToggleMap.containsKey(key)) {
            keyToggleMap.put(key, false);
        }
    }

    public void removeToggleKeys(ArrayList<Character> keys) {
        for(char key : keys) {
            removeToggleKey(key);
        }
    }

    public void removeToggleKey(char key) {
        if(keyToggleMap.containsKey(key)) {
            keyToggleMap.remove(key);
        }
    }

    public boolean keysActive() {
        return keysActive != 0;
    }

    public boolean movementKeysActive() {
        if(keysActive()) {
            for (char key : keyMap.keySet()) {
                if(key == 'w' || key == 's' || key == 'a' || key == 'd') {
                    if(keyMap.get(key)) {
                        return true;
                    }
                }
            }
        } 
        return false;
    }
}