package com.epam.multithreading.controller;

import com.epam.multithreading.model.bank.Bank;
import com.epam.multithreading.model.clients.HelpDesk;
import com.epam.multithreading.model.clients.Spender;
import com.epam.multithreading.model.clients.Worker;
import com.epam.multithreading.model.media.Media;
import com.epam.multithreading.model.media.Wiretapping;

import java.util.List;

public interface InitHelper {
    List<Bank> createBanks(int bankAmount);

    List<Worker> createWorkers(int workerAmount);

    List<Spender> createSpenders(int spenderAmount);

    Media createMedia(List<Wiretapping> sneakyObjects);

    HelpDesk createHelpDesk();

    void initBankSystemData(int bankAmount, int workerAmount, int spenderAmount);

    void startWorking(long workingTime);

    Media getMedia();
}
