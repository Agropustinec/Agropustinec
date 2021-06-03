package com.epam.multithreading.model.clients;

import com.epam.multithreading.controller.ReadConfigurationParameters;

public class WorkerFactory extends AbstractFactory<Worker> {
    private static final String WORKER_NAME = "Worker - ";
    private static final String INITIAL_AMOUNT_OF_MONEY_OF_WORKERS_AND_SPENDERS_KEY = "initialAmountOfMoneyOfWorkersAndSpenders";
    private ReadConfigurationParameters readConfigurationParameters = new ReadConfigurationParameters();
    private int index = 1;

    @Override
    public Worker create() {
        WorkerImpl worker = new WorkerImpl(readConfigurationParameters.getParameter(INITIAL_AMOUNT_OF_MONEY_OF_WORKERS_AND_SPENDERS_KEY));
        worker.setName(WORKER_NAME + index);
        index++;

        return worker;
    }
}
