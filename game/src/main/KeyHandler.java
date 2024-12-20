package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed,enterPressed;
	//DEBUG
    boolean checkDrawTime=false;
    public KeyHandler(GamePanel gp) {
    	this.gp=gp;
    }
 	@Override
	public void keyTyped(KeyEvent e) {
		//lazım değil bura
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode(); //returns the integer keyCode associated with the key in this event
		if(gp.gameState==gp.playState) {
			if(code == KeyEvent.VK_W) {
				upPressed=true;
				
			}
			
			if(code == KeyEvent.VK_S) {
				downPressed = true;
				
			}
			
			if(code == KeyEvent.VK_A) {
				leftPressed = true;
				
			}
			
			if(code == KeyEvent.VK_D) {
				rightPressed = true;
				
			}
			if(code == KeyEvent.VK_P) {
				gp.gameState=gp.pauseState;			
		}if(code == KeyEvent.VK_ENTER) {
			 enterPressed = true;;			
	}
		//PAUSE STATE
			else if(gp.gameState==gp.pauseState) {
		   if(code==KeyEvent.VK_P) {
			   gp.gameState=gp.pauseState;
		   }
	   }
	   //DIALOGUE STATE
			else if(gp.gameState==gp.dialogueState) {
			if(code==KeyEvent.VK_ENTER) {
				gp.gameState=gp.playState;
		}
	      }
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode(); //returns the integer keyCode associated with the key in this event
		
		if(code == KeyEvent.VK_W) {
			upPressed=false;
			
		}
		
		if(code == KeyEvent.VK_S) {
			downPressed = false;
			
		}
		
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
			
		}
		
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
			
		}
		
	}
	

}
