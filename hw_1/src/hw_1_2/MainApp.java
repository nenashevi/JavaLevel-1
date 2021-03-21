package hw_1_2;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        // 1 homework
        Arr a = new Arr();
        Integer[] arr1 = {1, 2, 3, 4, 5};
        String[] arr2 = {"sss", "ddd", "eee", "fff"};

        System.out.println("Исходный массив");
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }
        System.out.println();
        a.swapTwoElementsOfArr(arr1);
        System.out.println("Первый и последний элементы массива поменяны местами");
        for (int i = 0; i < arr1.length; i++) {
            System.out.print(arr1[i] + " ");
        }

        System.out.println();
        System.out.println("Исходный массив");
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }
        a.swapTwoElementsOfArr(arr2);
        System.out.println();
        System.out.println("Первый и последний элементы массива поменяны местами");
        for (int i = 0; i < arr2.length; i++) {
            System.out.print(arr2[i] + " ");
        }

        // 2 homework
        Integer[] arr3 = {1, 2, 3, 4};
        List list1;


        list1 = a.ArrToArrayList(arr3);
        System.out.println();
        System.out.println("Вывод коллекции елементов типа Integer:");
        System.out.println(list1);

        String[] arr4 = {"aaa", "bbb", "ccc", "ddd"};
        List list2;
        list2 = a.ArrToArrayList(arr4);

        System.out.println();
        System.out.println("Вывод коллекции елементов типа String:");
        System.out.println(list2);










    }
//    public static void swapTwoElementsOfArr (int[] arr) {
//        int tempEl;
//        tempEl = arr[0];
//        arr[0] = arr[arr.length -1];
//        arr[arr.length -1] = tempEl;
//    }
}
