package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues(); // Initialize player's default values
        getPlayerImage(); // Load player's sprite images
    }

    public void setDefaultValues() {
        x = 100; // Starting X position
        y = 100; // Starting Y position
        speed = 4; // Player movement speed
        direction = "down"; // Initial direction
    }

    public void getPlayerImage() {
        try {
            // Load player sprites for all directions
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
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/up3.png"));
            up4 = ImageIO.read(getClass().getResourceAsStream("/player/up4.png"));
        } catch (IOException e) {
            e.printStackTrace(); // Print error if images fail to load
        }
    }

    public void update() {
        // Check if any movement keys are pressed
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up"; // Update direction to up
                y -= speed; // Move up
            } else if (keyH.downPressed) {
                direction = "down"; // Update direction to down
                y += speed; // Move down
            } else if (keyH.leftPressed) {
                direction = "left"; // Update direction to left
                x -= speed; // Move left
            } else if (keyH.rightPressed) {
                direction = "right"; // Update direction to right
                x += speed; // Move right
            }

            // Handle sprite animation
            spriteCounter++;
            if (spriteCounter > 10) {
                spriteNum++; 
                if (spriteNum > 4) { 
                    spriteNum = 1; // Reset animation to the first frame
                }
                spriteCounter = 0; // Reset counter
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        // Select the appropriate sprite based on direction and animation frame
        switch (direction) {
            case "up":
                if (spriteNum == 1) image = up1;
                if (spriteNum == 2) image = up2;
                if (spriteNum == 3) image = up3;
                if (spriteNum == 4) image = up4;
                break;

            case "down":
                if (spriteNum == 1) image = down1;
                if (spriteNum == 2) image = down2;
                if (spriteNum == 3) image = down3;
                if (spriteNum == 4) image = down4;
                break;

            case "left":
                if (spriteNum == 1) image = left1;
                if (spriteNum == 2) image = left2;
                if (spriteNum == 3) image = left3;
                if (spriteNum == 4) image = left4;
                break;

            case "right":
                if (spriteNum == 1) image = right1;
                if (spriteNum == 2) image = right2;
                if (spriteNum == 3) image = right3;
                if (spriteNum == 4) image = right4;
                break;
        }

        // Scale the character size for drawing
        double scale = 0.67; 
        int newWidth = (int) (image.getWidth() * scale); 
        int newHeight = (int) (image.getHeight() * scale); 

        // Draw the player sprite on the screen
        g2.drawImage(image, x, y, newWidth, newHeight, null);
    }
}
