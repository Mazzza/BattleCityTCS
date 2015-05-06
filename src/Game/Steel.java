package Game;

import java.awt.*;

/**
 * @author LM, PP
 */
public class Steel extends Actor{

    public Steel(Stage stage){
        super(stage);
        setSpriteNames(new String[]{"Steel.png"});
    }

    public void paint(Graphics2D g){
        g.drawImage( spriteCache.getSprite(spriteNames[0]), x,y, stage );
    }
}