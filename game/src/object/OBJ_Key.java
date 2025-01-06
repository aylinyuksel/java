package object;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class OBJ_Key extends Entity {

    GamePanel gp;
    public static final String objName = "Key";

    public OBJ_Key(GamePanel gp) {
        super(gp); // Call to the parent class constructor
        this.gp = gp; // Store the GamePanel reference for later use
        type = type_consumable; // Set the type of entity to consumable (can be picked)
        name = objName; 
        down1 = setup("/objects/key", gp.tileSize, gp.tileSize); // Load the image for the key
        description = "[" + name + "]\nIt opens a door."; // Set the description of the key
        stackable = true;

        setDialogue();
    }

    // set the dialogue when using the key
    public void setDialogue() {
        dialogues[0][0] = "You use the " + name + " and open the door.";
    }

    // use the key and interact with a door
    public boolean use(Entity entity) {
        int objIndex = getDetected(entity, gp.obj, "Door"); // Check if a door is near the user
        if (objIndex != 999) { // If a door is detected
            startDialogue(this, 0); 
            gp.playSE(3); 
            gp.obj[gp.currentMap][objIndex] = null; // Remove the door object (open the door)
            return true; 
        } else {
            startDialogue(this, 1); 
            return false; // Return false if no door was found to open
        }
    }
}
