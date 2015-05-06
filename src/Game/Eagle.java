package Game;

/**
 * @author LM, PP
 */
public class Eagle extends Actor{

    public Eagle(Stage stage){
        super(stage);
        setSpriteNames(new String[]{"Eagle.png"});
    }

    public void collision(Actor a) {
        if(a instanceof Bullet){
            stage.gameOver();
        }
    }
}
