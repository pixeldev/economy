package net.cosmogrp.economy;

import net.cosmogrp.economy.account.EconomyAccount;
import net.cosmogrp.economy.context.TransactionContext;
import net.cosmogrp.economy.message.MessageSender;
import net.cosmogrp.economy.transaction.TransactionType;
import net.cosmogrp.economy.transaction.amount.TransactionAmount;
import net.cosmogrp.economy.transaction.executor.TransactionExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public abstract class AbstractEconomyHandler<T extends TransactionContext>
        implements EconomyHandler {

    private final TransactionExecutor<T> transactionExecutor;
    protected final MessageSender messageSender;

    protected final String identifier;

    public AbstractEconomyHandler(
            TransactionExecutor<T> transactionExecutor,
            MessageSender messageSender,
            String identifier
    ) {
        this.transactionExecutor = transactionExecutor;
        this.messageSender = messageSender;
        this.identifier = identifier;
    }

    @Override
    public boolean deposit(
            @Nullable CommandSender source,
            Player target,
            double amount
    ) {
        return execute(
                source, target,
                TransactionType.DEPOSIT, amount
        );
    }

    @Override
    public boolean withdraw(
            @Nullable CommandSender source,
            Player target,
            double amount
    ) {
        return execute(
                source, target,
                TransactionType.WITHDRAW, amount
        );
    }

    @Override
    public boolean transfer(
            Player source, Player target,
            TransactionAmount amount
    ) {
        return executeTransfer(
                source, target, amount,
                getAccount(source),
                getTargetAccount(source, target)
        );
    }

    @Override
    public void sendBalance(Player source) {
        EconomyAccount account = getAccount(source);

        if (account == null) {
            return;
        }

        messageSender.sendMessage(
                source, "balance",
                "%balance%", account.getBalance()
        );
    }

    @Override
    public boolean hasEnough(Player source, double amount) {
        EconomyAccount account = getAccount(source);

        if (account == null) {
            return false;
        }

        return account.getBalance() >= amount;
    }

    @Override
    public double getBalance(Player source) {
        EconomyAccount account = getAccount(source);

        if (account == null) {
            return -1;
        }

        return account.getBalance();
    }

    protected boolean executeTransfer(
            CommandSender source,
            @Nullable Player target,
            TransactionAmount amount,
            EconomyAccount sourceAccount,
            EconomyAccount targetAccount
    ) {
        if (sourceAccount == null || targetAccount == null) {
            return false;
        }

        double finalAmount = amount.parse(sourceAccount);

        if (finalAmount == -1) {
            return false;
        }

        return transactionExecutor.execute(createContext(
                source, target, finalAmount,
                TransactionType.TRANSFER,
                sourceAccount, targetAccount
        ));
    }

    private boolean execute(
            CommandSender source, Player target,
            TransactionType type, double amount
    ) {
        if (source == null) {
            source = defaultSource();
        }

        return transactionExecutor.execute(createContext(
                source, target, amount,
                type,
                null, getTargetAccount(source, target)
        ));
    }

    protected abstract CommandSender defaultSource();

    protected abstract T createContext(
            CommandSender source,
            Player target,
            double amount,
            TransactionType type,
            EconomyAccount sourceAccount,
            EconomyAccount targetAccount
    );

}
