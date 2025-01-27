/*Ali Buğra Tekin 230610058
Aylin Yüksel 220610036
Aysha Mallamahmoud 230610013
Hazar Dardağan 220610010
Feyza Tiryaki 230610052
*/

package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Blue extends Entity {

    public static final String objName = "Blue Shield"; 

    public OBJ_Shield_Blue(GamePanel gp) {
        super(gp); // Call to the parent class constructor

        type = type_shield; 
        name = objName; 
        down1 = setup("/objects/shield_blue", gp.tileSize, gp.tileSize); // Load the image for the blue shield
        defenseValue = 2; // Set the defense value
        description = "[" + name + "]\nA shiny blue shield."; // Set the description of the shield
    }
}
