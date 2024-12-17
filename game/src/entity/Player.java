package entity;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.awt.Rectangle;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    GamePanel gp;
	KeyHandler keyH;
	
	//bu değişkenler playerı screende nereye koyacağımızı gösterir
	public final int screenX;
	public final int screenY; 
	//final dedik çünkü oyun boyunca pozisyonları değişmeyecek, kamera farklı yerleri gösterecek ama bu eleman hep ortada kalsın istiyoruz
	
	
	public Player(GamePanel gp, KeyHandler keyH) {
		
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 -(gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8; //solid area nın başlangıç köşesi
		solidArea.y = 16;
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
		
		try {
			
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
			up3 = ImageIO.read(getClass().getResourceAsStream("/player/up3.png"));
			up4 = ImageIO.read(getClass().getResourceAsStream("/player/up4.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
			down3 = ImageIO.read(getClass().getResourceAsStream("/player/down3.png"));
			down4 = ImageIO.read(getClass().getResourceAsStream("/player/down4.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
			left3 = ImageIO.read(getClass().getResourceAsStream("/player/left3.png"));
			left4 = ImageIO.read(getClass().getResourceAsStream("/player/left4.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
			right3 = ImageIO.read(getClass().getResourceAsStream("/player/right3.png"));
			right4 = ImageIO.read(getClass().getResourceAsStream("/player/right4.png"));

		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
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
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize,null);
		
		
	}
}
