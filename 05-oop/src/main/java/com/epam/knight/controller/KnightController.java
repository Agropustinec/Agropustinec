package com.epam.knight.controller;

import com.epam.knight.model.Knight;
import com.epam.knight.view.ConsoleView;

import java.util.Scanner;

public class KnightController {
    private static final int EQUIPMENT_KNIGHT_SWORD = 1;
    private static final int EQUIPMENT_KNIGHT_HELMET = 2;
    private static final int SORT_BY_COST = 1;
    private static final int SORT_BY_WEIGHT = 2;
    private static final int SEARCH_BY_COST = 1;
    private static final int SEARCH_BY_WEIGHT = 2;
    private final Knight knight = KnightGenerator.generateKnight();
    private final ConsoleView consoleView = new ConsoleView();
    private final Scanner scanner = new Scanner(System.in);

    void displayKnightStatic() {
        consoleView.printKnightStatic(knight);
    }

    void displayAmmunition() {
        consoleView.showAmmunition(knight);
    }

    void equipmentKnight() {
        consoleView.equipmentQuestion();
        consoleView.chooseOption();
        int choiceEquip = scanner.nextInt();
        switch (choiceEquip) {
            case EQUIPMENT_KNIGHT_SWORD:
                equipmentKnightSword();
                break;
            case EQUIPMENT_KNIGHT_HELMET:
                equipmentKnightHelmet();
                break;
            default:
                consoleView.printErrorOperator();
                break;
        }
    }

    void equipmentKnightSword() {
        consoleView.printInputWeight();
        int weightAmmunition = scanner.nextInt();
        consoleView.printInputCost();
        int costAmmunition = scanner.nextInt();
        consoleView.printInputDamage();
        int damageAmmunition = scanner.nextInt();
        knight.equip(knight.generateEquipSword(weightAmmunition, costAmmunition, damageAmmunition));
    }

    void equipmentKnightHelmet() {
        consoleView.printInputWeight();
        int weightAmmunition = scanner.nextInt();
        consoleView.printInputCost();
        int costAmmunition = scanner.nextInt();
        consoleView.printInputDamage();
        int protectionAmmunition = scanner.nextInt();
        knight.equip(knight.generateEquipHelmet(weightAmmunition, costAmmunition, protectionAmmunition));
    }

    void sortAmmunition() {
        consoleView.printSortType();
        int choiceSort = scanner.nextInt();
        switch (choiceSort) {
            case SORT_BY_COST:
                knight.sortByCost(knight.getAmmunition());
                consoleView.showAmmunition(knight);
                break;
            case SORT_BY_WEIGHT:
                knight.sortByWeight(knight.getAmmunition());
                consoleView.showAmmunition(knight);
                break;
            default:
                consoleView.printErrorOperator();
                break;
        }
    }

    void findElementAmmunition() {
        consoleView.printSearchField();
        int choiceFind = scanner.nextInt();
        int lowerRange;
        int upperRange;
        switch (choiceFind) {
            case SEARCH_BY_COST:
                consoleView.printMinimumCost();
                lowerRange = scanner.nextInt();
                consoleView.printMaximumCost();
                upperRange = scanner.nextInt();
                for (int i = 0; i < knight.getAmmunition().length; i++) {
                    if (knight.getAmmunition()[i].getCost() >= lowerRange && knight.getAmmunition()[i].getCost() < upperRange) {
                        consoleView.searchAmmunition(knight.getAmmunition()[i]);
                    }
                }
                break;
            case SEARCH_BY_WEIGHT:
                consoleView.printMinimumWeight();
                lowerRange = scanner.nextInt();
                consoleView.printMaximumWeight();
                upperRange = scanner.nextInt();
                for (int i = 0; i < knight.getAmmunition().length; i++) {
                    if (knight.getAmmunition()[i].getWeight() >= lowerRange && knight.getAmmunition()[i].getWeight() < upperRange) {
                        consoleView.searchAmmunition(knight.getAmmunition()[i]);
                    }
                }
                break;
            default:
                consoleView.printErrorOperator();
                break;
        }
    }
}