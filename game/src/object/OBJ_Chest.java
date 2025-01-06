package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Chest extends Entity {

    GamePanel gp;
    public static final String objName = "Chest";

    public OBJ_Chest(GamePanel gp) {
        super(gp); // Call to the parent class constructor
        this.gp = gp; // Store the GamePanel 

        type = type_obstacle;
        name = objName; 
        image = setup("/objects/chest", gp.tileSize, gp.tileSize); // Load the image for the closed chest
        image2 = setup("/objects/chest_opened", gp.tileSize, gp.tileSize); // Load the image for the opened chest
        down1 = image; // Set the initial image to the closed chest
        collision = true; // Enable collision for the chest

        // Set the solid area for collision detection (interactable chest)
        solidArea.x = 4;
        solidArea.y = 16;
        solidArea.width = 40;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
    }

    // set the loot that the chest will contain
    public void setLoot(Entity loot) {
        this.loot = loot; 
        setDialogue(); 
    }

    //set the dialogues for interacting with the chest
    public void setDialogue() {
        // Dialogue when the chest is opened and the player cannot carry the item
        dialogues[0][0] = "You open the chest and find a " + loot.name + "!\n...But you cannot carry any more!";
        // Dialogue when the chest is opened and the player obtains the item
        dialogues[1][0] = "You open the chest and find a " + loot.name + "!\nYou obtain the " + loot.name + "!";
        // Dialogue when the chest is empty
        dialogues[2][0] = "It's empty.";
    }

    // interact with the chest
    public void interact() {
        if (opened == false) { 
            gp.playSE(3); 

            // Check if the player can obtain the loot from the chest
            if (gp.player.canObtainItem(loot) == false) {
                startDialogue(this, 0); 
            } else {
                startDialogue(this, 1); 
                down1 = image2; // Change the sprite to the opened chest
                opened = true; // Mark the chest as opened
            }
        } else {
            startDialogue(this, 2); 
        }
    }
}
