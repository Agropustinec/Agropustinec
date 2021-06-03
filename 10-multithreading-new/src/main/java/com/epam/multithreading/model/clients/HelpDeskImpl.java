package com.epam.multithreading.model.clients;

import com.epam.multithreading.controller.ReadConfigurationParameters;
import com.epam.multithreading.model.bank.Bank;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class HelpDeskImpl implements HelpDesk {
    private static final String LENGTH_OF_WORKING_ONE_WORKER_KEY = "lengthOfThWorkingOfOneWorker";
    private static final String MAX_AMOUNT_OF_MONEY_OF_WORKERS_KEY = "maxAmountOfMoneyOfWorkers";
    private static final String SALARY_SIZE_KEY = "salarySize";
    private static final String LENGTH_OF_LUNCH_KEY = "lengthOfLunch";
    private static final String INITIAL_AMOUNT_OF_MONEY_OF_WORKERS_AND_SPENDERS_KEY = "initialAmountOfMoneyOfWorkersAndSpenders";
    private static HelpDesk instance = new HelpDeskImpl();
    private ReadConfigurationParameters readConfigurationParameters = new ReadConfigurationParameters();
    private List<Bank> bankList;
    private List<Worker> workers;

    private HelpDeskImpl() {
    }

    public static HelpDesk getInstance() {
        if (instance == null) {
            synchronized(HelpDesk.class) {
                createInstance();
            }
        }
        return instance;
    }

    private static void createInstance() {
        HelpDesk inst = instance;
        if (inst == null) {
            synchronized(HelpDesk.class) {
                instance = new HelpDeskImpl();
            }
        }
    }

    @Override
    public Long getWorkerWorkingTime() {
        return readConfigurationParameters.getTime(LENGTH_OF_WORKING_ONE_WORKER_KEY);
    }

    @Override
    public int getWorkerMoneyLimit() {
        return readConfigurationParameters.getParameter(MAX_AMOUNT_OF_MONEY_OF_WORKERS_KEY);
    }

    @Override
    public int getSalarySize() {
        return readConfigurationParameters.getParameter(SALARY_SIZE_KEY);
    }

    @Override
    public Bank getRandomBank() {
        return getRandom(bankList);
    }

    @Override
    public Worker getRandomWorker() {
        return getRandom(workers);
    }

    private <T> T getRandom(List<T> list) {
        int index = ThreadLocalRandom.current().nextInt(list.size());
        return list.get(index);
    }

    @Override
    public long getLunchTime() {
        return readConfigurationParameters.getTime(LENGTH_OF_LUNCH_KEY);
    }

    @Override
    public int getCreditSize() {
        return readConfigurationParameters.getParameter(INITIAL_AMOUNT_OF_MONEY_OF_WORKERS_AND_SPENDERS_KEY);
    }

    @Override
    public void setBankList(List<Bank> bankList) {
        this.bankList = bankList;
    }

    @Override
    public void setWorkers(List<Worker> workers) {
        this.workers = workers;
    }
}
