package com.epam.multithreading.model.media;

import java.util.List;

public class MediaImpl implements Media {
    private static final String NEWS_MASSAGE = "Good news for everyone! Total amount money in city is: %d$%n";
    private static final String NEWS_INFO = "This %s has money: %d$%n";
    private static final long NEWS_TIME_OF_LUNCH = 1000L;
    private final List<Wiretapping> wiretappings;

    public MediaImpl(List<Wiretapping> wiretappings) {
        this.wiretappings = wiretappings;
    }

    @Override
    public void printInfo() {
        System.out.printf(NEWS_MASSAGE, getMoneyAmount());
        for (Wiretapping wiretapping : wiretappings) {
            System.out.printf(NEWS_INFO, wiretapping.getName(), wiretapping.getMoneyAmount());
        }
    }

    @Override
    public int getMoneyAmount() {
        int totalAmount = 0;
        for (Wiretapping wiretapping : wiretappings) {
            totalAmount += wiretapping.getMoneyAmount();
        }

        return totalAmount;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            printInfo();
            try {
                synchronized (this) {
                    Thread.sleep(NEWS_TIME_OF_LUNCH);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
