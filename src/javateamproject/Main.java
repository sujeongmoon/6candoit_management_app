package javateamproject;

import javateamproject.display.MainDisplay;
import javateamproject.store.Store;

import java.util.*;

public class Main {
        private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Store.init();
        try {
            MainDisplay.displayMain();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
