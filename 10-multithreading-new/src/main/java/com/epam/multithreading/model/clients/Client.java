package com.epam.multithreading.model.clients;

public interface Client extends Runnable {
    void setHelpDesk(HelpDesk helpDesk);
}
