package Game;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * @author LM, PP
 */
public class Level{
    private int num;
    protected Stage stage;
    public BufferStrategy buffer;
    private Graphics2D g;
    private int brickSize;
    private int steelSize;

    Level(BufferStrategy buffer, Stage stage, int i){
        num = i;
        this.stage = stage;
        this.buffer = buffer;
        this.g = (Graphics2D)buffer.getDrawGraphics();
        this.brickSize = new Brick(stage,0,0).getWidth();
        this.steelSize = new Steel(stage).getWidth();
    }

    public void makeWall(int x, int y, int numWidth, int numHeight){
        for(int i=0; i<numWidth; i++){
            for(int j=0; j<numHeight; j++){
                Brick b = new Brick(stage,(i%2),(j%2));
                b.setX(x + b.getWidth() * i);
                b.setY(y + b.getHeight() * j);
                stage.addBrick(b);
            }
        }
    }

    public void makeSteel(int x, int y, int numWidth, int numHeight){
        for(int i=0; i<numWidth; i++){
            for(int j=0; j<numHeight; j++){
                Steel s = new Steel(stage);
                s.setX(x + s.getWidth()*i);
                s.setY(y + s.getHeight() * j);
                stage.addBrick(s);
            }
        }
    }
      
    public void init(){
        switch(num){
            case 1:
                makeWall(Stage.GAME_FIELD_X + 4*brickSize, Stage.GAME_FIELD_Y + 4*brickSize, 4, 18);
                makeWall(Stage.GAME_FIELD_X + 12*brickSize, Stage.GAME_FIELD_Y + 4*brickSize, 4, 18);
                makeWall(Stage.GAME_FIELD_X + 20*brickSize, Stage.GAME_FIELD_Y + 4*brickSize, 4, 14);
                
                makeSteel(Stage.GAME_FIELD_X + 24*brickSize, Stage.GAME_FIELD_Y + 12*brickSize, 2, 2);
                
                makeWall(Stage.GAME_FIELD_X + 28*brickSize, Stage.GAME_FIELD_Y + 4*brickSize, 4, 14);
                makeWall(Stage.GAME_FIELD_X + 36*brickSize, Stage.GAME_FIELD_Y + 4*brickSize, 4, 18);
                makeWall(Stage.GAME_FIELD_X + 44*brickSize, Stage.GAME_FIELD_Y + 4*brickSize, 4, 18);

                makeWall(Stage.GAME_FIELD_X + 20*brickSize, Stage.GAME_FIELD_Y + 22*brickSize, 4, 4);
                makeWall(Stage.GAME_FIELD_X + 28*brickSize, Stage.GAME_FIELD_Y + 22*brickSize, 4, 4);

                makeWall(Stage.GAME_FIELD_X, Stage.GAME_FIELD_Y + 26*brickSize, 4, 2);
                makeWall(Stage.GAME_FIELD_X + 48*brickSize, Stage.GAME_FIELD_Y + 26*brickSize, 4, 2);
                makeSteel(Stage.GAME_FIELD_X, Stage.GAME_FIELD_Y + 28*brickSize, 2, 1);
                makeSteel(Stage.GAME_FIELD_X + 48*brickSize, Stage.GAME_FIELD_Y + 28*brickSize, 2, 1);

                makeWall(Stage.GAME_FIELD_X + 8*brickSize, Stage.GAME_FIELD_Y + 26*brickSize, 8, 4);
                makeWall(Stage.GAME_FIELD_X + 36*brickSize, Stage.GAME_FIELD_Y + 26*brickSize, 8, 4);

                makeWall(Stage.GAME_FIELD_X + 4*brickSize, Stage.GAME_FIELD_Y + 34*brickSize, 4, 14);
                makeWall(Stage.GAME_FIELD_X + 12*brickSize, Stage.GAME_FIELD_Y + 34*brickSize, 4, 14);
                makeWall(Stage.GAME_FIELD_X + 36*brickSize, Stage.GAME_FIELD_Y + 34*brickSize, 4, 14);
                makeWall(Stage.GAME_FIELD_X + 44*brickSize, Stage.GAME_FIELD_Y + 34*brickSize, 4, 14);

                makeWall(Stage.GAME_FIELD_X + 20*brickSize, Stage.GAME_FIELD_Y + 30*brickSize, 4, 12);
                makeWall(Stage.GAME_FIELD_X + 28*brickSize, Stage.GAME_FIELD_Y + 30*brickSize, 4, 12);
                makeWall(Stage.GAME_FIELD_X + 24*brickSize, Stage.GAME_FIELD_Y + 32*brickSize, 4, 4);

                makeWall(Stage.GAME_FIELD_X + 22*brickSize, Stage.GAME_FIELD_Y + 46*brickSize, 2, 6);
                makeWall(Stage.GAME_FIELD_X + 28*brickSize, Stage.GAME_FIELD_Y + 46*brickSize, 2, 6);
                makeWall(Stage.GAME_FIELD_X + 24*brickSize, Stage.GAME_FIELD_Y + 46*brickSize, 4, 2);

                break;
        }
    }

}
