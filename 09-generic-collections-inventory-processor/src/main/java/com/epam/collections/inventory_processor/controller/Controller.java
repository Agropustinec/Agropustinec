package com.epam.collections.inventory_processor.controller;

import com.epam.collections.inventory_processor.InventoryProcessor;
import com.epam.collections.inventory_processor.Product;
import com.epam.collections.inventory_processor.view.View;

import java.util.List;

public class Controller {
    public void run(String src) {
        InventoryProcessor inventoryProcessor = new InventoryProcessor();
        View view = new View();
        List<Product> originalListProduct = inventoryProcessor.originalList(inventoryProcessor.readFile(src));
        List<Product> productListSort = inventoryProcessor.sort(originalListProduct);
        List<Product> productListDistinct = inventoryProcessor.distinct(productListSort);
        view.printListOriginal(originalListProduct);
        view.printListSort(productListSort);
        view.printListDistinct(productListDistinct);
    }
}