package slick2d;

/**
 *
 * @author FrankoH
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

public class Slick2D extends BasicGame {

    map mapa1;
    Player personaje;
    Bot bot1;
    float tempX;
    float tempY;
    Camera camera;
    ClienteChat cliente;
    Console consola;
    Chat chat;

    public Slick2D(String gamename) {

        super(gamename);
        mapa1 = new map(0, 0);
        personaje = new Player(0, 0, mapa1);
        camera = new Camera(personaje);
        personaje.setCamera(camera);
        bot1 = new Bot(300, 200, mapa1);
        consola = new Console();
        cliente = new ClienteChat("127.0.0.1", "2000", consola);
        cliente.conectar();
        chat = new Chat(cliente);
        personaje.setChat(chat);
    }

    @Override
    public void init(GameContainer gc) throws SlickException {
        personaje.init(gc);
        bot1.init(gc);
        mapa1.init(gc);
        camera.init(gc);
        mapa1.addColision(179, 122, 269, 190);
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
        g.scale(camera.getGameScale(), camera.getGameScale());
        g.translate(camera.camX, camera.camY);
        mapa1.render(gc, g);
        bot1.render(gc, g);
        personaje.render(gc, g);
        chat.render(gc, g, personaje.CollisionBox.getX() - (chat.getWidth() / 2) + personaje.CollisionBox.getHalfX(), personaje.CollisionBox.getY() + personaje.CollisionBox.yDistance2 + (chat.getHeight() / 2));
        consola.render(gc, g, camera);
        g.setAntiAlias(true);
        g.drawString("X:" + Integer.toString(Math.round(personaje.getX())), personaje.getX(), personaje.getY() - 40);
        g.drawString("Y:" + Integer.toString(Math.round(personaje.getY())), personaje.getX(), personaje.getY() + 80);
        g.drawString(Float.toString(-camera.camX + gc.getInput().getMouseX() / camera.getGameScale()), -camera.camX + gc.getInput().getMouseX() / camera.getGameScale() + 30, -camera.camY + gc.getInput().getMouseY() / camera.getGameScale());
        g.drawString(Float.toString(-camera.camY + gc.getInput().getMouseY() / camera.getGameScale()), -camera.camX + gc.getInput().getMouseX() / camera.getGameScale() + 30, -camera.camY + gc.getInput().getMouseY() / camera.getGameScale() + 20);
        g.setAntiAlias(false);
    }

    @Override
    public void keyPressed(int key, char c) {
        //System.out.println(key);
        if (chat.isMostrar()) {
            if ((key > 1 && key < 14) || (key > 15 && key < 28) || (key > 29 && key < 42) || (key > 42 && key < 54) || (key > 70 && key < 84) || (key > 143 && key < 148) || (key == 57 || key == 55 || key == 181)) {

                String msg = (Character.toString(c));
                chat.setText(msg);
            }
        }

    }

    public static void main(String[] args) {
        try {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Slick2D("Juego GG"));
            boolean fullscreen = false;
            int value = JOptionPane.showConfirmDialog(null,
                    "¿Desea ingresar en pantalla completa?",
                    "Modo de pantalla",
                    JOptionPane.YES_NO_OPTION);
            if (value == JOptionPane.YES_OPTION) {
                fullscreen = true;
            } else if (value == JOptionPane.NO_OPTION) {
                fullscreen = false;
            }
            String resNativa = appgc.getScreenWidth() + "x" + appgc.getScreenHeight();
            String[] pos = {resNativa + " (Nativo)",
                "1920x1080",
                "1600x900",
                "1280x720",
                "800x600"};
            String s = (String) JOptionPane.showInputDialog(
                    null,
                    "Elija la resolucion: ",
                    "Selector de resolucion",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    pos,
                    pos);
            if (s != null) {
                switch (s) {
                    case "1920x1080":
                        appgc.setDisplayMode(1920, 1080, fullscreen);
                        System.out.println("1080");
                        break;
                    case "1600x900":
                        appgc.setDisplayMode(1600, 900, fullscreen);
                        System.out.println("900");
                        break;
                    case "1280x720":
                        appgc.setDisplayMode(1280, 720, fullscreen);
                        System.out.println("720");
                        break;
                    case "800x600":
                        appgc.setDisplayMode(800, 600, fullscreen);
                        System.out.println("600");
                        break;
                    default:
                        try {
                            appgc.setDisplayMode(appgc.getScreenWidth(), appgc.getScreenHeight(), fullscreen);
                            System.out.println("Nativa");
                        } catch (SlickException e) {
                            System.out.println("Resolucion nativa no permitida");
                            System.exit(0);
                        }
                        break;
                }
            } else {
                System.exit(0);
            }

            appgc.setShowFPS(true);
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(Slick2D.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
