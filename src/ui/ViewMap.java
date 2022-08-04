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
        for (int i = 0; i < theField.getHeight(); i++) {
            for (int j = 0; j < theField.getWidth(); j++) {
                Location location = new Location(i, j);
                Action animal = theField.getAction(location);
                if (animal != null) {
                    animal.draw(g, j * GRID_SIZE, i * GRID_SIZE, GRID_SIZE);
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(theField.getWidth() * GRID_SIZE, theField.getHeight() * GRID_SIZE);
    }
}
