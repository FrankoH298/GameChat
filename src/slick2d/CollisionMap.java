
package slick2d;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;



public class CollisionMap {
    
    map mapa1;
    ArrayList collisionsM;
    CollisionBorder collisionBox;
    float posX;
    float posY;

    public CollisionMap(map mapa1,float posX, float posY) {
        this.mapa1 = mapa1;
        this.collisionBox = new CollisionBorder (posX, posY, 10 , 10);
        mapa1.addColision(collisionBox);
    }
   
    
    public void update(GameContainer gc, int delta) throws SlickException {
        //Input input = gc.getInput();
    }
    
    public void render(GameContainer gc,Graphics g) throws SlickException {
        collisionBox.render(gc, g);
    }
}
