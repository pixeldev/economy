package net.cosmogrp.economy;

import net.cosmogrp.economy.account.EconomyAccount;
import net.cosmogrp.economy.context.TransactionContext;
import net.cosmogrp.economy.message.MessageSender;
import net.cosmogrp.economy.transaction.TransactionType;
import net.cosmogrp.economy.transaction.executor.TransactionExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class DefaultEconomyHandler
        extends AbstractEconomyHandler<TransactionContext> {

    public DefaultEconomyHandler(
            TransactionExecutor<TransactionContext> transactionExecutor,
            MessageSender messageSender,
            String identifier
    ) {
        super(transactionExecutor, messageSender, identifier);
    }

    @Override
    protected TransactionContext createContext(
            CommandSender source, Player target,
            double amount, TransactionType type,
            EconomyAccount sourceAccount,
            EconomyAccount targetAccount
    ) {
        return TransactionContext.builder()
                .setAmount(amount)
                .setType(type)
                .setParentPath(identifier)
                .setSource(source)
                .setTarget(target)
                .setSourceAccount(sourceAccount)
                .setTargetAccount(targetAccount)
                .build();
    }
}
