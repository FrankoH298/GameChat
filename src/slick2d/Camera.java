package slick2d;

import org.newdawn.slick.GameContainer;

public class Camera {

    public float camX;
    public float camY;
    float screenWidth = 1280;
    float screenHeight = 720;
    float gameScale = 1;
    private final Player player;

    public Camera(Player p) {

        player = p;

    }

    public void update(GameContainer gc) {

        //System.out.println(); //25.5
        //System.out.println(player.CollisionBox.getHalfY()); //50.5
        camX = screenWidth/gameScale/2f - (player.CollisionBox.lineTop.getX() + player.CollisionBox.getHalfX()); 
        camY = screenHeight/gameScale/2f - (player.CollisionBox.lineTop.getY() + player.CollisionBox.getHalfY()); 
    }

    public float getGameScale() {
        return gameScale;
    }

    public void setGameScale(float gameScale) {
        this.gameScale = gameScale;
    }

}