package com.epam.multithreading.model.bank;

import com.epam.multithreading.model.clients.HelpDesk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BankImpl implements Bank {
    private static final Logger LOGGER = LogManager.getLogger(BankImpl.class);
    private static final int BREAK_BETWEEN_LUNCH = 1000;
    private String name;
    private volatile int moneyAmount;
    private HelpDesk helpDesk;
    private boolean active = true;

    public BankImpl(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void run() {
        while (active) {
            goToLunch();
            try {
                Thread.sleep(BREAK_BETWEEN_LUNCH);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            active = active && !Thread.interrupted();
        }
        LOGGER.info("Thread {} has been stopped.", name);
    }

    @Override
    public synchronized void takeMoney(int money) {
        moneyAmount += money;
    }

    @Override
    public synchronized int giveMoney() {
        int credit = Math.min(helpDesk.getCreditSize(), this.moneyAmount);
        this.moneyAmount -= credit;
        return credit;
    }

    @Override
    public synchronized void goToLunch() {
        try {
            Thread.sleep(helpDesk.getLunchTime());
        } catch (InterruptedException e) {
            this.active = false;
        }
    }

    @Override
    public void setHelpDesk(HelpDesk helpDesk) {
        this.helpDesk = helpDesk;
    }
}
