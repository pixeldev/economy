package net.cosmogrp.economy.transaction.amount;

import net.cosmogrp.economy.account.EconomyAccount;

public interface TransactionAmount {

    /**
     * Transforms the input string into a double value.
     *
     * @param account the account to use if the input is a percentage
     * @return -1 if the input is invalid or the value is too high.
     */
    double parse(EconomyAccount account);

    /**
     * Creates a new TransactionAmount that is a
     * percentage of the given account.
     * <p>
     * Also, it can parse directly a number value.
     *
     * @param input the input string
     * @return the new TransactionAmount
     */
    static TransactionAmount text(String input) {
        return new TextTransactionAmount(input);
    }
}
