package hw_3;

import java.util.ArrayList;
import java.util.List;

public class Box <T extends Fruit> {
    List<T> fruits;

    public Box() {
        fruits = new ArrayList<>();
    }

    public List<T> getFruits() {
        return fruits;
    }

    public void addFruit(T fruit) {
        fruits.add(fruit);
    }
    public void removeFruit() {
        if (fruits.size() > 0) {
            fruits.remove(0);
        } else
            System.out.println("Удаление фрукта невозможно, коробка пуста");
    }

    public float getWeight() {
        float sum = 0;
        if(fruits.size() > 0) {
            sum = fruits.size() * fruits.get(0).getWeight();
            return sum;
        } else
            return 0;

    }
    public boolean compare(Box<?> anotherBox) {
        float boxSum = getWeight();
        float anotherBoxSum = anotherBox.getWeight();
        return  Math.abs(boxSum - anotherBoxSum) < 0.0001;
    }

    public void moveAllFruitsToAnotherBox ( Box<T> anotherBox) {
        if (fruits.size() == 0) {
            System.out.println("Текущая коробка пустая, пересыпать из нее фрукты невозможно");
            return;
        }
        int tempFruitsSize = fruits.size();
        for (int i = 0; i < tempFruitsSize; i++) {
            anotherBox.addFruit(fruits.get(0));
            removeFruit();
        }
    }


}
