package com.epam.knight.controller;

import com.epam.knight.view.ConsoleView;

import java.util.Scanner;

public class KnightApplication {
    private static final int DISPLAY_KNIGHT_STATIC = 1;
    private static final int DISPLAY_AMMUNITION = 2;
    private static final int EQUIPMENT_KNIGHT = 3;
    private static final int SORT_AMMUNITION = 4;
    private static final int FIND_ELEMENT_AMMUNITION = 5;
    private static final int EXIT = 6;

    public static void main(String[] args) {
        KnightApplication knightApplication = new KnightApplication();
        knightApplication.start();
    }

    private void start() {
        KnightController knightController = new KnightController();
        ConsoleView consoleView = new ConsoleView();
        Scanner scanner = new Scanner(System.in);
        boolean startApplication = true;
        while (startApplication) {
            consoleView.printMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case DISPLAY_KNIGHT_STATIC:
                    knightController.displayKnightStatic();
                    break;
                case DISPLAY_AMMUNITION:
                    knightController.displayAmmunition();
                    break;
                case EQUIPMENT_KNIGHT:
                    knightController.equipmentKnight();
                    break;
                case SORT_AMMUNITION:
                    knightController.sortAmmunition();
                    break;
                case FIND_ELEMENT_AMMUNITION:
                    knightController.findElementAmmunition();
                    break;
                case EXIT:
                    consoleView.printBye();
                    startApplication = false;
                    break;
                default:
                    consoleView.printErrorOperator();
            }
        }
    }
}