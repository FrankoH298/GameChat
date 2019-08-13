package slick2d;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author FrankoH
 */
public final class Console {

    String[] chat;

    public Console() {
        chat = new String[7];
        for (int a = 0; a < getLength(); a++) {
            setChat(a, "");
        }
    }

    public String getChat(int index) {
        return chat[index];
    }

    public void setChat(int index, String chat) {
        this.chat[index] = chat;
    }

    public int getLength() {
        return chat.length;
    }

    public void recibirMensaje(String msg) {
        if (msg.length() > 1 && !msg.equals("")) {
            chat[0] = chat[1];
            chat[1] = chat[2];
            chat[2] = chat[3];
            chat[3] = chat[4];
            chat[4] = chat[5];
            chat[5] = chat[6];
            chat[6] = msg;
        }

    }

    public void render(GameContainer gc, Graphics g, Camera camera) throws SlickException {
        g.setAntiAlias(true);
        g.setColor(new Color(255f, 255f, 255f, 0.3f));
        g.drawString(getChat(0), -camera.camX, -camera.camY + gc.getHeight() / camera.getGameScale() - 140);
        g.setColor(new Color(255f, 255f, 255f, 0.5f));
        g.drawString(getChat(1), -camera.camX, -camera.camY + gc.getHeight() / camera.getGameScale() - 120);
        g.setColor(new Color(255f, 255f, 255f, 0.7f));
        g.drawString(getChat(2), -camera.camX, -camera.camY + gc.getHeight() / camera.getGameScale() - 100);
        g.setColor(new Color(255f, 255f, 255f, 0.8f));
        g.drawString(getChat(3), -camera.camX, -camera.camY + gc.getHeight() / camera.getGameScale() - 80);
        g.setColor(Color.white);
        g.drawString(getChat(4), -camera.camX, -camera.camY + gc.getHeight() / camera.getGameScale() - 60);
        g.drawString(getChat(5), -camera.camX, -camera.camY + gc.getHeight() / camera.getGameScale() - 40);
        g.drawString(getChat(6), -camera.camX, -camera.camY + gc.getHeight() / camera.getGameScale() - 20);
        g.setColor(Color.white);
        g.setAntiAlias(false);
    }
}
