package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    //screen settings
	
	final int originalTileSize = 16; //16*16size
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; //48*48 tile -- farklı packagedan erişim sağladığım için public yapmam gerekti
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; //768 pixelo
	public final int screenHeight = tileSize * maxScreenRow; //576 pixelo
	
	
	//WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxScreenCol;
	public final int worldHeight = tileSize * maxScreenRow;
	
	
	
	KeyHandler keyH = new KeyHandler();
	
	TileManager tileM = new TileManager(this);
	
	
	//Thread, çoklu işlemler gerçekleştirmek için kullanılan  bir sınıf
	Thread gameThread;
	//oyunlarda bir tarafta oyun mantığını işleyen döngü çalışırken
	//bir taraftan da kullanıcı arayüzü ve ekran güncellemeleri yapılır(iki farklı işlem)
	
	//thread yapısı için:
	//	1 - Thread sınıfını extend edebiliriz
	//	2 - Runnable arayüzünü implement edebiliriz 
	
	
	public CollisionChecker collisionC = new CollisionChecker(this);
	
	public Player player = new Player(this, keyH);
	
	GamePanel(){
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //görüntüyü daha temiz hale getirmek için 
		this.setFocusable(true);
		this.addKeyListener(keyH);
		
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	

	@Override
	public void run() {
		while(gameThread != null) {
			
			// 1 - update: update info such as charachter position
			update(); //oyunun mantığını işle
			
			//2 - draw: draw the screen with the updated info
			repaint(); //ekranı yenile ve yeni durumları kullanıcıya göster 
			//paintComponent metodunun çağrılma şekli
			
			//FPS kullanmam gerek -- 60FPS seçilir genelde -- bir saniye boyunca ekran 60 kez yenilenir
			//bir saniyede 60 kez yenileme istiyorum -- bekleme süresi(ms) = 1000 /FPS = 1000/60 = 16.67 ms
			//Thread.sleep() ms cinsinden oldugu icin öyle hesapladım
			
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}

	}
	
	public void update() { 
		
		player.update();
		
	}
	
	@Override
	public void paintComponent(Graphics g) { //javanın kendisinde olan bir metot
		//bir şeyler çizmek için bu sınıfı kullanıyorum
		super.paintComponent(g); // ekrandaki önceki çizimler silinir, eski çizimlerin ekranda kalma ihitmalini kaldırır
		
		Graphics2D g2 = (Graphics2D)g; 
		//Graphics nesnesini 2D dönüştürür
		
		tileM.draw(g2);
		player.draw(g2);
		
		g2.dispose(); //çizim sonrası kullanılan kaynakları bırakıır (gereksiz bellek kullanımını engellemek için)
		
		
	}
	
	
	
}
