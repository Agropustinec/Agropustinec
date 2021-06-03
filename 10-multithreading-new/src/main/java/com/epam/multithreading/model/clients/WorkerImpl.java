package com.epam.multithreading.model.clients;

import com.epam.multithreading.model.bank.Bank;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorkerImpl implements Worker {
    private static final Logger LOGGER = LogManager.getLogger(WorkerImpl.class);
    private String name;
    private HelpDesk helpDesk;
    private volatile int moneyAmount;
    private Spender spender;
    private boolean active = true;

    public WorkerImpl(int moneyAmount) {
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

    @Override
    public synchronized void receiveJobFromSpender(Spender spender) {

        while (this.spender != null) {
            try {
                wait();
            } catch (InterruptedException e) {
                active = false;
                Thread.currentThread().interrupt();
                break;
            }
        }

        this.spender = spender;
    }

    @Override
    public synchronized void receiveMoneyFromSpender(int money) {
        this.moneyAmount += money;
    }

    @Override
    public synchronized void putMoneyToBank() {
        if (moneyAmount < helpDesk.getWorkerMoneyLimit()) {
            return;
        }
        Bank bank = helpDesk.getRandomBank();
        bank.takeMoney(moneyAmount);
        moneyAmount = 0;
    }

    @Override
    public synchronized void run() {
        MAIN: while (active) {
            while (spender == null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    active = false;
                    break MAIN;
                }
            }
            try {
                Thread.sleep(helpDesk.getWorkerWorkingTime());
                spender.payMoneyToWorker();
                synchronized (spender) {
                    spender.notify();
                }
                spender = null;
            } catch (InterruptedException e) {
                active =false;
                break;
            }
            putMoneyToBank();

            active = active && !Thread.interrupted();
        }
        LOGGER.info("Thread {} has been stopped.", name);
    }

    @Override
    public void setHelpDesk(HelpDesk helpDesk) {
        this.helpDesk = helpDesk;
    }

    public void setName(String name) {
        this.name = name;
    }
}
