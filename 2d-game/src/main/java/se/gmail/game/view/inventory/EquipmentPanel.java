package se.gmail.game.view.inventory;

import java.awt.AlphaComposite;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.JPanel;

import se.gmail.game.util.ImageLoader;
import se.gmail.game.view.inventory.equipmentSlots.*;

public class EquipmentPanel extends JPanel {

    private int eqWidth = 0;
    private int eqHeight = 0;

    private int xOffset = 16;
    private int yOffset = 16;

    private HashMap<String, EquipmentSlot> eqSlots = new HashMap<>();

    private BufferedImage eqFrame;

    public EquipmentPanel() {
        this.eqFrame = ImageLoader.loadImage("/ui/equipment_window/equipment_frame.png");
        initEquipmentSlots();

        this.eqWidth = (eqSlots.get("mainhand").getSlotWidth() * 2) + eqSlots.get("chest").getSlotWidth() + 96;
        this.eqHeight = eqSlots.get("helmet").getSlotHeight() + eqSlots.get("chest").getSlotHeight() + eqSlots.get("belt").getSlotHeight() + eqSlots.get("pants").getSlotHeight() + 112;

        this.setPreferredSize(new Dimension(eqWidth, eqHeight));
        this.setVisible(true);
        this.setOpaque(false);
    }

    public void initEquipmentSlots() {
        this.eqSlots.put("helmet", new HelmetSlot());
        this.eqSlots.put("chest", new ChestSlot());        
        this.eqSlots.put("belt", new BeltSlot());
        this.eqSlots.put("pants", new PantsSlot());
        this.eqSlots.put("boots", new BootsSlot());
        this.eqSlots.put("gloves", new GlovesSlot());
        this.eqSlots.put("mainhand", new MainHandSlot());
        this.eqSlots.put("offhand", new OffHandSlot());
        this.eqSlots.put("gem1", new GemSlot());
        this.eqSlots.put("gem2", new GemSlot());
        this.eqSlots.put("gem3", new GemSlot());
        this.eqSlots.put("gem4", new GemSlot());
        this.eqSlots.put("ring1", new RingSlot());
        this.eqSlots.put("ring2", new RingSlot());
        this.eqSlots.put("necklace1", new NecklaceSlot());
        this.eqSlots.put("necklace2", new NecklaceSlot());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        g2.setComposite(AlphaComposite.SrcOver);
        g2.drawImage(eqFrame, 0, 0, eqWidth, eqHeight, this);

        int currentH = yOffset*2;
        EquipmentSlot eSlot;
        eSlot = eqSlots.get("necklace1");
        eSlot.draw(g2, xOffset*2, currentH);

        eSlot = eqSlots.get("necklace2");
        eSlot.draw(g2, xOffset*2 + eqSlots.get("necklace1").getSlotWidth(), currentH);

        eSlot = eqSlots.get("ring1");
        eSlot.draw(g2, eqWidth - eSlot.getSlotWidth()*2 - (xOffset*2), currentH);

        eSlot = eqSlots.get("ring2");
        eSlot.draw(g2, eqWidth - eSlot.getSlotWidth() - (xOffset*2), currentH);

        eSlot = eqSlots.get("helmet");
        eSlot.draw(g2, (int)(eqWidth/2) - (eSlot.getSlotWidth()/2), currentH);
        currentH += eSlot.getSlotHeight() + yOffset;

        eSlot = eqSlots.get("mainhand");
        eSlot.draw(g2, xOffset*2, currentH);

        eSlot = eqSlots.get("offhand");
        eSlot.draw(g2, eqWidth - eSlot.getSlotWidth() - (xOffset*2),currentH);

        eSlot = eqSlots.get("chest");
        eSlot.draw(g2, (int)(eqWidth/2) - (eSlot.getSlotWidth()/2), currentH);
        currentH += (eSlot.getSlotHeight() + yOffset);

        eSlot = eqSlots.get("gloves");
        eSlot.draw(g2, xOffset*2, currentH);

        eSlot = eqSlots.get("boots");
        eSlot.draw(g2, eqWidth - eSlot.getSlotWidth() - xOffset*2, currentH);
        
        eSlot = eqSlots.get("belt");
        eSlot.draw(g2, (int)(eqWidth/2) - (eSlot.getSlotWidth()/2), currentH);
        currentH += eSlot.getSlotHeight() + yOffset;

        eSlot = eqSlots.get("pants");
        eSlot.draw(g2, (int)(eqWidth/2) - (eSlot.getSlotWidth()/2), currentH);

        eSlot = eqSlots.get("gem1");
        eSlot.draw(g2, xOffset*2, eqHeight - eSlot.getSlotHeight() - yOffset*2);

        eSlot = eqSlots.get("gem2");
        eSlot.draw(g2, xOffset*2 + eqSlots.get("gem1").getSlotWidth(), eqHeight - eSlot.getSlotHeight() - yOffset*2);

        eSlot = eqSlots.get("gem3");
        eSlot.draw(g2, eqWidth - eSlot.getSlotWidth()*2 - (xOffset*2), eqHeight - eSlot.getSlotHeight() - yOffset*2);

        eSlot = eqSlots.get("gem4");
        eSlot.draw(g2, eqWidth - eSlot.getSlotWidth() - (xOffset*2), eqHeight - eSlot.getSlotHeight() - yOffset*2);
    }

    public int getWindowWidth() {
        return eqWidth;
    }

    public int getWindowHeight() {
        return eqHeight;
    }

    public void setEquipment(String slot, BufferedImage itemImage) {
        this.eqSlots.get(slot).setItemImage(itemImage);
    }

    public void removeEquipment(String slot) {
        this.eqSlots.get(slot).removeItemImage();
    }

    public String getHoveringSlot(int xPos, int yPos) {
        for (String key : eqSlots.keySet()) {
            if(eqSlots.get(key).getSlotRect().contains(xPos, yPos)) {
                return key;
            }
        }
        return "none";
    }
}