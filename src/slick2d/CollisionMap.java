
package slick2d;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;



public class CollisionMap {
    map mapa1;
    ArrayList<CollisionBorder> collisions;
    
    
    public CollisionMap(map mapa1,float posX, float posY, float posX2, float posY2) {
        collisions = new ArrayList();
        this.mapa1 = mapa1;
        collisions.add(new CollisionBorder (posX, posY, posX2 - posX , posY2 - posY));
        mapa1.addColision(collisions.get(collisions.size()-1));
    }
    
    public void render(GameContainer gc,Graphics g) throws SlickException {
        for (int a = 0; a < collisions.size(); a++){
            collisions.get(a).render(gc, g);
        }
        
    }
}
