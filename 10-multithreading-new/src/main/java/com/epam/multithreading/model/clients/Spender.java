package com.epam.multithreading.model.clients;

import com.epam.multithreading.model.media.Wiretapping;

public interface Spender extends Wiretapping, Client {
    void hireWorker();

    void payMoneyToWorker();

    void getMoneyFromBank();
}