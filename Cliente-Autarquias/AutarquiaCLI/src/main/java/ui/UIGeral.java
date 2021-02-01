package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UIGeral {
    public static long getNumber(String label) {

        Scanner kbd = new Scanner(System.in);
        boolean flag;
        long number = -1;
        do {
            System.out.print(label + ": ");
            try {
                flag = false;
                number = kbd.nextLong();
            } catch (InputMismatchException e) {
                flag = true;
            }
            kbd.nextLine();
        } while (flag);
        return number;
    }

    public static String getText(String label) {
        Scanner kbd = new Scanner(System.in);
        String text = "";
        System.out.print(label + ": ");
        text = kbd.nextLine();
        return text;
    }
}
