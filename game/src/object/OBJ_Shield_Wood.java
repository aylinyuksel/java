/*Ali Buğra Tekin 230610058
Aylin Yüksel 220610036
Aysha Mallamahmoud 230610013
Hazar Dardağan 220610010
Feyza Tiryaki 230610052
*/

package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Wood extends Entity {

    public static final String objName = "Wood Shield"; 

    public OBJ_Shield_Wood(GamePanel gp) {
        super(gp);

        type = type_shield; // Set the type of entity to shield
        name = objName; 
        down1 = setup("/objects/shield_wood", gp.tileSize, gp.tileSize); // Load the image for the wood shield
        defenseValue = 1; // Set the defense value of the shield
        description = "[" + name + "]\nMade by wood."; // Set the description of the shield
    }
}
