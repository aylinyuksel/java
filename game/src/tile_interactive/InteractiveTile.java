/*Ali Buğra Tekin 230610058
Aylin Yüksel 220610036
Aysha Mallamahmoud 230610013
Hazar Dardağan 220610010
Feyza Tiryaki 230610052
*/

package tile_interactive;

import entity.Entity;
import main.GamePanel;

import java.awt.*;

public class InteractiveTile extends Entity {

    GamePanel gp;
    public boolean destructible = false;
    public InteractiveTile(GamePanel gp, int col, int row)
    {
        super(gp);
        this.gp = gp;
    }
    
    //checks if the correct item is used on the interactive tile
    public boolean isCorrectItem(Entity entity)
    {
        boolean isCorrectItem = false;
        //Sub-class specifications
        return isCorrectItem;
    }
    
    public void playSE()
    {
    	//sound effect logic will be defined in subclasses
    }
    
    public InteractiveTile getDestroyedForm()
    {
        InteractiveTile tile = null;
        //Sub-class specifications
        return tile;
    }
    public void update()
    {
        if(invincible == true)
        {
            invincibleCounter++;
            if(invincibleCounter > 20) //after 20 ticks, reset invincibility
            {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void draw(Graphics2D g2) {

        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            g2.drawImage(down1, screenX, screenY, null);
        }
    }
}
