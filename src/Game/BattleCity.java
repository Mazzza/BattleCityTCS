
package Game;

/**
 *
 * @author LM, PP
 */
import java.awt.Canvas;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;


public class BattleCity extends Canvas implements Stage, KeyListener{
    public BufferStrategy buffer;
    private SpriteCache spriteCache;
    private ArrayList enemies, bullets, bricks;
    private Player player;
    private Eagle eagle;
    private boolean gameEnded=false;
    private Level level;


    public BattleCity(int i){
        spriteCache = new SpriteCache();
        JFrame okno = new JFrame(".: BATTLE CITY :.");
        
        JPanel panel = (JPanel)okno.getContentPane();
        setBounds(0,0,Stage.WIDTH,Stage.HEIGHT);
        panel.setPreferredSize(new Dimension(Stage.WIDTH,Stage.HEIGHT));
        panel.setLayout(null);
        panel.add(this);
        
        okno.setBounds(0,0,Stage.WIDTH,Stage.HEIGHT);
        okno.setVisible(true);
        
        okno.addWindowListener( new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        
        okno.setResizable(false);
        createBufferStrategy(2);
        buffer = getBufferStrategy();
        requestFocus();
        addKeyListener(this);
        level = new Level(buffer, this, i);
    }
    
    
    public void initWorld(){
        enemies = new ArrayList();
        bullets = new ArrayList();
        bricks = new ArrayList();
        int setX, setY;

        
        for (int i = 0; i < 3; i++){
            Enemy m = new Enemy(this);
            setX = i * (Stage.GAME_FIELD_WIDTH - m.getWidth()) / 2 + (getWidth() - Stage.GAME_FIELD_WIDTH) / 2;
            setY = (getHeight() - Stage.GAME_FIELD_HEIGHT) / 2;
            m.setX(setX);
            m.setY(setY);
            m.setPrev(setX, setY);
            m.setV(2);
            enemies.add(m);
        }
        player = new Player(this);
        setX = (getWidth() - Stage.GAME_FIELD_WIDTH) / 2 + player.getWidth() * 4;
        setY = (Stage.HEIGHT + Stage.GAME_FIELD_HEIGHT) / 2 - player.getHeight();
        player.setX(setX);
        player.setY(setY);
        player.setPrev(setX, setY);

        level.init();
        
        eagle = new Eagle(this);
        eagle.setX(player.getX() + 2 * player.getWidth());
        eagle.setY(player.getY());
    }
    
   
    public void paintWorld(){
        Graphics2D g = (Graphics2D)buffer.getDrawGraphics();
        g.setColor(Color.GRAY);
        g.fillRect(0,0,getWidth(),getHeight());
        g.setColor(Color.BLACK);
        g.fillRect((getWidth() - Stage.GAME_FIELD_WIDTH) / 2, (getHeight() - Stage.GAME_FIELD_HEIGHT) / 2, Stage.GAME_FIELD_WIDTH, Stage.GAME_FIELD_HEIGHT);
        
        for(int i = 0; i < bullets.size(); i++) {
            Actor m = (Actor)bullets.get(i);
            m.paint(g);
        }
        
        player.paint(g);
        
        for(int i = 0; i < enemies.size(); i++) {
            Actor m = (Actor)enemies.get(i);
            m.paint(g);
        }
        
        for(int i = 0; i < bricks.size(); i++) {
            Actor m = (Actor)bricks.get(i);
            m.paint(g);
        }
        
        eagle.paint(g);
        buffer.show();
    }
    
    
    public void updateWorld(){
        int i = 0;
        while (i < enemies.size()){
            Actor m = (Actor)enemies.get(i);
            if(m.isMarkedForRemoval()){
                enemies.remove(i);
            }else{
                m.act();
                i++;
            }
        }
        i = 0;
        while (i < bullets.size()){
            Actor m = (Actor)bullets.get(i);
            if(m.isMarkedForRemoval()){
                bullets.remove(i);
            }else{
                m.act();
                i++;
            }
        }
        i = 0;
        while (i < bricks.size()){
            Actor m = (Actor)bricks.get(i);
            if(m.isMarkedForRemoval()){
                bricks.remove(i);
            }else{
                m.act();
                i++;
            }
        }
        if(player.isMarkedForRemoval()){
            gameOver();
        }else{
            player.act();
        }
    }
    
    public SpriteCache getSpriteCache() {
        return spriteCache;
    }
    
    public void game(){
        initWorld();
        while(isVisible() && !gameEnded){
            updateWorld();
            checkCollisions();
            paintWorld();
            try {
                Thread.sleep(Stage.SPEED);
            }catch (InterruptedException e) {}
        }
        paintGameOver();
        buffer.show();
    }
    
    
    public void keyPressed(KeyEvent e){
        player.keyPressed(e);
    }
    
    public void keyReleased(KeyEvent e){
        player.keyReleased(e);
    }
    
    public void keyTyped(KeyEvent e){}

    public void addEnemy(Actor a) {
        enemies.add(a);
    }
    
    public void addBullet(Actor a) {
        bullets.add(a);
    }
    
    public void addBrick(Actor a) {
        bricks.add(a);
    }

    public void checkCollisions(){
        Rectangle playerBounds = player.getBounds();
        Rectangle eagleBounds = eagle.getBounds();

        if(eagleBounds.intersects(playerBounds)){
            player.collision(eagle);
            eagle.collision(player);
        }

        for(int i = 0; i < enemies.size(); i++){
            Actor a1 = (Actor)enemies.get(i);
            Rectangle r1 = a1.getBounds();

            if(r1.intersects(playerBounds)){
                player.collision(a1);
                a1.collision(player);
            }
            if(r1.intersects(eagleBounds)){
                eagle.collision(a1);
                a1.collision(eagle);
            }

            for (int j = i+1; j < enemies.size(); j++){
                Actor a2 = (Actor)enemies.get(j);
                Rectangle r2 = a2.getBounds();
                if(r1.intersects(r2)){
                    a1.collision(a2);
                    a2.collision(a1);
                }
            }
            
            for (int j = 0; j < bullets.size(); j++){
                Actor a2 = (Actor)bullets.get(j);
                Rectangle r2 = a2.getBounds();
                if(r1.intersects(r2)){
                    a1.collision(a2);
                    a2.collision(a1);
                }
            }
            
            for (int j = 0; j < bricks.size(); j++){
                Actor a2 = (Actor)bricks.get(j);
                Rectangle r2 = a2.getBounds();
                if(r1.intersects(r2)){
                    a1.collision(a2);
                    a2.collision(a1);
                }
            }
        }
        
        for(int i = 0; i < bullets.size(); i++){
            Actor a1 = (Actor)bullets.get(i);
            Rectangle r1 = a1.getBounds();

            if(r1.intersects(playerBounds)){
                player.collision(a1);
                a1.collision(player);
            }
            if(r1.intersects(eagleBounds)){
                eagle.collision(a1);
                a1.collision(eagle);
            }
            
            for (int j = i + 1; j < bullets.size(); j++){
                Actor a2 = (Actor)bullets.get(j);
                Rectangle r2 = a2.getBounds();
                if(r1.intersects(r2)){
                    a1.collision(a2);
                    a2.collision(a1);
                }
            }
            
            for (int j = 0; j < bricks.size(); j++){
                Actor a2 = (Actor)bricks.get(j);
                Rectangle r2 = a2.getBounds();
                if(r1.intersects(r2)){
                    a1.collision(a2);
                    a2.collision(a1);
                }
            }
        }
        
        for(int i = 0; i < bricks.size(); i++){
            Actor a1 = (Actor)bricks.get(i);
            Rectangle r1 = a1.getBounds();

            if(r1.intersects(playerBounds)){
                player.collision(a1);
                a1.collision(player);
            }
        }
    }

    public void gameOver(){
        gameEnded = true;
    }

    public void paintGameOver(){
        Graphics2D g = (Graphics2D)buffer.getDrawGraphics();
        g.drawImage(spriteCache.getSprite("GameOver.png"), Stage.WIDTH / 2 - 100, Stage.HEIGHT / 2 - 50, this);
    }


    public static void main(String[] args) {
        BattleCity bc = new BattleCity(1);
        bc.game();
    }
}

