package se.gmail.game.view.inventory;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.RepaintManager;

import se.gmail.game.model.systems.inventory.Inventory;
public class InventoryWindow extends JFrame {

    InventoryPanel invPanel;

    public InventoryWindow(Inventory inv) {

        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setTitle("Stock Market");
        RepaintManager.currentManager(this).setDoubleBufferingEnabled(true);
        this.setLayout(new BorderLayout());

        invPanel = new InventoryPanel(inv);
        
        this.add(invPanel, BorderLayout.CENTER);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
