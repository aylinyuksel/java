package tile;

import java.awt.image.BufferedImage;

//this class represents a single tile in the game world, which includes its image and whether it has collision detection.
	public class Tile {
	
		public BufferedImage image;   // the image representing the tile.
		public boolean collision = false;   // a flag indicating if the tile causes a collision (e.g., walls, obstacles).
 
}