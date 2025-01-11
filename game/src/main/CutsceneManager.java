/*Ali Buğra Tekin 230610058
Aylin Yüksel 220610036
Aysha Mallamahmoud 230610013
Hazar Dardağan 220610010
Feyza Tiryaki 230610052
*/

package main;

import entity.PlayerDummy;
import monster.MON_SkeletonLord;
import object.OBJ_Treasure;
import object.OBJ_Door_Iron;

import java.awt.*;

public class CutsceneManager {

    GamePanel gp;
    Graphics2D g2;
    public int sceneNum;
    public int scenePhase;

    int counter = 0;
    float alpha = 0f;
    int y;
    String endCredit;

    //scene Number Constants
    public final int NA = 0;
    public final int skeletonLord = 1;
    public final int ending = 2;

    public CutsceneManager(GamePanel gp)
    {
        this.gp = gp;
        endCredit = "Developed by\n"
                + "Aylin Yuksel\nAysha Mallamahmoud\nAli Bugra Tekin\nFeyza Tiryaki\nHazar Dardagan\n"
                + "Thank you for playing!";
    }

    //draw method to display different cutscenes based on the current scene number
    public void draw(Graphics2D g2)
    {
        this.g2 = g2;

        switch(sceneNum)
        {
            case skeletonLord: scene_skeletonLord(); break;  //call cut scene for Skeleton Lord
            case ending: scene_ending(); break;  //call cut scene for ending
        }
    }

    // Skeleton Lord cut scene sequence
    public void scene_skeletonLord()
    {
        if(scenePhase == 0)
        {
            gp.bossBattleOn = true;

            //shut the iron door to trap player
            for(int i = 0; i < gp.obj[1].length; i++) //search for a vacant slot for the iron door
            {
                if(gp.obj[gp.currentMap][i] == null)
                {
                    gp.obj[gp.currentMap][i] = new OBJ_Door_Iron(gp);
                    gp.obj[gp.currentMap][i].worldX = gp.tileSize * 25;
                    gp.obj[gp.currentMap][i].worldY = gp.tileSize * 28;
                    gp.obj[gp.currentMap][i].temp = true; // Only needed during the boss fight
                    gp.playSE(21); 
                    break;
                }
            }

            //create a player dummy to simulate the players movement
            for(int i = 0; i < gp.npc[1].length; i++) //search for a vacant slot for the player dummy
            {
                if(gp.npc[gp.currentMap][i] == null)
                {
                    gp.npc[gp.currentMap][i] = new PlayerDummy(gp);
                    gp.npc[gp.currentMap][i].worldX = gp.player.worldX;
                    gp.npc[gp.currentMap][i].worldY = gp.player.worldY;
                    gp.npc[gp.currentMap][i].direction = gp.player.direction;
                    break;
                }
            }

            gp.player.drawing = false;  //hide the player during the cut scene

            scenePhase++;
        }
        if(scenePhase == 1)
        {
            gp.player.worldY -= 2;
            if(gp.player.worldY < gp.tileSize * 16) //to stop camera at a specific position
            {
                scenePhase++;
            }
        }
        if(scenePhase == 2)
        {
            //search for the boss monster (!Skeleton Lord!)
            for(int i = 0; i < gp.monster[1].length; i++)
            {
                if(gp.monster[gp.currentMap][i] != null && gp.monster[gp.currentMap][i].name.equals(MON_SkeletonLord.monName))
                {
                    gp.monster[gp.currentMap][i].sleep = false;  //wake up the boss !!!
                    gp.ui.npc = gp.monster[gp.currentMap][i];
                    scenePhase++;
                    break;
                }
            }
        }
        if(scenePhase == 3)
        {
            gp.ui.drawDialogueScreen();  //increase scenePhase for next phase
        }
        if(scenePhase == 4)
        {
            //return to the player

            //search for the player dummy and restore player position
            for(int i = 0; i < gp.npc[1].length; i++)
            {
                if(gp.npc[gp.currentMap][i] != null && gp.npc[gp.currentMap][i].name.equals(PlayerDummy.npcName))
                {
                    gp.player.worldX = gp.npc[gp.currentMap][i].worldX;
                    gp.player.worldY = gp.npc[gp.currentMap][i].worldY;
                    gp.player.direction = gp.npc[gp.currentMap][i].direction;
                    gp.npc[gp.currentMap][i] = null;  //delete the dummy
                    break;
                }
            }

            gp.player.drawing = true;  //start drawing the player again

            //reset the cut scene state
            sceneNum = NA;
            scenePhase = 0;
            gp.gameState = gp.playState;  //return to the play state

            gp.stopMusic();
            gp.playMusic(22);
        }
    }

