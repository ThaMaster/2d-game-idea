package se.gmail.game.controller;

import se.gmail.game.view.MainFrame;

public class GameController {

    KeyHandler keyHandler;
    public GameController() {
        MainFrame window = new MainFrame();
        keyHandler = new KeyHandler();
    }

}
