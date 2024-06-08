package se.gmail.game.view.inventory.equipmentSlots;

import se.gmail.game.util.ImageLoader;

public class PantsSlot extends EquipmentSlot {

    @Override
    public void loadSlotImages() {
        setSlotFrame(ImageLoader.loadImage("/ui/equipment_window/pants_slot.png"));
        setSlotFrameEmpty(ImageLoader.loadImage("/ui/equipment_window/pants_slot_empty.png"));
    }
        
}
