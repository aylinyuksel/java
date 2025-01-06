package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door_Iron extends Entity {

    GamePanel gp;
    public static final String objName = "Iron Door"; 

    public OBJ_Door_Iron(GamePanel gp) {
        super(gp); // Call to the parent class constructor
        this.gp = gp; // Store the GamePanel

        type = type_obstacle; 
        name = objName;
        down1 = setup("/objects/door_iron", gp.tileSize, gp.tileSize); // Load the image for the iron door
        collision = true; // Door has a solid collision

        // Define the solid collision area of the door
        solidArea.x = 0;
        solidArea.y = 16;
        solidArea.width = 48;
        solidArea.height = 32;

        // Set default collision area positions
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue(); // Initialize the dialogue for interaction
    }

    // set the dialogue when interacting with the iron door
    public void setDialogue() {
        dialogues[0][0] = "It won't budge."; 
    }

    // interacting with the iron door
    public void interact() {
        startDialogue(this, 0); 
    }
}
