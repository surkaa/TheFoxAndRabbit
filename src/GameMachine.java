import field.Field;
import ui.ViewMap;

import javax.swing.*;
import java.awt.*;

public class GameMachine {
    public static void main(String[] args) {
        GameMachine game = new GameMachine(
                40,
                40,
                16,
                0.05,
                0.01
        );
        game.runs(1000);
    }
    private final Field theField;
    private final ViewMap view;

    public GameMachine(int width, int height, int size, double numsOfRabbits, double numsOfFoxes) {
        theField = new Field(width, height, numsOfRabbits, numsOfFoxes);
        view = new ViewMap(size, theField);
        JFrame frame = new JFrame("Game of Fox and Rabbit");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(view, BorderLayout.CENTER);
        JButton button = new JButton("Next");
        button.addActionListener(e -> runOnce());
        frame.add(button, BorderLayout.SOUTH);
        frame.pack();

    }
    public void runs(int steps) {
        for (int i = 0; i < steps; i++) {
            runOnce();
        }
    }
    public void runOnce() {
        theField.onceActions();
        view.repaint();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
