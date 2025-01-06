package entity;

import main.GamePanel; 
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Entity {

    GamePanel gp;
    public BufferedImage up1,up2,down1,down2,left1,left2,right1,right2;
    public BufferedImage attackUp1,attackUp2,attackDown1,attackDown2,attackLeft1,attackLeft2,attackRight1,attackRight2,guardUp,guardDown,guardLeft,guardRight;
    public BufferedImage image, image2, image3;
    public Rectangle solidArea = new Rectangle(0,0, 48, 48);  //collision area
    public Rectangle attackArea = new Rectangle(0,0, 0, 0);  
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false; 
    public String dialogues[][] = new String[20][20];
    public Entity attacker; 
    public Entity linkedEntity; 
    public boolean temp = false;

    //STATE
    public int worldX,worldY; // player position
    public String direction = "down"; 
    public int spriteNum = 1; 
    public int dialogueSet = 0; 
    public int dialogueIndex = 0;
    public boolean collisionOn = false;
    public boolean invincible = false; 
    public boolean attacking = false;
    public boolean alive = true;
    public boolean dying = false;
    public boolean hpBarOn = false;
    public boolean onPath = false;
    public boolean knockBack = false;
    public String knockBackDirection;
    public boolean guarding = false;
    public boolean transparent = false; //invincible when only gets damage
    public boolean offBalance = false;
    public Entity loot;
    public boolean opened = false;
    public boolean inRage = false;
    public boolean sleep = false;
    public boolean drawing = true;

    //COUNTER
    public int spriteCounter = 0;
    public int actionLockCounter = 0;
    public int invincibleCounter = 0;
    public int shotAvailableCounter = 0;
    int dyingCounter = 0;
    public int hpBarCounter = 0;
    int knockBackCounter = 0;
    public int guardCounter = 0;
    int offBalanceCounter = 0;

    //CHARACTER ATTRIBUTES
    public String name;
    public int defaultSpeed;
    public int speed;
    public int maxLife;
    public int life;
    public int maxMana;
    public int mana;
    public int ammo;
    public int level;
    public int strength;
    public int dexterity;
    public int attack;
    public int defense;
    public int exp;
    public int nextLevelExp;
    public int coin;
    public int motion1_duration;
    public int motion2_duration;
    public Entity currentWeapon;
    public Entity currentShield;
    public Entity currentLight;
    public Projectile projectile;
    public boolean boss;

    //ITEM ATTRIBUTES
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
    public int attackValue;
    public int defenseValue;
    public String description = "";
    public int useCost;
    public int value;
    public int price;
    public int knockBackPower;
    public boolean stackable = false;
    public int amount = 1;
    public int lightRadius;

    //TYPE
    public int type; // 
    public final int type_player = 0;
    public final int type_npc = 1;
    public final int type_monster = 2;
    public final int type_sword = 3;
    public final int type_axe = 4;
    public final int type_shield = 5;
    public final int type_consumable = 6;
    public final int type_pickupOnly = 7;
    public final int type_obstacle = 8;
    public final int type_light = 9;
    public final int type_pickaxe = 10;

    public Entity(GamePanel gp)
    {
        this.gp = gp;

    }
    public int getScreenX()
    {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        return screenX;
    }
    public int getScreenY()
    {
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        return screenY;
    }
    public int getLeftX()
    {
        return worldX + solidArea.x;
    }
    public int getRightX()
    {
        return worldX + solidArea.width + solidArea.width;
    }
    public int getTopY()
    {
        return worldY + solidArea.y;
    }
    public int getBottomY()
    {
        return worldY + solidArea.y + solidArea.height;
    }
    public int getCol()
    {
        return (worldX + solidArea.x) / gp.tileSize;
    }
    public int getRow()
    {
        return (worldY + solidArea.y) / gp.tileSize;
    }
    public int getCenterX()
    {
        int centerX = worldX + left1.getWidth()/2;
        return centerX;
    }
    public int getCenterY()
    {
        int centerY = worldY + up1.getWidth()/2;
        return centerY;
    }
    public int getXdistance(Entity target)
    {
        int xDistance = Math.abs(getCenterX() - target.getCenterX());
        return xDistance;
    }
    public int getYdistance(Entity target)
    {
        int yDistance = Math.abs(getCenterY() - target.getCenterY());
        return yDistance;
    }
    public int getTileDistance(Entity target)
    {
        int tileDistance = (getXdistance(target) + getYdistance(target))/gp.tileSize;
        return tileDistance;
    }
    public int getGoalCol(Entity target)
    {
        int goalCol = (target.worldX + target.solidArea.x) / gp.tileSize;
        return goalCol;

    }
    public int getGoalRow(Entity target)
    {
        int goalRow = (target.worldY + target.solidArea.y) / gp.tileSize;
        return goalRow;
    }
    public void resetCounter()
    {
        spriteCounter = 0;
        actionLockCounter = 0;
        invincibleCounter = 0;
        shotAvailableCounter = 0;
        dyingCounter = 0;
        hpBarCounter = 0;
        knockBackCounter = 0;
        guardCounter = 0;
        offBalanceCounter = 0;
    }
    public void setDialogue()
    {

    }
    public void setLoot(Entity loot)
    {

    }
    public void setAction()
    {

    }
    public void move(String direction)
    {

    }
    public void damageReaction()
    {

    }
    public void speak()
    {

    }
    public void facePlayer()  // Sets the direction to face the player based on their current direction.
    {
        switch (gp.player.direction)
        {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
    public void startDialogue(Entity entity, int setNum) // Initiates a dialogue with the specified entity
    {
        gp.gameState = gp.dialogueState;
        gp.ui.npc = entity;
        dialogueSet = setNum;
    }
    public void interact()
    {

    }
    public boolean use(Entity entity)
    {
        return false;
        //return "true" if you used the item and "false" if you failed to use it.
    }
    public void checkDrop()
    {

    }
    public void dropItem(Entity droppedItem)  // Checks for item drops.
   // Places the dropped item in the first available slot on the map.
    {
        for(int i = 0; i < gp.obj[1].length; i++)
        {
            if(gp.obj[gp.currentMap][i] == null)
            {
                gp.obj[gp.currentMap][i] = droppedItem;
                gp.obj[gp.currentMap][i].worldX = worldX;  //the dead monster's worldX
                gp.obj[gp.currentMap][i].worldY = worldY;  //the dead monster's worldY
                break; //end loop after finding empty slot on array
            }
        }
    }
    public Color getParticleColor() // Returns the color of the particle 
    {
        Color color = null;
        //Sub-class specifications
        return color;
    }
    public int getParticleSize()
    {
        int size = 0; //pixels
        //Sub-class specifications
        return size;
    }
    public int getParticleSpeed()
    {
        int speed = 0;
        //Sub-class specifications
        return speed;
    }
    public int getParticleMaxLife()
    {
        int maxLife = 0;
        //Sub-class specifications
        return maxLife;
    }
    public void generateParticle(Entity generator, Entity target)
    {
    	//Generates four particles based on the generator's properties and adds them to the particle list.
        Color color = generator.getParticleColor();
        int size = generator.getParticleSize();
        int speed = generator.getParticleSpeed();
        int maxLife = generator.getParticleMaxLife();

        
        Particle p1 = new Particle(gp, target, color, size, speed, maxLife, -2, -1);    
        Particle p2 = new Particle(gp, target, color, size, speed, maxLife, 2, -1);     
        Particle p3 = new Particle(gp, target, color, size, speed, maxLife, -2, 1);     
        Particle p4 = new Particle(gp, target, color, size, speed, maxLife, 2, 1);      

        gp.particleList.add(p1);
        gp.particleList.add(p2);
        gp.particleList.add(p3);
        gp.particleList.add(p4);
    }
    public void checkCollision()
    { 
        collisionOn = false;  // Reset collision status before checks.
        gp.cChecker.checkTile(this);  // Check collision with tiles in the game 
        gp.cChecker.checkObject(this,false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        gp.cChecker.checkEntity(this,gp.iTile);
        boolean contactPlayer = gp.cChecker.checkPlayer(this); // Check if this entity makes contact with the player.
        if(this.type == type_monster && contactPlayer == true)  // If this entity is a monster and it contacts the player, deal damage
        {
            damagePlayer(attack); //Inflict damage on the player.
        }
    }
    public void update()
    {
        if(sleep == false) // Skip update if sleeping.
        {
            if(knockBack == true)
            {
                checkCollision();
                if(collisionOn == true)  // Stop knockback on collision.
                {
                    knockBackCounter = 0;
                    knockBack = false;
                    speed = defaultSpeed;
                }
                else if(collisionOn == false)  // Continue knockback in the specified direction.
                {
                    switch (knockBackDirection)
                    {
                        case "up" :
                            worldY -= speed;
                            break;

                        case "down" :
                            worldY += speed;
                            break;

                        case "left" :
                            worldX -= speed;
                            break;

                        case "right" :
                            worldX += speed;
                            break;
                    }
                }
                knockBackCounter++;
                if(knockBackCounter == 10) // Stop knockback after 10 ticks.
                {
                    knockBackCounter = 0;
                    knockBack = false;
                    speed = defaultSpeed;
                }
            }
            else if(attacking == true) // Handle attacking.
            {
                attacking();
            }
            else // Handle normal movement and actions.
            {
                setAction(); // Set entity action.
                checkCollision(); 

                if(collisionOn == false) // Move in the current direction if no collision.
                {
                    switch (direction)
                    {
                        case "up" :
                            worldY -= speed;
                            break;

                        case "down" :
                            worldY += speed;
                            break;

                        case "left" :
                            worldX -= speed;
                            break;

                        case "right" :
                            worldX += speed;
                            break;
                    }
                }
                spriteCounter++; // Update sprite animation.
                if (spriteCounter > 24) {
                    if (spriteNum == 1) // Alternate sprite frames every 12 ticks.
                    {
                        spriteNum = 2;
                    } else if (spriteNum == 2) {
                        spriteNum = 1;
                    }
                    spriteCounter = 0;   // Reset sprite counter.
                }
            }
            
            if(invincible == true) // Handle invincibility duration.
            {
                invincibleCounter++;
                if(invincibleCounter > 40)
                {
                    invincible = false;
                    invincibleCounter = 0;   //End invincibility after 40 ticks.
                }
            }
            if(shotAvailableCounter < 30) // Increment shot cooldown counter.

            {
                shotAvailableCounter++;
            }
            if(offBalance == true) // Handle off-balance state.
            {
                offBalanceCounter++;
                if(offBalanceCounter > 60)  // End off-balance state after 60 ticks.
                {
                    offBalance = false;
                    offBalanceCounter = 0;
                }
            }
        }
    }
    public void checkAttackOrNot(int rate, int straight, int horizontal)
 // Checks if the entity should attack the player based on range and probability.
    {
        boolean tartgetInRange = false; // Flag to check if the player is within attack range.
        int xDis = getXdistance(gp.player);
        int yDis = getYdistance(gp.player);

        switch (direction)  //if the player is in the attack range based on direction
        {
            case "up": // Check if the player is above and within range.
                if(gp.player.getCenterY() < getCenterY()  && yDis < straight && xDis < horizontal)
                {
                    tartgetInRange = true;
                }
                break;
            case "down": // Check if the player is below and within range.
                if(gp.player.getCenterY()  > getCenterY()  && yDis < straight && xDis < horizontal)
                {
                    tartgetInRange = true;
                }
                break;
            case "left": // Check if the player is to the left and within range.
                if(gp.player.getCenterX()  < getCenterX() && xDis < straight && yDis < horizontal)
                {
                    tartgetInRange = true;
                }
                break;
            case "right":
                if(gp.player.getCenterX() > getCenterX() && xDis < straight && yDis < horizontal)
                {
                    tartgetInRange = true;
                }
                break;
        }

        if(tartgetInRange == true)
        {
            //Check if it initiates an attack
            int i = new Random().nextInt(rate);
            if(i == 0)
            {
                attacking = true;
                spriteNum = 1;
                spriteCounter = 0;
                shotAvailableCounter = 0;
            }
        }

    }
    public void checkShootOrNot(int rate, int shotInterval)
    {
        int i = new Random().nextInt(rate);
        if(i == 0 && projectile.alive == false && shotAvailableCounter == shotInterval)
        {
            projectile.set(worldX,worldY,direction,true,this);
            //gp.projectileList.add(projectile);
            //CHECK VACANCY
            for(int ii = 0; ii < gp.projectile[1].length;ii++)
            {
                if(gp.projectile[gp.currentMap][ii] == null)
                {
                    gp.projectile[gp.currentMap][ii] = projectile;
                    break;
                }
            }
            shotAvailableCounter = 0;
        }
    }
    public void checkStartChasingOrNot(Entity target, int distance, int rate)
    {
        if(getTileDistance(target) < distance)
        {
            int i = new Random().nextInt(rate);
            if(i == 0)
            {
                onPath = true;
            }
        }
    }
    public void checkStopChasingOrNot(Entity target, int distance, int rate)
    {
        if(getTileDistance(target) > distance)
        {
            int i = new Random().nextInt(rate);
            if(i == 0)
            {
                onPath = false;
            }
        }
    }
    public void getRandomDirection(int interval)
    {
        actionLockCounter++;

        if(actionLockCounter > interval)
        {
            Random random = new Random();
            int i = random.nextInt(100) + 1;  // pick up  a number from 1 to 100
            if(i <= 25){direction = "up";}
            if(i>25 && i <= 50){direction = "down";}
            if(i>50 && i <= 75){direction = "left";}
            if(i>75 && i <= 100){direction = "right";}
            actionLockCounter = 0; // reset
        }
    }
    public void moveTowardPlayer(int interval)
    {
        actionLockCounter++;

        if(actionLockCounter > interval)
        {
            if(getXdistance(gp.player) > getYdistance(gp.player)) //if entity far to the player on X axis moves right or left
            {
                if(gp.player.getCenterX() < getCenterX()) //Player is left side, entity moves to left
                {
                    direction = "left";
                }
                else
                {
                    direction = "right";
                }
            }
            else if(getXdistance(gp.player) < getYdistance(gp.player))  //if entity far to the player on Y axis moves up or down
            {
                if(gp.player.getCenterY() < getCenterY()) //Player is up side, entity moves to up
                {
                    direction = "up";
                }
                else
                {
                    direction = "down";
                }
            }
            actionLockCounter = 0;
        }
    }
    public String getOppositeDirection(String direction)
    {
        String oppositeDirection = "";

        switch (direction)
        {
            case "up": oppositeDirection = "down";break;
            case "down": oppositeDirection = "up";break;
            case "left": oppositeDirection = "right";break;
            case "right": oppositeDirection = "left";break;
        }

        return oppositeDirection;
    }
    public void attacking()
    {
        spriteCounter++;

        if(spriteCounter <= motion1_duration)
        {
            spriteNum = 1;
        }
        if(spriteCounter > motion1_duration && spriteCounter <= motion2_duration)
        {
            spriteNum = 2;

            //Save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //Adjust player's worldX/worldY for the attackArea
            switch (direction)
            {
                case "up": worldY -= attackArea.height; break;                
                case "down" : worldY += gp.tileSize; break;                    
                case "left" : worldX -= attackArea.width; break;               
                case "right" : worldX += gp.tileSize; break;                   
            }

            //attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            if(type == type_monster)
            {
                if(gp.cChecker.checkPlayer(this) == true) //This means attack is hitting player
                {
                    damagePlayer(attack);
                }
            }
            else // Player
            {
                //Check monster collision with the updated worldX, worldY and solidArea
                int monsterIndex = gp.cChecker.checkEntity(this,gp.monster);
                gp.player.damageMonster(monsterIndex, this, attack, currentWeapon.knockBackPower);

                int iTileIndex = gp.cChecker.checkEntity(this, gp.iTile);
                gp.player.damageInteractiveTile(iTileIndex);

                int projectileIndex = gp.cChecker.checkEntity(this, gp.projectile);
                gp.player.damageProjectile(projectileIndex);
            }

            //After checking collision, restore the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        if(spriteCounter > motion2_duration)
        {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void damagePlayer(int attack)
    {
        if(gp.player.invincible == false)
        {
            int damage = attack - gp.player.defense;
            //Get an opposite direction of this attacker
            String canGuardDirection = getOppositeDirection(direction);

            if(gp.player.guarding == true && gp.player.direction.equals(canGuardDirection))
            {
                //Parry //If you press guard key less then 10 frames before the attack you receive 0 damage, and you get critical chance
                if(gp.player.guardCounter < 10)
                {
                    damage = 0;
                    gp.playSE(16);
                    setKnockBack(this, gp.player, knockBackPower); //Knockback attacker //You can use shield's knockBackPower!
                    offBalance = true;
                    spriteCounter =- 60; //Attacker's sprites returns to motion1//like a stun effect
                }
                else
                {
                    //Normal Guard
                    damage /= 2;
                    gp.playSE(15);
                }
            }
            else
            {
                //Not guarding
                gp.playSE(6);   //receivedamage.wav
                if(damage < 1 )
                {
                    damage = 1;
                }
            }
            if(damage != 0)
            {
                gp.player.transparent = true;
                setKnockBack(gp.player, this, knockBackPower);
            }

            //We can give damage
            gp.player.life -= damage;
            gp.player.invincible = true;
        }
    }
    public void setKnockBack(Entity target, Entity attacker, int knockBackPower)
 // Sets the knockback effect on the target monsters when attacked
    {
        this.attacker = attacker;
        target.knockBackDirection = attacker.direction;
        target.speed += knockBackPower;
        target.knockBack = true;
    }
    public boolean inCamera()
 // Checks if the player is within the camera's visible range.
    {
        boolean inCamera = false;
        if(     worldX + gp.tileSize*5 > gp.player.worldX - gp.player.screenX && //*5 because skeleton lord disappears when the top left corner isn't on the screen
                worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize*5 > gp.player.worldY - gp.player.screenY &&
                worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
        {
            inCamera = true; // Player is within the camera's visible range.
        }
        return inCamera;
    }
    public void draw(Graphics2D g2)
    {
        BufferedImage image= null;


        if(inCamera() == true)
        {
            int tempScreenX = getScreenX();
            int tempScreenY = getScreenY();


            switch (direction)
            {
                case "up" :
                    if(attacking == false) //Normal walking sprites
                    {
                        if(spriteNum == 1){image = up1;}
                        if(spriteNum == 2) {image = up2;}
                    }
                    if(attacking == true)  //Attacking sprites
                    {
                        tempScreenY = getScreenY() - up1.getHeight();    //Adjusted the player's position one tile to up. Explained why I did it at where I call attacking() in update().
                        if(spriteNum == 1) {image = attackUp1;}
                        if(spriteNum == 2) {image = attackUp2;}
                    }
                    break;

                case "down" :
                    if(attacking == false) //Normal walking sprites
                    {
                        if(spriteNum == 1){image = down1;}
                        if(spriteNum == 2){image = down2;}
                    }
                    if(attacking == true)  //Attacking sprites
                    {
                        if(spriteNum == 1){image = attackDown1;}
                        if(spriteNum == 2){image = attackDown2;}
                    }
                    break;

                case "left" :
                    if(attacking == false) //Normal walking sprites
                    {
                        if(spriteNum == 1) {image = left1;}
                        if(spriteNum == 2) {image = left2;}
                    }
                    if(attacking == true)  //Attacking sprites
                    {
                        tempScreenX = getScreenX() - up1.getWidth();    //Adjusted the player's position one tile left. Explained why I did it at where I call attacking() in update().
                        if(spriteNum == 1) {image = attackLeft1;}
                        if(spriteNum == 2) {image = attackLeft2;}
                    }
                    break;

                case "right" :
                    if(attacking == false) //Normal walking sprites
                    {
                        if(spriteNum == 1) {image = right1;}
                        if(spriteNum == 2) {image = right2;}
                    }
                    if(attacking == true)  //Attacking sprites
                    {
                        if(spriteNum == 1) {image = attackRight1;}
                        if(spriteNum == 2) {image = attackRight2;}
                    }
                    break;
            }

            //Make entity half-transparent (%30) when invincible
            if(invincible == true)
            {
                hpBarOn = true;    //when player attacks monster play hpBar
                hpBarCounter = 0;  //reset monster aggro
                changeAlpha(g2,0.4F);
            }

            if(dying == true)
            {
                dyingAnimation(g2);
            }

            g2.drawImage(image, tempScreenX, tempScreenY, null);

            //Reset graphics opacity / alpha
            changeAlpha(g2,1F);
        }
    }
    // Every 5 frames switch alpha between 0 and 1
    public void dyingAnimation(Graphics2D g2)
    {
        dyingCounter++;
        int i = 5;    //interval

        if(dyingCounter <= i) {changeAlpha(g2,0f);}                             //If you want add death animation or something like that, you can use your sprites instead of changing alpha inside of if statements
        if(dyingCounter > i && dyingCounter <= i*2) {changeAlpha(g2,1f);}
        if(dyingCounter > i*2 && dyingCounter <= i*3) {changeAlpha(g2,0f);}
        if(dyingCounter > i*3 && dyingCounter <= i*4) {changeAlpha(g2,1f);}
        if(dyingCounter > i*4 && dyingCounter <= i*5) {changeAlpha(g2,0f);}
        if(dyingCounter > i*5 && dyingCounter <= i*6) {changeAlpha(g2,1f);}
        if(dyingCounter > i*6 && dyingCounter <= i*7) {changeAlpha(g2,0f);}
        if(dyingCounter > i*7 && dyingCounter <= i*8) {changeAlpha(g2,1f);}
        if(dyingCounter > i*8)
        {
            alive = false;
        }
    }
    public void changeAlpha(Graphics2D g2, float alphaValue)
    {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alphaValue));
    }
    public BufferedImage setup(String imagePath, int width, int height)
    {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try
        {
            image = ImageIO.read(getClass().getResourceAsStream(imagePath + ".png"));
            image = uTool.scaleImage(image,width,height);   //it scales to tilesize , will fix for player attack(16px x 32px) by adding width and height
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return image;
    }
    public void searchPath(int goalCol, int goalRow)
    {
        int startCol = (worldX + solidArea.x) / gp.tileSize;
        int startRow = (worldY + solidArea.y) / gp.tileSize;
        gp.pFinder.setNodes(startCol,startRow,goalCol,goalRow,this);
        if(gp.pFinder.search() == true)
        {
            //Next WorldX and WorldY
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            //Entity's solidArea position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.height;

            // TOP PATH
            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize)
            {
                direction = "up";
            }
            // BOTTOM PATH
            else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize)
            {
                direction = "down";
            }
            // RIGHT - LEFT PATH
            else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize)
            {
                //either left or right
                // LEFT PATH
                if(enLeftX > nextX)
                {
                    direction = "left";
                }
                // RIGHT PATH
                if(enLeftX < nextX)
                {
                    direction = "right";
                }
            }
            //OTHER EXCEPTIONS
            else if(enTopY > nextY && enLeftX > nextX)
            {
                // up or left
                direction = "up";
                checkCollision();
                if(collisionOn == true)
                {
                    direction = "left";
                }
            }
            else if(enTopY > nextY && enLeftX < nextX)
            {
                // up or right
                direction = "up";
                checkCollision();
                if(collisionOn == true)
                {
                    direction = "right";
                }
            }
            else if(enTopY < nextY && enLeftX > nextX)
            {
                // down or left
                direction = "down";
                checkCollision();
                if(collisionOn == true)
                {
                    direction = "left";
                }
            }
            else if(enTopY < nextY && enLeftX < nextX)
            {
                // down or right
                direction = "down";
                checkCollision();
                if(collisionOn == true)
                {
                    direction = "right";
                }
            }
            // for following player, disable this. It should be enabled when npc walking to specified location
//            int nextCol = gp.pFinder.pathList.get(0).col;
//            int nextRow = gp.pFinder.pathList.get(0).row;
//            if(nextCol == goalCol && nextRow == goalRow)
//            {
//                onPath = false;
//            }
        }
    }
    public int getDetected(Entity user, Entity target[][], String targetName)
    {
        int index = 999;

        //Check the surrounding object
        int nextWorldX = user.getLeftX();
        int nextWorldY = user.getTopY();

        switch (user.direction)
        {
            case "up" : nextWorldY = user.getTopY() - gp.player.speed; break;
            case "down": nextWorldY = user.getBottomY() + gp.player.speed; break;
            case "left": nextWorldX = user.getLeftX() - gp.player.speed; break;
            case "right": nextWorldX = user.getRightX() + gp.player.speed; break;
        }
        int col = nextWorldX/gp.tileSize;
        int row = nextWorldY/gp.tileSize;

        for(int i = 0; i < target[1].length; i++)
        {
            if(target[gp.currentMap][i] != null)
            {
                if (target[gp.currentMap][i].getCol() == col                                //checking if player 1 tile away from target (key etc.) (must be same direction)
                        && target[gp.currentMap][i].getRow() == row
                            && target[gp.currentMap][i].name.equals(targetName))
                {
                    index = i;
                    break;
                }
            }

        }
        return  index;
    }
}
