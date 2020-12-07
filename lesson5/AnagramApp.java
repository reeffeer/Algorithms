package lesson5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AnagramApp {
    static int size;
    static int count;
    static char[] arr = new char[5];

    public static void main(String[] args) throws IOException {
        String input = getString();
        size = input.length();
        count = 0;
        for(int i=0;i<size;i++){
            arr[i] = input.charAt(i);
        }
        getAnagram(size);
    }

    public static void getAnagram(int newSize) {
        if (newSize == 1) {
            return;
        }
        for (int i=0;i<newSize;i++) {
            getAnagram(newSize-1);
            if (newSize == 2) {
                display();
            }
            rotate(newSize);
        }
    }

    public static void rotate(int newSize) {
        int i;
        int pos = size - newSize;
        char temp = arr[pos];
        for (i=pos+1;i<size;i++) {
            arr[i-1] = arr[i];
        }
        arr[i-1] = temp;
    }

    public static void display() {
        for(int i=0; i<size; i++) {
            System.out.print(arr[i]);
        }
        System.out.println("");
    }
    public static String getString() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        return br.readLine();
    }

}
