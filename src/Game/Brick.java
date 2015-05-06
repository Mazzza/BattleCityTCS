package Game;

import java.awt.*;

/**
 * @author LM, PP
 */
public class Brick extends Actor{

    public Brick(Stage stage, int i, int j){
        super(stage);
        if(i==0 && j==0){
            setSpriteNames(new String[]{"Brick11.png"});
        }else if(i==0 && j==1){
            setSpriteNames(new String[]{"Brick12.png"});
        }else if(i==1 && j==0){
            setSpriteNames(new String[]{"Brick21.png"});
        }else if(i==1 && j==1){
            setSpriteNames(new String[]{"Brick22.png"});
        }
    }

    public void collision(Actor a) {
        if(a instanceof Bullet){
            remove();
        }
    }

    public void paint(Graphics2D g){
        g.drawImage( spriteCache.getSprite(spriteNames[0]), x,y, stage );
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x + 6,y + 6,width - 12,height -12);
    }
}
