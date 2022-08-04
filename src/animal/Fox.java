package animal;

import action.Action;
import field.Location;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Fox extends Animal implements Action {

    public Fox() {
        this((int) (Math.random() * 15));
    }

    public Fox(int age) {
        super(age, 15, Color.RED);
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
        // 筛选出neighbours中的兔子
        HashMap<Location, Animal> rabbits = new HashMap<>();
        for (Location location : neighbors.keySet()) {
            if (neighbors.get(location) instanceof Rabbit) {
                rabbits.put(location, neighbors.get(location));
            }
        }

        if (rabbits.isEmpty()) {
            return null;
        }
        // 选择兔子中活力最高然后的吃掉
        double max = 0;
        Location maxLocation = null;
        for (Location location : rabbits.keySet()) {
            if (rabbits.get(location).getVitality() > max) {
                max = rabbits.get(location).getVitality();
                maxLocation = location;
            }
        }
        // 获得体力
        if (maxLocation != null) {
            double energy = rabbits.get(maxLocation).getEnergy();
            this.addEnergy(energy);
            return maxLocation;
        }
        return null;
    }

    @Override
    public Location breed(ArrayList<Location> emptyNeighbors) {
        if (emptyNeighbors.isEmpty() || getEnergy() < 0.9 || Math.random() > getVitality()) {
            return null;
        }
        clearEnergy();
        return emptyNeighbors.get((int) (Math.random() * emptyNeighbors.size()));
    }

    @Override
    public Location move(ArrayList<Location> emptyNeighbors) {
        if (emptyNeighbors.isEmpty() || getEnergy() < 0.5 || Math.random() > getVitality()) {
            return null;
        }
        addEnergy(-0.5);
        return emptyNeighbors.get((int) (Math.random() * emptyNeighbors.size()));
    }
}
