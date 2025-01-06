package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity {

    public static final String objName = "Normal Sword";

    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp); // Call to the parent class constructor to initialize shared properties

        type = type_sword; // Setting the type of entity to sword
        name = objName; 
        down1 = setup("/objects/sword_normal", gp.tileSize, gp.tileSize); // Loading the sword's image
        attackValue = 1; 
        attackArea.width = 36; 
        attackArea.height = 36; 
        description = "[" + name + "]\nAn old sword."; // Description of the sword
        knockBackPower = 3; 
        motion1_duration = 5; // Duration of a motion (swing animation)
        motion2_duration = 25; 
    }
}
