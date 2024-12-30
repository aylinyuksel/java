package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.channels.Pipe.SourceChannel;
import java.util.ArrayList;
import java.awt.Rectangle;
import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.OBJ_Axe;
import object.OBJ_Fireball;
import object.OBJ_Key;
import object.OBJ_Rock;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

public class Player extends Entity {
     
	KeyHandler keyH;
	
	//bu değişkenler playerı screende nereye koyacağımızı gösterir
	public final int screenX;
	public final int screenY; 
	//final dedik çünkü oyun boyunca pozisyonları değişmeyecek, kamera farklı yerleri gösterecek ama bu eleman hep ortada kalsın istiyoruz
	//public int hasKey = 0;
	int standCounter = 0;
	public boolean attackCanceled = false;
	public ArrayList<Entity> inventory = new ArrayList<>();
	public final int maxInventorySize = 20;
	
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
		
		//attackArea.width = 36;
		//attackArea.height = 36;
		
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
		setItems();
		
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		//worldX = gp.tileSize * 10;
		//worldY = gp.tileSize * 13;
		
		speed = 4;
		direction = "down"; //herhangi bir direction verebilirim
		
		//player status
		level = 1;
		maxLife = 6; //her can yarım kalp 6 can = 3 kalp 
		life = maxLife;
		maxMana = 4;
		mana = maxMana;
		ammo = 10;
		strength = 1; // the more strenght he has the more demage he gives
		dexterity = 1; //the more dexterity he has the less demage he decevies
		exp = 0;
		nextLevelExp = 5; // bir sonraki levele geçmesi için gerekli olan deneyim sayisi
		coin = 0;
		//currentWeapon = new OBJ_Sword_Normal(gp);
		currentWeapon = new OBJ_Axe(gp);
		currentShield = new OBJ_Shield_Wood(gp);
		projectile = new OBJ_Fireball(gp);
		//projectile = new OBJ_Rock(gp);
		attack = getAttack(); // the total attack value is deceived by strenght and weapon
		defense = getDefense(); // the total defense value is decided by dexterity and shield
		  
	}
	
	public void setDefaultPositions() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		direction = "down";
	}
	
	public void restoreLifeAndMana() {
		life = maxLife;
		mana = maxMana;
		invincible = false;
		
	}
	
	
	public void setItems() {
		
		inventory.clear();
		inventory.add(currentWeapon);
		inventory.add(currentShield);
		inventory.add(new OBJ_Key(gp));
	}
	
	public int getAttack() {
		attackArea = currentWeapon.attackArea;
		return attack = strength * currentWeapon.attackValue;
	}
	
	public int getDefense() {
		return defense = dexterity * currentShield.defenseValue;
		
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
		
		if(currentWeapon.type == type_sword) {
			
		    attackUp1 = setup("/player/boy_attack_up_1", gp.tileSize, gp.tileSize*2);
		    attackUp2 = setup("/player/boy_attack_up_2", gp.tileSize, gp.tileSize*2);
		    attackDown1 = setup("/player/boy_attack_down_1", gp.tileSize, gp.tileSize*2);
		    attackDown2 = setup("/player/boy_attack_down_2", gp.tileSize, gp.tileSize*2);
		    attackLeft1 = setup("/player/boy_attack_left_1", gp.tileSize*2, gp.tileSize);
		    attackLeft2 = setup("/player/boy_attack_left_2", gp.tileSize*2, gp.tileSize);
		    attackRight1 = setup("/player/boy_attack_right_1", gp.tileSize*2, gp.tileSize);
		    attackRight2 = setup("/player/boy_attack_right_2", gp.tileSize*2, gp.tileSize);
		}
		
		if(currentWeapon.type == type_axe) {
			
		    attackUp1 = setup("/player/boy_axe_up_1", gp.tileSize, gp.tileSize*2);
		    attackUp2 = setup("/player/boy_axe_up_2", gp.tileSize, gp.tileSize*2);
		    attackDown1 = setup("/player/boy_axe_down_1", gp.tileSize, gp.tileSize*2);
		    attackDown2 = setup("/player/boy_axe_down_2", gp.tileSize, gp.tileSize*2);
		    attackLeft1 = setup("/player/boy_axe_left_1", gp.tileSize*2, gp.tileSize);
		    attackLeft2 = setup("/player/boy_axe_left_2", gp.tileSize*2, gp.tileSize);
		    attackRight1 = setup("/player/boy_axe_right_1", gp.tileSize*2, gp.tileSize);
		    attackRight2 = setup("/player/boy_axe_right_2", gp.tileSize*2, gp.tileSize);
		}

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
			//CHECK INTERACTIVE TILE COLLISION
			int iTileIndex=gp.cChecker.checkEntity(this,gp.iTile);
			
			
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
			
			if(keyH.enterPressed == true && attackCanceled == false) {
				
				//gp.playSE(7); buradaki sesi ekleyince tüm oyun kasıyo eklenmedi sanırım?
				attacking = true;
				spriteCounter = 0;
			}
			attackCanceled = false;
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
		
		if(gp.keyH.shotKeyPressed == true && projectile.alive == false 
				&& shotAvailableCounter ==30 && projectile.haveResource(this) == true){
			
			//SETTING DEFAULT COORDINATES DIRECTIONS AND USERS
			projectile.set(worldX, worldY, direction, true, this);
			
			//SUBTRACT THE COST (MANA, AMMO, ETC.)
			projectile.subtractResource(this);
			
			//ADD TO LIST
			gp.projectileList.add(projectile);
			shotAvailableCounter = 0;
			gp.playSE(10);
		}
		
		//bu kod if in disinda olmak zorundadir.
		if(invincible == true) { //karakter damage yiyince rengi soluklasacak.
			invincibleCounter++;
			if(invincibleCounter>60) {
				invincible = false;
				invincibleCounter=0; //counter sifirlandi 
			}
		}
		
		if(shotAvailableCounter < 30) { // u cant shoot another fire until 30 frames
			shotAvailableCounter++;
		}
		
		if(life > maxLife) {
			life = maxLife;
		}
		
		if(mana > maxMana) {
			mana = maxMana;
		}
		if(life <=0) {
			gp.gameState = gp.gameOverState;
			gp.playSE(12);
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
	        damageMonster(monsterIndex, attack);
	        
	        int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
	        damageInteractiveTile(iTileIndex);
	        
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
			
			//PICKUP ONLY ITEMS
			if(gp.obj[i].type == type_pickupOnly) {
				
				gp.obj[i].use(this);
				gp.obj[i] = null;
			}
			
			//INVENTORY ITEMS
			else {

				String text;
				
				if (inventory.size() != maxInventorySize) {
					
					inventory.add(gp.obj[i]);
					gp.playSE(1);
					text = "Got a " + gp.obj[i].name + "!";
				}
				else {
					text = "You cannot carry any more";
				}
				gp.ui.addMessage(text);
				gp.obj[i] = null;
			}
			
		}
	}
	
	public void interactNPC(int i) {

	    if(gp.keyH.enterPressed == true) {
	        if(i != 999) {
	           attackCanceled = true;
	           gp.gameState = gp.dialogueState;
	           gp.npc[i].speak();
	        }
	        
	        
	        /*else {	            
	                attacking = true;
	                //gp.playSE(7); daha eklemedik bunu asssetlerde yok bulamadim eklenecek kalsin
	        }*/
	    }
	}

	public void contactMonster(int i) {
		if(i!=999) {
			if(invincible == false && gp.monster[i].dying == false) { //monster canli iken bize dokunursa canimiz azalir.
				
				gp.playSE(6);
				
				int demage =  gp.monster[i].attack - defense;
				
	        	if (demage < 0) {
	        		demage = 0;
	        	}
	        	
				life -= demage;             
				invincible = true;
			}
		}
		
	}
	
	public void damageMonster(int i, int attack) { 

	    if(i != 999) {

	        if(gp.monster[i].invincible == false) {
	        	
	        	gp.playSE(5); //sound efekti

	        	
	        	int demage = attack - gp.monster[i].defense;
	        	if (demage < 0) {
	        		demage = 0;
	        	}
	        	
	        	
	            gp.monster[i].life -= demage;
	            gp.ui.addMessage(demage + " damage!");
	            
	            gp.monster[i].invincible = true;
	            gp.monster[i].damageReaction();

	            if(gp.monster[i].life <= 0) {
	                gp.monster[i].dying = true;
	                gp.ui.addMessage("killed the " + gp.monster[i].name +"!");
	                gp.ui.addMessage("Exp + " + gp.monster[i].exp);
	                exp += gp.monster[i].exp;
	                checkLevelUp();
	            }
	        }
	    }
	}
	
	public void damageInteractiveTile(int i) {
		if(i != 999 && gp.iTile[i].destructible == true && gp.iTile[i].isCorrectItem(this) && gp.iTile[i].invincible == false) {
			
			gp.iTile[i].playSE();
			gp.iTile[i].life--;
			gp.iTile[i].invincible = true;
			
			generateParticle(gp.iTile[i],gp.iTile[i]);
			
			if(gp.iTile[i].life-- == 0) {
			gp.iTile[i] = gp.iTile[i].getDestroyedForm();
			}
		}
	}
	public void checkLevelUp() {
		
		if(exp >= nextLevelExp) {
			
			level++;
			nextLevelExp = nextLevelExp*2;
			maxLife += 2;
			strength++;
			dexterity++;
			attack = getAttack();
			defense = getDefense();
			
			gp.playSE(8);
			gp.gameState = gp.dialogueState;
			gp.ui.currentDialogue = "You are level " + level + " now!\n" + "You feel stronger!";
			
		}
	}

	public void selectItem() {
		
		int itemIndex = gp.ui.getItemIndexOnSlot();
		
		if(itemIndex < inventory.size()) {
			
			Entity selectedItem = inventory.get(itemIndex);
			
			if(selectedItem.type == type_sword || selectedItem.type == type_axe) {
				 
				 currentWeapon = selectedItem;
				 attack = getAttack();
				 getPlayerAttackImage();
			}
			if(selectedItem.type == type_shield) {
				
				currentShield = selectedItem;
				defense = getDefense();
			}
			if(selectedItem.type == type_consumable) {
				
				selectedItem.use(this);
				inventory.remove(itemIndex);
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
