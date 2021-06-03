package com.epam.knight.model;

import com.epam.knight.model.ammunition.Ammunition;
import com.epam.knight.model.ammunition.Helmet;
import com.epam.knight.model.ammunition.Sword;


import java.util.Arrays;


public class Knight {
    private int damage;
    private int protection;
    private int weight;
    private int cost;
    private Ammunition[] ammunition;

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }


    public Ammunition[] getAmmunition() {
        return ammunition;
    }

    /**
     * Add new ammunition element to knight
     *
     * @param element that should be equipped to the knight
     */

    public void equip(Ammunition element) {
        if (ammunition == null) {
            ammunition = new Ammunition[1];
        } else {
            ammunition = Arrays.copyOf(ammunition, ammunition.length + 1);
        }
        ammunition[ammunition.length - 1] = element;
    }

    public Ammunition generateEquipSword(int weightAmmunition, int costAmmunition, int damageAmmunition) {
        setCost(getCost() + costAmmunition);
        setWeight(getWeight() + weightAmmunition);
        setDamage(getDamage() + damageAmmunition);
        return new Sword(weightAmmunition, costAmmunition, damageAmmunition);
    }

    public Ammunition generateEquipHelmet(int weightAmmunition, int costAmmunition, int protectionAmmunition) {
        setCost(getCost() + costAmmunition);
        setWeight(getWeight() + weightAmmunition);
        setProtection(getProtection() + protectionAmmunition);
        return new Helmet(weightAmmunition, costAmmunition, protectionAmmunition);
    }


    public void sortByCost(Ammunition[] ammunition) {
        Arrays.sort(ammunition, new CostComparator());
    }

    public void sortByWeight(Ammunition[] ammunition) {
        Arrays.sort(ammunition, new WeightComparator());
    }

    public int calculateAmmunitionWeight() {
        int sumWeight = 0;
        for (Ammunition am : ammunition) {
            sumWeight += am.getWeight();
        }
        return sumWeight;
    }

    public int calculateAmmunitionCost() {
        int sumCost = 0;
        for (Ammunition am : ammunition) {
            sumCost += am.getCost();
        }
        return sumCost;
    }

    public int calculateAmmunitionDamage() {
        int sumDamage = 0;
        for (Ammunition am : ammunition) {
            sumDamage += am.getClass().equals(Sword.class) ? ((Sword) am).getDamage() : 0;
        }
        return sumDamage;
    }

    public int calculateAmmunitionProtection() {
        int sumProtection = 0;
        for (Ammunition am : ammunition) {
            sumProtection += am.getClass().equals(Helmet.class) ? ((Helmet) am).getProtection() : 0;
        }
        return sumProtection;
    }
}
