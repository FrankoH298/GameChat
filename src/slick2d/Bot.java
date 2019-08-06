package slick2d;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author FrankoH
 */
public class Bot extends Agent {

    public Bot(float x, float y, map Mapa1) {
        super(x, y, Mapa1);
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        Input input = gc.getInput();
        int speed = 100;
        float distance = speed * ((float) delta / 1000);
        if (input.isKeyDown(Input.KEY_W)) {
            sprite = up;
            currentHead = headUp;
            standing = false;
            sprite.update(delta);
            Mapa1.checkCollision();
            if (!blocked[0]) {
                y -= distance;
                blocked[0] = false;
            } else {
                standing = true;
            }

        } else if (input.isKeyDown(Input.KEY_S)) {
            sprite = down;
            currentHead = headDown;
            standing = false;
            sprite.update(delta);
            Mapa1.checkCollision();
            if (!blocked[1]) {
                y += distance;
                blocked[1] = false;
            } else {
                standing = true;
            }
        } else if (input.isKeyDown(Input.KEY_D)) {
            sprite = right;
            currentHead = headRight;
            standing = false;
            sprite.update(delta);
            Mapa1.checkCollision();
            if (!blocked[2]) {
                x += distance;
                blocked[2] = false;
            } else {
                standing = true;
            }
        } else if (input.isKeyDown(Input.KEY_A)) {
            sprite = left;
            currentHead = headLeft;
            standing = false;
            sprite.update(delta);
            Mapa1.checkCollision();
            if (!blocked[3]) {
                x -= distance;
                blocked[3] = false;
            } else {
                standing = true;
            }
        } else {
            if (!standing) {
                standing = true;
            }
        }
        this.CollisionBox.setX(x);
        this.CollisionBox.setY(y - 20);
    }
}
