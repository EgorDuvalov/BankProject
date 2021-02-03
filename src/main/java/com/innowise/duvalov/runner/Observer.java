package com.innowise.duvalov.runner;

import com.innowise.duvalov.data.impl.Account;
import com.innowise.duvalov.data.impl.Cashbox;

import java.util.concurrent.BlockingQueue;

public class Observer extends Thread {
    BlockingQueue<Cashbox> cashboxes;
    private final Account mainStorage;
    private static final float MIN_CASH = 1000;
    private static final float MAX_CASH = 10000;

    public Observer(BlockingQueue<Cashbox> cashboxes, float storageCapacity) {
        mainStorage = new Account("storage", storageCapacity);
        this.cashboxes = cashboxes;
    }

    @Override
    public void run() {
        float cash;
        Cashbox cashbox;
        while (true) {
            try {
                cashbox = cashboxes.take();
                cash = cashbox.getCash();
                if (cashbox.getNeededCash() != 0) {
                    mainStorage.setCash(mainStorage.getCash() - cashbox.getNeededCash());
                    cashbox.setNeededCash(0);
                    System.out.println("Window #" + cashbox.getId() + " gets more cash");
                } else if (cash < MIN_CASH || cash > MAX_CASH) {
                    mainStorage.setCash(mainStorage.getCash() - (5000 - cash));
                    System.out.println("Window #" + cashbox.getId() + " cashbox renewed");
                }
                cashboxes.add(cashbox);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

