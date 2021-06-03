package com.epam.multithreading.model.bank;

import com.epam.multithreading.model.clients.HelpDesk;
import com.epam.multithreading.model.media.Wiretapping;

public interface Bank extends Wiretapping, Runnable {
    void takeMoney(int money);

    int giveMoney();

    void goToLunch();

    void setHelpDesk(HelpDesk helpDesk);
}