/*Ali Buğra Tekin 230610058
Aylin Yüksel 220610036
Aysha Mallamahmoud 230610013
Hazar Dardağan 220610010
Feyza Tiryaki 230610052
*/

package monster;

import data.Progress;

import entity.Entity;
import main.GamePanel;

import object.OBJ_Door_Iron;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

import java.util.Random;

public class MON_SkeletonLord extends Entity {
    GamePanel gp; // needed to being in a different package

    public static final String monName = "Skeleton Lord";

    public MON_SkeletonLord(GamePanel gp) {
    	
        super(gp); // Call the constructor of the parent class (Entity)
        this.gp = gp; // Initialize GamePanel reference


        type = type_monster; // Type is monster
        boss = true; 
        name = monName; 
        defaultSpeed = 1; 
        speed = defaultSpeed; // Set current speed
        maxLife = 5; 
        life = maxLife; // Set current health
        attack = 16; 
        defense = 3; 
        exp = 40; // Set experience gained upon defeat
        knockBackPower = 5; 
        sleep = true; // Set sleep state when the player isnt attacking

        // Set the size and solid area for the monster
        int size = gp.tileSize * 5;
        solidArea.x = 48;
        solidArea.y = 48;
        solidArea.width = size - 48*2;
        solidArea.height = size - 48;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        
        // Set attack range
        attackArea.width = 170; 
        attackArea.height = 170; 

        // Set durations for idle animations
        motion1_duration = 25;
        motion2_duration = 50;
        
        getImage();
        getAttackImage();
        setDialogue();
    }

