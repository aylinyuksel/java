/*Ali Buğra Tekin 230610058
Aylin Yüksel 220610036
Aysha Mallamahmoud 230610013
Hazar Dardağan 220610010
Feyza Tiryaki 230610052
*/

package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {

    public static final String objName = "Woodcutter's Axe"; 

    public OBJ_Axe(GamePanel gp) {
        super(gp); // Call to the parent class constructor

        type = type_axe; 
        name = objName; 
        down1 = setup("/objects/axe", gp.tileSize, gp.tileSize); // Load the image for the axe
        attackValue = 2; 
        
        //setting attacks area
        attackArea.width = 26; 
        attackArea.height = 26; 
        description = "[" + name + "]\nA bit rusty but still \ncan cut some trees."; // Description of the axe
        knockBackPower = 5; 
        
        //setting the duration for motions
        motion1_duration = 20;
        motion2_duration = 40;
    }
}
