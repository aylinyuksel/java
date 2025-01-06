package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {

    GamePanel gp; 
    public static final String objName = "Red Potion"; 

    public OBJ_Potion_Red(GamePanel gp) {
        super(gp); // Call to the parent class constructor
        this.gp = gp; // Store the GamePanel reference for later use

        type = type_consumable; // Set the type of entity to consumable (can be picked)
        name = objName; 
        value = 5; // amount of life the potion restores
        down1 = setup("/objects/potion_red", gp.tileSize, gp.tileSize); // Load the image for the red potion
        description = "[" + name + "]\nHeals your life by " + value + "."; // Set the description of the potion
        stackable = true; 

        setDialogue(); 
    }

    // set the dialogue displayed when using the potion
    public void setDialogue() {
        dialogues[0][0] = "You drink the " + name + "!\n" + "Your life has been recovered by " + value + "."; 
    }

    // use the potion and restore health
    public boolean use(Entity entity) {
        startDialogue(this, 0); 
        entity.life += value; // Increase the entity's life by the potion's value
        gp.playSE(2);
        return true; // Return true indicating the potion was used successfully
    }
}
