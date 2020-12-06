package lesson1;

public class MainApp {
    public static void main(String[] args) {
        byte var1 = 110;
        short var2 = 1000;
        int var3 = 5000;
        long var4 = 6000L;
        float var5 = 125.20f;
        double var6 = 500.45;
        boolean var7 = true;
        char var8 = 'x';
        String str1 = "Hello";
        //System.out.println(str1);
        float a = 2f;
        float b = 3f;
        float c = 8f;
        float d = 4f;
        float result;


        result = calculate(a, b, c, d);
        System.out.println("Результат выполнения метода calculate() равен: " + result);
        var7 = checkSumBetween10and20(7, 5);
        System.out.println("Результат выполнения метода checkSumBetween10and20() равен: " + var7);
        isNumPositiveOrNegotive(100);
        System.out.println("Результат выполнения метода isNegative() равен: " + isNegative(-10));
        greetings("Игорь");
        isLeapYear(2000);

    }
    public static float calculate(float a, float b, float c, float d) {
        return a * (b + (c / d));
    }
    public static boolean checkSumBetween10and20(int a, int b) {
        int sum = a+b;
        if (sum >= 10 && sum <= 20)
            return true;
          else
            return false;

    }
    public static void isNumPositiveOrNegotive(int a) {
        if ( a >= 0)
            System.out.println("Заданное число пложительное");
        else
            System.out.println("Заданное число отрицательное");
    }
    public static boolean isNegative(int a) {
        if (a < 0) return true;
        else return false;
    }
    public static void greetings(String name) {
        System.out.println("Привет, " + name + "!");
    }
    public static void isLeapYear(int year) {
        if (year%400 == 0) {
            System.out.println("Год " + year + " является високосным");
            return;
        }
        if (year%100 == 0) {
            System.out.println("Год " + year + " не является високосным");
            return;
        }
        if (year%4 == 0) {
            System.out.println("Год " + year + " является високосным");
            return;
        }

    }


}
