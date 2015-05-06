
package Game;

/**
 *
 * @author LM, PP
 */
import java.awt.event.KeyEvent;

public class Player extends Actor{
    protected static final int PLAYER_SPEED = 2;
    protected int vx;
    protected int vy;
    protected boolean pressed;
    private boolean up,down,left,right;

    
    
    public Player(Stage stage){
        super(stage);
        setSpriteNames( new String[] {"PlayerTankD1.png", "PlayerTankD2.png","PlayerTankR1.png", "PlayerTankR2.png",
                                      "PlayerTankU1.png", "PlayerTankU2.png","PlayerTankL1.png", "PlayerTankL2.png"});
        setFrameSpeed(9);
        pressed = false;
        direction  = 2;
    }
    
    public void act(){
        if(pressed){
            super.act();
        }
        super.setPrev(x, y);
        x+=vx;
        y+=vy;
        if(x < Stage.GAME_FIELD_X){
            x = Stage.GAME_FIELD_X;
        }else if(x > Stage.GAME_FIELD_X + Stage.GAME_FIELD_WIDTH - getWidth()){
            x = Stage.GAME_FIELD_X + Stage.GAME_FIELD_WIDTH - getWidth();
        }
        
        if(y < Stage.GAME_FIELD_Y){
            y = Stage.GAME_FIELD_Y;
        }else if(y > Stage.GAME_FIELD_Y + Stage.GAME_FIELD_HEIGHT - getHeight()){
            y = Stage.GAME_FIELD_Y + Stage.GAME_FIELD_HEIGHT - getHeight();
        }
    }
    
    public int getVx(){ return vx; }
    
    public void setVx(int i){vx = i; }
    
    public int getVy(){ return vy; }
    
    public void setVy(int i){ vy = i; }
    
    protected void updateSpeed(){
        vx=0;
        vy=0;
        if(down) vy = PLAYER_SPEED;
        if(up) vy = -PLAYER_SPEED;
        if(left) vx = -PLAYER_SPEED;
        if(right) vx = PLAYER_SPEED;
    }
    
    
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN : 
                down = false; 
                pressed = false; 
                break;
            case KeyEvent.VK_UP : 
                up = false;
                pressed = false;
                break;
            case KeyEvent.VK_LEFT : 
                left = false;
                pressed = false;
                break;
            case KeyEvent.VK_RIGHT : 
                right = false;
                pressed = false;
                break;
        }
        updateSpeed();
    }
    
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP: 
                up = true;
                down = false;
                left = false;
                right = false;
                pressed = true;
                direction = 2;
                break;
            case KeyEvent.VK_DOWN: 
                up = false;
                down = true;
                left = false;
                right = false;
                pressed = true;
                direction = 0;
                break;
            case KeyEvent.VK_LEFT: 
                up = false;
                down = false;
                left = true;
                right = false;
                pressed = true;
                direction = 3;
                break;
            case KeyEvent.VK_RIGHT: 
                up = false;
                down = false;
                left = false;
                right = true;
                pressed = true;
                direction = 1;
                break;
            case KeyEvent.VK_SPACE:
                if(!hasFired() && System.currentTimeMillis() - lastTimeFired > Stage.PLAYER_FIRING_SPEED){
                    lastTimeFired = System.currentTimeMillis();
                    fire();
                }
                break;
        }
        updateSpeed();
    }

    public void collision(Actor a){
        if(a instanceof Bullet && ((Bullet) a).owner instanceof Enemy){
            remove();
        }
        
        if(a instanceof Enemy || a instanceof Player || a instanceof Brick || a instanceof Steel || a instanceof Eagle){
            super.usePrev();
        }
    }
}
