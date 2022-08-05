package ui;

import animal.Animal;
import animal.Fox;
import animal.Rabbit;
import field.Field;
import field.Location;

import java.io.File;

public class DataOutput {

    private final Field theField;

    private final File file;

    public DataOutput(Field theField) {
        this.theField = theField;
        this.file = new File("src/res/data.txt");
    }

    public void update() {
        int numsOfFoxes = 0;
        int numsOfRabbits = 0;
        int[] numsOfAgeFoxes = new int[new Fox().getAgeLimit()];
        int[] numsOfAgeRabbits = new int[new Rabbit().getAgeLimit()];
        for (int i = 0; i < theField.getWidth(); i++) {
            for (int j = 0; j < theField.getHeight(); j++) {
                Animal animal = (Animal) theField.getAction(new Location(i, j));
                if (animal != null) {
                    if (animal instanceof Rabbit) {
                        numsOfRabbits++;
                        numsOfAgeRabbits[animal.getAge()]++;
                    } else if (animal instanceof Fox) {
                        numsOfFoxes++;
                        numsOfAgeFoxes[animal.getAge()]++;
                    }
                }
            }
        }
        //把数据写入data文件
        StringBuilder dataOfRabbit = new StringBuilder();
        StringBuilder dataOfFox = new StringBuilder();
        for (int num : numsOfAgeRabbits) {
            dataOfRabbit.append(num).append(" ");
        }
        for (int num : numsOfAgeFoxes) {
            dataOfFox.append(num).append(" ");
        }
        try {
            java.io.FileWriter fw = new java.io.FileWriter(file, true);
            fw.write("\n");
            fw.write("" + numsOfRabbits + " " + dataOfRabbit);
            fw.write("" + numsOfFoxes + " " + dataOfFox);
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
