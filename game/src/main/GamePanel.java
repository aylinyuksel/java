package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int originalTileSize = 16; // Base tile size (16x16 pixels)
    final int scale = 3; // Scale factor for tiles

    public final int tileSize = originalTileSize * scale; // Final tile size (48x48 pixels)
    public final int maxScreenCol = 16; // Maximum number of columns on the screen
    public final int maxScreenRow = 12; // Maximum number of rows on the screen
    public final int screenWidth = tileSize * maxScreenCol; // Total screen width in pixels
    public final int screenHeight = tileSize * maxScreenRow; // Total screen height in pixels

    // FPS (Frames Per Second) setting
    int FPS = 60;

    // Game components
    TileManager tileM = new TileManager(this); // Manages the tile system
    KeyHandler keyH = new KeyHandler(); // Handles keyboard inputs
    Thread gameThread; // Main game loop thread
    Player player = new Player(this, keyH); // The player entity

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight)); // Set screen size
        this.setBackground(Color.black); // Set background color
        this.setDoubleBuffered(true); // Enable double buffering for smoother rendering
        this.addKeyListener(keyH); // Add keyboard listener
        this.setFocusable(true); // Ensure the panel can capture keyboard inputs
    }

    public void startGameThread() {
        gameThread = new Thread(this); // Initialize the game thread
        gameThread.start(); // Start the game loop
    }

    @Override
    public void run() {
        // Calculate time between frames (16 ms for 60 FPS)
        double drawInterval = 1000000000 / FPS; // Time per frame in nanoseconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            update(); // Update game state (e.g., player movement)
            repaint(); // Redraw the screen

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000; // Convert to milliseconds
                if (remainingTime < 0) {
                    remainingTime = 0; // Prevent negative sleep time
                }
                Thread.sleep((long) remainingTime); // Pause the thread to maintain FPS
                nextDrawTime += drawInterval; // Calculate next frame time
            } catch (InterruptedException e) {
                e.printStackTrace(); // Handle interruption
            }
        }
    }

    public void update() {
        player.update(); // Update the player's state (position, animation, etc.)
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Clear the screen

        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2); // Draw tiles
        player.draw(g2); // Draw the player
        g2.dispose(); // Release resources used by the Graphics2D object
    }
}
