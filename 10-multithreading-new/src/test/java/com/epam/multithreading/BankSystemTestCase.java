package com.epam.multithreading;

import com.epam.multithreading.controller.Controller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class BankSystemTestCase {

    @Test
    public void bankSystemCase() {
        int bankAmount = 10;
        int workerAmount = 20;
        int spenderAmount = 30;
        long workingTime = 5000;

        Controller controller = new Controller();
        controller.initBankSystemData(bankAmount, workerAmount, spenderAmount);

        int countBefore = controller.getMedia().getMoneyAmount();
        controller.startWorking(workingTime);
        int countAfter = controller.getMedia().getMoneyAmount();
        assertEquals("The amount of money in the system at the end of work does not match the original amount.", countAfter, countBefore);

    }

    @Test
    public void bankSystemSimpleCase() {
        int bankAmount = 2;
        int workerAmount = 4;
        int spenderAmount = 5;
        long workingTime = 5000;

        Controller controller = new Controller();
        controller.initBankSystemData(bankAmount, workerAmount, spenderAmount);

        int countBefore = controller.getMedia().getMoneyAmount();
        controller.startWorking(workingTime);
        int countAfter = controller.getMedia().getMoneyAmount();
        assertEquals("The amount of money in the system at the end of work does not match the original amount.", countAfter, countBefore);

    }
}
