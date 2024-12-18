package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	public int worldX, worldY;
	public int speed;

	
	public BufferedImage up1, up2, up3,up4, down1, down2, down3, down4, left1, left2, left3, left4, right1, right2, right3,right4;
	//BufferedImage bir resmi kullanmak için olan java sınıfı, resim belleğe yüklenir
	
	public String direction;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public Rectangle solidArea; //Rectangle classıyla invisible bi dörtgen olusturabilirim(x,y,width,height)
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn=false;

}
