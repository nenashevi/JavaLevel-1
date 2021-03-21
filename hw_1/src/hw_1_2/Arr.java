package hw_1_2;

import java.util.ArrayList;
import java.util.List;

public class Arr <T> {
    public void swapTwoElementsOfArr (T[] arr) {
        T tempEl;
        tempEl = arr[0];
        arr[0] = arr[arr.length -1];
        arr[arr.length -1] = tempEl;
    }

    public ArrayList<T> ArrToArrayList(T[] arr) {
        List<T> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(i, arr[i]);
        }
    return (ArrayList) list;
    }
}
