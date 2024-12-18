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
    GamePanel gp;
	KeyHandler keyH;
	
	//bu değişkenler playerı screende nereye koyacağımızı gösterir
	public final int screenX;
	public final int screenY; 
	//final dedik çünkü oyun boyunca pozisyonları değişmeyecek, kamera farklı yerleri gösterecek ama bu eleman hep ortada kalsın istiyoruz
	public int hasKey = 0;
	int standCounter = 0;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
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

		up1 = setup("up1");
		up2 = setup("up2");
		up3 = setup("up3");
		up4 = setup("up4");
		down1 = setup("down1");
		down2 = setup("down2");
		down3 = setup("down3");
		down4 = setup("down4");
		left1 = setup("left1");
		left2 = setup("left2");
		left3 = setup("left3");
		left4 = setup("left4");
		right1 = setup("right1");
		right2 = setup("right2");
		right3 = setup("right3");
		right4 = setup("right4");

	}
	
	public BufferedImage setup(String imageName) {
		
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/player/" + imageName + ".png"));
			image = uTool.scaledImage(image, gp.tileSize, gp.tileSize);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
		
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
			int objIndex = gp.collisionC.checkObject(this, true);
			pickUpObject(objIndex);
			
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
			
			String objectName = gp.obj[i].name;
			
			switch(objectName) {
			case "Key": 
				gp.playSE(1);
				hasKey++;
				gp.obj[i] = null; //delete
				gp.ui.showMessage("You Got a Key!");
				break;
			case "Door": 
				gp.playSE(3);
				if(hasKey>0) {
					gp.obj[i] = null;
					hasKey--;
					gp.ui.showMessage("You Opened the Door!");
				}
				else {
					gp.ui.showMessage("You Need a Key!");
				}
				
				break;
			case "Boots":
				gp.playSE(2);
				speed += 1;
				gp.obj[i] = null;
				gp.ui.showMessage("Speed Up!");
				break;
			case "Chest":
				gp.ui.gameFinished = true;
				gp.stopMusic();
				gp.playSE(4);
				break;
			}
		}
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
