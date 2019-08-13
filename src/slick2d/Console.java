package slick2d;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author FrankoH
 */
public class Console {

    String[] chat;

    public Console() {
        chat = new String[4];
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
            chat[3] = msg;
        }

    }

    public void render(GameContainer gc, Graphics g, Camera camera) throws SlickException {
        g.setColor(Color.lightGray);
        g.fillRoundRect(-camera.camX, -camera.camY + 630, 300, 80, 6);
        g.setColor(Color.black);
        g.setAntiAlias(true);
        g.drawRoundRect(-camera.camX + 2, -camera.camY + 632, 296, 76, 4);
        g.setAntiAlias(false);
        g.setColor(Color.white);
        g.drawString(getChat(0), -camera.camX + 2, -camera.camY + 630);
        g.drawString(getChat(1), -camera.camX + 2, -camera.camY + 650);
        g.drawString(getChat(2), -camera.camX + 2, -camera.camY + 670);
        g.drawString(getChat(3), -camera.camX + 2, -camera.camY + 690);
        g.setColor(Color.white);
    }
}
