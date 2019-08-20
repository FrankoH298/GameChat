package slick2d;

/**
 *
 * @author FrankoH
 */
public class Bot extends Agent {

    public Bot(float x, float y, Slick2D slick) {
        super(x, y, slick);
    }

    @Override
    public String toString() {
        return (this.x + "@" + this.y);
    }

    public void updatePlayer(float x, float y) {
        if (this.x != x) {
            if (this.x < x) {
                moveRight();
            } else {
                moveLeft();
            }
            this.x = x;
        }
        if (this.y != y) {
            if (this.y < y) {
                moveDown();
            } else {
                moveUp();
            }
            this.y = y;
        }
    }

    public void moveUp() {
        sprite = up;
        currentHead = headUp;
        heading = 1;
        standing = false;
        //sprite.update(100);
        mapa1.checkCollision();
        if (blocked[0]) {
            standing = true;
        }
    }

    public void moveDown() {
        sprite = down;
        currentHead = headDown;
        heading = 2;
        standing = false;
        //sprite.update(100);
        if (blocked[1]) {
            standing = true;
        }
    }

    public void moveLeft() {
        sprite = left;
        currentHead = headLeft;
        heading = 3;
        standing = false;
        //sprite.update(100);
        if (blocked[3]) {
            standing = true;
        }
    }

    public void moveRight() {
        sprite = right;
        currentHead = headRight;
        heading = 4;
        standing = false;
        //sprite.update(100);
        if (blocked[2]) {
            standing = true;
        }
    }

}
