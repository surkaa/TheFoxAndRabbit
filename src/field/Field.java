package field;

import action.Action;
import animal.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Field {
    private final int width;
    private final int height;
    private final Action[][] theField;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Field(int width, int height, double numsOfRabbits, double numsOfFoxes) {
        this.width = width;
        this.height = height;
        theField = new Action[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                double random = Math.random();
                if (random < numsOfRabbits) {
                    theField[i][j] = new Rabbit();
                } else if (random < numsOfRabbits + numsOfFoxes) {
                    theField[i][j] = new Fox();
                } else {
                    theField[i][j] = null;
                }
            }
        }
    }

    public Action getAction(Location L) {
        return theField[L.getX()][L.getY()];
    }

    public void clear(Location L) {
        theField[L.getX()][L.getY()] = null;
    }

    public void moveAction(Location L, Action action) {
        theField[L.getX()][L.getY()] = action;
    }

    public void newAction(Location L, Action action) {
        theField[L.getX()][L.getY()] = action;
    }

    public ArrayList<Location> getEmptyLocations(Location L) {
        ArrayList<Location> emptyLocations = new ArrayList<>();
        for (int i = L.getX() - 1; i <= L.getX() + 1; i++) {
            for (int j = L.getY() - 1; j <= L.getY() + 1; j++) {
                if (i >= 0 && i < width && j >= 0 && j < height && !(i == L.getX() && j == L.getY())) {
                    if (theField[i][j] == null) {
                        emptyLocations.add(new Location(i, j));
                    }
                }
            }
        }
        return emptyLocations;
    }

    public HashMap<Location, Animal> getNeighbors(Location L) {
        HashMap<Location, Animal> neighbors = new HashMap<>();
        for (int i = L.getX() - 1; i <= L.getX() + 1; i++) {
            for (int j = L.getY() - 1; j <= L.getY() + 1; j++) {
                if (i >= 0 && i < width && j >= 0 && j < height && !(i == L.getX() && j == L.getY())) {
                    if (theField[i][j] != null) {
                        Location location = new Location(i, j);
                        neighbors.put(location, (Animal) getAction(location));
                    }
                }
            }
        }
        return neighbors;
    }

    public void onceActions() {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (theField[i][j] != null) {
                    step(new Location(i, j));
                }
            }
        }
    }

    public void step(Location L) {
        Animal animal = (Animal) theField[L.getX()][L.getY()];
        if (animal.grow()) {
            clear(L);
            return;
        }
        huntAnimal(L);
        breedAnimal(L);
        moveAnimal(L);
    }

    public void huntAnimal(Location L) {
        Action action = getAction(L);
        HashMap<Location, Animal> neighbors = getNeighbors(L);
        Location target = action.hunt(neighbors);
        if (target != null) {
            clear(target);
        }
    }

    public void breedAnimal(Location L) {
        Action action = getAction(L);
        ArrayList<Location> emptyLocations = getEmptyLocations(L);
        Location newLocation = action.breed(emptyLocations);
        if (newLocation == null) {
            return;
        }

        if (getAction(L) instanceof Rabbit) {
            newAction(newLocation, new Rabbit(0));
        } else if (getAction(L) instanceof Fox) {
            newAction(newLocation, new Fox(0));
        }
    }

    public void moveAnimal(Location L) {
        Action action = getAction(L);
        ArrayList<Location> emptyLocations = getEmptyLocations(L);
        Location target = action.move(emptyLocations);
        if (target == null) {
            return;
        }
        moveAction(target, action);
        clear(L);
    }
}
