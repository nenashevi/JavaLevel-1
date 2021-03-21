package hw_3;

public class MainApp {
    public static void main(String[] args) {
        Apple apple1 = new Apple();
        Apple apple2 = new Apple();
        Box<Apple> appleBox1 = new Box<>();

        System.out.println("Демонстрация работы класа Box");
        System.out.println( "Вес appleBox1: "+ appleBox1.getWeight());
        appleBox1.addFruit(apple1);
        appleBox1.addFruit(apple2);
        System.out.println("Вес appleBox1: " + appleBox1.getWeight());
        appleBox1.removeFruit();
        System.out.println("Вес appleBox1: " + appleBox1.getWeight());

        Orange orange1 = new Orange();
        Orange orange2 = new Orange();
        Box<Orange> orangeBox1 = new Box<>();

        System.out.println("Вес orangeBox1: " + orangeBox1.getWeight());
        orangeBox1.addFruit(orange1);
        orangeBox1.addFruit(orange2);
        System.out.println("Вес orangeBox1: " + orangeBox1.getWeight());
        orangeBox1.removeFruit();
        System.out.println("Вес orangeBox1: " + orangeBox1.getWeight());

        System.out.println("Сравнение по весу appleBox1 и orangeBox1: " + appleBox1.compare(orangeBox1));
        appleBox1.addFruit(apple1);
        appleBox1.addFruit(apple2);
        orangeBox1.addFruit(orange1);

        System.out.println("Вес appleBox1: " + appleBox1.getWeight());
        System.out.println("Вес orangeBox1: " + orangeBox1.getWeight());
        System.out.println("Сравнение по весу appleBox1 и orangeBox1: " + appleBox1.compare(orangeBox1));

        Box<Apple> appleBox2 = new Box<>();
        System.out.println();
        System.out.println("Вес appleBox1: " + appleBox1.getWeight());
        System.out.println("Вес appleBox2: " + appleBox2.getWeight());

        appleBox1.moveAllFruitsToAnotherBox(appleBox2);
        System.out.println("Вес appleBox1: " + appleBox1.getWeight());
        System.out.println("Вес appleBox2: " + appleBox2.getWeight());

        appleBox1.moveAllFruitsToAnotherBox(appleBox2);
        System.out.println("Вес appleBox1: " + appleBox1.getWeight());
        System.out.println("Вес appleBox2: " + appleBox2.getWeight());


    }
}
