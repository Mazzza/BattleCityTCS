package Game;

import java.awt.Rectangle;

/**
 * @author LM, PP
 */

public class Bullet extends Actor{
    protected static final int BULLET_SPEED=5;
    protected Actor owner;

    public Bullet(Stage stage, Actor owner, int direction){
        super(stage);
        setSpriteNames( new String[] {"BulletD.png", "BulletD.png", "BulletR.png", "BulletR.png",
                                      "BulletU.png", "BulletU.png", "BulletL.png", "BulletL.png"} );
        this.owner = owner;
        this.direction = direction;
    }

    public void act(){
        super.act();

        if(direction==0){
            y += BULLET_SPEED;
            if(y > Stage.GAME_FIELD_Y + Stage.GAME_FIELD_HEIGHT - getHeight()){
                remove();
                owner.setFired(false);
            }
        }else if(direction==1){
            x += BULLET_SPEED;
            if(x > Stage.GAME_FIELD_X + Stage.GAME_FIELD_WIDTH - getWidth()){
                remove();
                owner.setFired(false);
            }
        }else if(direction==2){
            y -= BULLET_SPEED;
            if(y < Stage.GAME_FIELD_Y){
                remove();
                owner.setFired(false);
            }
        }else if(direction==3){
            x -= BULLET_SPEED;
            if(x < Stage.GAME_FIELD_X){
                remove();
                owner.setFired(false);
            }
        }
    }

    public void collision(Actor a){
        if((owner instanceof Enemy && a instanceof Enemy) || (owner instanceof Player && a instanceof Player)
                || (owner instanceof Enemy && a instanceof Bullet && ((Bullet) a).owner instanceof Enemy)
                || (owner instanceof Player && a instanceof Bullet && ((Bullet) a).owner instanceof Player)
                || a instanceof Eagle){

        }else{
            remove();
            owner.setFired(false);
        }
    }
}
