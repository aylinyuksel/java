package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_BlueHeart extends Entity {

    GamePanel gp; 
    public static final String objName = "Blue Heart";

    public OBJ_BlueHeart(GamePanel gp) {
        super(gp); // Call to the parent class constructor
        this.gp = gp; // Store the GamePanel reference for later use

        type = type_pickupOnly; // Set the type of entity to pickup only (cannot be used)
        name = objName;
        down1 = setup("/objects/blueheart", gp.tileSize, gp.tileSize); // Load the image for the Blue Heart
        setDialogues(); 
    }

    // set the dialogues for when the Blue Heart is picked up
    public void setDialogues() {
        dialogues[0][0] = "You pick up a beautiful blue gem."; 
        dialogues[0][1] = "You find the Blue Heart, the legendary treasure!"; 
    }

    // use the Blue Heart object
    public boolean use(Entity entity) {
        gp.gameState = gp.cutsceneState; // Change the game state to cutscene state
        gp.csManager.sceneNum = gp.csManager.ending; // Trigger the ending scene in the cutscene manager
        return true;
    }

}
