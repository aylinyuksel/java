package object;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class OBJ_Coin_Bronze extends Entity {

    GamePanel gp; 
    public static final String objName = "Bronze Coin";

    public OBJ_Coin_Bronze(GamePanel gp) {
        super(gp); // Call to the parent class constructor
        this.gp = gp; // Store the GamePanel

        type = type_pickupOnly; // Set the type of entity to pickup only (cannot be used)
        name = objName; 
        value = 30; // amount added to the player's coin count
        down1 = setup("/objects/coin_bronze", gp.tileSize, gp.tileSize); // Load the image for the bronze coin
    }

    // use the coin 
    public boolean use(Entity entity) {
        gp.playSE(1); 
        gp.ui.addMessage("Coin +" + value); // Display message for coin collection
        entity.coin += value; // Add the coin value to the player's total coin count
        return true;
    }
}
