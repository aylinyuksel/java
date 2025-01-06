package object;

import entity.Entity;
import entity.Projectile;
import main.GamePanel;

import java.awt.*;

public class OBJ_Rock extends Projectile {

    GamePanel gp;
    public static final String objName = "Rock";

    public OBJ_Rock(GamePanel gp) {
        super(gp); // Call to the parent class constructor
        this.gp = gp; // Store the GamePanel reference for later use

        name = objName;
        speed = 5;
        maxLife = 60; // (after 60 frames, the projectile disappears)
        life = maxLife; // Set the initial life of the projectile
        attack = 2; 
        useCost = 1; // Cost of using the projectile (1 ammo)
        alive = false; // Initially is not alive
        getImage(); 
        knockBackPower = 1;
    }

    // set the images for the projectile's directions
    public void getImage() {
        up1 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize); 
        up2 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize); 
        down1 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize); 
        left1 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize); 
        left2 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize); 
        right1 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize); 
        right2 = setup("/projectile/rock_down_1", gp.tileSize, gp.tileSize);
    }

    // check if the user has enough resources (ammo) to use the rock projectile
    public boolean haveResource(Entity user) {
        boolean haveResource = false; // Flag to track if user has enough resources
        if (user.ammo >= useCost) { 
            haveResource = true; // Set flag to true
        }
        return haveResource; 
    }

    // subtract resources (ammo) after using the projectile
    public void subtractResource(Entity user) {
        user.ammo -= useCost; // Subtract the cost of using the projectile from the user's ammo
    }

    // get the color of the particle effect when the projectile is used
    public Color getParticleColor() {
        Color color = new Color(40, 50, 0); // dark greenish 
        return color; 
    }

    // get the size of the particle effect (in pixels)
    public int getParticleSize() {
        int size = 10; // in pixels
        return size;
    }

    // get the speed of the particle effect
    public int getParticleSpeed() {
        int speed = 1; 
        return speed; 
    }

    // get the maximum life duration of the particle effect (in frames)
    public int getParticleMaxLife() {
        int maxLife = 20; //  20 frames
        return maxLife; 
    }
}
