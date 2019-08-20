package slick2d;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.util.Timer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author FrankoH
 */
public class Player extends Agent {

    boolean personalization;
    float distance;
    Camera camera;
    Chat chat;
    Slick2D slick;
    Image flechad;
    Image flechai;

    public Player(float x, float y, Slick2D slick) {
        super(x, y, slick);
        this.slick = slick;

    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        cargarAnimacion();
        sprite = down;
        cargarCara();
        currentHead = headDown;
        mapa1.addAgent(this);
        flechad = new Image("data/flecha.png");
        flechai = new Image("data/flecha.png");
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public void cambiarCara(Input input, int delta) throws SlickException {
        if (!chat.isMostrar()) {
            if (input.isKeyPressed(Input.KEY_F)) {
                personalization = !personalization;
                if (personalization) {
                    camera.setGameScale(2f);
                    sprite = down;
                    currentHead = headDown;
                    heading = 2;
                    standing = false;
                    sprite.update(delta);
                }
            }
            if (personalization) {
                if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                    if (-camera.camX + input.getMouseX() / camera.getGameScale() > x - 60 && -camera.camX + input.getMouseX() / camera.getGameScale() < x  ) {

                        if (true) {
                            System.out.println("aeg");
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
                }
            }

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
            
            
         */
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
            slick.cliente.EnviarMensaje("N" + this.x + "@" + this.y + "@");
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
            slick.cliente.EnviarMensaje("N" + this.x + "@" + this.y + "@");
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
            slick.cliente.EnviarMensaje("N" + this.x + "@" + this.y + "@");
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
            slick.cliente.EnviarMensaje("N" + this.x + "@" + this.y + "@");
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
        if (personalization) {
            flechad.draw(x - 60, y);
            flechai.setRotation(180f);
            flechai.draw(x + 60, y);
        }

    }

}
