package monster;

import entity.Entity;
import main.GamePanel;
import object.OBJ_Coin_Bronze;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

import java.util.Random;

public class MON_Bat extends Entity {

    GamePanel gp; // required due to different package
    public MON_Bat(GamePanel gp) {
    	
        super(gp); // Calls the parent class constructor
        this.gp = gp; // Initializes the GamePanel reference

        type = type_monster; // type of entity to monster
        name = "Bat";
        defaultSpeed = 4; 
        speed = defaultSpeed; // Set current speed
        maxLife = 1; 
        life = maxLife; // Sets current health
        attack = 0; 
        defense = 0; 
        exp = 0; 

        // Set the collision area for the bat
        solidArea.x = 3;
        solidArea.y = 15;
        solidArea.width = 42;
        solidArea.height = 21;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage(); 
    }

    public void getImage()
    {
        // Loads different directions of the bat using images
        up1 = setup("/monster/bat_down_1",gp.tileSize,gp.tileSize);
        up2 = setup("/monster/bat_down_2",gp.tileSize,gp.tileSize);
        down1 = setup("/monster/bat_down_1",gp.tileSize,gp.tileSize);
        down2 = setup("/monster/bat_down_2",gp.tileSize,gp.tileSize);
        left1 = setup("/monster/bat_down_1",gp.tileSize,gp.tileSize);
        left2 = setup("/monster/bat_down_2",gp.tileSize,gp.tileSize);
        right1 = setup("/monster/bat_down_1",gp.tileSize,gp.tileSize);
        right2 = setup("/monster/bat_down_2",gp.tileSize,gp.tileSize);
    }

    public void setAction()
    {
        // If the bat is on a path, it will follow it, otherwise it moves randomly
        if(onPath == true) {
            
        }
        else {
            getRandomDirection(10); // Random movement in one of 10 directions
        }
    }

    public void damageReaction() {
        actionLockCounter = 0; // when damaged reset
    }

    public void checkDrop()
    {
        // generating a random number between 1 and 100
        int i = new Random().nextInt(100) + 1;

        // Set item drop chances based on random number
        if(i < 50) {
            dropItem(new OBJ_Coin_Bronze(gp)); 
        }
        if(i >= 50 && i < 75) {
            dropItem(new OBJ_Heart(gp));
        }
        if(i >= 75 && i < 100) {
            dropItem(new OBJ_ManaCrystal(gp)); 
        }
    }
}

