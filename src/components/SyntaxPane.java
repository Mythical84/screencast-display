package components;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import java.awt.event.KeyListener;
import java.io.*;

public class SyntaxPane extends JRootPane implements SyntaxConstants {
    private RTextScrollPane scrollPane;
    private RSyntaxTextArea textArea;

    // constructor
    public SyntaxPane() {
        textArea = createTextArea();
        textArea.setSyntaxEditingStyle(SYNTAX_STYLE_JAVA);
        scrollPane = new RTextScrollPane(textArea, true);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        getContentPane().add(scrollPane);
        InputStream in = getClass().getResourceAsStream("/org/fife/ui/rsyntaxtextarea/themes/dark.xml");
        try {
            Theme theme = Theme.load(in);
            theme.apply(textArea);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private RSyntaxTextArea createTextArea() {
        RSyntaxTextArea textArea = new RSyntaxTextArea(25, 70);
        textArea.setTabSize(3);
        textArea.setCaretPosition(0);
        textArea.requestFocusInWindow();
        textArea.setMarkOccurrences(true);
        textArea.setCodeFoldingEnabled(true);
        textArea.setClearWhitespaceLinesEnabled(false);

        return textArea;
    }

    public void setText(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        textArea.setText("");
        String st;
        while ((st=br.readLine()) != null) {
            textArea.append(st + "\n");
        }
    }

    public void setText(String text) {
        textArea.setText(text);
    }

    public String getText() {
        return textArea.getText();
    }

    @Override
    public synchronized void addKeyListener(KeyListener l) {
        textArea.addKeyListener(l);
    }
}
