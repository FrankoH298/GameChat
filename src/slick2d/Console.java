package slick2d;

import org.newdawn.slick.GameContainer;
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

    public void update(GameContainer gc, int delta) throws SlickException {

    }
}
