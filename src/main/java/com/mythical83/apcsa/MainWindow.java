package com.mythical83.apcsa;

import com.mythical83.apcsa.components.JConsole;
import com.mythical83.apcsa.components.SyntaxPane;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainWindow extends JFrame {
    private static MainWindow mainWindow;

    private SyntaxPane syntaxPane;
    private JPanel displayPane;
    private JConsole jConsole;
    private JSplitPane jSplitPane1;
    private JSplitPane jSplitPane2;
    private JTabbedPane projectTabs;
    private JTabbedPane fileTabs;

    private MainWindow() {
        initComponents();
    }

    public static MainWindow getWindow() {
       if (mainWindow == null) mainWindow = new MainWindow();
       return mainWindow;
    }

    public void setText(String file) throws IOException {
        syntaxPane.setText(new File(file));
    }

    private void initComponents() {
        projectTabs = new JTabbedPane();
        jSplitPane1 = new JSplitPane();
        jSplitPane2 = new JSplitPane();
        displayPane = new JPanel();
        jConsole = JConsole.getJConsole();
        fileTabs = new JTabbedPane();
        syntaxPane = new SyntaxPane();
        displayPane.setBackground(Color.BLACK);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setDividerLocation(200);

        jSplitPane2.setDividerLocation(150);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        javax.swing.GroupLayout displayPaneLayout = new javax.swing.GroupLayout(displayPane);
        displayPane.setLayout(displayPaneLayout);
        displayPaneLayout.setHorizontalGroup(
                displayPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 342, Short.MAX_VALUE)
        );
        displayPaneLayout.setVerticalGroup(
                displayPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 150, Short.MAX_VALUE)
        );

        jSplitPane2.setTopComponent(displayPane);

        javax.swing.GroupLayout jConsoleLayout = new javax.swing.GroupLayout(jConsole);
        jConsole.setLayout(jConsoleLayout);
        jConsoleLayout.setHorizontalGroup(
                jConsoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 342, Short.MAX_VALUE)
        );
        jConsoleLayout.setVerticalGroup(
                jConsoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 98, Short.MAX_VALUE)
        );

        jSplitPane2.setRightComponent(jConsole);
        jSplitPane1.setRightComponent(jSplitPane2);
        fileTabs.setMinimumSize(new java.awt.Dimension(100, 100));

        fileTabs.addTab("tab5", syntaxPane);

        jSplitPane1.setLeftComponent(fileTabs);

        projectTabs.addTab("tab1", jSplitPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(projectTabs)
                                .addGap(3, 3, 3))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(projectTabs)
                                .addContainerGap())
        );

        pack();
    }
}
