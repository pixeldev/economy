package net.cosmogrp.economy.transaction.handle;

import net.cosmogrp.economy.context.TransactionContext;

public class DepositTransactionHandler implements TransactionHandler {
    @Override
    public boolean handle(TransactionContext context) {
        context.getTargetAccount().deposit(context.getOutputAmount());

        context.appendSourceDetail(
                "deposit-source",
                "%amount%", context.getHumanOutput(),
                "%target%", context.getTarget().getName()
        );

        context.appendTargetDetail(
                "deposit-target",
                "%amount%", context.getHumanOutput()
        );

        return true;
    }
}
