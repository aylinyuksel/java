package entity;

import main.GamePanel;
import object.OBJ_Door;
import object.OBJ_Door_Iron;
import tile_interactive.IT_MetalPlate;
import tile_interactive.InteractiveTile;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class NPC_BigRock extends Entity{

    public static final String npcName = "Big Rock";

    public NPC_BigRock(GamePanel gp)
    {
        super(gp);

        name = npcName; // Set the name of the NPC
        direction = "down"; // Initial direction
        speed = 7; // Movement speed of the rock

        solidArea = new Rectangle(); // Define the solid area for collision
        solidArea.x = 2;
        solidArea.y = 6;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 44;
        solidArea.height = 40;

        dialogueSet = -1; // Initialize the dialogue set index

        getImage(); // Load images for the rock
        setDialogue(); // Initialize dialogue lines
    }

    public void getImage()
    {
        // Load the same image for all directions
        up1 = setup("/npc/bigrock",gp.tileSize,gp.tileSize);
        up2 = setup("/npc/bigrock",gp.tileSize,gp.tileSize);
        down1 = setup("/npc/bigrock",gp.tileSize,gp.tileSize);
        down2 = setup("/npc/bigrock",gp.tileSize,gp.tileSize);
        left1 = setup("/npc/bigrock",gp.tileSize,gp.tileSize);
        left2 = setup("/npc/bigrock",gp.tileSize,gp.tileSize);
        right1 = setup("/npc/bigrock",gp.tileSize,gp.tileSize);
        right2 = setup("/npc/bigrock",gp.tileSize,gp.tileSize);
    }

    public void setDialogue()
    {
        // Set the dialogue text
        dialogues[0][0] = "It's a giant rock.";
    }

    public void setAction()
    {
        // No specific action is implemented
    }

    public void update()
    {
        // No update logic is implemented
    }

    public void speak()
    {
        facePlayer(); // Face towards the player
        startDialogue(this,dialogueSet); // Start the dialogue

        dialogueSet++; // Move to the next dialogue set
        if(dialogues[dialogueSet][0] == null) // Check if there are no more dialogues
        {
            dialogueSet--; // Revert to the last available dialogue set
        }
    }

    public void move(String d)
    {
        this.direction = d; // Update direction

        checkCollision(); // Check for collisions

        if(collisionOn == false) // If no collision, move in the specified direction
        {
            switch(direction)
            {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }

        detectPlate(); // Check if the rock is on a metal plate
    }

    public void detectPlate()
    {
        ArrayList<InteractiveTile> plateList = new ArrayList<>(); // List of metal plates
        ArrayList<Entity> rockList = new ArrayList<>(); // List of rocks

        // Create a plate list
        for(int i = 0; i < gp.iTile[1].length; i++)
        {
            if(gp.iTile[gp.currentMap][i] != null && gp.iTile[gp.currentMap][i].name != null && gp.iTile[gp.currentMap][i].name.equals(IT_MetalPlate.itName))
            {
                plateList.add(gp.iTile[gp.currentMap][i]); // Add metal plate to the list
            }
        }

        // Create a rock list
        for(int i = 0; i < gp.npc[1].length; i++)
        {
            if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(NPC_BigRock.npcName))
            {
                rockList.add(gp.npc[gp.currentMap][i]); // Add rock to the list
            }
        }

        int count = 0; // Counter for rocks on plates

        // Scan the plate list
        for(int i = 0; i < plateList.size(); i++)
        {
            int xDistance = Math.abs(worldX - plateList.get(i).worldX); // X-axis distance
            int yDistance = Math.abs(worldY - plateList.get(i).worldY); // Y-axis distance
            int distance = Math.max(xDistance, yDistance); // Maximum distance

            if(distance < 8) // If the rock is close to the plate
            {
                if(linkedEntity == null) // If not already linked
                {
                    linkedEntity = plateList.get(i); // Link the rock to the plate
                    gp.playSE(3); // Play a sound effect
                }
            }
            else
            {
                if(linkedEntity == plateList.get(i)) // If the rock moves away from the plate
                {
                    linkedEntity = null; // Unlink the rock
                }
            }
        }

        // Scan the rock list
        for(int i = 0; i < rockList.size(); i++)
        {
            // Count the rocks on the plates
            if(rockList.get(i).linkedEntity != null)
            {
                count++;
            }
        }

        // If all the rocks are on the plates, the iron door opens
        if(count == rockList.size())
        {
            for(int i = 0; i < gp.obj[1].length; i++)
            {
                if(gp.obj[gp.currentMap][i] != null && gp.obj[gp.currentMap][i].name.equals(OBJ_Door_Iron.objName))
                {
                    gp.obj[gp.currentMap][i] = null; // Remove the iron door
                    gp.playSE(21); // Play a sound effect
                }
            }
        }
    }
}