    //ending cut scene sequence
    public void scene_ending()
    {
        if(scenePhase == 0)
        {
            gp.stopMusic();
            gp.ui.npc = new OBJ_Treasure(gp);  
            scenePhase++;
        }
        if(scenePhase == 1)
        {
            gp.ui.drawDialogueScreen();
        }
        if(scenePhase == 2)
        {
            gp.playSE(4);
            scenePhase++;
        }
        if(scenePhase == 3)
        {
            //wait until the sound effect ends (5 second delay)
            if(counterReached(300) == true)
            {
                scenePhase++;
            }
        }
        if(scenePhase == 4)
        {
            //the screen gets darker gradually
            alpha = graduallyAlpha(alpha, 0.005f);

            drawBlackBackground(alpha);

            if(alpha == 1f)
            {
                alpha = 0;
                scenePhase++;
            }
        }
        if(scenePhase == 5)
        {
            drawBlackBackground(1f);

            //show message gradually
            alpha = graduallyAlpha(alpha, 0.005f);

            String text = "The warrior finally escaped the dungeon with the treasure .\n"
            		+ "He left the dangers behind and started a new, happy life.\n"
            		+ "With the treasure, he built a peaceful home and never looked back.\n"
            		+ "His adventure was over, but his story will always be remembered.";



            drawString(alpha, 38f, 200, text, 70);

            if(counterReached(600) == true && alpha == 1f)
            {
                gp.playMusic(0); 
                alpha = 0;
                scenePhase++;
            }
        }
        if(scenePhase == 6)
        {
            drawBlackBackground(1f);

            alpha = graduallyAlpha(alpha, 0.01f);

            drawString(alpha, 120f, gp.screenHeight / 2, "Dungeon Break", 40);

            if(counterReached(480) == true && alpha == 1f)
            {
                scenePhase++;
                alpha = 0;
            }
        }
        if(scenePhase == 7)
        {
            //first credits
            drawBlackBackground(1f);

            alpha = graduallyAlpha(alpha, 0.01f);

            y = gp.screenHeight / 2;

            drawString(alpha, 38f, y, endCredit, 40);

            if(counterReached(240) == true && alpha == 1f)
            {
                scenePhase++;
                alpha = 0;
            }
        }
        if(scenePhase == 8)
        {
            drawBlackBackground(1f);

            // scroll the credits
            y--;
            drawString(1f, 38f, y, endCredit, 40);
            if(counterReached(1320) == true)  // 22-second delay
            {
                // reset the cut scene manager state
                sceneNum = NA;
                scenePhase = 0;

                //transition back to the game
                gp.gameState = gp.playState;
                gp.resetGame(false);
            }
        }
    }

    //method to check if the counter has reached the target value
    public boolean counterReached(int target)
    {
        boolean counterReached = false;
        counter++;
        if(counter > target)
        {
            counterReached = true;
            counter = 0;  // reset counter
        }
        return counterReached;
    }

    //method to draw a black background with alpha transparency
    public void drawBlackBackground(float alpha)
    {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    // method to draw a string with gradual alpha transparency
    public void drawString(float alpha, float fontSize, int y, String text, int lineHeight)
    {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(fontSize));

        for(String line : text.split("\n"))
        {
            int x = gp.ui.getXforCenteredText(line);
            g2.drawString(line, x, y);
            y += lineHeight;
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }

    //method to gradually change alpha transparency
    public float graduallyAlpha(float alpha, float grade)
    {
        alpha += grade;
        if(alpha > 1f)
        {
            alpha = 1f;
        }
        return alpha;
    }
}
