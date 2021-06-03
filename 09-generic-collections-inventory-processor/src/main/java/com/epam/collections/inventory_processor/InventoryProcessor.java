package com.epam.collections.inventory_processor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/**
 * Class for working with inventory
 */
public class InventoryProcessor {
    private static final Logger LOGGER = LogManager.getLogger(InventoryProcessor.class);
    private static final String REGEX_FOR_SPLIT_PRODUCTS = "\r\n";
    private static final String REGEX_FOR_SPLIT_PRODUCT_ITEMS = "=";

    public List<Product> sort(List<Product> products) {
        return new ArrayList<>(new TreeSet<>(products));
    }

    public List<Product> distinct(List<Product> products) {
        Set<Product> productsSet = new HashSet<>(products);
        return new ArrayList<>(productsSet);
    }

    public String readFile(String src) {
        String returnedProductString = null;
        try {
            File file = new File(src);
            returnedProductString = Files.readString(file.toPath());
        } catch (IOException e) {
            LOGGER.error("Error has occurred in readFile", e);
        }
        return returnedProductString;
    }

    public List<Product> originalList(String listOfProducts) {
        String[] productStrings = listOfProducts.split(REGEX_FOR_SPLIT_PRODUCTS);
        List<Product> products = new ArrayList<>();
        for (String productString : productStrings) {
            String[] productItems = productString.split(REGEX_FOR_SPLIT_PRODUCT_ITEMS);
            products.add(new Product(Integer.parseInt(productItems[0]), productItems[1]));
        }

        return products;
    }
}
