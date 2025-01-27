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

public class IT_DryTree extends InteractiveTile{

    GamePanel gp;

    public IT_DryTree(GamePanel gp, int col, int row) {
        super(gp, col, row);
        this.gp = gp;

        this.worldX = gp.tileSize * col;
        this.worldY = gp.tileSize * row;

        down1 = setup("/tiles_interactive/drytree",gp.tileSize,gp.tileSize);
        destructible = true;
        life = 2;
    }
 //checks if the correct item (Axe) is used to chop down the tree
    public boolean isCorrectItem(Entity entity)
    {
        boolean isCorrectItem = false;
        if(entity.currentWeapon.type == type_axe)
        {
            isCorrectItem = true; //the tree can only be chopped with an axe
        }
        return isCorrectItem;
    }
    public void playSE() //plays a sound effect when the tree is chopped
    {
        gp.playSE(11);
    }
    public InteractiveTile getDestroyedForm()
    {
        InteractiveTile tile = new IT_Trunk(gp, worldX / gp.tileSize, worldY / gp.tileSize);
        return tile; //return a new trunk as the destroyed form
    }
    public Color getParticleColor()
    {
        Color color = new Color(65,50,30); //brown color for the tree particles
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
