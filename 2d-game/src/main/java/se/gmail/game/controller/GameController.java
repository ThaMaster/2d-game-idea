package se.gmail.game.controller;

import se.gmail.game.view.MainFrame;

public class GameController {

    UpdateHandler updateHandler;
    public GameController() {
        KeyHandler keyHandler = new KeyHandler();
        MainFrame window = new MainFrame();
        window.getGamePanel().addKeyListener(keyHandler);
        this.updateHandler = new UpdateHandler(keyHandler, window);
        updateHandler.startGameThread();
    }
}
