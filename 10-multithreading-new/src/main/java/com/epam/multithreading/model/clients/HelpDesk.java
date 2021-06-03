package com.epam.multithreading.model.clients;

import com.epam.multithreading.model.bank.Bank;

import java.util.List;

public interface HelpDesk {

    Long getWorkerWorkingTime();

    int getWorkerMoneyLimit();

    int getSalarySize();

    Bank getRandomBank();

    Worker getRandomWorker();

    long getLunchTime();

    int getCreditSize();

    void setBankList(List<Bank> bankList);

    void setWorkers(List<Worker> workers);
}
