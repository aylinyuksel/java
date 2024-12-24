package main;

import java.awt.BasicStroke;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore.Entry;
import java.text.DecimalFormat;
import java.util.ArrayList;

import object.OBJ_Heart;
import object.OBJ_Key;
import entity.Entity;

public class UI {
	
	GamePanel gp;
	Graphics2D g2;
	Font caruban;
	//BufferedImage keyImage;
	BufferedImage heart_full, heart_half, heart_blank;
	public boolean messageOn = false;
	
	//public String message = "";
	//int messageCounter = 0;
	
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	
	public boolean gameFinished = false;
	public String currentDialogue="";
	String dialogues[]=new String[20];
	public int commandNum = 0;
	public int titleScreenState=0; // 0: ilk ekran :1 ikinci ekran
	
	
	public UI (GamePanel gp) {
		this.gp = gp;
		
		
		try {
			InputStream is=getClass().getResourceAsStream("/font/Caruban-EBK8.ttf");
			caruban=Font.createFont(Font.TRUETYPE_FONT, is);
		}catch(FontFormatException e) {
			e.printStackTrace();
		} catch(IOException e) {	
	        e.printStackTrace();
		}
		
		//create hud object
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
	}
	
	public void addMessage(String text) {

		message.add(text);
		messageCounter.add(0);
	}
	
	public void draw(Graphics2D g2) {
	   this.g2=g2;
	   
	   g2.setFont(caruban);
	   g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	   g2.setColor(Color.white);
	   
	   //TITLE STATE
	   if(gp.gameState == gp.titleState) {
		   drawTitleScreeen();
	   }
	   //PLAY STATE
	   if(gp.gameState==gp.playState) {
		   drawPlayerLife();
		   drawMessage();
	   }
	   //PAUSE STATE
	   if(gp.gameState==gp.pauseState) {
		   drawPauseScreen();
		   drawPlayerLife();
	   }
	   //DIALOGUE STATE
	   if(gp.gameState==gp.dialogueState) {
		 drawDialogueScreen();
		 drawPlayerLife();
	   }
	   
	   //CHARACTER STATE
	   if(gp.gameState == gp.characterState) {
		   drawCharacterScreen();
	   }
	  
	}
	
	public void drawPlayerLife() {
		
		//gp.player.life = 6;
		
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		
		//DRAW MAX HEART
		
		while(i < gp.player.maxLife/2) {
			g2.drawImage(heart_blank, x, y, null);
			i++;
			x += gp.tileSize;
		}
		
		//RESET
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		
		//DRAW CURRENT LIFE
		while(i < gp.player.life) {
			g2.drawImage(heart_half, x, y, null);
			i++;
			if(i < gp.player.life) {
				g2.drawImage(heart_full, x, y, null);
			}
			i++;
			x += gp.tileSize;
			
		}
		
	}
	
