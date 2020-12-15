package lesson_5;

public class MainApp {
    public static void main(String[] args) {

        Employee[] employeeArray= new Employee[5];
        employeeArray[0] = new Employee("Ivanov Ivan", "Engineer", "ivanovi@mail.ru", "+79254657823", 50000, 45);
        employeeArray[1] = new Employee("Petrov Petr", "Specialist", "petrovp@gmail.com", "+79245967312", 30000, 25);
        employeeArray[2] = new Employee("Sidorov Alexey", "Manager", "sidorov@yandex.ru", "+79648512745", 40000, 30);
        employeeArray[3] = new Employee("Demidov Sergey", "General Director", "demidovs@mail.ru", "+79465925873", 150000, 50);
        employeeArray[4] = new Employee("Kandrashov Vadim", "IT Specialist", "kandrv@inbox.ru", "+79645782586", 60000, 42);

        for (int i = 0; i < employeeArray.length; i++) {
            if (employeeArray[i].getAge() > 40)
                employeeArray[i].info();
        }

    }
}
