package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class NPC_OldMan extends Entity {

	public NPC_OldMan(GamePanel gp) {
		super(gp);
		direction="down";
		speed=1;
		getImage();
		setDialogue();
	}
	public void getImage() {

		up1 = setup("/npc/oldman_up1");
		up2 = setup("/npc/oldman_up2");
		down1 = setup("/npc/oldman_down1");
		down2 = setup("/npc/oldman_down2");
		left1 = setup("/npc/oldman_left1");
		left2 = setup("/npc/oldman_left2");
		right1 = setup("/npc/oldman_right1");
		right2 = setup("/npc/oldman_right2");

	}
	public void setDialogue() {
		dialogues[0]="Hello, lad.";
		dialogues[1]="So yo've come to this island to find treasure";
		dialogues[2]="I used to be a great wizard ..";
		dialogues[3]="see you";
	}
public void setAction() {
	actionLockCounter++;
   if(actionLockCounter==120) {
	Random random=new Random();
	int i=random.nextInt(100)+1; // pick up a number from 1 to 100
	if(i<=25) {
		direction="up";
	}
	if(i>=25 && i<=50) {
		direction="down";
	}
	if(i>50 && i<=75) {
		direction="left";
	}
	if(i>75&&i<=100) {
		direction="right";
	}
	actionLockCounter=0;
   }	
}
public void speak() {
	
	//Do this character specific stuff
	super.speak();
   }
}
