/*Ali Buğra Tekin 230610058
Aylin Yüksel 220610036
Aysha Mallamahmoud 230610013
Hazar Dardağan 220610010
Feyza Tiryaki 230610052
*/

package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity {

    public static final String objName = "Lantern";

    public OBJ_Lantern(GamePanel gp) {
        super(gp); // Call to the parent class constructor to initialize shared properties

        type = type_light; // Setting the type of entity to light
        name = objName; 
        down1 = setup("/objects/lantern", gp.tileSize, gp.tileSize); // Loading the lantern's image
        description = "[Lantern]\nIlluminates your \nsurroundings."; // Description of the lantern
        lightRadius = 350; //the radius of the light that the lantern produce
    }
}
