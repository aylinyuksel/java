package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;
import tile_interactive.InteractiveTile;

public class GamePanel extends JPanel implements Runnable {

    //screen settings
	
	final int originalTileSize = 16; //16*16size
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale; //48*48 tile -- farklı packagedan erişim sağladığım için public yapmam gerekti
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; //768 pixelo
	public final int screenHeight = tileSize * maxScreenRow; //576 pixelo
	
	
	//WORLD SETTINGS
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	//public final int worldWidth = tileSize * maxScreenCol;
	//public final int worldHeight = tileSize * maxScreenRow;
	
	//FPS
	int FPS=60;
	
	//SYSTEM
	public KeyHandler keyH = new KeyHandler(this);
	TileManager tileM = new TileManager(this);
	Sound music = new Sound(); 
	Sound se = new Sound();
	public CollisionChecker cChecker = new CollisionChecker(this);
	public AssetSetter aSetter = new AssetSetter(this); 
	public UI ui = new UI(this);
	public EventHandler eHandler = new EventHandler(this);
	Thread gameThread;
	//Thread, çoklu işlemler gerçekleştirmek için kullanılan  bir sınıf
	//oyunlarda bir tarafta oyun mantığını işleyen döngü çalışırken
	//bir taraftan da kullanıcı arayüzü ve ekran güncellemeleri yapılır(iki farklı işlem)
	//thread yapısı için:
	//	1 - Thread sınıfını extend edebiliriz
	//	2 - Runnable arayüzünü implement edebiliriz 
	
	
	//ENTITY AND OBJECTS
	public Player player = new Player(this, keyH);
	public Entity obj[] = new Entity[20];  //ayni anda max 10 obj
	public Entity npc[]=new Entity[10];
	public Entity monster[]=new Entity[20];
	public InteractiveTile iTile[] = new InteractiveTile[50];
	public ArrayList<Entity> projectileList = new ArrayList<>();
	public ArrayList<Entity> particleList = new ArrayList<>();
	ArrayList<Entity> entityList = new ArrayList<>(); //Oyundaki tüm NPC'ler ve nesneler bu listeye eklendi. 
	
	
	//GAME STATE
	public int gameState;
	public final int titleState = 0;
	public final int playState=1;
	public final int pauseState=2;
	public final int dialogueState=3;
	public final int characterState = 4;
	public final int gameOverState =6;
	
	
	
	
	
