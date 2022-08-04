package animal;

import action.Action;
import field.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Rabbit extends Animal implements Action {
    public Rabbit() {
        this((int) (Math.random() * 10));
    }

    public Rabbit(int age) {
        super(age, 10, Color.YELLOW);
    }

    @Override
    public void draw(Graphics g, int x, int y, int size) {
        g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), getBrightness()));
        g.fillRect(x, y, size, size);
    }

    @Override
    public Location hunt(HashMap<Location, Animal> neighbors) {
        addEnergy(0.5);
        return null;
    }

    @Override
    public Location breed(ArrayList<Location> emptyNeighbors) {
        if (emptyNeighbors.isEmpty() || getEnergy() < 0.2 || Math.random() > getVitality()) {
            return null;
        }
        clearEnergy();
        return emptyNeighbors.get((int) (Math.random() * emptyNeighbors.size()));
    }

    @Override
    public Location move(ArrayList<Location> emptyNeighbors) {
        if (emptyNeighbors.isEmpty() || getEnergy() < 0.2 || Math.random() > getVitality()) {
            return null;
        }
        clearEnergy();
        return emptyNeighbors.get((int) (Math.random() * emptyNeighbors.size()));
    }
}
