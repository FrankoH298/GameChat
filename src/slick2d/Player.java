package slick2d;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author FrankoH
 */
public class Player extends Agent {

    boolean personalization;
    float distance;
    Camera camera;
    Chat chat;

    public Player(float x, float y, map mapa1) {
        super(x, y, mapa1);
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public void cambiarCara(Input input, int delta) throws SlickException {
        if (input.isKeyPressed(Input.KEY_F)) {
            personalization = !personalization;
            if (personalization) {
                camera.setGameScale(2f);
                sprite = down;
                currentHead = headDown;
                heading = 2;
                standing = false;
                sprite.update(delta);
            } else {
                camera.setGameScale(1f);
            }
            /*if (numberHead == 1) {
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
             */
        }
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        Input input = gc.getInput();
        int speed = 175;
        distance = speed * ((float) delta / 1000);
        if (!chat.isMostrar()) {
            if (!personalization) {
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
            } else {
                standing = true;
            }
            cambiarCara(input, delta);
        } else {
            standing = true;
        }
        this.CollisionBox.setX(x);
        this.CollisionBox.setY(y - 20);
    }

    public void moveUp(int delta) {
        sprite = up;
        currentHead = headUp;
        heading = 1;
        standing = false;
        sprite.update(delta);
        mapa1.checkCollision();
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
        mapa1.checkCollision();
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
        mapa1.checkCollision();
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
        mapa1.checkCollision();
        if (!blocked[2]) {
            x += distance;
            blocked[2] = false;
        } else {
            standing = true;
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        sprite.draw(x, y);
        currentHead.draw(x + 10, y - 18);
        CollisionBox.render(gc, g);
        if (standing && !sprite.isStopped()) {
            sprite.stop();
            sprite.setCurrentFrame(0);
        } else if (sprite.isStopped() && !standing) {
            sprite.start();
        }
    }
}
