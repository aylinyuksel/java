package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    // Booleans to track whether movement keys are pressed
    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
        // This method is not used but must be implemented because of KeyListener
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // Get the key code of the pressed key

        // Check for movement keys and set the corresponding boolean to true
        if (code == KeyEvent.VK_W) { // W key for moving up
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) { // S key for moving down
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) { // A key for moving left
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) { // D key for moving right
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode(); // Get the key code of the released key

        // Check for movement keys and set the corresponding boolean to false
        if (code == KeyEvent.VK_W) { // W key for moving up
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) { // S key for moving down
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) { // A key for moving left
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) { // D key for moving right
            rightPressed = false;
        }
    }
}
