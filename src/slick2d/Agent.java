package slick2d;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author FrankoH
 */
public class Agent {

    map mapa1;
    int numberHead = 1;
    int heading = 2;
    String data_cara = "data/head.png";
    CollisionBorder CollisionBox;
    boolean[] blocked;
    boolean standing;
    Image headDraw;
    SpriteSheet head;
    Animation sprite;
    Animation up;
    Animation down;
    Animation left;
    Animation right;
    Image currentHead;
    Image headUp;
    Image headDown;
    Image headLeft;
    Image headRight;
    float x, y;
    private boolean initialized;

    public Agent(float x, float y, Slick2D slick) {
        this.x = x;
        this.y = y;
        this.CollisionBox = new CollisionBorder(x, y - 70, 30, 30);
        blocked = new boolean[4];
        this.mapa1 = slick.mapa1;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    public void cargarAnimacion() throws SlickException {
        SpriteSheet movementUp = new SpriteSheet("data/norte.png", 50, 90);
        SpriteSheet movementDown = new SpriteSheet("data/sur.png", 50, 90);
        SpriteSheet movementLeft = new SpriteSheet("data/izquierda.png", 50, 90);
        SpriteSheet movementRight = new SpriteSheet("data/derecha.png", 50, 90);
        up = new Animation(movementUp, 200);
        down = new Animation(movementDown, 200);
        left = new Animation(movementLeft, 200);
        right = new Animation(movementRight, 200);
    }

    public void cargarCara() throws SlickException {
        head = new SpriteSheet(data_cara, 34, 32);
        headUp = head.getSubImage(102, 0, 34, 32);
        headDown = head.getSubImage(0, 0, 34, 32);
        headLeft = head.getSubImage(72, 0, 34, 32);
        headRight = head.getSubImage(34, 0, 34, 32);
    }

    public void init(GameContainer gc) throws SlickException {
        cargarAnimacion();
        sprite = down;
        cargarCara();
        currentHead = headDown;
        mapa1.addAgent(this);
    }

    public void remove() {
        mapa1.entities.remove(this);
    }

    public void update(GameContainer gc, int delta) throws SlickException {
        if (!isInitialized()) {
            init(gc);
            setInitialized(true);
        }
        if (!standing) {
            standing = true;
        }
        this.CollisionBox.setX(x + 10);
        this.CollisionBox.setY(y + 50);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setBlocked(int position, boolean blocked) {
        this.blocked[position] = blocked;
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        if (!isInitialized()) {
            init(gc);
            setInitialized(true);
        }
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
