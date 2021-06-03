package com.epam.collections.inventory_processor;

import com.epam.collections.inventory_processor.controller.Controller;

public class Runner {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.run(args[0]);
    }
}