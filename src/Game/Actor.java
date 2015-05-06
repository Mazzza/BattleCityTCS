
package Game;

/**
 *
 * @author LM, PP
 */
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;




public class Actor {
    protected int x,y;
    protected int width, height;
    protected Stage stage;
    protected SpriteCache spriteCache;
    protected int currentFrame;
    protected String[] spriteNames;
    protected int frameSpeed;
    protected int t;
    protected boolean markedForRemoval;
    protected int direction;    //0-down; 1-right; 2-up; 3-left
    protected boolean fired;
    protected long lastTimeFired;
    protected int prevX, prevY;


    public Actor(Stage stage){
        this.stage = stage;
        spriteCache = stage.getSpriteCache();
        currentFrame = 0;
        frameSpeed = 1;
        t=0;
        fired = false;
        direction = 0;
        lastTimeFired = System.currentTimeMillis();
    }
    
    public void paint(Graphics2D g){
        g.drawImage( spriteCache.getSprite(spriteNames[2*direction+currentFrame]), x,y, stage );
    }

    public int getX(){ return x; }
    
    public void setX(int i){ x = i; }
    
    public int getY(){ return y; }
    
    public void setY(int i){ y = i; }
    
    public String[] getSpriteNames(){ return spriteNames; }
    
   public void setSpriteNames(String[] names) {
        spriteNames = names;
        height = 0;
        width = 0;
        for(int i = 0; i < names.length; i++ ) {
            BufferedImage image = spriteCache.getSprite(spriteNames[i]);
            height = Math.max(height,image.getHeight());
            width = Math.max(width,image.getWidth());
        }
    }
    
    public int getHeight(){ return height; }
    
    public int getWidth(){ return width; }
    
    public void setHeight(int i){ height = i; }
    
    public void setWidth(int i){ width = i; }
    
    public void act(){
        t++;
        if (t % frameSpeed == 0){
            t=0;
            currentFrame = (currentFrame + 1) % 2;
        }
    }
    
    public int getFrameSpeed(){ return frameSpeed; }
    
    public void setFrameSpeed(int i){ frameSpeed = i; }

    public void remove(){
        markedForRemoval = true;
    }

    public boolean isMarkedForRemoval(){
        return markedForRemoval;
    }

    public void fire() {
        Bullet b = new Bullet(stage,this,direction);
        if(direction == 0){
            b.setX(x);
            b.setY(y);
        }else if(direction == 1){
            b.setX(x);
            b.setY(y);
        }else if(direction == 2){
            b.setX(x);
            b.setY(y);
        }else if(direction == 3){
            b.setX(x);
            b.setY(y);
        }
        stage.addBullet(b);
        fired = true;
    }

    public boolean hasFired(){
        return fired;
    }

    public void setFired(boolean f){
        fired = f;
    }

    public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);
    }

    public void collision(Actor a){}

    public void setPrev(int x, int y)
    {
        prevX = x;
        prevY = y;
    }

    public void usePrev()
    {
        x = prevX;
        y = prevY;
    }

}
