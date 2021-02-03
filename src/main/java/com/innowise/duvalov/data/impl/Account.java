package com.innowise.duvalov.data.impl;

import com.innowise.duvalov.data.Storage;

public class Account implements Storage {

    private final String name;
    private float money;

    public Account(String name, float money) {
        this.name = name;
        this.money = money;
    }

    @Override
    public float getCash() {
        return money;
    }

    @Override
    public void setCash(float cash) {
        money = cash;
    }

    public String getName() {
        return name;
    }
}
