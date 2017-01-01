package pudata;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by Ololo on 22.10.2016.
 */
public class Puckman {

    public static void main(String[] args) {
        JFrame frame1 =  new JFrame();
        JFrame frame =  new JFrame();
        JFrame text = new JFrame();

        JMenuBar menuBarGame = new JMenuBar();
        JMenuBar menuBarE = new JMenuBar();

        JMenu editor = new JMenu("Game Editor");
        JMenuItem editItem = new JMenuItem("Editor");
        editor.add(editItem);
        JMenuItem loadMap = new JMenuItem("Load map");
        editor.add(loadMap);
        //JFrame frame =  new JFrame();

        LevelEditor lvlEdit = new LevelEditor();

       // JMenuBar menuBarE = new JMenuBar();
        JMenu editorMenu = new JMenu("Editor");
        JMenuItem cleanItem = new JMenuItem("Clean");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem saveAsLevel = new JMenuItem("Save as level");
        editorMenu.add(saveItem);
        editorMenu.add(saveAsLevel);
       // JFrame text = new JFrame();
        JButton save = new JButton("Save");
        text.setLayout(new GridBagLayout());
        text.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        text.setResizable(false);
        text.setTitle("Enter level number");

        text.add(save);
            NumberFormat level = new DecimalFormat("level#");
            JFormattedTextField levelNumber = new JFormattedTextField( new NumberFormatter(level));
            levelNumber.setColumns(10);
            levelNumber.setText("level");
            text.add(levelNumber);
            save.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if(levelNumber.getText().isEmpty()){
                        JOptionPane.showMessageDialog(frame,"Неправильный формат имени!");
                    }
                    else {
                        lvlEdit.save(levelNumber.getText());
                        text.setVisible(false);

                    }
                }
            });

        saveAsLevel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setBounds(800, 200, 300, 100);
                text.setVisible(true);
            }
        });
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lvlEdit.save("");
            }
        });
        JMenuItem discardItem = new JMenuItem("Discard");
        editorMenu.add(discardItem);
        discardItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lvlEdit.discard();
            }
        });
        editorMenu.add(cleanItem);
        cleanItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lvlEdit.cleanMap();
            }
        });
        menuBarE.add(editorMenu);
        frame.setJMenuBar(menuBarE);
        lvlEdit.editor();

        editItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                frame.setLayout(null);
                frame.setDefaultCloseOperation( WindowConstants.HIDE_ON_CLOSE);
                frame.setResizable(false);
                lvlEdit.getCanvas().setBounds(0, 0, 600, 600);
                frame.add(lvlEdit.getCanvas());
                frame.setTitle("Puckman Editor");
                frame.setBounds(606,00,606,650);
                frame.setVisible(true);}

        });

        JMenu gameMenu = new JMenu("Game");
        JMenuItem newGame = new JMenuItem("Game");
        Game game = new Game();
        gameMenu.add(newGame);
        gameMenu.addSeparator();
        JMenuItem exitItem = new JMenuItem("Exit");
        gameMenu.add(exitItem);
        menuBarGame.add(gameMenu);
        menuBarGame.add(editor);
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.start();
            }
        });
        loadMap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.loadEditMap();
            }
        });
        game.game();
        frame1.setJMenuBar(menuBarGame);
        frame1.setLayout(null);
        frame1.setDefaultCloseOperation( javax.swing.WindowConstants.EXIT_ON_CLOSE );
        frame1.setResizable(false);
        game.getCanvas().setBounds(0, 0, 600, 600);
        frame1.add(game.getCanvas());
        frame1.setTitle("Puckman");
        frame1.setBounds(00,00,606,650);
        frame1.setVisible(true);


    }
}
