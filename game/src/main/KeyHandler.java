/*Ali Buğra Tekin 230610058
Aylin Yüksel 220610036
Aysha Mallamahmoud 230610013
Hazar Dardağan 220610010
Feyza Tiryaki 230610052
*/

package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed,rightPressed,enterPressed,shotKeyPressed, spacePressed;
    //DEBUG
    public boolean showDebugText = false;
    public boolean godModeOn = false;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public KeyHandler(GamePanel gp)
    {
        this.gp = gp;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //TITLE STATE
        if(gp.gameState == gp.titleState) {
            titleState(code);
        }
        // PLAY STATE
        else if(gp.gameState == gp.playState)
        {
            playState(code);
        }
        // PAUSE STATE
        else if(gp.gameState == gp.pauseState)
        {
            pauseState(code);
        }
        //DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState || gp.gameState == gp.cutsceneState)
        {
            dialogueState(code);
        }
        // CHARACTER STATE
        else if(gp.gameState == gp.characterState)
        {
            characterState(code);
        }
        // OPTIONS STATE
        else if(gp.gameState == gp.optionsState)
        {
            optionsState(code);
        }
        // GAMEOVER STATE
        else if(gp.gameState == gp.gameOverState)
        {
            gameOverState(code);
        }
        // MAP STATE
        else if(gp.gameState == gp.mapState)
        {
            mapState(code);
        }
    }

    public void titleState(int code)
    {
        //MAIN MENU
        if (gp.ui.titleScreenState == 0) {
            if (code == KeyEvent.VK_W) {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0) {
                    gp.ui.commandNum = 2;
                }
            }
            if (code == KeyEvent.VK_S) {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 2) {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER) {
                if (gp.ui.commandNum == 0) {
                    gp.gameState = gp.playState;
                    gp.playMusic(0);

                }
                if (gp.ui.commandNum == 1) {
                    //LOAD GAME
                    gp.saveLoad.load();
                    gp.gameState = gp.playState;
                    gp.playMusic(0);

                }
                if (gp.ui.commandNum == 2) {
                    System.exit(0);
                }
            }
        }
        
    }
    public void playState(int code)
    {
        if(code == KeyEvent.VK_W)
        {
            upPressed = true;
        }
        if(code == KeyEvent.VK_S)
        {
            downPressed = true;
        }
        if(code == KeyEvent.VK_A)
        {
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D)
        {
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P)
        {
            gp.gameState = gp.pauseState;
        }
        if(code == KeyEvent.VK_C)
        {
            gp.gameState = gp.characterState;
        }
        if(code == KeyEvent.VK_ENTER)
        {
            enterPressed = true;
        }
        if(code == KeyEvent.VK_F)
        {
            shotKeyPressed = true;
        }
        if(code == KeyEvent.VK_ESCAPE)
        {
            gp.gameState = gp.optionsState;
        }
        if(code == KeyEvent.VK_M)
        {
            gp.gameState = gp.mapState;
        }
        if(code == KeyEvent.VK_X)
        {
            if(gp.map.miniMapOn == false)
            {
                gp.map.miniMapOn = true;
            }
            else
            {
                gp.map.miniMapOn = false;
            }
        }
        if(code == KeyEvent.VK_SPACE)
        {
            spacePressed = true;
        }

    }
    public void pauseState(int code)
    {
        if(code == KeyEvent.VK_ESCAPE)
        {
            gp.gameState = gp.playState;
        }
    }
    public void dialogueState(int code)
    {
        if(code == KeyEvent.VK_ENTER)
        {
            enterPressed = true;
        }
    }
    public void characterState(int code)
    {
        if(code == KeyEvent.VK_C)
        {
            gp.gameState = gp.playState;
        }

        if(code == KeyEvent.VK_ENTER)
        {
            gp.player.selectItem();
        }
        playerInventory(code);
    }
    public void optionsState(int code)
    {
        if(code == KeyEvent.VK_ESCAPE)
        {
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER)
        {
            enterPressed = true;
        }
        int maxCommandNum = 0;
        switch (gp.ui.subState)
        {
            case 0: maxCommandNum = 5; break;
            case 3: maxCommandNum = 1; break;
        }
        if(code == KeyEvent.VK_W)
        {
            gp.ui.commandNum--;
            gp.playSE(9);
            if(gp.ui.commandNum < 0)
            {
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_S)
        {
            gp.ui.commandNum++;
            gp.playSE(9);
            if(gp.ui.commandNum > maxCommandNum)
            {
                gp.ui.commandNum = 0;
            }
        }
        if(code == KeyEvent.VK_A)
        {
            if(gp.ui.subState == 0)
            {
                if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0) //music
                {
                    gp.music.volumeScale--;
                    gp.music.checkVolume();  //check for music maybe a song is already being played, but you dont need it for SE, when set a sound checkVolume will be execute.
                    gp.playSE(9);
                }
                if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0) //SE
                {
                    gp.se.volumeScale--;
                    gp.playSE(9);
                }
            }
        }
        if(code == KeyEvent.VK_D)
        {
            if(gp.ui.subState == 0)
            {
                if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5) //music
                {
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(9);
                }
                if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5) //SE
                {
                    gp.se.volumeScale++;
                    gp.playSE(9);
                }
            }
        }
    }
    public void gameOverState(int code)
    {
        if(code == KeyEvent.VK_W)
        {
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0)
            {
                gp.ui.commandNum = 1;
            }
            gp.playSE(9);
        }
        if(code == KeyEvent.VK_S)
        {
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 1)
            {
                gp.ui.commandNum = 0;
            }
            gp.playSE(9);
        }
        if(code == KeyEvent.VK_ENTER)
        {
            if(gp.ui.commandNum == 0) //RETRY, reset position, life, mana, monsters, npcs...
            {
                gp.gameState = gp.playState;
                gp.resetGame(false);
                gp.playMusic(0);
            }
            else if(gp.ui.commandNum == 1) //QUIT, reset everything
            {
                gp.ui.titleScreenState = 0;
                gp.gameState = gp.titleState;
                gp.resetGame(true);
            }
        }
    }
    
    public void mapState(int code)
    {
       if(code == KeyEvent.VK_M)
       {
           gp.gameState = gp.playState;
       }
    }
    public void playerInventory(int code)
    {
        if(code == KeyEvent.VK_W)
        {
            if(gp.ui.playerSlotRow != 0)
            {
                gp.ui.playerSlotRow--;
                gp.playSE(9);   //cursor.wav
            }
        }
        if(code == KeyEvent.VK_A)
        {
            if(gp.ui.playerSlotCol !=0)
            {
                gp.ui.playerSlotCol--;
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_S)
        {
            if(gp.ui.playerSlotRow != 3)
            {
                gp.ui.playerSlotRow++;
                gp.playSE(9);
            }
        }
        if(code == KeyEvent.VK_D)
        {
            if(gp.ui.playerSlotCol != 4)
            {
                gp.ui.playerSlotCol++;
                gp.playSE(9);
            }
        }
    }
    

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W)
        {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S)
        {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A)
        {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D)
        {
            rightPressed = false;
        }
        if(code == KeyEvent.VK_F)
        {
            shotKeyPressed = false;
        }
        if(code == KeyEvent.VK_ENTER)
        {
            enterPressed = false;
        }
        if(code == KeyEvent.VK_SPACE)
        {
            spacePressed = false;
        }
    }
}
