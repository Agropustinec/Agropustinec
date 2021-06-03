package com.epam.knight.model;

import com.epam.knight.model.ammunition.Ammunition;

import java.util.Comparator;

public class CostComparator implements Comparator<Ammunition> {
    @Override
    public int compare(Ammunition firstItem, Ammunition secondItem) {
        return Integer.compare(firstItem.getCost(), secondItem.getCost());
    }
}
