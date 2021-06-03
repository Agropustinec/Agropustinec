package com.epam.collections.inventory_processor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class InventoryProcessorTestCase {

    private static final Product FIRST_PRODUCT = new Product(1, "First");
    private static final Product SECOND_PRODUCT = new Product(2, "Second");
    private static final Product FIRST_PRODUCT_DUPLICATE = new Product(1, "Second");


    @Test
    public void testSort() {
        List<Product> input = Arrays.asList(SECOND_PRODUCT, FIRST_PRODUCT);
        List<Product> expected = Arrays.asList(FIRST_PRODUCT, SECOND_PRODUCT);
        InventoryProcessor inventoryProcessor = new InventoryProcessor();
        List<Product> result = inventoryProcessor.sort(input);
        assertThat(String.format("Method sort returned incorrect result, input is %s", input), result, is(expected));
    }

    @Test
    public void testSortShouldNotRemoveDuplicates() {
        List<Product> input = Arrays.asList(FIRST_PRODUCT_DUPLICATE, FIRST_PRODUCT);
        List<Product> expected = Arrays.asList(FIRST_PRODUCT, FIRST_PRODUCT_DUPLICATE);
        InventoryProcessor inventoryProcessor = new InventoryProcessor();
        List<Product> result = inventoryProcessor.sort(input);
        assertThat(String.format("Method sort should not remove duplicates, input is %s", input), result, is(expected));
    }

    @Test
    public void testDistinct() {
        List<Product> input = Arrays.asList(FIRST_PRODUCT_DUPLICATE, FIRST_PRODUCT);
        List<Product> expected = Collections.singletonList(FIRST_PRODUCT_DUPLICATE);
        InventoryProcessor inventoryProcessor = new InventoryProcessor();
        List<Product> result = inventoryProcessor.distinct(input);
        assertThat(String.format("Method distinct returned incorrect result, input is %s", input),
                result, is(expected));
    }

}
