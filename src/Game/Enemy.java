
package Game;

import java.util.Random;

/**
 *
 * @author LM, PP
 */
public class Enemy extends Actor{

    protected int v, vx, vy;
    protected Random random;
    protected int distance_pixels;
    protected int distance_fields;
    
    public Enemy(Stage stage){
        super(stage);
        setSpriteNames( new String[]{"EnemyTank1D1.png", "EnemyTank1D2.png","EnemyTank1R1.png", "EnemyTank1R2.png",
                                     "EnemyTank1U1.png", "EnemyTank1U2.png","EnemyTank1L1.png", "EnemyTank1L2.png"});
        setFrameSpeed(9);
        random = new Random();
        distance_pixels = 0;
        distance_fields = 0;
    }
    
    public void act(){
        super.act();
        if(distance_pixels == 0)
        {
            int tmp = random.nextInt(4 * Stage.GAME_FIELD_HEIGHT / getHeight());
            if(tmp < distance_fields + 1)
            {
                setNewDirection();
            }
            distance_fields = (distance_fields + 1) % (4 * Stage.GAME_FIELD_HEIGHT / getHeight());
        }
        distance_pixels = (distance_pixels + 1) % (getWidth() / 4);
        super.setPrev(x, y);
        x += vx;
        y += vy;
        
        if (x < Stage.GAME_FIELD_X){
            x = Stage.GAME_FIELD_X;
            setNewDirection();
        }else if(x > Stage.GAME_FIELD_X + Stage.GAME_FIELD_WIDTH - getWidth()){
            x = Stage.GAME_FIELD_X + Stage.GAME_FIELD_WIDTH - getWidth();
            setNewDirection();
        }
        
        if (y < Stage.GAME_FIELD_Y){
            y = Stage.GAME_FIELD_Y;
            setNewDirection();
        }else if(y > Stage.GAME_FIELD_Y + Stage.GAME_FIELD_HEIGHT - getHeight()){
            y = Stage.GAME_FIELD_Y + Stage.GAME_FIELD_HEIGHT - getHeight();
            setNewDirection();
        }

        if(!hasFired() && System.currentTimeMillis() - lastTimeFired > Stage.ENEMY_FIRING_SPEED){
            lastTimeFired = System.currentTimeMillis();
            fire();
        }
    }
    
    protected void setNewDirection()
    {
        distance_fields = 0;
        direction = random.nextInt(4);
        if(direction == 0){
            vx = 0;
            vy = v;
        }else if(direction == 1){
            vx = v;
            vy = 0;
        }else if(direction == 2){
            vx = 0;
            vy = -v;
        }else{
            vx = -v;
            vy = 0;
        }
    }
    
    public int getVx(){ return vx; }
    
    public void setV(int i){ v = i; }
    
    public int getVy(){ return vy; }

    public void collision(Actor a) {
        if(a instanceof Bullet && ((Bullet) a).owner instanceof Player){
               remove();
        }

        if(a instanceof Enemy || a instanceof Player || a instanceof Brick || a instanceof Steel || a instanceof Eagle){
            super.usePrev();
            setNewDirection();
        }
    }
}
