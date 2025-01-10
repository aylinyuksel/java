package tile_interactive;

import entity.Entity;

import main.GamePanel;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;

import java.awt.*;
import java.util.Random;

public class IT_DestructibleWall extends InteractiveTile{

    GamePanel gp;

    public IT_DestructibleWall(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/tiles_interactive/destructiblewall",gp.tileSize,gp.tileSize);
        destructible = true;
        life = 3; //the destructible wall has 3 life points
    }
    
    public boolean isCorrectItem(Entity entity)
    {
        boolean isCorrectItem = false;
        if(entity.currentWeapon.type == type_pickaxe)
        {
            isCorrectItem = true; //the tile can only be destroyed with a pickaxe
        }
        return isCorrectItem;
    }
    public void playSE()
    {
        gp.playSE(20);
    }
    
    public InteractiveTile getDestroyedForm()
    {
        InteractiveTile tile = null;
        return tile;
    }
    
    public Color getParticleColor() //returns the color of particles when the tile is destroyed
    {
        Color color = new Color(65,65,65);
        return color;
    }
    public int getParticleSize()
    {
        int size = 6; //pixels
        return size;
    }
    public int getParticleSpeed()
    {
        int speed = 1;
        return speed;
    }
    public int getParticleMaxLife()
    {
        int maxLife = 20;
        return maxLife;
    }
    
}
