package view;

import model.SimplePlayer;
import model.interfaces.DicePair;
import model.interfaces.GameEngine;
import model.interfaces.GameEngineCallback;
import model.interfaces.Player;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.Collection;

public class GameEngineCallbackGUI implements GameEngineCallback {

    private JFrame frame;
    private JPanel panel;
    private JMenuBar menuBar;
    private JMenu menu, game;
    private JMenuItem addPlayer;
    private GameEngine gameEngine;
    private Collection<SimplePlayer> players;
    private JToolBar toolBar;
    private JButton bt1, bt2, bt3;
    private BufferedImage face1, face2, face3, face4, face5, face6, background;
    private JLabel header, firstDice, secondDice, name, bet, roll, result;

    public GameEngineCallbackGUI (GameEngine gameEngine) throws IOException {
        this.gameEngine = gameEngine;
        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        // toolBar = new JToolBar("Add Player");
        addPlayer = new JMenuItem("Add Player");

        faceImage();
        GUI();

    }

    public void faceImage() {
        try {
            face1 = ImageIO.read(new File("/Users/dungbean/Downloads/DiceGameSADI/images/face1.jpeg"));
            face2 = ImageIO.read(new File("/Users/dungbean/Downloads/DiceGameSADI/images/face2.jpeg"));
            face3 = ImageIO.read(new File("/Users/dungbean/Downloads/DiceGameSADI/images/face3.jpeg"));
            face4 = ImageIO.read(new File("/Users/dungbean/Downloads/DiceGameSADI/images/face4.jpeg"));
            face5 = ImageIO.read(new File("/Users/dungbean/Downloads/DiceGameSADI/images/face5.jpeg"));
            face6 = ImageIO.read(new File("/Users/dungbean/Downloads/DiceGameSADI/images/face6.jpeg"));
            background = ImageIO.read(new File("/Users/dungbean/Downloads/DiceGameSADI/images/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void GUI() throws IOException {
        frame = new JFrame("Casino DiceGame");
        frame.setVisible(true);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        bt1 = new JButton();


        panel.add(bt1);
        panel.add(header);

        bt1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent ae) {
                btMouseClicked(ae);
            }
        });



    }

    private void btMouseClicked(java.awt.event.MouseEvent ae) {
        header.setText("Casino DiceGame");
    }

//    public void menuBar() {
//        menuBar = new JMenuBar();
//        menu = new JMenu("Menu");
//        game = new JMenu("Game");
//        JMenuItem newMenu = new JMenuItem("New");
//
//        MenuItemListener listener = new MenuItemListener(firstDice);
//
//        newMenu.addActionListener(listener);
//
//        menu.add(newMenu);
//        menuBar.add(menu);
//        frame.setJMenuBar(menuBar);
//
//    }


    @Override
    public void intermediateResult(Player player, DicePair dicePair, GameEngine gameEngine) {
//        if(dicePair.getDice1() == 1) {
//            firstDice.setIcon(new ImageIcon(face1));
//        }
//        if(dicePair.getDice1() == 2) {
//            firstDice.setIcon(new ImageIcon(face2));
//        }
//        if(dicePair.getDice1() == 3) {
//            firstDice.setIcon(new ImageIcon(face3));
//        }
//        if(dicePair.getDice1() == 4) {
//            firstDice.setIcon(new ImageIcon(face4));
//        }
//        if(dicePair.getDice1() == 5) {
//            firstDice.setIcon(new ImageIcon(face5));
//        }
//        if(dicePair.getDice1() == 6) {
//            firstDice.setIcon(new ImageIcon(face6));
//        }
//        if(dicePair.getDice2() == 1) {
//            secondDice.setIcon(new ImageIcon(face1));
//        }
//        if(dicePair.getDice2() == 2) {
//            secondDice.setIcon(new ImageIcon(face2));
//        }
//        if(dicePair.getDice2() == 3) {
//            secondDice.setIcon(new ImageIcon(face3));
//        }
//        if(dicePair.getDice2() == 4) {
//            secondDice.setIcon(new ImageIcon(face4));
//        }
//        if(dicePair.getDice2() == 5) {
//            secondDice.setIcon(new ImageIcon(face5));
//        }
//        if(dicePair.getDice2() == 6) {
//            secondDice.setIcon(new ImageIcon(face6));
//        }
    }

    @Override
    public void result(Player player, DicePair result, GameEngine gameEngine) {

    }

    @Override
    public void intermediateHouseResult(DicePair dicePair, GameEngine gameEngine) {

    }

    @Override
    public void houseResult(DicePair result, GameEngine gameEngine) {

    }
}
