package net.cosmogrp.economy.transaction.handle;

import net.cosmogrp.economy.context.TransactionContext;

public class WithdrawTransactionHandler implements TransactionHandler {
    @Override
    public boolean handle(TransactionContext context) {
        context.getTargetAccount().withdraw(context.getOutputAmount());

        context.appendSourceDetail(
                "withdraw-source",
                "%amount%", context.getHumanOutput(),
                "%target%", context.getTarget().getName()
        );

        context.appendTargetDetail(
                "withdraw-target",
                "%amount%", context.getHumanOutput()
        );

        return true;
    }
}
