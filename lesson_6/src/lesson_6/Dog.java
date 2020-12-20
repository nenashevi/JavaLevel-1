package lesson_6;

public class Dog extends Animal {
    float maxRun;
    float maxJump;
    float maxSwim;
    public Dog() {
    }
    public Dog(String name, String color, float maxRun, float maxJump, float maxSwim) {
        super(name, color);
        this.maxRun = maxRun;
        this.maxJump = maxJump;
        this.maxSwim = maxSwim;
    }

    public float getMaxRun() {
        return maxRun;
    }

    public float getMaxJump() {
        return maxJump;
    }

    public float getMaxSwim() {
        return maxSwim;
    }

    public void setMaxRun(float maxRun) {
        this.maxRun = maxRun;
    }

    public void setMaxJump(float maxJump) {
        this.maxJump = maxJump;
    }

    public void setMaxSwim(float maxSwim) {
        this.maxSwim = maxSwim;
    }

    @Override
    public void info() {
        super.info();
        System.out.println("Животное: Собака, " + "Максимальная длина забега: " + this.maxRun + " метров,"
                + " Максимальный прыжок: " + maxJump + " метров, Максимальный заплыв: " + maxSwim);
    }
    boolean run(float runDistance) {
        if (runDistance > 0 && runDistance <= maxRun) {
            System.out.println("Собака " + getName() + " пробежала дистанцию " + runDistance + " метров" );
            return true;
        }
        if(runDistance <= 0) {
            System.out.println("Длина забега не может быть отрицательной или нулевой величиной");
            return false;
        }
        if(runDistance > maxRun) {
            System.out.println("Собака " + getName() + " не способна пробежать дистанцию свыше " + maxRun + " метров");
            return false;
        }
        return false;

    }
    boolean jump(float jumpHeight) {
        if (jumpHeight > 0 && jumpHeight <= maxJump) {
            System.out.println("Собака " + getName() + " перепрыгнула препятствие высотой " + jumpHeight + " метров" );
            return true;
        }
        if(jumpHeight <= 0) {
            System.out.println("Высота препятствия не может быть отрицательной или нулевой величиной");
            return false;
        }
        if(jumpHeight > maxJump) {
            System.out.println("Собака " + getName() + " не способна перепрыгнуть препятствие свыше " + maxJump + " метров");
            return false;
        }
        return false;

    }
    boolean swim(float swimDistance) {
        if (swimDistance > 0 && swimDistance <= maxSwim) {
            System.out.println("Собака " + getName() + " проплыла дистанцию " + swimDistance + " метров" );
            return true;
        }
        if(swimDistance <= 0) {
            System.out.println("Длина заплыва не может быть отрицательной или нулевой величиной");
            return false;
        }
        if(swimDistance > maxSwim) {
            System.out.println("Собака " + getName() + " не способна проплыть дистанцию свыше " + maxSwim + " метров");
            return false;
        }
        return false;

    }


}
