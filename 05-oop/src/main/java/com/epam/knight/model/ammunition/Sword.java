package com.epam.knight.model.ammunition;


public class Sword implements Ammunition {

    private final int weight;
    private final int cost;
    private final int damage;

    public Sword(int weight, int cost, int damage) {
        this.weight = weight;
        this.cost = cost;
        this.damage = damage;
    }


    public int getWeight() {
        return this.weight;
    }

    public int getCost() {
        return this.cost;
    }

    public int getDamage() {
        return this.damage;
    }
}

