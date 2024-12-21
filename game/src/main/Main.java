package main;

//import java.awt.Component;

import javax.swing.JFrame;

public class Main {
    //private static final Component GamePanel = null;

	public static void main(String[] args) {
        JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //pencere kapatıldığında uygulamanın kapatılmasını sağlar
		window.setResizable(false); //pencerenin yeniden boyutlandırılmasını devre dışı bırakma
		window.setTitle("2D Adventure");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack(); // JFrame için kullanılan bir metot
		//bileşenlere uygun pencere boyutunu hesaplar
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.setUpGame();
		gamePanel.startGameThread(); 
        
    }
}
 