package net.cosmogrp.economy.transaction.amount;

import net.cosmogrp.economy.account.EconomyAccount;
import org.bukkit.util.NumberConversions;

public class TextTransactionAmount implements TransactionAmount {

    private final String input;

    public TextTransactionAmount(String input) {
        this.input = input;
    }

    public double parse(EconomyAccount account) {
        // check if the input is a percentage
        int percentageIndex = input.indexOf('%');

        if (percentageIndex > 0) {
            double percentage = NumberConversions.toDouble(
                    input.substring(0, percentageIndex)
            );

            if (percentage <= 0) {
                return -1;
            }

            // apply the percentage with the given account
            return account.getBalance() * (percentage / 100);
        }

        return NumberConversions.toDouble(input);
    }

}
