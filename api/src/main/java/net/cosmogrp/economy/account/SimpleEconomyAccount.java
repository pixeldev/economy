package net.cosmogrp.economy.account;

import java.beans.ConstructorProperties;

public class SimpleEconomyAccount implements EconomyAccount {

    private final String id;
    private double balance;

    public SimpleEconomyAccount(String id) {
        this(id, 0);
    }

    @ConstructorProperties({"id", "balance"})
    public SimpleEconomyAccount(String id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public String getHumanBalance() {
        return DECIMAL_FORMAT.format(balance);
    }

    @Override
    public void withdraw(double amount) {
        balance -= amount;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }
}
