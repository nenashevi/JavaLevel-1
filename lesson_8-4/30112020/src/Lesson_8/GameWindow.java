package Lesson_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    private static final int WIN_HEIGHT = 555;
    private static final int WIN_WIDTH = 508;
    private static final int WIN_POS_X = 800;
    private static final int WIN_POS_Y = 300;

    private static StartNewGameWindow startNewGameWindow;
    private static Map field;
    JLabel infoGame;

    public GameWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(WIN_POS_X, WIN_POS_Y, WIN_WIDTH, WIN_HEIGHT);
        setTitle("Tic Tac Toe");

        setResizable(false);

        JPanel bottomPanel = new JPanel(new GridLayout(1,2));

        JButton btnNewGame = new JButton("Start new game");
        JButton btnExit = new JButton("Exit");

        bottomPanel.add(btnNewGame);
        bottomPanel.add(btnExit);

        add(bottomPanel, BorderLayout.SOUTH);
        JPanel upperPanel = new JPanel();
        infoGame= new JLabel("");
        upperPanel.add(infoGame);
        add(upperPanel, BorderLayout.NORTH);
        //infoGame.setText("123456");
        //setTextToInfoGame("qwerty");



        startNewGameWindow = new StartNewGameWindow(this);
        field = new Map(this);
        add(field, BorderLayout.CENTER);

        btnNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startNewGameWindow.setVisible(true);
            }
        });

        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);

    }


    void startNewGame(int mode, int fieldSizeX, int fieldSizeY, int winLen) {
        //field.startNewGame(mode, fieldSizeX, fieldSizeY, winLen);
        field.startNewGame(mode, fieldSizeX, fieldSizeY, 4);
    }
    void setTextToInfoGame(String s) {
        infoGame.setText(s);
    }
}
