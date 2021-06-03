package com.epam.multithreading.controller;

import com.epam.multithreading.model.bank.Bank;
import com.epam.multithreading.model.bank.BankImpl;
import com.epam.multithreading.model.clients.*;
import com.epam.multithreading.model.media.Media;
import com.epam.multithreading.model.media.MediaImpl;
import com.epam.multithreading.model.media.Wiretapping;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Controller implements InitHelper {
    private static final String TREAD_MEDIA_NAME = "Media - 1";
    private static final String BANK_NAME = "Bank - ";
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);
    private static final String  INITIAL_AMOUNT_OF_MONEY_IN_BANKS_KEY = "initialAmountOfMoneyInBanks";

    private ReadConfigurationParameters readConfigurationParameters = new ReadConfigurationParameters();
    private List<Bank> banks;
    private List<Worker> workers;
    private List<Spender> spenders;
    private Media media;

    @Override
    public List<Bank> createBanks(int bankAmount) {
        List<Bank> banks = new ArrayList<>();
        for (int i = 0; i < bankAmount; i++) {
            BankImpl bank = new BankImpl(readConfigurationParameters.getParameter(INITIAL_AMOUNT_OF_MONEY_IN_BANKS_KEY));
            bank.setName(BANK_NAME + i);
            bank.setHelpDesk(HelpDeskImpl.getInstance());
            banks.add(bank);
        }
        return banks;
    }

    @Override
    public List<Worker> createWorkers(int workerAmount) {
        WorkerFactory workerFactory = new WorkerFactory();
        return workerFactory.createList(workerAmount);
    }

    @Override
    public List<Spender> createSpenders(int spenderAmount) {
        SpenderFactory spenderFactory = new SpenderFactory();
        return spenderFactory.createList(spenderAmount);
    }


    @Override
    public Media createMedia(List<Wiretapping> sneakyObjects) {
        return new MediaImpl(sneakyObjects);
    }

    @Override
    public HelpDesk createHelpDesk() {
        return HelpDeskImpl.getInstance();
    }

    @Override
    public void initBankSystemData(int bankAmount, int workerAmount, int spenderAmount) {
        banks = createBanks(bankAmount);
        workers = createWorkers(workerAmount);
        spenders = createSpenders(spenderAmount);

        HelpDesk helpDesk = createHelpDesk();
        helpDesk.setBankList(banks);
        helpDesk.setWorkers(workers);

        ArrayList<Wiretapping> sneakyObjects = new ArrayList<>();
        sneakyObjects.addAll(banks);
        sneakyObjects.addAll(workers);
        sneakyObjects.addAll(spenders);
        this.media = createMedia(sneakyObjects);
    }

    @Override
    public void startWorking(long workingTime) {
        List<Thread> threads = new LinkedList<>();
        for (Bank bank : banks) {
            threads.add(new Thread(bank, bank.getName()));
        }
        for (Worker worker : workers) {
            threads.add(new Thread(worker, worker.getName()));
        }
        for (Spender spender : spenders) {
            threads.add(new Thread(spender, spender.getName()));
        }
        treadsJob(workingTime, threads);
    }

    private void treadsJob(long workingTime, List<Thread> threads) {
        Thread mediaThread = new Thread(media, TREAD_MEDIA_NAME);
        mediaThread.setDaemon(true);
        mediaThread.start();

        try {
            for (Thread thread : threads) {
                thread.start();
            }

            Thread.sleep(workingTime);
            for (Thread thread : threads) {
                thread.interrupt();
            }
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            LOGGER.error("Interrupt error", e);
        }
    }

    @Override
    public Media getMedia() {
        return this.media;
    }
}
