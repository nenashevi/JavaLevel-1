package lesson_6;

public class MainApp {
    public static void main(String[] args) {
        Cat cat1 = new Cat ("Barsik", "white", 200f, 2f);
        Cat cat2 = new Cat ("Vaska", "black", 190f, 1.5f);
        Dog dog1 = new Dog ("Bobik", "red", 500f, 0.5f, 10f);
        Dog dog2 = new Dog ("Tuzik", "green", 600f, 0.8f, 15f);
        cat1.info();
        cat1.run(50);
        cat1.run(201);
        cat1.jump(2);
        cat1.jump(2.5f);
        System.out.println();

        cat2.info();
        cat2.run(40);
        cat2.run(195);
        cat2.jump(1.4f);
        cat2.jump(1.8f);
        System.out.println();
        System.out.println();

        dog1.info();
        dog1.run(500);
        dog1.run(550);
        dog1.jump(0.5f);
        dog1.jump(0.6f);
        dog1.swim(10f);
        dog1.swim(12f);
        System.out.println();

        dog2.info();
        dog2.run(600);
        dog2.run(700);
        dog2.jump(0.8f);
        dog2.jump(1.2f);
        dog2.swim(15f);
        dog2.swim(18f);





    }
}
