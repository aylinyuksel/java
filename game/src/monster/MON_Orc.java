package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

import java.util.Random;

public class MON_Orc extends Entity {
    GamePanel gp; // needed due to different package
    public MON_Orc(GamePanel gp) {
    	
        super(gp); // Calls the parent class constructor
        this.gp = gp; // Initializes the GamePanel reference

        type = type_monster; // Set the type to monster
        name = "Orc"; 
        defaultSpeed = 3; 
        speed = defaultSpeed; // Set the current speed
        maxLife = 8; 
        life = maxLife; // Set current health
        attack = 0; 
        defense = 2; 
        exp = 8; // Experience points for defeating the orc
        knockBackPower = 5; 

        // Set the solid area for the orc
        solidArea.x = 4;
        solidArea.y = 4;
        solidArea.width = 40;
        solidArea.height = 44;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        // Set attack area dimensions (for melee attacks)
        attackArea.width = 48;
        attackArea.height = 48;

        // Set duration for the orc's motions 
        motion1_duration = 40; 
        motion2_duration = 85;

        getImage(); // idle animations
        getAttackImage(); // attack animations
    }

    public void getImage()
    {
        // Load images for different directions of the orc's idle animations
        up1 = setup("/monster/orc_up_1",gp.tileSize,gp.tileSize);
        up2 = setup("/monster/orc_up_2",gp.tileSize,gp.tileSize);
        down1 = setup("/monster/orc_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/monster/orc_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("/monster/orc_left_1",gp.tileSize,gp.tileSize);
        left2 = setup("/monster/orc_left_2",gp.tileSize,gp.tileSize);
        right1 = setup("/monster/orc_right_1",gp.tileSize,gp.tileSize);
        right2 = setup("/monster/orc_right_2",gp.tileSize,gp.tileSize);
    }

    public void getAttackImage()
    {
        // Load images for the orc's attack animations in different directions
        attackUp1 = setup("/monster/orc_attack_up_1",gp.tileSize, gp.tileSize * 2);
        attackUp2 = setup("/monster/orc_attack_up_2",gp.tileSize, gp.tileSize * 2);
        attackDown1 = setup("/monster/orc_attack_down_1",gp.tileSize, gp.tileSize * 2);
        attackDown2 = setup("/monster/orc_attack_down_2",gp.tileSize, gp.tileSize * 2);
        attackLeft1 = setup("/monster/orc_attack_left_1",gp.tileSize * 2, gp.tileSize);
        attackLeft2 = setup("/monster/orc_attack_left_2",gp.tileSize * 2, gp.tileSize);
        attackRight1 = setup("/monster/orc_attack_right_1",gp.tileSize * 2, gp.tileSize);
        attackRight2 = setup("/monster/orc_attack_right_2",gp.tileSize * 2, gp.tileSize);
    }

    public void setAction()
    {
        // If the orc is chasing the player
        if(onPath == true)
        {
            // Check if it should stop chasing
            checkStopChasingOrNot(gp.player, 15, 100);

            // Search for the player's position and move towards it
            searchPath(getGoalCol(gp.player), getGoalRow(gp.player));
        }
        else
        {
            // Check if the orc should start chasing
            checkStartChasingOrNot(gp.player, 5, 100);

            // Get a random direction to move if not chasing
            getRandomDirection(120);
        }

        // Check if the orc should attack
        if(attacking == false)
        {
            // If not attacking, check if it's time to attack the player
            checkAttackOrNot(30, gp.tileSize*4, gp.tileSize); // Small rate = More aggressive
        }
    }

    public void damageReaction() {
        actionLockCounter = 0; // Reset action lock after taking damage
        onPath = true; // Start chasing the player (aggro)
    }

    public void checkDrop()
    {
        // Generate a random number to determine the item drop
        int i = new Random().nextInt(100) + 1;

        // Drop items based on the random number
        if(i < 50)
        {
            dropItem(new OBJ_Coin_Bronze(gp)); 
        }
        if(i >= 50 && i < 75)
        {
            dropItem(new OBJ_Heart(gp)); 
        }
        if(i >= 75 && i < 100)
        {
            dropItem(new OBJ_ManaCrystal(gp)); 
        }
    }
}
