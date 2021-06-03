package com.epam.multithreading;

import com.epam.multithreading.controller.Controller;
import com.epam.multithreading.controller.ReadConfigurationParameters;

public class AppRunner {
    private static final String NUMBER_OF_BANKS_KEY = "numberOfBanks";
    private static final String NUMBER_OF_WORKERS_KEY = "numberOfWorkers";
    private static final String NUMBER_OF_SPENDERS_KEY = "numberOfSpenders";
    private static final String LENGTH_OF_WORKING_DAY_KEY = "lengthOfWorkingDay";
    public static void main(String[] args) {
        AppRunner appRunner = new AppRunner();
        appRunner.run();
    }

    public void run() {
        Controller controller = new Controller();
        ReadConfigurationParameters readConfigurationParameters = new ReadConfigurationParameters();
        controller.initBankSystemData(
                readConfigurationParameters.getParameter(NUMBER_OF_BANKS_KEY),
                readConfigurationParameters.getParameter(NUMBER_OF_WORKERS_KEY),
                readConfigurationParameters.getParameter(NUMBER_OF_SPENDERS_KEY));
        controller.startWorking(readConfigurationParameters.getParameter(LENGTH_OF_WORKING_DAY_KEY));
    }
}
