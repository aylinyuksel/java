package environment;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Lighting {

    GamePanel gp; // Reference to the game panel
    BufferedImage darknessFilter; // Image to represent the darkness filter
    public int dayCounter; // Counter to track day progress
    public float filterAlpha = 0f; // Alpha value for darkness transparency

    public final int day = 0; // Constant for day state
  
    public int dayState = day; // Current state of the day

    // Constructor: Initializes lighting and sets the light source
    public Lighting(GamePanel gp) {
        this.gp = gp;
        setLightSource(); // Create the initial light source
    }

    // Creates the light source and applies gradient effects
    public void setLightSource() {
        // Create a transparent darkness filter
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();

        // If the player has no active light source
        if (gp.player.currentLight == null) {
            g2.setColor(new Color(0, 0, 0.08f, 0.97f)); // Apply full darkness
        } else {
            // Calculate the center of the light circle
            int centerX = gp.player.screenX + (gp.tileSize) / 2;
            int centerY = gp.player.screenY + (gp.tileSize) / 2;

            // Define gradient colors and transparency levels
            Color color[] = new Color[12];
            float fraction[] = new float[12];
            color[0] = new Color(0, 0, 0.08f, 0.1f); // Center of the light
            color[11] = new Color(0, 0, 0.08f, 0.94f); // Edge of the light

            // Gradual transparency transition
            fraction[0] = 0f; // Start at the center
            fraction[11] = 1f; // End at the edge

            for (int i = 1; i < 11; i++) { // Fill intermediate gradient values
                float alpha = 0.1f + i * 0.07f;
                color[i] = new Color(0, 0, 0.08f, alpha);
                fraction[i] = 0.4f + (i * 0.05f);
            }

            // Create a radial gradient for the light circle
            RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, gp.player.currentLight.lightRadius, fraction, color);

            // Apply the gradient to the graphics object
            g2.setPaint(gPaint);
        }

        // Fill the entire screen with the darkness filter
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.dispose(); // Release resources
    }

    // Resets the lighting to the day state
    public void resetDay() {
        dayState = day; // Reset day state
        filterAlpha = 0f; // Reset darkness transparency
    }

    // Updates the lighting, checks for changes in light source
    public void update() {
        if (gp.player.lightUpdated) { // If the player updated the light source
            setLightSource(); // Recalculate the light source
            gp.player.lightUpdated = false; // Reset update flag
        }
    }

    // Draws the darkness filter on the screen
    public void draw(Graphics2D g2) {
        if (gp.currentArea == gp.outside) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha)); // Adjust alpha for outdoor areas
        }
        if (gp.currentArea == gp.outside || gp.currentArea == gp.dungeon || gp.currentArea == gp.dungeonnew) {
            g2.drawImage(darknessFilter, 0, 0, null); // Apply darkness filter for outdoor and dungeon areas
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)); // Reset alpha for indoor areas
    }
}