/*Ali Buğra Tekin 230610058
Aylin Yüksel 220610036
Aysha Mallamahmoud 230610013
Hazar Dardağan 220610010
Feyza Tiryaki 230610052
*/

package environment;

import main.GamePanel;

import java.awt.*;

public class EnvironmentManager {

    GamePanel gp; // Reference to the game panel
    public Lighting lighting; // Lighting object to handle light effects

    
    public EnvironmentManager(GamePanel gp) {
        this.gp = gp; // Set the game panel
    }

    // Initialize the environment
    public void setup() {
        lighting = new Lighting(gp); // Create a new lighting object
    }

    // Update the environment
    public void update() {
        lighting.update(); // Update lighting effects
    }

    // Draw the environment
    public void draw(Graphics2D g2) {
        lighting.draw(g2); // Render lighting effects
    }
}
