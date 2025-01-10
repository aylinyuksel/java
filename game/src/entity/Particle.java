/*Ali Buğra Tekin 230610058
Aylin Yüksel 220610036
Aysha Mallamahmoud 230610013
Hazar Dardağan 220610010
Feyza Tiryaki 230610052
*/

package entity;

import main.GamePanel;

import java.awt.*;

public class Particle extends Entity {

    Entity generator; // The entity that generates this particle
    Color color; // Particle color
    int size; // Particle size
    int xd; // Horizontal movement direction
    int yd; // Vertical movement direction

    // Constructor
    public Particle(GamePanel gp, Entity generator, Color color, int size, int speed, int maxLife, int xd, int yd) {
        super(gp);

        this.generator = generator; 
        this.color = color;
        this.size = size;
        this.speed = speed;
        this.maxLife = maxLife;
        this.xd = xd;
        this.yd = yd;

        life = maxLife; 
        int offset = (gp.tileSize / 2) - size / 2; // Center particle on generator
        worldX = generator.worldX + offset; 
        worldY = generator.worldY + offset;
    }

    // Update particle state
    public void update() {
        life--;

        if (life < maxLife / 3) { 
            yd++; 
            size--; 
        }

        worldX += xd * speed;
        worldY += yd * speed;

        if (life == 0) { 
            alive = false;
        }
    }

    // Draw particle
    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        g2.setColor(color);
        g2.fillRect(screenX, screenY, size, size);
    }
}
