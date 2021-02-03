package com.innowise.duvalov.runner;

import com.innowise.duvalov.data.impl.Cashbox;
import com.innowise.duvalov.exceptions.NotEnoughMoneyException;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class MoneyTransactionHandler {
    private final Client client;
    private final BlockingQueue<Cashbox> cashboxes;

    public MoneyTransactionHandler(Client client, BlockingQueue<Cashbox> cashboxes) {
        Cashbox cashbox = null;
        this.client = client;
        this.cashboxes = cashboxes;
        try {
            operationSelection();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void operationSelection() throws InterruptedException {
        boolean repeat = true;
        //while (repeat) {
        System.out.println("============================");
        System.out.println("Please, select an operation:\n" +
                "1. Withdraw\n" +
                "2. Top Up\n" +
                "3. Pay a bill\n" +
                "4. Exchange\n" +
                "5. Transfer\n" +
                "6. Exit.");
        System.out.println("============================");
        Cashbox cashbox = cashboxes.take();
        int choice = new Random().nextInt(4);
        switch (choice) {
            case 1 -> withdraw(cashbox);
            case 2 -> topUp();
            case 3 -> pay(cashbox);
            case 4 -> exchange();
            case 5 -> transfer();
            case 6 -> showBalance();
            //case 6 -> repeat = false;
            default -> System.out.println("No such operation");
        }
        cashboxes.add(cashbox);
        //}
    }

    public void withdraw(Cashbox cashbox) throws InterruptedException {
        //TODO asking more money than actually is
        int sum = new Random().nextInt(5000);
        float clientCash = client.getAccount().getCash();
        if (clientCash < sum) {
            throw new NotEnoughMoneyException();
        }
        float cashInBox = cashbox.getCash();
        if (cashInBox < sum) {
            cashbox.setNeededCash(cashInBox - sum + 5000);
            cashboxes.add(cashbox);
        }
        cashboxes.take();
        client.getAccount().setCash(clientCash - sum);
        System.out.println(client.getName() + " withdrawn " + sum + "$ from account");
    }

    public void topUp() {
        int sum = new Random().nextInt(10000);
        System.out.println(client.getName() + " put " + sum + "$ into account ");
    }

    public void pay(Cashbox cashbox) throws InterruptedException {
        withdraw(cashbox);
    }

    public void exchange() {
        //TODO
        System.out.println(client.getName() + ": Successful exchange");
    }

    public void transfer() {
        //TODO
        System.out.println(client.getName() + ": Successful transfer");
    }

    private void showBalance() {
        System.out.println("Current balance of " + client.getName() + ": " + client.getAccount().getCash());
    }
}
