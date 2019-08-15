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
    
    public void updatePlayer(float x, float y){
        this.x = x;
        this.y = y;
    }

}
