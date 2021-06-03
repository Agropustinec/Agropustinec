package com.epam.knight.controller;

import com.epam.knight.model.Knight;

public class KnightGenerator {
    private static final int BASE_DAMAGE = 10;
    private static final int BASE_PROTECTION = 10;
    private static final int BASE_WEIGHT = 70;

    /**
     * Use it to quickly generate knight
     *
     * @return knight
     */
    public static Knight generateKnight() {
        Knight knight = new Knight();
        knight.setDamage(BASE_DAMAGE);
        knight.setProtection(BASE_PROTECTION);
        knight.setWeight(BASE_WEIGHT);
        knight.setCost(0);
        return knight;
    }
}