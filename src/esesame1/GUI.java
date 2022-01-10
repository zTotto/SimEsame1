package esesame1;

import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.awt.*;

public class GUI extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 2169519042850821148L;
    private final Map<JButton, Pair<Integer, Integer>> buttonMap = new HashMap<>();

    public GUI(final int grid, final int ship) {
        final int gridSize = grid;
        final int shipSize = ship;
        final Logics logics = new LogicsImpl(gridSize, shipSize);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(500, 500);
        JPanel panel = new JPanel(new GridLayout(gridSize, gridSize));
        this.getContentPane().add(BorderLayout.CENTER, panel);

        ActionListener al = (e) -> {
            final JButton bt = (JButton) e.getSource();
            if (logics.hit(buttonMap.get(bt).getX(), buttonMap.get(bt).getY())) {
                bt.setText("X");
            } else {
                bt.setText("O");
            }
            bt.setEnabled(false);
            if (logics.isOver()) {
                System.exit(1);
            }
        };
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                final JButton jb = new JButton();
                buttonMap.put(jb, new Pair<>(j, i));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI(5, 3);
    }

}
