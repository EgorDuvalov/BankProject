package com.innowise.duvalov.runner;

import com.innowise.duvalov.data.impl.Account;
import com.innowise.duvalov.data.impl.Cashbox;

import java.util.Locale;
import java.util.concurrent.BlockingQueue;

public class Client extends Thread {

    private static int generator = 0;
    private int id;
    private final String name;
    private final Account account;
    private final BlockingQueue<Cashbox> cashboxes;

    public Client(String name, BlockingQueue<Cashbox> cashboxes, float money) {
        this.name = name;
        this.id = generator++;
        this.cashboxes = cashboxes;
        this.account = new Account(name.toLowerCase(Locale.ROOT), money);
    }

    @Override
    public void run() {
        new MoneyTransactionHandler(this,cashboxes);
    }

    public Account getAccount() {
        return account;
    }
}
