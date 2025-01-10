/*Ali Buğra Tekin 230610058
Aylin Yüksel 220610036
Aysha Mallamahmoud 230610013
Hazar Dardağan 220610010
Feyza Tiryaki 230610052
*/

package tile;

//import entity.Entity;
import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


//the TileManager class is responsible for loading, managing, and rendering tiles in the game world.
//it loads tile images, stores tile information (like collision), and handles map data for rendering.

public class TileManager  {
    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][]; // For Transition Between Maps
    boolean drawPath = true; // for debug(true = draws the path)
    ArrayList<String> fileNames = new ArrayList<>();
    ArrayList<String> collisionStatus = new ArrayList<>();


    public TileManager(GamePanel gp)
    {
        this.gp = gp;

        //READ TILE DATA FILE
        InputStream is = getClass().getResourceAsStream("/maps/tiledata.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        //GETTING TILE NAMES AND COLLISION INFO FROM THE FILE
        String line;
        try {
            while((line = br.readLine()) != null)
            {
                fileNames.add(line);
                collisionStatus.add(br.readLine()); //read next line
            }
                br.close();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }

        //INITIALIZE THE TILE ARRAY BASED ON THE fileNames size
        tile = new Tile[fileNames.size()]; // grass, wall, water00, water01...
        getTileImage();

        //GET THE maxWorldCol & Row
        is = getClass().getResourceAsStream("/maps/dungeon02.txt");
        br = new BufferedReader(new InputStreamReader(is));

        try
        {
            String line2 = br.readLine();
            String maxTile[] = line2.split(" ");

            gp.maxWorldCol = maxTile.length;
            gp.maxWorldRow = maxTile.length;

            mapTileNum = new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

            br.close();
        }
        catch(IOException e)
        {
            System.out.println("Exception!");
        }


        loadMap("/maps/worldmap.txt",0); // 2
        loadMap("/maps/dungeon01.txt",2);//4
        loadMap("/maps/dungeon02.txt",3);//5
        loadMap("/maps/level1.txt", 4);//1
        loadMap("/maps/dungeonnew.txt",5);//3
        loadMap("/maps/finalscence.txt",6);//6
    }
    
    
    public void getTileImage()
    {
        for(int i = 0; i < fileNames.size(); i++)
        {
            String fileName;
            boolean collision;

            //Get a file name
            fileName = fileNames.get(i);

            //Get a collision status
            if(collisionStatus.get(i).equals("true"))
            {
                collision = true;
            }
            else
            {
                collision = false;
            }

            setup(i, fileName, collision);

        }

     
    }
    public void setup(int index, String imageName, boolean collision) //method to setup each tile with its image and collision status.
    {                                                                       // IMPROVING RENDERING // Scaling with uTool
        UtilityTool uTool = new UtilityTool();                              // With uTool I'm not using anymore like: g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize,null);
        try                                                                 // I use g2.drawImage(tile[tileNum].image, screenX, screenY,null);
        {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imageName));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void loadMap(String filePath,int map) //method to load a map's tile data from a file.
    {
        try
        {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); // to read from txt

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow)
            {
                String line = br.readLine();

                while(col < gp.maxWorldCol)
                {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row] = num; //set the tile number at the current position.
                    col++;
                }
                if(col == gp.maxWorldCol)
                {
                    col = 0;
                    row++;
                }
            }
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2)
    {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow)
        {
            int tileNum = mapTileNum[gp.currentMap][worldCol][worldRow]; //drawing current map

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            //this part checks if the tile is within the player's visible screen area
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY)
            {
                g2.drawImage(tile[tileNum].image, screenX, screenY,null);
            }

            worldCol++;

            if(worldCol == gp.maxWorldCol)
            {
                worldCol = 0;
                worldRow++;
            }
        }
        
    }
}