	GamePanel(){
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true); //görüntüyü daha temiz hale getirmek için 
		this.setFocusable(true);
		this.addKeyListener(keyH);
	
		
	}
	
	public void setUpGame() {
		
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		aSetter.setInteractiveTile();
		playMusic(4);
		stopMusic();
		gameState=titleState;
	}
	
	public void retry() {
		
		player.setDefaultPositions();
		player.restoreLifeAndMana();
		aSetter.setNPC();
		aSetter.setMonster();
		
	}
	
	public void restart() {
		
		player.setDefaultValues();
		player.setItems();
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setMonster();
		aSetter.setInteractiveTile();
 		
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

	    if(gameState == playState) {
	        // PLAYER
	        player.update();

	        // NPC
	        for(int i = 0; i < npc.length; i++) {
	            if(npc[i] != null) {
	                npc[i].update();
	            }
	        }
	        
	        //monster
	        for(int i = 0; i < monster.length; i++) {
	            if(monster[i] != null) {
	                if(monster[i].alive == true && monster[i].dying==false) {
	                    monster[i].update();
	                }
	                if(monster[i].alive == false) {
	                	monster[i].checkDrop();
	                    monster[i]=null;
	                }
	            }
	        }
	        
	        //projectile
	        for(int i = 0; i < projectileList.size(); i++) {
	            if(projectileList.get(i) != null) {
	                if(projectileList.get(i).alive == true) {
	                	projectileList.get(i).update();
	                }
	                if(projectileList.get(i).alive == false) {
	                	projectileList.remove(i);
	                }
	            }
	        }
	        
	        for(int i = 0; i < particleList.size(); i++) {
	            if(particleList.get(i) != null) {
	                if(particleList.get(i).alive == true) {
	                	particleList.get(i).update();
	                }
	                if(particleList.get(i).alive == false) {
	                	particleList.remove(i);
	                }
	            }
	        }
	        
	        for(int i = 0; i < iTile.length; i++) {
	        	if(iTile[i] != null) {
	        		iTile[i].update();
	        	}
	        }
	        
	    }
		if(gameState==pauseState) {
			//nothing
		}
		//player.update(); bunu sakın etkinleştirmeyin yoksa pause olunca karakter hareket edebilir.
	}
	
	@Override
	public void paintComponent(Graphics g) { //javanın kendisinde olan bir metot
		//bir şeyler çizmek için bu sınıfı kullanıyorum
		super.paintComponent(g); // ekrandaki önceki çizimler silinir, eski çizimlerin ekranda kalma ihitmalini kaldırır
		
		Graphics2D g2 = (Graphics2D)g; 
		//Graphics nesnesini 2D dönüştürür
		
		long drawStart = 0;
		if(keyH.showDebugText == true ) {
			drawStart = System.nanoTime();
		}
		
		
		
		
		
		//TITLE SCREEN
		if(gameState == titleState) {
			ui.draw(g2);
		}
		
		//OTHERS
		else {
			
			 //TILE
			tileM.draw(g2);
			entityList.add(player);
			
			//INTERACTIVE TILE
			for(int i = 0; i < iTile.length; i++) {
				if(iTile[i] != null) {
					iTile[i].draw(g2);
				}
			}
			
			//add entities to the list 
			for(int i =0;i<npc.length;i++) {  //oyundaki tum NPC ler entity arrayine eklenecek 
				if(npc[i] != null) {
					entityList.add(npc[i]);
				}
			}
			for(int i=0;i<obj.length;i++) {  
				if(obj[i]!=null) {
					entityList.add(obj[i]);
				}
			}
			for(int i=0;i<monster.length;i++) {  
				if(monster[i]!=null) {
					entityList.add(monster[i]);
				}
			}
			for(int i=0;i<projectileList.size();i++) {  
				if(projectileList.get(i)!=null) {
					entityList.add(projectileList.get(i));
				}
			}
			for(int i=0;i<particleList.size();i++) {  
				if(particleList.get(i)!=null) {
					entityList.add(particleList.get(i));
				}
			}
			
			//SORT 
			Collections.sort(entityList,new Comparator<Entity>() { //worldY degerlerini kullanip entity listesindeki tum oyun 
																	//elemanlarinin cizim siralarini belirleyecek .

				@Override
				public int compare(Entity e1, Entity e2) {
					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}
				
			});
			
			//DRAW ENTITIES 
			for(int i=0;i<entityList.size();i++) {  // entitylistteki her varligin oyunda gosterilmesini saglar
				entityList.get(i).draw(g2);
				}
			//Empty entity list
			entityList.clear(); // gosterilen varliklari cikarip listeyi bosaltir dongunun saglanmasi icin gerekli.
				
				
			
			//UI
			ui.draw(g2);
		}
		
		//DEBUG
		
		if(keyH.showDebugText == true) {
			
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			
			g2.setFont(new Font("Arial" , Font.PLAIN,20));
			g2.setColor(Color.white);
			int x= 10;
			int y = 400;
			int lineHeight = 20;
			
			g2.drawString("WorldX" + player.worldX, x, y);
			y+=lineHeight;
			g2.drawString("WorldY" + player.worldY, x, y);
			y+=lineHeight;
			g2.drawString("Col" + (player.worldX + player.solidArea.x) / tileSize, x, y);
			y+=lineHeight;
			g2.drawString("Row" + (player.worldY + player.solidArea.y) / tileSize, x, y);
			y+=lineHeight;
			g2.drawString("Draw Time: " + passed, x , y);
			
		}
		
		
		
		    g2.dispose();
			
	}
		
		
		
	
	
	public void playMusic(int i) {
		if (music.clip != null && music.clip.isRunning()) {
	        // Eğer müzik çalıyorsa, bir şey yapma
	        return;
	    }
		music.setFile(i);
		music.play();
		music.loop();
	}
	
	public void stopMusic() {
		
		music.stop();
	}
	
	public void playSE (int i) {
		
		se.setFile(i);
		se.play();
	}
	
}























