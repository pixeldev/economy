package net.cosmogrp.economy.transaction.handle;

import net.cosmogrp.economy.account.EconomyAccount;
import net.cosmogrp.economy.context.TransactionContext;
import org.bukkit.entity.Player;

public class TransferTransactionHandler implements TransactionHandler {
    @Override
    public boolean handle(TransactionContext context) {
        EconomyAccount sourceAccount = context.getSourceAccount();

        if (sourceAccount == null) {
            // nothing should happen, just surrounding intellij warn
            return false;
        }

        EconomyAccount targetAccount = context.getTargetAccount();

        sourceAccount.withdraw(context.getInputAmount());
        targetAccount.deposit(context.getOutputAmount());

        Player target = context.getTarget();
        String outputAmount = context.getHumanOutput();

        if (target == null) {
            context.appendSourceDetail(
                    "transfer-to-" + targetAccount.getId(),
                    "%amount%", outputAmount
            );
        } else {
            context.appendSourceDetail(
                    "transfer-to-player",
                    "%amount%", outputAmount,
                    "%target%", target.getName()
            );

            context.appendTargetDetail(
                    "transfer-to-player-target",
                    "%amount%", outputAmount,
                    "%source%", context.getSource().getName()
            );
        }

        return true;
    }
}
