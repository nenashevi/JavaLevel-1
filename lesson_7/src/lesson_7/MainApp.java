package lesson_7;

public class MainApp {
    public static void main(String[] args) {
        Plate plate = new Plate(20);
        Cat[] cats = {new Cat("Barsik", 60), new Cat("Persik", 30), new Cat("Rygik", 40)};

        plate.increaseFood(100);
        for (Cat a: cats) {
            a.info();
        }
        plate.info();
        for (Cat a: cats) {
            a.eat(plate);
        }
        System.out.println();
        for (Cat a: cats) {
            a.info();
        }
        plate.info();
    }
}
