package com.epam.multithreading.model.media;

public interface Media extends Runnable {
    void printInfo();

    int getMoneyAmount();
}
