package net.cosmogrp.economy.account;

import java.text.DecimalFormat;

public interface EconomyAccount {

    DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#,###.##");

    /**
     * @return the id of the account
     */
    String getId();

    /**
     * @return current balance of the account
     */
    double getBalance();

    /**
     * @return format {@link #DECIMAL_FORMAT} to the balance
     */
    String getHumanBalance();

    /**
     * It just executes the withdrawal operation,
     * but it doesn't check if the account has
     * enough money, so it's up to the caller to
     * check if the account has enough money.
     *
     * You need to use {@link net.cosmogrp.economy.EconomyHandler}
     * to execute this operation safely.
     * @param amount amount to withdraw
     */
    void withdraw(double amount);

    /**
     * It just executes the deposit operation.
     *
     * You need to use {@link net.cosmogrp.economy.EconomyHandler}
     * to execute this operation safely.
     * @param amount amount to deposit
     */
    void deposit(double amount);

}
