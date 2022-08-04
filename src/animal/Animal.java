package animal;

import java.awt.*;


public abstract class Animal {

    private int age;
    private final int ageLimit;
    protected Color color;

    /**
     * 动物的体力，用于记录发生行为后的变化，范围（0 - 1）
     * 每次移动都会减少一点活力，
     * 生育则会清零
     * 而每次捕食都会根据吃到的增加一点活力
     */
    private double energy = 0;

    public double getEnergy() {
        return energy;
    }

    public void clearEnergy() {
        energy = 0;
    }

    public void addEnergy(double energy) {
        this.energy += energy;
        if (this.energy > 1) {
            this.energy = 1;
        }
        if (this.energy < 0) {
            this.energy = 0;
        }
    }

    /**
     * 动物的活力，
     * 用于行为发生前的判断（实例：Math.random < vitality），范围（0 - 1）
     * 函数：vitality = 4 * age * (ageLimit - age) / (ageLimit * ageLimit)
     * 倒二次函数形状，模拟壮年时最具有活力
     */
    protected double getVitality() {
        return 4.0 * age * (ageLimit - age) / (ageLimit * ageLimit);
    }

    /**
     * 在使用draw方法时，绘制动物颜色的明亮
     * @return （0 - 255） 颜色的亮度
     */
    protected int getBrightness() {
        return (int) ((1 - (double) age / ageLimit) * 255);
    }

    public Animal(int age, int ageLimit, Color color) {
        this.age = age;
        this.ageLimit = ageLimit;
        this.color = color;
    }

    /**
     * 长大
     * @return 动物该死就得死了
     */
    public boolean grow() {
        age++;
        if (age >= ageLimit) {
            age = 0;
            return true;
        }
        return false;
    }
}
