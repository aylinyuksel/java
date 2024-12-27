package main;

import javax.sound.midi.VoiceStatus;

import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import object.OBJ_Axe;
import object.OBJ_Boots;
import object.OBJ_Chest;
import object.OBJ_Coin_Bronze;
import object.OBJ_Door;
import object.OBJ_Heart;
import object.OBJ_Key;
import object.OBJ_ManaCrystal;
import object.OBJ_Potion_Red;
import object.OBJ_Shield_Blue;
import tile_interactive.IT_DryTree;

public class AssetSetter {
	
	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {	
		
		int i =  0;
		gp.obj[i]= new OBJ_Coin_Bronze(gp);
		gp.obj[i].worldX = gp.tileSize*25;
		gp.obj[i].worldY = gp.tileSize*19;
		i++;
		gp.obj[i]= new OBJ_Coin_Bronze(gp);
		gp.obj[i].worldX = gp.tileSize*21;
		gp.obj[i].worldY = gp.tileSize*19;
		i++;
		gp.obj[i]= new OBJ_Coin_Bronze(gp);
		gp.obj[i].worldX = gp.tileSize*26;
		gp.obj[i].worldY = gp.tileSize*21;
		i++;
		gp.obj[i]= new OBJ_Axe(gp);
		gp.obj[i].worldX = gp.tileSize*33;
		gp.obj[i].worldY = gp.tileSize*21;
		i++;
		gp.obj[i]= new OBJ_Shield_Blue(gp);
		gp.obj[i].worldX = gp.tileSize*35;
		gp.obj[i].worldY = gp.tileSize*21;
		i++;
		gp.obj[i]= new OBJ_Potion_Red(gp);
		gp.obj[i].worldX = gp.tileSize*22;
		gp.obj[i].worldY = gp.tileSize*27;
		i++;
		gp.obj[i]= new OBJ_Heart(gp);
		gp.obj[i].worldX = gp.tileSize*22;
		gp.obj[i].worldY = gp.tileSize*29;
		i++;
		gp.obj[i]= new OBJ_ManaCrystal(gp);
		gp.obj[i].worldX = gp.tileSize*22;
		gp.obj[i].worldY = gp.tileSize*31;
		i++;
		
		
	}
	public void setNPC() {  
		gp.npc[0]=new NPC_OldMan(gp);
		gp.npc[0].worldX=gp.tileSize*21; //npc nin konumu
		gp.npc[0].worldY=gp.tileSize*21;		
	}
	public void setMonster() {
		
		int i=0;
		gp.monster[i]=new MON_GreenSlime (gp); //2 yeni monster ekledik 
		gp.monster[i].worldX=gp.tileSize*21; 
		gp.monster[i].worldY=gp.tileSize*38;
		
		i++;
		gp.monster[i]=new MON_GreenSlime (gp);
		gp.monster[i].worldX=gp.tileSize*23; 
		gp.monster[i].worldY=gp.tileSize*42;
		
		i++;
		gp.monster[i]=new MON_GreenSlime (gp);
		gp.monster[i].worldX=gp.tileSize*24; 
		gp.monster[i].worldY=gp.tileSize*37;
		
		i++;
		gp.monster[i]=new MON_GreenSlime (gp);
		gp.monster[i].worldX=gp.tileSize*34; 
		gp.monster[i].worldY=gp.tileSize*42;
		
		i++;
		gp.monster[i]=new MON_GreenSlime(gp);
		gp.monster[i].worldX=gp.tileSize*38; 
		gp.monster[i].worldY=gp.tileSize*42;
		
		
		//gp.monster[0]=new MON_GreenSlime(gp);
		//gp.monster[0].worldX=gp.tileSize*11; 
		//gp.monster[0].worldY=gp.tileSize*10;
		
		//gp.monster[1]=new MON_GreenSlime(gp);
		//gp.monster[1].worldX=gp.tileSize*11; 
		//gp.monster[1].worldY=gp.tileSize*9;
	}
	
	public void setInteractiveTile() {
		
		int i = 0;
		gp.iTile[i] = new IT_DryTree(gp,26,7);
		i++;
		gp.iTile[i] = new IT_DryTree(gp,27,7);
		i++;
		gp.iTile[i] = new IT_DryTree(gp,28,7);
		i++;
		gp.iTile[i] = new IT_DryTree(gp,29,7);
		i++;
		gp.iTile[i] = new IT_DryTree(gp,30,7);
		i++;
		gp.iTile[i] = new IT_DryTree(gp,31,7);
		i++;
		gp.iTile[i] = new IT_DryTree(gp,32,7);
		i++;
		gp.iTile[i] = new IT_DryTree(gp,33,7);
		i++;
		
	}
	
}










