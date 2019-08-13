package slick2d;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author FrankoH
 */
public class Chat {

    private boolean mostrar = false;
    private String text = "";
    private Console consola;
    Input input;
    float width = 200, height = 20;
    ClienteChat cliente;

    public Chat(GameContainer gc, Console consola, ClienteChat cliente) {
        this.consola = consola;
        this.cliente = cliente;
        input = gc.getInput();
    }

    public void update(GameContainer gc, int delta) throws SlickException {
        if (mostrar) {
            if (input.isKeyPressed(Input.KEY_RETURN)) {
                cliente.EnviarMensaje("M" + text);
                mostrar = false;
                text = "";
            } else if (input.isKeyPressed(14)) {
                if (text.length() > 0) {
                    text = text.substring(0, text.length() - 1);
                }
            }
        } else {
            if (input.isKeyPressed(Input.KEY_RETURN)) {
                mostrar = true;
                text = "";
            }
        }
    }

    public void render(GameContainer gc, Graphics g, float x, float y) throws SlickException {
        if (mostrar) {
            g.drawRect(x, y, width, height);
            g.drawString(text, x, y);
        }
    }

    public void alternChat() {
        mostrar = !mostrar;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        if (this.text.length() < 22) {
            this.text += text;
        }
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
