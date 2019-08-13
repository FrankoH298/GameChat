package slick2d;

/**
 *
 * @author FrankoH
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Slick2D extends BasicGame {

    map mapa1 = new map(0, 0);
    Player personaje = new Player(0, 0, mapa1);
    Bot bot1 = new Bot(300, 200, mapa1);
    Bot bot2 = new Bot(300, 405, mapa1);
    Bot bot3 = new Bot(360, 300, mapa1);
    float tempX;
    float tempY;
    Camera camera;
    CollisionMap p;

    public Slick2D(String gamename) {
        super(gamename);
        mapa1.addAgent(personaje);
        mapa1.addAgent(bot1);
        mapa1.addAgent(bot2);
        mapa1.addAgent(bot3);
        camera = new Camera(personaje);
        p = new CollisionMap(mapa1,100,100);
        
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        personaje.init(gc);
        bot1.init(gc);
        bot2.init(gc);
        bot3.init(gc);
        mapa1.init(gc);
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        mapa1.update(gc, delta);
        personaje.update(gc, delta);
        bot1.update(gc, delta);
        bot2.update(gc, delta);
        bot3.update(gc, delta);
        camera.update(gc);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.translate(-camera.camX, -camera.camY);
        mapa1.render(gc, g);
        bot1.render(gc, g);
        bot2.render(gc, g);
        bot3.render(gc, g);
        personaje.render(gc, g);
        g.drawString("X:" + Integer.toString(Math.round(personaje.getX())), personaje.getX(), personaje.getY() -40);
        g.drawString("Y:" + Integer.toString(Math.round(personaje.getY())), personaje.getX(), personaje.getY() +80);
        p.render(gc, g);
    }

    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Slick2D("Juego GG"));
            appgc.setDisplayMode(1280, 720, false);
            appgc.setShowFPS(true);
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(Slick2D.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
