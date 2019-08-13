package slick2d;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author FrankoH
 */
public class Player extends Agent {

    float distance;

    public Player(float x, float y, map Mapa1) {
        super(x, y, Mapa1);
    }

    public void cambiarCara(Input input) throws SlickException {
        if (input.isKeyPressed(Input.KEY_F)) {
            if (numberHead == 1) {
                data_cara = "data/head2.png";
                numberHead = 2;
            } else if (numberHead == 2) {
                data_cara = "data/head.png";
                numberHead = 1;
            }
            cargarCara();
            switch (heading) {
                case 1:
                    currentHead = headUp;
                    break;
                case 2:
                    currentHead = headDown;
                    break;
                case 3:
                    currentHead = headLeft;
                    break;
                case 4:
                    currentHead = headRight;
                    break;
            }
            currentHead.draw();
        }
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        Input input = gc.getInput();
        int speed = 175;
        distance = speed * ((float) delta / 1000);
        if (input.isKeyDown(Input.KEY_UP)) {
            moveUp(delta);
        } else if (input.isKeyDown(Input.KEY_DOWN)) {
            moveDown(delta);
        } else if (input.isKeyDown(Input.KEY_RIGHT)) {
            moveRight(delta);
        } else if (input.isKeyDown(Input.KEY_LEFT)) {
            moveLeft(delta);
        } else {
            if (!standing) {
                standing = true;
            }
        }
        this.CollisionBox.setX(x);
        this.CollisionBox.setY(y - 20);
        cambiarCara(input);
    }

    public void moveUp(int delta) {
        sprite = up;
        currentHead = headUp;
        heading = 1;
        standing = false;
        sprite.update(delta);
        Mapa1.checkCollision();
        if (!blocked[0]) {
            y -= distance;
            blocked[0] = false;
        } else {
            standing = true;
        }
    }

    public void moveDown(int delta) {
        sprite = down;
        currentHead = headDown;
        heading = 2;
        standing = false;
        sprite.update(delta);
        Mapa1.checkCollision();
        if (!blocked[1]) {
            y += distance;
            blocked[1] = false;
        } else {
            standing = true;
        }
    }

    public void moveLeft(int delta) {
        sprite = left;
        currentHead = headLeft;
        heading = 3;
        standing = false;
        sprite.update(delta);
        Mapa1.checkCollision();
        if (!blocked[3]) {
            x -= distance;
            blocked[3] = false;
        } else {
            standing = true;
        }
    }

    public void moveRight(int delta) {
        sprite = right;
        currentHead = headRight;
        heading = 4;
        standing = false;
        sprite.update(delta);
        Mapa1.checkCollision();
        if (!blocked[2]) {
            x += distance;
            blocked[2] = false;
        } else {
            standing = true;
        }
    }
}
