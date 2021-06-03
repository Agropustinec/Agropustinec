package com.epam.multithreading.model.clients;

import com.epam.multithreading.controller.ReadConfigurationParameters;

public class SpenderFactory extends AbstractFactory<Spender> {
    private static final String SPENDER_NAME = "Spender - ";
    private static final String INITIAL_AMOUNT_OF_MONEY_OF_WORKERS_AND_SPENDERS_KEY = "initialAmountOfMoneyOfWorkersAndSpenders";
    private ReadConfigurationParameters readConfigurationParameters = new ReadConfigurationParameters();
    private int index = 1;

    @Override
    public Spender create() {
        SpenderImpl spender = new SpenderImpl(readConfigurationParameters.getParameter(INITIAL_AMOUNT_OF_MONEY_OF_WORKERS_AND_SPENDERS_KEY));
        spender.setName(SPENDER_NAME + index);
        index++;

        return spender;
    }
}
