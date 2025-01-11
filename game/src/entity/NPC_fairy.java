/*Ali Buğra Tekin 230610058
Aylin Yüksel 220610036
Aysha Mallamahmoud 230610013
Hazar Dardağan 220610010
Feyza Tiryaki 230610052
*/

package entity;

import main.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_fairy extends Entity {

    public NPC_fairy(GamePanel gp) {
        super(gp);
        direction = "down"; // Initial direction of the NPC
        speed = 1; // Movement speed of the NPC

        // Define the collision area
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;

        // Default values for the collision area
        solidAreaDefaultX = 8;
        solidAreaDefaultY = 16;

        dialogueSet = -1; // Initialize dialogue set index

        getImage(); // Load NPC images
        setDialogue(); // Set dialogue text
    }

    public void getImage() {
        // Load images for all directions
        up1 = setup("/npc/fairy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/npc/fairy_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/npc/fairy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/npc/fairy_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/npc/fairy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/npc/fairy_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/npc/fairy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/npc/fairy_right_2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue() {
        // Define the dialogue text for the NPC
        dialogues[0][0] = "Congratulations, hero!";
        dialogues[0][1] = "You have reached the end of this challenging journey.";
        dialogues[0][2] = "Escaping from the underground wasn’t easy, \n but you made it.";
        
        dialogues[0][3] = "This treasure is now yours—use it to live a happy life.";
    }

    public void setAction() {
        if (onPath == true) {
            // If on a path, calculate and follow the path towards the player
            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
            searchPath(goalCol, goalRow);
        } else {
            actionLockCounter++; // Increment the action lock counter

            if (actionLockCounter == 120) { // Perform an action every 120 frames
                Random random = new Random();
                int i = random.nextInt(100) + 1; // Generate a random number between 1 and 100

                // Change direction based on the random number
                if (i <= 25) {
                    direction = "up";
                }
                if (i > 25 && i <= 50) {
                    direction = "down";
                }
                if (i > 50 && i <= 75) {
                    direction = "left";
                }
                if (i > 75 && i <= 100) {
                    direction = "right";
                }
                actionLockCounter = 0; // Reset the counter
            }
        }
    }

    public void speak() {
        facePlayer(); // Turn towards the player
        startDialogue(this, dialogueSet); // Start the dialogue

        dialogueSet++; // Move to the next dialogue set
        if (dialogues[dialogueSet][0] == null) {
            dialogueSet--; // Revert to the last dialogue set if no more dialogues
        }
    }
}
