package com.epam.multithreading.model.clients;

import com.epam.multithreading.model.media.Wiretapping;

public interface Worker extends Wiretapping, Client {
    void receiveJobFromSpender(Spender spender);

    void receiveMoneyFromSpender(int money);

    void putMoneyToBank();
}
