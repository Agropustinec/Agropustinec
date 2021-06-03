package com.epam.multithreading.model.clients;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SpenderImpl implements Spender {
    private static final Logger LOGGER = LogManager.getLogger(SpenderImpl.class);
    private String name;
    private volatile int moneyAmount;
    private HelpDesk helpDesk;
    private Worker worker;

    public SpenderImpl(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    @Override
    public int getMoneyAmount() {
        return moneyAmount;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public synchronized void hireWorker() {
        this.worker = helpDesk.getRandomWorker();
        this.worker.receiveJobFromSpender(this);
        synchronized (worker) {
            this.worker.notify();
        }
    }

    @Override
    public synchronized void payMoneyToWorker() {
        int salarySize = helpDesk.getSalarySize();
        this.moneyAmount -= salarySize;
        worker.receiveMoneyFromSpender(salarySize);
        this.worker = null;
    }

    @Override
    public synchronized void getMoneyFromBank() {
        moneyAmount += helpDesk.getRandomBank().giveMoney();
    }

    @Override
    public synchronized void run() {
        while (!Thread.interrupted()) {
            if (moneyAmount < helpDesk.getSalarySize()) {
                getMoneyFromBank();
            }
            if (moneyAmount < helpDesk.getSalarySize()) {
                continue;
            }

            hireWorker();
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

        }
        LOGGER.info("Thread {} has been stopped.", name);
    }

    @Override
    public void setHelpDesk(HelpDesk helpDesk) {
        this.helpDesk = helpDesk;
    }
}