    // load images for idle animations
    public void getImage() {
        int i = 5; // Scale
        if (inRage == false) {
            // Load images for when the monster is not in rage
            up1 = setup("/monster/skeletonlord_up_1", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("/monster/skeletonlord_up_2", gp.tileSize * i, gp.tileSize * i);
            down1 = setup("/monster/skeletonlord_down_1", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("/monster/skeletonlord_down_2", gp.tileSize * i, gp.tileSize * i);
            left1 = setup("/monster/skeletonlord_left_1", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("/monster/skeletonlord_left_2", gp.tileSize * i, gp.tileSize * i);
            right1 = setup("/monster/skeletonlord_right_1", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("/monster/skeletonlord_right_2", gp.tileSize * i, gp.tileSize * i);
        }
        if (inRage == true) {
            // Load images for when the monster is in rage mode
            up1 = setup("/monster/skeletonlord_phase2_up_1", gp.tileSize * i, gp.tileSize * i);
            up2 = setup("/monster/skeletonlord_phase2_up_2", gp.tileSize * i, gp.tileSize * i);
            down1 = setup("/monster/skeletonlord_phase2_down_1", gp.tileSize * i, gp.tileSize * i);
            down2 = setup("/monster/skeletonlord_phase2_down_2", gp.tileSize * i, gp.tileSize * i);
            left1 = setup("/monster/skeletonlord_phase2_left_1", gp.tileSize * i, gp.tileSize * i);
            left2 = setup("/monster/skeletonlord_phase2_left_2", gp.tileSize * i, gp.tileSize * i);
            right1 = setup("/monster/skeletonlord_phase2_right_1", gp.tileSize * i, gp.tileSize * i);
            right2 = setup("/monster/skeletonlord_phase2_right_2", gp.tileSize * i, gp.tileSize * i);
        }
    }

    // load images for attack animations
    public void getAttackImage() {
        int i = 5; // Scale 

        if (inRage == false) {
            // Load attack images for when the monster is not in rage
            attackUp1 = setup("/monster/skeletonlord_attack_up_1", gp.tileSize * i, gp.tileSize * 2 * i);
            attackUp2 = setup("/monster/skeletonlord_attack_up_2", gp.tileSize * i, gp.tileSize * 2 * i);
            attackDown1 = setup("/monster/skeletonlord_attack_down_1", gp.tileSize * i, gp.tileSize * 2 * i);
            attackDown2 = setup("/monster/skeletonlord_attack_down_2", gp.tileSize * i, gp.tileSize * 2 * i);
            attackLeft1 = setup("/monster/skeletonlord_attack_left_1", gp.tileSize * 2 * i, gp.tileSize * i);
            attackLeft2 = setup("/monster/skeletonlord_attack_left_2", gp.tileSize * 2 * i, gp.tileSize * i);
            attackRight1 = setup("/monster/skeletonlord_attack_right_1", gp.tileSize * 2 * i, gp.tileSize * i);
            attackRight2 = setup("/monster/skeletonlord_attack_right_2", gp.tileSize * 2 * i, gp.tileSize * i);
        }
        if (inRage == true) {
            // Load attack images for when the monster is in rage mode
            attackUp1 = setup("/monster/skeletonlord_phase2_attack_up_1", gp.tileSize * i, gp.tileSize * 2 * i);
            attackUp2 = setup("/monster/skeletonlord_phase2_attack_up_2", gp.tileSize * i, gp.tileSize * 2 * i);
            attackDown1 = setup("/monster/skeletonlord_phase2_attack_down_1", gp.tileSize * i, gp.tileSize * 2 * i);
            attackDown2 = setup("/monster/skeletonlord_phase2_attack_down_2", gp.tileSize * i, gp.tileSize * 2 * i);
            attackLeft1 = setup("/monster/skeletonlord_phase2_attack_left_1", gp.tileSize * 2 * i, gp.tileSize * i);
            attackLeft2 = setup("/monster/skeletonlord_phase2_attack_left_2", gp.tileSize * 2 * i, gp.tileSize * i);
            attackRight1 = setup("/monster/skeletonlord_phase2_attack_right_1", gp.tileSize * 2 * i, gp.tileSize * i);
            attackRight2 = setup("/monster/skeletonlord_phase2_attack_right_2", gp.tileSize * 2 * i, gp.tileSize * i);
        }
    }

    // set dialogue for the monster
    public void setDialogue() {
        dialogues[0][0] = "You have come all this way for nothing, warrior.";
        dialogues[0][1] = "You will never make it out of here alive.";
        dialogues[0][2] = "You will remain in this dungeon forever! \n HA HA HA HA!";
    }

    // define monster behavior
    public void setAction() {
        // Rage mode: Monster enters rage when its health drops below half
        if (inRage == false && life < maxLife / 2) {
            inRage = true; 
            getImage(); 
            getAttackImage(); 
            defaultSpeed++; // Increase speed in rage mode
            speed = defaultSpeed; // Apply increased speed
            attack *= 2; // Double attack 
        }

        // If the player is close enough, move towards the player
        if (getTileDistance(gp.player) < 10) {
            moveTowardPlayer(60); // Move towards the player with some speed
        } else {
            // Otherwise, move randomly
            getRandomDirection(120);
        }

        // Check if the monster should attack
        if (attacking == false) {
            checkAttackOrNot(60, gp.tileSize * 7, gp.tileSize * 5); // Small rate = More aggressive
        }
    }

    // Method to handle damage reaction
    public void damageReaction() {
        actionLockCounter = 0; // Reset action lock
    }

    // handle drops after the boss is defeated
    public void checkDrop() {
        gp.bossBattleOn = false; 
        Progress.skeletonLordDefeated = true; // Mark Skeleton Lord as defeated

        // Restore previous music
        gp.stopMusic();
        gp.playMusic(19);

        // Remove all iron doors from the current map
        for (int i = 0; i < gp.obj[1].length; i++) {
            if (gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_Door_Iron.objName)) {
                gp.playSE(21);
                gp.obj[gp.currentMap][i] = null; // Remove the door
            }
        }

        // Randomly determine the items to drop by generating numbers between 1 to 100
        int i = new Random().nextInt(100) + 1;
        
        if(i <= 50) {
            dropItem(new OBJ_Heart(gp));
        }
        if(i>50 && i < 100) {
            dropItem(new OBJ_ManaCrystal(gp)); 
        }
    }
}
