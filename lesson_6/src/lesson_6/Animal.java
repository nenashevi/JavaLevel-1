package lesson_6;

public class Animal {
    String name;
    String color;
    Animal () {

    }
    public Animal (String name, String color) {
       this.name = name;
       this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void info () {
        System.out.println("Кличка: " + name + " Цвет: " + color);
    }
}
