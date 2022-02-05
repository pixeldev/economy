package net.cosmogrp.economy.transaction.handle;

import net.cosmogrp.economy.context.TransactionContext;

public class EnoughBalanceHandler implements TransactionHandler {
    @Override
    public boolean handle(TransactionContext context) {
        if (context.getSourceBalance() < context.getInputAmount()) {
            context.appendSourceDetail("insufficient-funds");
            return false;
        }

        return true;
    }
}
