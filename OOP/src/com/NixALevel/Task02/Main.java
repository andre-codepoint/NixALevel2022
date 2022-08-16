package com.NixALevel.Task02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Оберить: 1-Iron, 2-Water, 3-Oxygen");
        Scanner scanner = new Scanner(System.in);
        int subCode = scanner.nextInt();
        BaseSub sub = null;
        if (subCode == 1) {
            sub = new Iron();
        } else if (subCode == 2) {
            sub = new Water();
        } else if (subCode == 3) {
            sub = new Oxygen();
        } else {
            System.out.println("Error");
            System.exit(1);
        }
        int temp;
        System.out.println("Зменить температуру");
        while ((temp = scanner.nextInt()) != 0) {
            int startTemp = sub.getTemp();
            State state = sub.heatUp(temp);
            int newTemp = sub.getTemp();
            System.out.println("Змінили температуру "+sub.getName()+" з " +startTemp+" на " + newTemp + " в стан " + state);
        }
        System.out.println("Кінець роботи");
    }
}
