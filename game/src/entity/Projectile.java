package entity;

import main.GamePanel;

public class Projectile extends Entity {

    Entity user; // The entity that shoots this projectile

    // Constructor
    public Projectile(GamePanel gp) {
        super(gp);
    }

    // Set projectile properties
    public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
        this.worldX = worldX; // Starting X position
        this.worldY = worldY; // Starting Y position
        this.direction = direction; // Direction to move
        this.alive = alive; // If the projectile is active
        this.user = user; // The shooter
        this.life = this.maxLife; // Reset life to max
    }

    // Update projectile behavior
    public void update() {
        // If the shooter is the player, check for monster hits
        if (user == gp.player) {
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            if (monsterIndex != 999) { // If projectile hits a monster
                gp.player.damageMonster(monsterIndex, this, attack * (1 + (gp.player.level / 2)), knockBackPower); // Deal damage
                generateParticle(user.projectile, gp.monster[gp.currentMap][monsterIndex]); // Create hit effect
                alive = false; // Destroy projectile
            }
        }

        // If the shooter is not the player, check for player hits
        if (user != gp.player) {
            boolean contactPlayer = gp.cChecker.checkPlayer(this);
            if (!gp.player.invincible && contactPlayer) { // If player gets hit
                damagePlayer(attack); // Damage player
                if (gp.player.guarding) {
                    generateParticle(user.projectile, user.projectile); // Block effect
                } else {
                    generateParticle(user.projectile, gp.player); // Hit effect
                }
                alive = false; // Destroy projectile
            }
        }

        // Move the projectile
        switch (direction) {
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
        }

        // Reduce life over time
        life--;
        if (life <= 0) {
            alive = false; // Destroy projectile when life ends
        }

        // Handle animation
        spriteCounter++;
        if (spriteCounter > 12) {
            spriteNum = (spriteNum == 1) ? 2 : 1; // Switch between sprites
            spriteCounter = 0; // Reset counter
        }
    }

    // Check if the user has resources to shoot
    public boolean haveResource(Entity user) {
        return false; 
    }

    // Deduct resources when shooting
    public void subtractResource(Entity user) {
        
    }
}
