package lesson2;

public class MainApp {
    public static void main(String[] args) {

        // Задание №1
        System.out.println("Задание №1");
        int[] arr1 = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] == 0)
                arr1[i] =1;
            else
                arr1[i] =0;
            System.out.print(arr1[i] + " ");
        }

        // Задание №2
        System.out.println("\nЗадание №2");
        int[] arr2 = new int[8];
        int counter = 0;
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = counter;
            counter+=3;
            System.out.print(arr2[i] + " ");
        }

        // Задание №3
        System.out.println("\nЗадание №3");
        int[] arr3 = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        for (int i = 0; i < arr3.length; i++) {
            if (arr3[i] < 6) arr3[i]*=2;
            System.out.print(arr3[i] + " ");
        }

        // Задание №4
        System.out.println("\nЗадание №4");
        int[][] arr4 = new int[10][10];
        for (int i = 0; i < arr4.length; i++) {
            for (int j = 0; j < arr4[i].length; j++) {
                if (i == j || i == ((arr4[i].length-1)-j))
                    arr4[i][j] = 1;
                else
                    arr4[i][j] =0;
                System.out.print(arr4[i][j] + " ");
            }
            System.out.println("");
        }

        // Задание №5
        System.out.println("\nЗадание №5");
        int[] arr5 = {5 ,7 ,24 ,13 ,8 ,19 ,35 ,2 ,31 ,7 };
        int maxNumber = arr5[0];
        int minNumber = arr5[0];
        for (int i = 1; i < arr5.length; i++) {
            if (arr5[i] > maxNumber) maxNumber = arr5[i];
            if (arr5[i] < minNumber) minNumber = arr5[i];
        }
        System.out.println("Максимальный элемент массива равен: " + maxNumber);
        System.out.println("Минимальный элемент массива равен: " + minNumber);



        //Задание №6
        System.out.println("\nЗадание №6");
        int[] arr6 = {2, 3, 4, 3, 5, 1}; // {2, 3, 4,|| 3, 5, 1}
        System.out.println(checkBalance(arr6));

        //Задание №7
        System.out.println("\nЗадание №7");
        int[] arr7 = {2, 3, 5, 4, 6, 8};
        shiftArray(arr7, -3);


    }
    public static boolean checkBalance(int[] arr) {
        int leftSumEl;
        int rightSumEl;
        boolean balanceStatus = false;
        for (int i = 0; i < (arr.length - 1); i++) {
            leftSumEl = 0;
            rightSumEl = 0;
            for (int j = 0; j <= i ; j++) {
                leftSumEl += arr[j];

            }
            for (int k = i+1; k < arr.length ; k++) {
                rightSumEl += arr[k];

            }
            if (leftSumEl == rightSumEl)
                balanceStatus = true;
        }
    return balanceStatus;
    }
    // {2, 3, 5, 4, 6, 8}
    // Метод смещает все элементы по кругу
    public static void shiftArray(int[] arr, int n) {
        System.out.println("Исходный массив");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");

        }

        int tempEl;
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                tempEl = arr[0];
                for (int j = 0; j <= arr.length -2; j++) {
                    arr[j] = arr[j+1];
                }
            arr[arr.length-1] = tempEl;
            }
            
        }

        if (n < 0) {
            n = n *-1;

            for (int i = 0; i < n; i++) {
                tempEl = arr[arr.length-1];
                for (int j = arr.length-1; j >= 1 ; j--) {
                    arr[j] = arr[j-1];
                }
            arr[0] = tempEl;
            }

        }
        System.out.println("\nМодифицированный массив");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");

        }


    }
}
