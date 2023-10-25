package com.mythical83.apcsa;

import com.mythical83.apcsa.components.SyntaxPane;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

// window for editing imported code
class EditorWindow extends JFrame {
    private static EditorWindow editorWindow;

    private SyntaxPane sp;

    private EditorWindow() {
        setTitle("Code Editor");
        sp = new SyntaxPane();
        sp.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // when the user types control s, hide this window, clear the editor,
                // and send the text to the main window
                if (e.getKeyChar() == '') {
                    try {
                        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\artin\\projects\\ApCSA Screencast Display\\src\\Test.java"));
                        bw.write(sp.getText());
                        bw.close();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    setVisible(false);
                    sp.setText("");
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        add(sp);
        setSize(400, 300);

    }

    public void setText(String path) throws IOException {
        String text = "";
        BufferedReader br = new BufferedReader(new FileReader(path));
        String st;
        while ((st=br.readLine()) != null) {
            st = st.replace("JFrame", "JPanel");
            st = st.replace("System.out.println", "lib.JConsole.getJConsole");
            text += st + "\n";
        }
        sp.setText(text);
        br.close();
    }

    public static EditorWindow getWindow() {
        if (editorWindow == null) editorWindow = new EditorWindow();
        return editorWindow;
    }
}
