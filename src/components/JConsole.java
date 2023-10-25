package components;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class JConsole extends JTextArea {
    private static JConsole jConsole;

    private boolean canType;
    // A count of how many characters the user has typed since it's last reset
    private int userTyped;


    private JConsole() {
        canType = true;
        userTyped = 0;
        setEditable(false);
        setSize(400, 400);
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                boolean backspace = e.getKeyCode() == KeyEvent.VK_BACK_SPACE;
                boolean control = e.getModifiersEx() == KeyEvent.VK_CONTROL;
                boolean enter = e.getKeyCode() == KeyEvent.VK_ENTER;
                boolean delete = e.getKeyCode() == KeyEvent.VK_DELETE;

                // if the user presses backspace, can type, and the backspace wouldn't
                // be deleting part of the prompt then remove the last character
                if ((backspace || delete) && canType && userTyped > 0) {
                    String text = getText();
                    setText(text.substring(0,text.length()-1));
                    userTyped--;
                }

                // if the user presses the enter key reset the typed length to 0
                if (enter && canType) {
                    userTyped = -1;
                }

                // only add character if typing is allowed and the character is an ascii character
                // and the control key wasn't pressed
                if (canType && ("" + e.getKeyChar()).matches("\\A\\p{ASCII}*\\z") &&!control &&!backspace &&!delete)
                    write(e.getKeyChar());

            }
        });
    }

    private void write(char in) {
        userTyped++;
        append(""+in);
    }

    public static JConsole getJConsole() {
        if (jConsole == null) jConsole = new JConsole();
        return jConsole;
    }
}
