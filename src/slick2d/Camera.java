package slick2d;

import org.newdawn.slick.GameContainer;

public class Camera {

    public int offsetMaxX;
    public int offsetMaxY;
    public int offsetMinX;
    public int offsetMinY;

    public float camX;
    public float camY;

    private Player player;

    public Camera(Player p) {

        //Setting offset max's and minimums
        offsetMaxX = 0;
        offsetMaxY = 0;
        offsetMinX = 0;
        offsetMinY = 0;

        player = p;

    }

    public void update(GameContainer gc) {
        //System.out.println(); //25.5
        //System.out.println(player.CollisionBox.getHalfY()); //50.5
        camX = (player.CollisionBox.lineTop.getX() + player.CollisionBox.getHalfX()) - (gc.getWidth() / 2);
        
        camY = (player.CollisionBox.lineTop.getY() + player.CollisionBox.getHalfY()) - (gc.getHeight() / 2);

    }

}