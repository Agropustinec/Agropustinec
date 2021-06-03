package com.epam.knight.view;

import com.epam.knight.model.Knight;
import com.epam.knight.model.ammunition.Ammunition;
import com.epam.knight.model.ammunition.Helmet;
import com.epam.knight.model.ammunition.Sword;

public class ConsoleView {
    private static final String MAIN_MENU = "Main menu";
    private static final String KNIGHT_STATS = "1. Print knight stats";
    private static final String SHOW_AMMUNITION = "2. Show ammunition";
    private static final String EQUIP_AMMUNITION = "3. Equip ammunition";
    private static final String SORT_AMMUNITION = "4. Sort ammunition";
    private static final String SEARCH_AMMUNITION = "5. Search ammunition";
    private static final String EXIT = "6. Exit";
    private static final String CHOSE_OPTION = "Choose option: ";
    private static final String AMMUNITION_COST = "Ammunition cost: ";
    private static final String WEIGHT = "Weight: ";
    private static final String DAMAGE = "Damage: ";
    private static final String PROTECTION = "Protection: ";
    private static final String NO_AMMUNITION = "no ammunition found";
    private static final String SWORD_DAMAGE = "Sword - damage = ";
    private static final String WEIGHT_AMMUNITION = ", weight = ";
    private static final String COST_AMMUNITION = ", cost = ";
    private static final String HELMET_PROTECTION = "Helmer - protection = ";
    private static final String BYE = "Bye";
    private static final String EQUIPMENT_QUESTION = "What kind of ammunition do you want to equip?";
    private static final String SWORD = "1. Sword";
    private static final String HELMET = "2. Helmet";
    private static final String INPUT_WEIGHT = "Input weight: ";
    private static final String INPUT_COST = "Input cost: ";
    private static final String INPUT_DAMAGE = "Input damage: ";
    private static final String INPUT_PROTECTION = "Input protection: ";
    private static final String CHOOSE_SORT_TYPE = "Choose sort type: ";
    private static final String SORT_TYPE_COST = "1. Cost";
    private static final String SORT_TYPE_WEIGHT = "2. Weight";
    private static final String SEARCH_FIELD = "Choose search field: ";
    private static final String COST_MIN = "Input minimum cost:";
    private static final String COST_MAX = "Input maximum cost:";
    private static final String WEIGHT_MIN = "Input minimum weight:";
    private static final String WEIGHT_MAX = "Input maximum weight:";
    private static final String ERROR_OPERATOR = "Enter correct operator, please";


    public void printMenu() {
        System.out.println(MAIN_MENU);
        System.out.println(KNIGHT_STATS);
        System.out.println(SHOW_AMMUNITION);
        System.out.println(EQUIP_AMMUNITION);
        System.out.println(SORT_AMMUNITION);
        System.out.println(SEARCH_AMMUNITION);
        System.out.println(EXIT);
        System.out.println(CHOSE_OPTION);
    }

    public void printKnightStatic(Knight knight) {
        System.out.println(AMMUNITION_COST + knight.getCost());
        System.out.println(WEIGHT + knight.getWeight());
        System.out.println(DAMAGE + knight.getDamage());
        System.out.println(PROTECTION + knight.getProtection());
    }

    public void showAmmunition(Knight knight) {
        String ammunitionPrint;
        Ammunition[] workArray = knight.getAmmunition();
        if (knight.getAmmunition() == null) {
            System.out.println(NO_AMMUNITION);
        } else {
            for (Ammunition ammunition : workArray) {
                if (ammunition.getClass().equals(Sword.class)) {
                    ammunitionPrint = SWORD_DAMAGE + ((Sword) ammunition).getDamage() + WEIGHT_AMMUNITION + ammunition.getWeight() + COST_AMMUNITION
                            + ammunition.getCost();
                    System.out.println(ammunitionPrint);
                }
                if (ammunition.getClass().equals(Helmet.class)) {
                    ammunitionPrint = HELMET_PROTECTION + ((Helmet) ammunition).getProtection() + WEIGHT_AMMUNITION + ammunition.getWeight() +
                            COST_AMMUNITION
                            + ammunition.getCost();
                    System.out.println(ammunitionPrint);
                }
            }
        }
    }

    public void searchAmmunition(Ammunition ammunition) {
        if (ammunition.getClass().equals(Sword.class)) {
            System.out.println(SWORD_DAMAGE + ((Sword) ammunition).getDamage() + WEIGHT_AMMUNITION + ammunition.getWeight() + COST_AMMUNITION
                    + ammunition.getCost());
        }
        if (ammunition.getClass().equals(Helmet.class)) {
            System.out.println(HELMET_PROTECTION + ((Helmet) ammunition).getProtection() + WEIGHT_AMMUNITION + ammunition.getWeight() +
                    COST_AMMUNITION
                    + ammunition.getCost());
        }
    }

    public void printBye() {
        System.out.println(BYE);
    }

    public void equipmentQuestion() {
        System.out.println(EQUIPMENT_QUESTION);
        System.out.println(SWORD);
        System.out.println(HELMET);
    }

    public void chooseOption() {
        System.out.println(CHOSE_OPTION);
    }

    public void printInputWeight() {
        System.out.println(INPUT_WEIGHT);
    }

    public void printInputCost() {
        System.out.println(INPUT_COST);
    }

    public void printInputDamage() {
        System.out.println(INPUT_DAMAGE);
    }

    public void printInputProtection() {
        System.out.println(INPUT_PROTECTION);
    }

    public void printSortType() {
        System.out.println(CHOOSE_SORT_TYPE);
        System.out.println(SORT_TYPE_COST);
        System.out.println(SORT_TYPE_WEIGHT);
    }

    public void printSearchField() {
        System.out.println(SEARCH_FIELD);
        System.out.println(SORT_TYPE_COST);
        System.out.println(SORT_TYPE_WEIGHT);
    }

    public void printMinimumCost() {
        System.out.println(COST_MIN);
    }

    public void printMaximumCost() {
        System.out.println(COST_MAX);
    }

    public void printMinimumWeight() {
        System.out.println(WEIGHT_MIN);
    }

    public void printMaximumWeight() {
        System.out.println(WEIGHT_MAX);
    }

    public void printErrorOperator() {
        System.out.println(ERROR_OPERATOR);
    }

}