package monster;

import entity.Entity;

import main.GamePanel;

import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

import java.util.Random;

public class MON_RedSlime extends Entity {

    GamePanel gp; //needed due to different package
    public MON_RedSlime(GamePanel gp) {
    	
        super(gp); // Calls the constructor of the parent class (Entity)
        this.gp = gp; // Assign the GamePanel instance

        type = type_monster; // Set the entity type to monster
        name = "Red Slime"; 
        defaultSpeed = 2; 
        speed = defaultSpeed; // Set the current speed
        maxLife = 8; 
        life = maxLife; // Set current health
        attack = 0; 
        defense = 0; 
        exp = 4; // Experience points for defeating the Red Slime
        projectile = new OBJ_Rock(gp); // The Red Slime can shoot rocks (projectile)

        // Set the solid area for the Red Slime
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
        // Load images for different directions of the Red Slime's idle animations
        up1 = setup("/monster/redslime_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/monster/redslime_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/monster/redslime_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/monster/redslime_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/monster/redslime_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/monster/redslime_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/monster/redslime_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/monster/redslime_down_2", gp.tileSize, gp.tileSize);
    }

    public void setAction()
    {
        if (onPath == true) {
            // If chasing the player, check if it stops chasing
            checkStopChasingOrNot(gp.player, 15, 100);

            // Search for the player's position and move towards it
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));

            // Check if it should shoot a projectile (rock)
            checkShootOrNot(200, 30); // Check if it shoots a rock every 200ms
        } else {
            // If not chasing, check if it should start chasing the player
            checkStartChasingOrNot(gp.player, 5, 100);

            // Get a random direction if not chasing
            getRandomDirection(120); // Random movement
        }
    }

    public void damageReaction() {
        actionLockCounter = 0; // Reset action lock when taking damage
        onPath = true; // Start chasing the player (aggro)
    }

    public void checkDrop()
    {
        // Generate a random number between 1 and 100 to determine item drop
        int i = new Random().nextInt(100) + 1;

        // Drop items based on the generated random number
       
        if(i <= 50) {
            dropItem(new OBJ_Heart(gp));
        }
        if(i>50 && i < 100) {
            dropItem(new OBJ_ManaCrystal(gp)); 
        }
    }
}
