package slick2d;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

public class Camera {

    public float camX;
    public float camY;
    float screenWidth;
    float screenHeight;
    float gameScale = 1f;
    private final Player player;

    public Camera(Player p) {
        player = p;
    }

    public void init(GameContainer gc) throws SlickException {
        screenWidth = gc.getWidth();
        screenHeight = gc.getHeight();
    }

    public void update(GameContainer gc) throws SlickException {

        //System.out.println(); //25.5
        //System.out.println(player.CollisionBox.getHalfY()); //50.5
        camX = screenWidth / gameScale / 2f - (player.CollisionBox.lineTop.getX() + player.CollisionBox.getHalfX());
        camY = screenHeight / gameScale / 2f - (player.CollisionBox.lineTop.getY() + player.CollisionBox.getHalfY());
    }

    public float getGameScale() {
        return gameScale;
    }

    public void setGameScale(float gameScale) {
        this.gameScale = gameScale;
    }

}
