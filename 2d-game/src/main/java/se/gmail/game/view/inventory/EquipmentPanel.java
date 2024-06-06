package se.gmail.game.view.inventory;

import java.awt.Dimension;

import javax.swing.JPanel;


public class EquipmentPanel extends JPanel {

    private int eqWidth = 0;
    private int eqHeight = 0;

    public EquipmentPanel() {
        eqWidth = 64*3;
        eqHeight = 64*3;
        this.add(new UiWallPanel(eqWidth, eqHeight));
        this.setPreferredSize(new Dimension(eqWidth, eqHeight));
        this.setVisible(true);
    }

    public int getEquipmentWidth() {
        return eqWidth;
    }

    public int getEquipmentHeight() {
        return eqHeight;
    }
}