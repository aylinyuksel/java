package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    GamePanel gp;
    Tile[] tile; // Array to hold different types of tiles
    int mapTileNum[][]; // 2D array to store the map structure (tile numbers)

    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10]; // Initialize the tile array with space for 10 tiles
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow]; // Map grid dimensions

        getTileImage(); // Load tile images
        loadMap("/maps/map1.txt"); // Load map structure from a text file
    }

    public void getTileImage() {
        try {
            // Load tile images and assign them to tile objects
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png")); 

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png")); 

        } catch (IOException e) {
            e.printStackTrace(); // Print error if tile images fail to load
        }
    }

    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath); // Open map file
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            // Read the map file line by line and populate the mapTileNum array
            while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
                String line = br.readLine();
                while (col < gp.maxScreenCol) {
                    String numbers[] = line.split(" "); // Split numbers in the line
                    int num = Integer.parseInt(numbers[col]); // Parse tile number
                    mapTileNum[col][row] = num; // Assign tile number to the map
                    col++;
                }
                if (col == gp.maxScreenCol) { // Move to the next row
                    col = 0;
                    row++;
                }
            }
            br.close(); // Close the file
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions during file reading
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        // Loop through each tile in the map and draw it on the screen
        while (col < gp.maxScreenCol && row < gp.maxScreenRow) {
            int tileNum = mapTileNum[col][row]; // Get the tile number from the map

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null); // Draw the tile
            col++;
            x += gp.tileSize; // Move to the next tile in the row

            if (col == gp.maxScreenCol) { // Move to the next row when the current row is done
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
