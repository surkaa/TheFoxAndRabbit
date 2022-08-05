package WorldManager;

import field.Field;
import ui.DataOutput;
import ui.ViewMap;

import javax.swing.*;
import java.awt.*;

public class GameMachine {
    public static void main(String[] args) {
        GameMachine game = new GameMachine(
                300,
                300,
                5,
                0.05,
                0.01
        );
        game.runs(5000);
    }
    private final Field theField;
    private final ViewMap view;
    private final DataOutput data;
    public static int RabbitWidth;
    public static int RabbitHeight;

    public GameMachine(int width, int height, int size, double numsOfRabbits, double numsOfFoxes) {
        theField = new Field(width, height, numsOfRabbits, numsOfFoxes);
        view = new ViewMap(size, theField);
        data = new DataOutput(theField);
        JFrame frame = new JFrame("Game of Fox and Rabbit");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.add(view, BorderLayout.CENTER);
        JButton button = new JButton("Next");
        button.addActionListener(e -> runOnce());
        frame.add(button, BorderLayout.SOUTH);
        frame.pack();
        RabbitWidth = width;
        RabbitHeight = height;
    }
    public void runs(int steps) {
        for (int i = 0; i < steps; i++) {
            runOnce();
        }
    }
    public void runOnce() {
        theField.onceActions();
        data.update();
        view.repaint();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
