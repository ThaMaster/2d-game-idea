package se.gmail.game.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class KeyHandler implements KeyListener {

    HashMap<Integer, Boolean> keyMap;
    public KeyHandler() {
        this.keyMap = new HashMap<>();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyMap.put(e.getKeyCode(), true);
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keyMap.put(e.getKeyCode(), false);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    public boolean isUpPressed() {
        return keyMap.containsKey(KeyEvent.VK_W) && keyMap.get(KeyEvent.VK_W);
    }
}
