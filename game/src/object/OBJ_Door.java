package object;
import entity.Entity;

import main.GamePanel;

public class OBJ_Door extends Entity {
	
	
	
	public OBJ_Door(GamePanel gp) {
		
		super(gp); //GamePanel referansini aliyor.
		name = "Door";
		down1 = setup("/objects/door", gp.tileSize, gp.tileSize); //grafik dosyasi 
		collision = true;  //Kapinin carpisma algilamasini aktif ettik.
		solidArea.x = 0; //X konumu
		solidArea.y = 16; // Y konumu
		solidArea.width =48; // carpma genisligi
		solidArea.height =32; //carpma yuksekligi
		solidAreaDefaultX = solidArea.x;  //Carpisma alaninin varsayilan X degeri kaydedildi
		solidAreaDefaultY = solidArea.y;  //Carpisma alaninin varsayilan Y degeri kaydedildi
		
		
	}
}
