/*Ali Buğra Tekin 230610058
Aylin Yüksel 220610036
Aysha Mallamahmoud 230610013
Hazar Dardağan 220610010
Feyza Tiryaki 230610052
*/

package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends Entity {

    GamePanel gp;
    public static final String objName = "Door";

    public OBJ_Door(GamePanel gp) {
        super(gp); // Call to the parent class constructor
        this.gp = gp; // Store the GamePanel

        type = type_obstacle; 
        name = objName; 
        down1 = setup("/objects/door", gp.tileSize, gp.tileSize); // Load the image
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

    // set the dialogue when interacting with the door
    public void setDialogue() {
        dialogues[0][0] = "You need a key to open this."; 
    }

    // interacting with the door
    public void interact() {
        startDialogue(this, 0);
    }
}
