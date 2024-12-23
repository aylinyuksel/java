package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.channels.Pipe.SourceChannel;
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
		
		attackArea.width = 36;
		attackArea.height = 36;
		
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
		
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		//worldX = gp.tileSize * 10;
		//worldY = gp.tileSize * 13;
		
		speed = 4;
		direction = "down"; //herhangi bir direction verebilirim
		
		//player status
		
		maxLife = 6; //her can yarım kalp 6 can = 3 kalp 
		life = maxLife;
	}
	
	public void getPlayerImage() {
	    up1 = setup("/player/boy_up_1", gp.tileSize, gp.tileSize);
	    up2 = setup("/player/boy_up_2", gp.tileSize, gp.tileSize);
	    down1 = setup("/player/boy_down_1", gp.tileSize, gp.tileSize);
	    down2 = setup("/player/boy_down_2", gp.tileSize, gp.tileSize);
	    left1 = setup("/player/boy_left_1", gp.tileSize, gp.tileSize);
	    left2 = setup("/player/boy_left_2", gp.tileSize, gp.tileSize);
	    right1 = setup("/player/boy_right_1", gp.tileSize, gp.tileSize);
	    right2 = setup("/player/boy_right_2", gp.tileSize, gp.tileSize);
	}

	public void getPlayerAttackImage() {
	    attackUp1 = setup("/player/boy_attack_up_1", gp.tileSize, gp.tileSize*2);
	    attackUp2 = setup("/player/boy_attack_up_2", gp.tileSize, gp.tileSize*2);
	    attackDown1 = setup("/player/boy_attack_down_1", gp.tileSize, gp.tileSize*2);
	    attackDown2 = setup("/player/boy_attack_down_2", gp.tileSize, gp.tileSize*2);
	    attackLeft1 = setup("/player/boy_attack_left_1", gp.tileSize*2, gp.tileSize);
	    attackLeft2 = setup("/player/boy_attack_left_2", gp.tileSize*2, gp.tileSize);
	    attackRight1 = setup("/player/boy_attack_right_1", gp.tileSize*2, gp.tileSize);
	    attackRight2 = setup("/player/boy_attack_right_2", gp.tileSize*2, gp.tileSize);
	}


	
	
	public void update() {
		
		if(attacking == true) {
			attacking();  //saldiriyi enter tusu ile yapacagimiz icin hem konusma hem saldiri ayni tusla olacak o yuzden kontrol ediyoruz bu kod ile
		}
		
		//in java the uppder left corner is origin
		//X values increases to the right
		//Y values increases as they go down	
		
		if(keyH.upPressed == true || keyH.downPressed ==true || 
				keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {
			

			if(keyH.upPressed == true) {
				direction = "up";
			}
					
			else if(keyH.downPressed == true) {
				direction ="down";	
			}

			else if(keyH.leftPressed == true) {
				direction = "left";
			}

			else if(keyH.rightPressed == true) {
				direction = "right";
			}		
			
			
			//check tile collision 
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			//check obj collison
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);
			//CHECK NPC COLLISON
			int npcIndex=gp.cChecker.checkEntity(this,gp.npc);
			interactNPC(npcIndex);
			//CHECK MONSTER COLLISION
			int monsterIndex=gp.cChecker.checkEntity(this,gp.monster);
			contactMonster(monsterIndex);
			
			
			//CHECK EVENT
			gp.eHandler.checkEvent();  
			
			
			
			//if collision is false player cant move
			if (collisionOn == false && gp.keyH.enterPressed==false) {
				
				switch(direction) {
				case "up": worldY -= speed; break;
				case "down": worldY += speed; break;
				case "left": worldX -= speed; break;
				case "right": worldX += speed; break;
				}
				
				
			}
			gp.keyH.enterPressed=false;
			
			spriteCounter++; //anlamadim tam bi daha bak
			
			if(spriteCounter > 12) {
				if(spriteNum==1) {
					spriteNum = 2;
				}
				else if(spriteNum==2) {
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
		//bu kod if in disinda olmak zorundadir.
		if(invincible == true) { //karakter damage yiyince rengi soluklasacak.
			invincibleCounter++;
			if(invincibleCounter>60) {
				invincible = false;
				invincibleCounter=0; //counter sifirlandi 
			}
		}
	}
	public void attacking() {

	    spriteCounter++;

	    if(spriteCounter <= 5) {
	        spriteNum = 1;
	    }
	    if(spriteCounter > 5 && spriteCounter <= 25) {
	        spriteNum = 2;
	        int currentWorldX = worldX;   //saldırı alanının pozisyonunu belirlendi
	        int currentWorldY = worldY;
	        int solidAreaWidth = solidArea.width;
	        int solidAreaHeight = solidArea.height;
	        
	        switch(direction) {
	        case "up": worldY -= attackArea.height; break;  //karakter sadece silahi ile saldirsin diye saldırı alanının pozisyonuni değiştirdik.
	        case "down": worldY += attackArea.height; break;
	        case "left": worldX -= attackArea.width; break;
	        case "right": worldX += attackArea.width; break;
	        }
	        
	        solidAreaWidth = attackArea.width; //pozisyon disinda alani da degistirdik. 
	        solidAreaHeight = attackArea.height;
	        // Check monster collision with the updated worldX, worldY and solidArea
	        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);//bu kod ile monster ile etkilesime gecebilecegiz.
	        damageMonster(monsterIndex);
	        
	        // After checking collision, restore the original data
	        worldX = currentWorldX; //monsterin sadece silah ile etkilesime girmesi icin yukaridaki kod ile alani ayarladiktan sonra yeni data olarak guncelledik bu degistirlen degerleri
	        worldY = currentWorldY;
	        solidArea.width = solidAreaWidth;
	        solidArea.height = solidAreaHeight;
	        


	        
	    }
	    if(spriteCounter > 25) {
	        spriteNum = 1;
	        spriteCounter = 0;
	        attacking = false;
	    }
	}

	
	public void pickUpObject(int i) {
		
		if(i != 999) {
			
		}
	}
	public void interactNPC(int i) {

	    if(gp.keyH.enterPressed == true) {
	        if(i != 999) {	            
	           gp.gameState = gp.dialogueState;
	           gp.npc[i].speak();
	        }
	        else {	            
	                attacking = true;	            
	        }
	    }
	}

	public void contactMonster(int i) {
		if(i!=999) {
			if(invincible == false) { //monster canli iken bize dokunursa canimiz azalir.
				life -= 1;             
				invincible = true;
			}
		}
		
	}
	public void damageMonster(int i) { //monster 4 kere vurunca olecek bu kod ile

	    if(i != 999) {

	        if(gp.monster[i].invincible == false) {

	            gp.monster[i].life -= 1;
	            gp.monster[i].invincible = true;

	            if(gp.monster[i].life <= 0) {
	                gp.monster[i] = null;
	            }
	        }
	    }
	}

	public void draw(Graphics2D g2) {

	    BufferedImage image = null;
	    int tempScreenX = screenX;  //left up yaparken saldiri assetinde sikinti oluyor onu kaldirmak icin yazdik.
	    int tempScreenY = screenY;

	    switch(direction) {
	    case "up":
	        if(attacking == false) {
	            if(spriteNum == 1) {image = up1;}
	            if(spriteNum == 2) {image = up2;}
	        }
	        if(attacking == true) {
	        	tempScreenY = screenY - gp.tileSize; //bozulan asset goruntusu duzeltildi
	            if(spriteNum == 1) {image = attackUp1;}
	            if(spriteNum == 2) {image = attackUp2;}
	        }
	        break;
	    case "down":
	        if(attacking == false) {
	            if(spriteNum == 1) {image = down1;}
	            if(spriteNum == 2) {image = down2;}
	        }
	        if(attacking == true) {
	            if(spriteNum == 1) {image = attackDown1;}
	            if(spriteNum == 2) {image = attackDown2;}
	        }
	        break;
	    case "left":
	        if(attacking == false) {
	            if(spriteNum == 1) {image = left1;}
	            if(spriteNum == 2) {image = left2;}
	        }
	        if(attacking == true) {
	        	tempScreenX = screenX - gp.tileSize; //bozulan asset goruntusu duzeltildi
	            if(spriteNum == 1) {image = attackLeft1;}
	            if(spriteNum == 2) {image = attackLeft2;}
	        }
	        break;
	    case "right":
	        if(attacking == false) {
	            if(spriteNum == 1) {image = right1;}
	            if(spriteNum == 2) {image = right2;}
	        }
	        if(attacking == true) {
	            if(spriteNum == 1) {image = attackRight1;}
	            if(spriteNum == 2) {image = attackRight2;}
	        }
	        break;
	    }
	

	

		if(invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f)); //nesne üzerinde bir saydamlık  efekti oluşturur. 

		}
		
		g2.drawImage(image, tempScreenX, tempScreenY, null); //left down saldiri assetinde bozukluk yarattigi icin screenx yerine tempscreen yazildi.
		
		//RESET ALPHA
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f)); //normale dondurduk
		//invincible icin debug test
		//g2.setFont(new Font("Arial", Font.PLAIN, 26));
		//g2.setColor(Color.white);
		//g2.drawString("Invincible: " + invincibleCounter, 10, 400);

		
		
	}
}
