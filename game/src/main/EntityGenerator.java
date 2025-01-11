/*Ali Buğra Tekin 230610058
Aylin Yüksel 220610036
Aysha Mallamahmoud 230610013
Hazar Dardağan 220610010
Feyza Tiryaki 230610052
*/

package main;

import entity.Entity;
import object.*;

public class EntityGenerator {

    GamePanel gp;

    public EntityGenerator(GamePanel gp)
    {
        this.gp = gp;
    }

    //method to generate an object based on its name
    public Entity getObject(String itemName)
    {
        Entity obj = null; //initially set to null

        switch (itemName)//switch statement to match the given item name with a specific object

        {
            case OBJ_Axe.objName: obj = new OBJ_Axe(gp);break;
            case OBJ_Treasure.objName: obj = new OBJ_Treasure(gp);break;
            case OBJ_Chest.objName: obj = new OBJ_Chest(gp);break;
            
            case OBJ_Door.objName: obj = new OBJ_Door(gp);break;
            case OBJ_Door_Iron.objName: obj = new OBJ_Door_Iron(gp);break;
            case OBJ_Fireball.objName: obj = new OBJ_Fireball(gp);break;
            case OBJ_Heart.objName: obj = new OBJ_Heart(gp);break;
            case OBJ_Key.objName: obj = new OBJ_Key(gp);break;
            case OBJ_Lantern.objName: obj = new OBJ_Lantern(gp);break;
            case OBJ_ManaCrystal.objName: obj = new OBJ_ManaCrystal(gp);break;
            case OBJ_Pickaxe.objName: obj = new OBJ_Pickaxe(gp);break;
            case OBJ_Potion_Red.objName: obj = new OBJ_Potion_Red(gp);break;
            case OBJ_Rock.objName: obj = new OBJ_Rock(gp);break;
            case OBJ_Shield_Blue.objName: obj = new OBJ_Shield_Blue(gp);break;
            case OBJ_Shield_Wood.objName: obj = new OBJ_Shield_Wood(gp);break;
            case OBJ_Sword_Normal.objName: obj = new OBJ_Sword_Normal(gp);break;
            

        }
        return obj;
    }

}
