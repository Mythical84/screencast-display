package com.mythical83.apcsa;

import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class Main extends JFrame {

    public static void main(String[] args) {
        MainWindow mw = MainWindow.getWindow();
        EditorWindow ew = EditorWindow.getWindow();
        FileWindow fw = new FileWindow(mw, ew);
        mw.setVisible(true);
        ew.setVisible(true);
        fw.setVisible(true);
    }

}

// file selector window
class FileWindow extends JFrame {
    public FileWindow(MainWindow mw, EditorWindow ew) {
        JFileChooser fc = new JFileChooser();
        fc.setCurrentDirectory(new File("C:\\Users\\artin\\projects"));
        add(fc);
        setSize(800, 500);
        // when the user selects a file, close the window and send the path
        // to the main and editor windows
        fc.addActionListener(e -> {
            try {
                mw.setText(fc.getSelectedFile().getPath());
                ew.setText(fc.getSelectedFile().getPath());
                setVisible(false);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}