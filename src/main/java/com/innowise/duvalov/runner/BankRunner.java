package com.innowise.duvalov.runner;

import com.innowise.duvalov.data.impl.Cashbox;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BankRunner {
    private static final float DEFAULT_STORAGE_CAPACITY = 100000;
    private static final int CASHIER_AMOUNT = 2;
    private static BlockingQueue<Cashbox> cashboxes = new ArrayBlockingQueue<>(CASHIER_AMOUNT);


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < CASHIER_AMOUNT; i++) {
            cashboxes.add(new Cashbox());
        }

        Client client1 = new Client("1", cashboxes, 5000);
        Client client2 = new Client("2", cashboxes, 5000);
        Client client3 = new Client("3", cashboxes, 5000);
        Client client4 = new Client("4", cashboxes, 5000);
        Client client5 = new Client("5", cashboxes, 5000);
        Client client6 = new Client("6", cashboxes, 5000);
        Client client7 = new Client("7", cashboxes, 5000);

        Observer observer = new Observer(cashboxes, DEFAULT_STORAGE_CAPACITY);
        observer.start();

        for (Client client : Arrays.asList(client1, client2, client3, client4, client5, client6, client7)) {
            client.start();
        }
    }
}
