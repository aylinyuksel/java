package entity;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.awt.Rectangle;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {
     
	KeyHandler keyH;
	
	//bu değişkenler playerı screende nereye koyacağımızı gösterir
	public final int screenX;
	public final int screenY; 
	//final dedik çünkü oyun boyunca pozisyonları değişmeyecek, kamera farklı yerleri gösterecek ama bu eleman hep ortada kalsın istiyoruz
	//public int hasKey = 0;
	int standCounter = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 -(gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8; //solid area nın başlangıç köşesi
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		
		setDefaultValues();
		getPlayerImage();
		
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down"; //herhangi bir direction verebilirim
	}
	
	public void getPlayerImage() {

		up1 = setup("/player/up1");
		up2 = setup("/player/up2");
		up3 = setup("/player/up3");
		up4 = setup("/player/up4");
		down1 = setup("/player/down1");
		down2 = setup("/player/down2");
		down3 = setup("/player/down3");
		down4 = setup("/player/down4");
		left1 = setup("/player/left1");
		left2 = setup("/player/left2");
		left3 = setup("/player/left3");
		left4 = setup("/player/left4");
		right1 = setup("/player/right1");
		right2 = setup("/player/right2");
		right3 = setup("/player/right3");
		right4 = setup("/player/right4");

	}
	
	
	public void update() {
		
		//in java the uppder left corner is origin
		//X values increases to the right
		//Y values increases as they go down	
		
		if(keyH.upPressed == true || keyH.downPressed ==true || 
				keyH.leftPressed == true || keyH.rightPressed == true) {
			

			if(keyH.upPressed == true) {
				direction = "up";
			}
					
			if(keyH.downPressed == true) {
				direction ="down";	
			}

			if(keyH.leftPressed == true) {
				direction = "left";
			}

			if(keyH.rightPressed == true) {
				direction = "right";
			}		
			
			
			//check tile collision 
			collisionOn = false;
			gp.collisionC.checkTile(this);
			
			//check obj collison
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			//CHECK NPC COLLISON
			int npcIndex=gp.cChecker.checkEntity(this,gp.npc);
			interactNPC(npcIndex);
			
			//if collision is false player cant move
			if (collisionOn == false) {
				
				switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
				
				
			}
			
			spriteCounter++; //anlamadim tam bi daha bak
			
			if(spriteCounter > 12) {
				if(spriteNum==1) {
					spriteNum = 4;
				}
				else if(spriteNum==4) {
					spriteNum = 1;
				}
				spriteCounter=0;
			}			
		
		}
		else { //to look more natural when stopping sideway 
			
			standCounter++; 
			if(standCounter == 20) { //frames
				spriteNum = 1;
				standCounter = 0;
			}
		}
	}
	
	public void pickUpObject(int i) {
		
		if(i != 999) {
			
		}
	}
	public void interactNPC(int i) {
		if(i!=999) {
			if(gp.keyH.enterPressed==true) {
				gp.gameState=gp.dialogueState;
				gp.npc[i].speak();
			}
		}
		gp.keyH.enterPressed=false;
	}
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			
			if(spriteNum==1) {
				image=up1;
			}
			if(spriteNum==2) {
				image=up2;
			}
			if(spriteNum==3) {
				image=up3;
			}
			if(spriteNum==4) {
				image=up4;
			}
			break;
			
		case "down":
			
			if(spriteNum==1) {
				image=down1;
			}
			if(spriteNum==2) {
				image=down2;
			}
			if(spriteNum==3) {
				image=down3;
			}
			if(spriteNum==4) {
				image=down4;
			}
			break;
		
		case "left":
			
			if(spriteNum==1) {
				image=left1;
			}
			if(spriteNum==2) {
				image=left2;
			}
			if(spriteNum==3) {
				image=left3;
			}
			if(spriteNum==4) {
				image=left4;
			}
			break;
			
		case "right":
			if(spriteNum==1) {
				image=right1;
			}
			if(spriteNum==2) {
				image=right2;
			}
			if(spriteNum==3) {
				image=right3;
			}
			if(spriteNum==4) {
				image=right4;
			}
			break;

		}
		
		g2.drawImage(image, screenX, screenY, null);
		
		
	}
}
