package animal;

import action.Action;
import field.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import static WorldManager.GameMachine.RabbitWidth;
import static WorldManager.GameMachine.RabbitHeight;

public class Rabbit extends Animal implements Action {

    private static final int RABBIT_MAX_AGE = 10;

    public Rabbit() {
        this(0);
    }

    public Rabbit(double random) {
        super((int) (random * RABBIT_MAX_AGE), RABBIT_MAX_AGE, Color.YELLOW);
    }

    @Override
    public void draw(Graphics g, int x, int y, int size) {
        g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), getBrightness()));
        g.fillRect(x, y, size, size);
    }

    @Override
    public Location hunt(HashMap<Location, Animal> neighbors) {
        if (neighbors.isEmpty()) {
            return null;
        }
        int x = 0, y = 0;
        for (Location location : neighbors.keySet()) {
            x += location.x();
            y += location.y();
        }
        double averageX = (double) x / neighbors.size();
        double averageY = (double) y / neighbors.size();
        addEnergy(0.3);
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
        addEnergy(-0.1);
        return emptyNeighbors.get((int) (Math.random() * emptyNeighbors.size()));
    }
}
