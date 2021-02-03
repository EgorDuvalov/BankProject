package com.innowise.duvalov.data.impl;

import com.innowise.duvalov.data.Storage;

public class Cashbox implements Storage {
    private static int generator =0;
    private final int id;
    private float cash = 2000;
    private float neededCash = 0;

    public Cashbox() {
        id = generator++;
    }

    public int getId() {
        return id;
    }

    public float getCash() {
        return cash;
    }

    public void setCash(float cash) {
        this.cash = cash;
    }

    public void setNeededCash(float needCash) {
        this.neededCash = needCash;
    }

    public float getNeededCash() {
        return neededCash;
    }
}
