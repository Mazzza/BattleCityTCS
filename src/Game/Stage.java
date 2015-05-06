
package Game;

import java.awt.image.ImageObserver;

/**
 *
 * @author LM, PP
 */
public interface Stage extends ImageObserver {
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 1000;
    public static final int SPEED = 10;
    public static final int ENEMY_FIRING_SPEED = 500;
    public static final int PLAYER_FIRING_SPEED = 400;
    public static final int GAME_FIELD_WIDTH = 780;
    public static final int GAME_FIELD_HEIGHT = 780;
    public static final int GAME_FIELD_X = (Stage.WIDTH - Stage.GAME_FIELD_WIDTH) / 2;
    public static final int GAME_FIELD_Y = (Stage.HEIGHT - Stage.GAME_FIELD_HEIGHT) / 2;
    public SpriteCache getSpriteCache();
    public void addEnemy(Actor a);
    public void addBullet(Actor a);
    public void addBrick(Actor a);
    public void gameOver();

}
