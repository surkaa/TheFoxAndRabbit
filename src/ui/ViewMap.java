package ui;

import action.Action;
import field.*;

import javax.swing.JPanel;
import java.awt.*;

public class ViewMap extends JPanel {
    private final int GRID_SIZE;
    private final Field theField;
    public ViewMap(int grid_size, Field theField) {
        GRID_SIZE = grid_size;
        this.theField = theField;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < theField.getWidth(); i++) {
            g.drawLine(i * GRID_SIZE, 0, i * GRID_SIZE, theField.getHeight() * GRID_SIZE);
        }
        for (int i = 0; i < theField.getHeight(); i++) {
            g.drawLine(0, i * GRID_SIZE, theField.getWidth() * GRID_SIZE, i * GRID_SIZE);
        }
        for (int i = 0; i < theField.getWidth(); i++) {
            for (int j = 0; j < theField.getHeight(); j++) {
                Action animal = theField.getAction(new Location(i, j));
                if (animal != null) {
                    animal.draw(g, i * GRID_SIZE, j * GRID_SIZE, GRID_SIZE);
                } else {
                    g.setColor(new Color(0, 255, 0, 150));
                    g.fillRect(i * GRID_SIZE, j * GRID_SIZE, GRID_SIZE, GRID_SIZE);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(theField.getWidth() * GRID_SIZE, theField.getHeight() * GRID_SIZE);
    }
}
