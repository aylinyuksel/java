/*Ali Buğra Tekin 230610058
Aylin Yüksel 220610036
Aysha Mallamahmoud 230610013
Hazar Dardağan 220610010
Feyza Tiryaki 230610052
*/

package main;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static JFrame window;

    public static void main(String[] args) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false); // Cant Resizable
        window.setTitle("Dungeon Break\n"); // Window Name
       
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);


        window.pack(); // Resizes to prefered size and prevents overflow.

        window.setLocationRelativeTo(null); // Starts center of screen
        window.setVisible(true);

        gamePanel.setupGame(); // Setting up the game before starts
        gamePanel.startGameThread();
    }
   
}