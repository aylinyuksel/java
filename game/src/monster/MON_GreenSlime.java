package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

import java.util.Random;

public class MON_GreenSlime extends Entity {

    GamePanel gp; //needed due to different package
    public MON_GreenSlime(GamePanel gp) {
    	
        super(gp); // Calls the parent class constructor
        this.gp = gp; // Initialize GamePanel reference

        type = type_monster; // Set type to monster
        name = "Dungeon Slime"; 
        defaultSpeed = 1; 
        speed = defaultSpeed; // Set current speed
        maxLife = 4; 
        life = maxLife; // Set current health
        attack = 0; 
        defense = 0; 
        exp = 2; // Experience points for defeating the monster

        // Set collision area for the slime
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }

    public void getImage()
    {
        // Load the slime's images for different movement directions
        up1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/greenslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/greenslime_down_2", gp.tileSize, gp.tileSize);
    }

    public void setAction()
    {
        // If the slime is following a path, continue chasing the player
        if (onPath == true) {
            // Check if the slime should stop chasing
            checkStopChasingOrNot(gp.player, 15, 100);
            
            // Search for the player's location to set a new path
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
        } else {
            // If not chasing, check if it should start chasing
            checkStartChasingOrNot(gp.player, 5, 100);
            
            // Get a random direction if not on path
            getRandomDirection(120);
        }
    }

    public void damageReaction() {
        actionLockCounter = 0; // Reset action lock after taking damage
        onPath = true; // Slime gets aggro and starts chasing the player
    }

    public void checkDrop()
    {
        // Generate a random number between 1 and 100 to determine the item drop
        int i = new Random().nextInt(100) + 1;

        // Set item drop based on the random number
        if (i < 50) {
            dropItem(new OBJ_Coin_Bronze(gp)); 
        }
        if (i >= 50 && i < 75) {
            dropItem(new OBJ_Heart(gp)); 
        }
        if (i >= 75 && i < 100) {
            dropItem(new OBJ_ManaCrystal(gp)); 
        }
    }
}
