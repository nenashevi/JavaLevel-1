package lesson_5;

public class Employee {
    private String fio;
    private String position;
    private String email;
    private String phone;
    private int salary;
    private int age;
    public Employee () {

    }
    public Employee (String fio, String position, String email, String phone, int salary, int age) {
        this.fio = fio;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        if (age >= 0)
            this.age = age;
        else
            System.out.println("Возраст не может быть отрицательным");
    }
    public void info () {
        System.out.println(fio + ", " + position + ", " + email + ", " + phone + ", " + salary + ", " + age);
    }

}
