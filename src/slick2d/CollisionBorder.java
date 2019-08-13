package slick2d;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author FrankoH
 */
public class CollisionBorder {

    Agent player;
    boolean BanderaTop;
    boolean BanderaBottom;
    boolean BanderaRight;
    boolean BanderaLeft;
    Rectangle lineTop;
    Rectangle lineBottom;
    Rectangle lineRight;
    Rectangle lineLeft;
    float xDistance1;
    float yDistance1;
    float xDistance2;
    float yDistance2;
    float halfX,halfY;

    public float getHalfX() {
        return (lineRight.getX() - lineLeft.getX()) /2;
    }
    public float getHalfY() {
        return (lineBottom.getY() - lineTop.getY()) /2;
    }

    public CollisionBorder(float x, float y, float xDistance1, float yDistance2) {
        this.xDistance1 = xDistance1;
        this.yDistance1 = 1;
        this.xDistance2 = 1;
        this.yDistance2 = yDistance2;
        this.lineTop = new Rectangle(x, y, this.xDistance1, this.yDistance1);
        this.lineBottom = new Rectangle(x, y + this.yDistance2 - this.yDistance1, this.xDistance1, this.yDistance1);
        this.lineRight = new Rectangle(x + this.xDistance1 - this.xDistance2, y, this.xDistance2, this.yDistance2);
        this.lineLeft = new Rectangle(x, y, this.xDistance2, this.yDistance2);
    }

    public void colisiona(Agent player, CollisionBorder shape) {
        if (!BanderaTop) {
            if (this.lineTop.intersects(shape.lineTop)) {
                player.blocked[0] = true;
                BanderaTop = true;
            } else if (this.lineTop.intersects(shape.lineBottom)) {
                player.blocked[0] = true;
                BanderaTop = true;
            } else if (this.lineTop.intersects(shape.lineRight)) {
                player.blocked[0] = true;
                BanderaTop = true;
            } else if (this.lineTop.intersects(shape.lineLeft)) {
                player.blocked[0] = true;
                BanderaTop = true;
            } else {
                player.blocked[0] = false;
                BanderaTop = false;
            }
        }
        //----------//
        if (!BanderaBottom) {
            if (this.lineBottom.intersects(shape.lineTop)) {
                player.blocked[1] = true;
                BanderaBottom = true;
            } else if (this.lineBottom.intersects(shape.lineBottom)) {
                player.blocked[1] = true;
                BanderaBottom = true;
            } else if (this.lineBottom.intersects(shape.lineRight)) {
                player.blocked[1] = true;
                BanderaBottom = true;
            } else if (this.lineBottom.intersects(shape.lineLeft)) {
                player.blocked[1] = true;
                BanderaBottom = true;
            } else {
                player.blocked[1] = false;
                BanderaBottom = false;
            }
        }
        //------/
        if (!BanderaRight) {
            if (this.lineRight.intersects(shape.lineTop)) {
                player.blocked[2] = true;
                BanderaRight = true;
            } else if (this.lineRight.intersects(shape.lineBottom)) {
                player.blocked[2] = true;
                BanderaRight = true;
            } else if (this.lineRight.intersects(shape.lineRight)) {
                player.blocked[2] = true;
                BanderaRight = true;
            } else if (this.lineRight.intersects(shape.lineLeft)) {
                player.blocked[2] = true;
                BanderaRight = true;
            } else {
                player.blocked[2] = false;
                BanderaRight = false;
            }
        }
        if (!BanderaLeft) {
            if (this.lineLeft.intersects(shape.lineTop)) {
                player.blocked[3] = true;
                BanderaLeft = true;
            } else if (this.lineLeft.intersects(shape.lineBottom)) {
                player.blocked[3] = true;
                BanderaLeft = true;
            } else if (this.lineLeft.intersects(shape.lineRight)) {
                player.blocked[3] = true;
                BanderaLeft = true;
            } else if (this.lineLeft.intersects(shape.lineLeft)) {
                player.blocked[3] = true;
                BanderaLeft = true;
            } else {
                player.blocked[3] = false;
                BanderaLeft = false;
            }
        }
    }

    public void setX(float x) {
        this.lineTop.setX(x);
        this.lineBottom.setX(x);
        this.lineRight.setX(x + xDistance1);
        this.lineLeft.setX(x - xDistance2);
    }

    public void setY(float y) {
        this.lineTop.setY(y);
        this.lineBottom.setY(y + yDistance2 + yDistance1);
        this.lineRight.setY(y + yDistance1);
        this.lineLeft.setY(y + yDistance1);
    }

    public float getX() {
        return this.lineTop.getX();
    }

    public float getY() {
        return this.lineTop.getY();
    }

    public void init(GameContainer gc) throws SlickException {

    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.draw(lineTop);
        g.draw(lineBottom);
        g.draw(lineRight);
        g.draw(lineLeft);

    }
}
