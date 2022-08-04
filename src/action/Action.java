package action;

import animal.Animal;
import field.Location;

import java.awt.*;
import java.util.*;

public interface Action {

    /**
     * 用于动物的绘画 (x, y) 到 (x + size, y + size)
     * @param g 绘画环境
     * @param x 动物的x坐标
     * @param y 动物的y坐标
     * @param size 绘画框的大小
     */
    void draw(Graphics g, int x, int y, int size);

    /**
     * 用于动物的移动,
     * 思路（先筛选出neighbours中被捕食者的位置再选择其中活力最高的吃掉，然后获得体力）
     * @param neighbors 该动物的邻居
     * @return 被捕食者的坐标
     */
    Location hunt(HashMap<Location, Animal> neighbors);

    /**
     * 用于动物的生育
     * @param emptyNeighbors 周围可以放置新动物的空坐标
     * @return 新动物的坐标
     */
    Location breed(ArrayList<Location> emptyNeighbors);

    /**
     * 用于动物的移动
     * @param emptyNeighbors 周围可以移动到的空坐标
     * @return 目的地的坐标
     */
    Location move(ArrayList<Location> emptyNeighbors);

}
