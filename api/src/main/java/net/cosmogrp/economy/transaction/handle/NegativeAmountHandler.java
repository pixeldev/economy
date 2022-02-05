package net.cosmogrp.economy.transaction.handle;

import net.cosmogrp.economy.context.TransactionContext;

public class NegativeAmountHandler implements TransactionHandler {
    @Override
    public boolean handle(TransactionContext context) {
        if (context.getInputAmount() < 0) {
            context.appendSourceDetail("negative-amount");
            return false;
        }

        return true;
    }
}
