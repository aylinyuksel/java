package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Shield_Blue extends Entity{
	
		public OBJ_Shield_Blue(GamePanel gp) {
			super(gp);
			
			type = type_shield;
			name = "BLue Shield";
			down1 = setup("/objects/shield_blue", gp.tileSize, gp.tileSize);
			defenseValue  = 2; // ne kadar can gideceğini hesaplarken kullan
			description = "[" + name + "]\nA shiny blue shield." ;
			
		}
}
