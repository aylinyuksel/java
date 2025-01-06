package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heart extends Entity {

    GamePanel gp;

    public static final String objName = "Heart";

    public OBJ_Heart(GamePanel gp) {
        super(gp); // Call to the parent class constructor to initialize shared properties
        this.gp = gp; // Storing the GamePanel 
        type = type_pickupOnly; 
        name = objName; 
        value = 2; // the healing value of one heart
        
        //images of the heart
        down1 = setup("/objects/heart_full", gp.tileSize, gp.tileSize); 
        image = setup("/objects/heart_full", gp.tileSize, gp.tileSize); 
        image2 = setup("/objects/heart_half", gp.tileSize, gp.tileSize); 
        image3 = setup("/objects/heart_blank", gp.tileSize, gp.tileSize); 
    }

    // using the heart object and restore life to an entity
    public boolean use(Entity entity) {
        gp.playSE(2); // Play a sound effect
        gp.ui.addMessage("Life +" + value); // Display a message 
        entity.life += value; // Increase the life of the entity by the heart's value
        return true; 
    }
}
