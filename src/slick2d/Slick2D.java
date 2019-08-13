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
    float tempX;
    float tempY;
    Camera camera;
    ClienteChat cliente;
    Console consola;
    Chat chat;
    CollisionMap p;

    public Slick2D(String gamename) {
        super(gamename);
        mapa1.addAgent(personaje);
        mapa1.addAgent(bot1);
        camera = new Camera(personaje);
        consola = new Console();
        cliente = new ClienteChat("192.168.60.177", "2000", consola);
        cliente.conectar();
        p = new CollisionMap(mapa1, 100, 100);
        for (int a = 0; a < consola.getLength(); a++) {
            consola.setChat(a, "");
        }
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        personaje.init(gc);
        bot1.init(gc);
        mapa1.init(gc);
        chat = new Chat(gc, consola, cliente);
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        mapa1.update(gc, delta);
        personaje.update(gc, delta);
        bot1.update(gc, delta);
        camera.update(gc);
        chat.update(gc, delta);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        g.translate(-camera.camX, -camera.camY);
        mapa1.render(gc, g);
        bot1.render(gc, g);
        personaje.render(gc, g);
        chat.render(gc, g, personaje.CollisionBox.getX() - (chat.getWidth() / 2) + personaje.CollisionBox.getHalfX(), personaje.CollisionBox.getY() + personaje.CollisionBox.yDistance2 + (chat.getHeight() / 2));
        p.render(gc, g);
        g.drawString("X:" + Integer.toString(Math.round(personaje.getX())), personaje.getX(), personaje.getY() - 40);
        g.drawString("Y:" + Integer.toString(Math.round(personaje.getY())), personaje.getX(), personaje.getY() + 80);
        g.drawString(consola.getChat(0), camera.camX, camera.camY + 640);
        g.drawString(consola.getChat(1), camera.camX, camera.camY + 660);
        g.drawString(consola.getChat(2), camera.camX, camera.camY + 680);
        g.drawString(consola.getChat(3), camera.camX, camera.camY + 700);
        g.drawRect(camera.camX, camera.camY + 640, 300, 80);

    }

    @Override
    public void keyPressed(int key, char c) {
        System.out.println(key);
        if ((key > 1 && key < 14) || (key > 15 && key < 28) || (key > 29 && key < 42) || (key > 42 && key < 54) || (key > 70 && key < 84) || (key > 143 && key < 148) || (key == 57 || key == 55 || key == 181)) {

            String msg = (Character.toString(c));
            chat.setText(msg);
        }
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
