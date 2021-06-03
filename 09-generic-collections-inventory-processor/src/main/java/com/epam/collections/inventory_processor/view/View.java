package com.epam.collections.inventory_processor.view;

import com.epam.collections.inventory_processor.Product;

import java.util.List;

public class View {
    private static final String HEADING_ORIGINAL_LIST_PRODUCT = "Original list:";
    private static final String HEADING_SORTED_LIST_PRODUCT = "Sorted list:";
    private static final String HEADING_DISTINCT_LIST_PRODUCT = "Distinct list:";

    public void printListOriginal(List<Product> list) {
        System.out.println(HEADING_ORIGINAL_LIST_PRODUCT);
        printList(list);
    }

    public void printListSort(List<Product> list) {
        System.out.println(HEADING_SORTED_LIST_PRODUCT);
        printList(list);
    }

    public void printListDistinct(List<Product> list) {
        System.out.println(HEADING_DISTINCT_LIST_PRODUCT);
        printList(list);
    }

    private void printList(List<Product> list) {
        for (Product p : list) {
            System.out.println(p);
        }
    }
}