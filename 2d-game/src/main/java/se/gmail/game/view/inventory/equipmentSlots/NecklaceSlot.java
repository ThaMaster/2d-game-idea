package se.gmail.game.view.inventory.equipmentSlots;

import se.gmail.game.util.ImageLoader;

public class NecklaceSlot extends EquipmentSlot {

    @Override
    public void loadSlotImages() {
        setSlotFrame(ImageLoader.loadImage("/ui/equipment_window/necklace_slot.png"));
        setSlotFrameEmpty(ImageLoader.loadImage("/ui/equipment_window/necklace_slot_empty.png"));
    }
    
    
}
