package com.zelda;

import javax.swing.JPanel;

import com.entity.Entity;
import com.entity.player.Player;
import com.map.TiledMapParser;
import com.object.SuperObject;
import com.tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {
    //Screen settings
    final int originalTileSize = 16; //16x16 pixels
    final int scale = 3; //16x16 pixels * 3 = 48x48 pixels

    final int tileSize = originalTileSize * scale;

    final int maxScreencol = 16;
    final int maxScreenrow = 12;
    final int screenWidth = tileSize * maxScreencol; //768 pixels
    final int screenHeight = tileSize * maxScreenrow; //576 pixels

    //World settings
    final int maxWorldCol = 50;
    final int maxWorldRow = 50;

    final int FPS = 60;

    KeyHandler keyHandler = new KeyHandler(this);
    Sound music = new Sound();
    Sound SE = new Sound();

    TileManager tileManager;
    TiledMapParser parser;

    CollisionChecker collisionChecker = new CollisionChecker(this);
    AssetSetter assetSetter = new AssetSetter(this); 

    UI ui = new UI(this);
    Thread gameThread;

    Player player = new Player(this, keyHandler);
    SuperObject obj[] = new SuperObject[10]; // This mean that we have 10 slots in the game to put objects at the same time
    Entity npc[] = new Entity[10]; // Same as above but for NPCs

    // Game states
    public int gameState;
    public final int PLAY_STATE = 1;
    public final int PAUSE_STATE = 2;

    public GamePanel() {
        super();
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        addKeyListener(keyHandler);
        setFocusable(true);
        parser = new TiledMapParser("./zelda/src/main/java/res/maps/world02.json");
        tileManager = new TileManager(this);
    }

    public void init(){
        assetSetter.setObject();
        assetSetter.setNPC();
        playMusic(0);
        gameState = PLAY_STATE;
    }

    public void startGameThread() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    @Override
    public void run() {
        while(gameThread != null){
            double drawInterval = 1000000000.0 / FPS; // 0.01666666666666666666666666666667 seconds
            double nextDrawTime = System.nanoTime() + drawInterval;

            update();
            repaint();

            try {
                double remainingTimeToDraw = nextDrawTime - System.nanoTime();
                if(remainingTimeToDraw < 0)
                    remainingTimeToDraw = 0;

                Thread.sleep((long) remainingTimeToDraw / 1000000);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        if(gameState == PLAY_STATE){
            //Player update
            player.update();
            //NPC update
            for(Entity n : npc)
                if(n != null)
                    n.update();
        }else if(gameState == PAUSE_STATE);
            // Nothing
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Debug
        long drawStart = System.nanoTime();

        // Draw tiles
        for(int i = 0; i < parser.getLayers().size(); i++)
            tileManager.draw(g2, parser.getLayers().get(i));

        // Draw objects
        for(SuperObject o : obj)
            if(o != null)
                o.draw(g2, this);
        
        // Draw NPCs
        for(Entity n : npc)
            if(n != null)
                n.draw(g2);

        // Draw player
        player.draw(g2);

        // Draw UI
        ui.draw(g2);

        long drawEnd = System.nanoTime();
        long drawTime = drawEnd - drawStart;
        System.out.println("Draw time: " + drawTime / 1000000 + "ms");

        g2.dispose();
    }

    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic(){
        music.stop();
    }

    public void playSE(int i){
        SE.setFile(i);
        SE.play();
    }

    // Getters
    public int getTileSize() {return tileSize;}
    public int getScreenWidth() {return screenWidth;}
    public int getScreenHeight() {return screenHeight;}
    public int getMaxScreencol() {return maxScreencol;}
    public int getMaxScreenrow() {return maxScreenrow;}
    public int getScale() {return scale;}
    public int getOriginalTileSize() {return originalTileSize;}
    public int getMaxWorldCol() {return maxWorldCol;}
    public int getMaxWorldRow() {return maxWorldRow;}
    public int getFPS() {return FPS;}
    public KeyHandler getKeyHandler() {return keyHandler;}
    public Sound getMusic() {return music;}
    public Sound getSE() {return SE;}
    public CollisionChecker getCollisionChecker() {return collisionChecker;}
    public AssetSetter getAssetSetter() {return assetSetter;}
    public Player getPlayer() {return player;}
    public UI getMyUI() {return ui;}
    public Thread getGameThread() {return gameThread;}
    public TileManager getTileManager() {return tileManager;}
    public TiledMapParser getParser() {return parser;}
    public SuperObject[] getObj() {return obj;}
    public SuperObject getObj(int objIndex) {return obj[objIndex];}
    public Entity[] getNpc() {return npc;}
    public Entity getNpc(int npcIndex) {return npc[npcIndex];}

    // Setters
    public void setObj(int index, SuperObject _obj) {obj[index] = _obj;}
    public void setNpc(int index, Entity _npc) {npc[index] = _npc;}
}
