package lesson_7;

public class Cat {
    private String name;
    private int appetite;
    boolean  wellFed;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public void eat(Plate p) {
// Закоментирована реализация когда кот в любом случае ест, даже если коту мало еды в тарелке
//        if(p.getFood() == 0) {
//            System.out.println("В тарелке нет еды");
//            return;
//        }
//        if(appetite > p.getFood()) {
//            wellFed = true;
//            appetite -= p.getFood();
//            p.decreaseFood(p.getFood());
//        }
//        else {
//            p.decreaseFood(appetite);
//            appetite = 0;
//            wellFed = true;
//        }
        if(p.getFood() == 0) {
            System.out.println("В тарелке нет еды");
            return;
        }
        if(appetite <= p.getFood()) {
            p.decreaseFood(appetite);
            appetite = 0;
            wellFed = true;
            return;
        }


    }
    public void info() {
        System.out.println("Кот: " + name + ", Аппетит: " + appetite + ", Сытость: " + wellFed);
    }
    String getName(){
        return name;
    }
}
