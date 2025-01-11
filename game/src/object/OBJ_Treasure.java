/*Ali Buğra Tekin 230610058
Aylin Yüksel 220610036
Aysha Mallamahmoud 230610013
Hazar Dardağan 220610010
Feyza Tiryaki 230610052
*/

package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Treasure extends Entity {

    GamePanel gp; 
    public static final String objName = "Treasure";

    public OBJ_Treasure(GamePanel gp) {
        super(gp); // Call to the parent class constructor
        this.gp = gp; // Store the GamePanel reference for later use

        type = type_pickupOnly; // Set the type of entity to pickup only (cannot be used)
        name = objName;
        down1 = setup("/objects/treasure", gp.tileSize, gp.tileSize); // Load the image for the treasure
        setDialogues(); 
    }

    // set the dialogues for when the Blue Heart is picked up
    public void setDialogues() {
    	dialogues[0][0] = "You have won the treasure!";
        
    }

    // use the Blue Heart object
    public boolean use(Entity entity) {
        gp.gameState = gp.cutsceneState; // Change the game state to cutscene state
        gp.csManager.sceneNum = gp.csManager.ending; // Trigger the ending scene in the cutscene manager
        return true;
    }

}
