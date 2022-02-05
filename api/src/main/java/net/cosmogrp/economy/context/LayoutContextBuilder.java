package net.cosmogrp.economy.context;

import net.cosmogrp.economy.account.EconomyAccount;
import net.cosmogrp.economy.transaction.TransactionType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class LayoutContextBuilder<T extends TransactionContextBuilder>
        implements TransactionContextBuilder {

    protected CommandSender source;
    protected Player target;
    protected double amount;
    protected TransactionType type;
    protected EconomyAccount sourceAccount;
    protected EconomyAccount targetAccount;
    protected String parentPath;

    protected LayoutContextBuilder() {}

    @Override
    public T setSource(CommandSender sourcePlayer) {
        this.source = sourcePlayer;
        return back();
    }

    @Override
    public T setTarget(Player targetPlayer) {
        this.target = targetPlayer;
        return back();
    }

    @Override
    public T setAmount(double amount) {
        this.amount = amount;
        return back();
    }

    @Override
    public T setType(TransactionType type) {
        this.type = type;
        return back();
    }

    @Override
    public T setSourceAccount(EconomyAccount sourceAccount) {
        this.sourceAccount = sourceAccount;
        return back();
    }

    @Override
    public T setTargetAccount(EconomyAccount targetAccount) {
        this.targetAccount = targetAccount;
        return back();
    }

    @Override
    public T setParentPath(String parentPath) {
        this.parentPath = parentPath;
        return back();
    }

    protected abstract T back();

}
