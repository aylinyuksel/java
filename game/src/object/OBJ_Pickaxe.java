/*Ali Buğra Tekin 230610058
Aylin Yüksel 220610036
Aysha Mallamahmoud 230610013
Hazar Dardağan 220610010
Feyza Tiryaki 230610052
*/

package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Pickaxe extends Entity {
    public static final String objName = "Pickaxe"; 

    public OBJ_Pickaxe(GamePanel gp) {
        super(gp); // Call to the parent class constructor

        type = type_pickaxe; // Set the type of entity to pickaxe
        name = objName;
        down1 = setup("/objects/pickaxe", gp.tileSize, gp.tileSize); // Load the image for the pickaxe
        attackValue = 1; // Set the attack value 
        
        // set the attack area
        attackArea.width = 26; 
        attackArea.height = 26; 
        
        description = "[" + name + "]\nYou will dig it!"; // Set the description of the pickaxe
        knockBackPower = 1; // Set the knockback power 
        
        // Set the duration
        motion1_duration = 10; 
        motion2_duration = 20; 
    }
}