	public void drawMessage() {
		
		int messageX = gp.tileSize;
		int messageY = gp.tileSize * 4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
		
		for(int i=0; i<message.size() ; i++) {
			
			if(message.get(i)!= null) {
				
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX + 2, messageY + 2);
				
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i) + 1;
				messageCounter.set(i, counter);
				messageY += 50;
				
				if(messageCounter.get(i) > 180) {
					message.remove(i);
					messageCounter.remove(i);
				}
			
			}
			
		}
	}

	
	
	public void drawTitleScreeen(){
		
		if(titleScreenState == 0) {
			g2.setColor(new Color(70,120,80));
			g2.fillRect(0 , 0, gp.screenWidth, gp.screenHeight);
			
			//TITLE NAME
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,96F));
			String text = "Blue Boy Adventure";
			int x = getXforCenteredText(text);
			int y = gp.tileSize*3;
			
			//SHADOW
			g2.setColor(Color.black);
			g2.drawString(text, x+5, y+5);
			
			//MAIN COLOR
			g2.setColor(Color.white);
			g2.drawString(text, x, y);
			
			//BLUE BOY IMAGE
			x = gp.screenWidth/2 - (gp.tileSize*2)/2;
			y+= gp.tileSize*2;
			g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);
			
			//MENU
			g2.setFont(g2.getFont().deriveFont(Font.PLAIN,48F));
			
			text = "NEW GAME";
			x = getXforCenteredText(text);
			y += gp.tileSize*3.5;
			g2.drawString(text ,x ,y);
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "LOAD GAME";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text ,x ,y);
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "QUIT";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text ,x ,y);
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tileSize, y);
			}
		}
		else if(titleScreenState == 1) {
			
			g2.setColor(Color.white);
			g2.setFont(g2.getFont().deriveFont(42F)); 
			
			String text = "Select your class!";
			int x = getXforCenteredText(text);
			int y = gp.tileSize*3;
			g2.drawString(text, x, y);
			
			text = "Fighter";
			x = getXforCenteredText(text);
			y += gp.tileSize*3;
			g2.drawString(text, x, y);
			if(commandNum == 0) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Thief";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 1) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Sorcerer";
			x = getXforCenteredText(text);
			y += gp.tileSize;
			g2.drawString(text, x, y);
			if(commandNum == 2) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			text = "Back";
			x = getXforCenteredText(text);
			y += gp.tileSize*2;
			g2.drawString(text, x, y);
			if(commandNum == 3) {
				g2.drawString(">", x-gp.tileSize, y);
			}
			
			
		}
		

	}
	public void drawPauseScreen(){ 
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
		String text="PAUSED";
        int x=getXforCenteredText(text);
        
        int y=gp.screenHeight/2;    
        g2.drawString(text, x, y);
	} 
	
	public void  drawDialogueScreen() {
		//WINDOW
		int x=gp.tileSize*2;
		int y=gp.tileSize/2;
		int width =gp.screenWidth - (gp.tileSize*4);
		int height=gp.tileSize*4;
		drawSubWindow(x,y,width,height);
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN,28F));
		x+=gp.tileSize;
		y+=gp.tileSize;
		for(String line:currentDialogue.split("\n")) {
			g2.drawString(line, x, y); 
			y+=40;
		}
		
		
	}
	
	public void drawCharacterScreen() {
		
		//CREATE A FRAME
		
		final int frameX = gp.tileSize ;
		final int frameY = gp.tileSize;
		final int frameWidth= gp.tileSize *5 ;
		final int frameHeight = gp.tileSize * 10;
		
		drawSubWindow(frameX, frameY, frameWidth, frameHeight);
		
		
		//TEXT
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32F));
		
		int textX = frameX + 20;
		int textY = frameY +gp.tileSize;
		final int lineHeight = 35;
		
		//NAME
		g2.drawString("Level" , textX , textY);
		textY += lineHeight;
		g2.drawString("Life" , textX , textY);
		textY += lineHeight;
		g2.drawString("Strenght" , textX , textY);
		textY += lineHeight;
		g2.drawString("Dexterity" , textX , textY);
		textY += lineHeight;
		g2.drawString("Attack" , textX , textY);
		textY += lineHeight;
		g2.drawString("Defense" , textX , textY);
		textY += lineHeight;
		g2.drawString("Exp" , textX , textY);
		textY += lineHeight;
		g2.drawString("Next Level" , textX , textY);
		textY += lineHeight;
		g2.drawString("Coin" , textX , textY);
		textY += lineHeight;
		g2.drawString("Weapon" , textX , textY);
		textY += lineHeight+ 15;
		g2.drawString("Shield" , textX , textY);
		textY += lineHeight;
		
		
		//VALUES
		int tailX =(frameX + frameWidth) - 30;
		//reset textY
		textY = frameY + gp.tileSize;
		String value;
		
		value = String.valueOf(gp.player.level);
		textX = getXforAlignText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.life +"/" + gp.player.maxLife);
		textX = getXforAlignText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.strength);
		textX = getXforAlignText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.dexterity);
		textX = getXforAlignText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.attack);
		textX = getXforAlignText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.defense);
		textX = getXforAlignText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.exp);
		textX = getXforAlignText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.nextLevelExp);
		textX = getXforAlignText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		value = String.valueOf(gp.player.coin);
		textX = getXforAlignText(value, tailX);
		g2.drawString(value, textX, textY);
		textY += lineHeight;
		
		g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 14, null);
		textY += gp.tileSize;
		g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 14, null);
		
	}
	
	
	public void drawSubWindow(int x, int y, int width, int height) {
		Color c=new Color(0,0,0,210);
		g2.setColor(Color.black);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		c= new Color(255,255,255);
		g2.setColor(c);
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25,25);
	}
	
	public int getXforCenteredText(String text) {
		int length=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
         int  x=gp.screenWidth/2 -length/2;
		return x;
		
		
	}
	
	public int getXforAlignText(String text, int tailX ) {
		int length=(int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
         int  x = tailX - length;
		return x;
		
		
	}
	
 }
