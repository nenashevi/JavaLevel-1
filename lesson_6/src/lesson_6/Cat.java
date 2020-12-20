package lesson_6;

public class Cat extends Animal {
    float maxRun;
    float maxJump;
    public Cat() {
    }
    public Cat(String name, String color, float maxRun, float maxJump) {
        super(name, color);
        this.maxRun = maxRun;
        this.maxJump = maxJump;
    }

    public float getMaxRun() {
        return maxRun;
    }

    public float getMaxJump() {
        return maxJump;
    }

    public void setMaxRun(float maxRun) {
        this.maxRun = maxRun;
    }

    public void setMaxJump(float maxJump) {
        this.maxJump = maxJump;
    }


    @Override
    public void info() {
        super.info();
        System.out.println("Животное: Кот, " + "Максимальная длина забега: " + this.maxRun + " метров,"
                + " Максимальный прыжок: " + maxJump + " метров, Плавать не умеет.");
    }
    boolean run(float runDistance) {
        if (runDistance > 0 && runDistance <= maxRun) {
            System.out.println("Кот " + getName() + " пробежал дистанцию " + runDistance + " метров" );
            return true;
        }
        if(runDistance <= 0) {
            System.out.println("Длина забега не может быть отрицательной или нулевой величиной");
            return false;
        }
        if(runDistance > maxRun) {
            System.out.println("Кот " + getName() + " не способен пробежать дистанцию свыше " + maxRun + " метров");
            return false;
        }
        return false;

    }
    boolean jump(float jumpHeight) {
        if (jumpHeight > 0 && jumpHeight <= maxJump) {
            System.out.println("Кот " + getName() + " перепрыгнул препятствие высотой " + jumpHeight + " метров" );
            return true;
        }
        if(jumpHeight <= 0) {
            System.out.println("Высота препятствия не может быть отрицательной или нулевой величиной");
            return false;
        }
        if(jumpHeight > maxJump) {
            System.out.println("Кот " + getName() + " не способен перепрыгнуть препятствие свыше " + maxJump + " метров");
            return false;
        }
        return false;

    }
}
