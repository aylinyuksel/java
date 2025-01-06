package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_ManaCrystal extends Entity {

    GamePanel gp; 
    public static final String objName = "Mana Crystal"; 

    public OBJ_ManaCrystal(GamePanel gp) {
        super(gp); // Call to the parent class constructor
        this.gp = gp; // Store the GamePanel reference for later use

        type = type_pickupOnly; // Set the type of entity to pickup only (cannot be used directly)
        name = objName; 
        value = 1; // The amount of mana the crystal restores
        
        // Load the images for the mana crystal
        down1 = setup("/objects/manacrystal_full", gp.tileSize, gp.tileSize); 
        image = setup("/objects/manacrystal_full", gp.tileSize, gp.tileSize); 
        image2 = setup("/objects/manacrystal_blank", gp.tileSize, gp.tileSize); 
    }

    // use the mana crystal and restore mana
    public boolean use(Entity entity) {
        gp.playSE(2); 
        gp.ui.addMessage("Mana +" + value); // Display a message showing the mana increase
        entity.mana += value; // Increase the entity's mana by the crystal's value
        return true; 
    }
}
