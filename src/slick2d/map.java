package slick2d;

/**
 *
 * @author FrankoH
 */
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

public class map {

    private TiledMap map;

    private float x;
    private float y;
    ArrayList<Agent> entities;
    ArrayList<CollisionBorder> collisions;

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void addAgent(Agent agent) {
        entities.add(agent);
    }

    public void addColision(float posX, float posY, float width, float height) {
        collisions.add(new CollisionBorder(posX, posY, width, height));
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public map(float x, float y) {
        this.x = x;
        this.y = y;
        entities = new ArrayList<>();
        collisions = new ArrayList<>();
    }

    public void checkCollision() {
        for (int a = 0; a < entities.size(); a++) {
            for (int b = 0; b < entities.size(); b++) {
                if (entities.get(a) != entities.get(b)) {
                    entities.get(a).CollisionBox.colisiona(entities.get(a), entities.get(b).CollisionBox);
                }
            }
            for (int c = 0; c < collisions.size(); c++) {
                if (entities.get(a).CollisionBox != collisions.get(c)) {
                    entities.get(a).CollisionBox.colisiona(entities.get(a), collisions.get(c));
                }
            }
            entities.get(a).CollisionBox.BanderaTop = false;
            entities.get(a).CollisionBox.BanderaBottom = false;
            entities.get(a).CollisionBox.BanderaRight = false;
            entities.get(a).CollisionBox.BanderaLeft = false;

        }
    }

    public void init(GameContainer gc) throws SlickException {
        map = new TiledMap("data/mapa.tmx");
        String layerValue;
        for (int d = 0; d < map.getLayerCount(); d++) {
            layerValue = map.getLayerProperty(d, "solid", "false");
            if (layerValue.equals("true")) {
                for (int a = 0; a < 200; a++) {
                    for (int b = 0; b < 200; b++) {
                        if (map.getTileId(a, b, d) != 0) {
                            addColision(a * 32, b * 32, 32, 32);
                            System.out.println(a + "+" + b);
                        }
                    }
                }
            }
        }
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {
        map.render(Math.round(x), Math.round(y));
        for (int a = 0; a < collisions.size(); a++) {
            collisions.get(a).render(gc, g);
        }
    }
}
