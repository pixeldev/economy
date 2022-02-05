package net.cosmogrp.economy;

import net.cosmogrp.economy.account.EconomyAccount;
import net.cosmogrp.economy.transaction.amount.TransactionAmount;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public interface EconomyHandler {

    @Nullable EconomyAccount getAccount(Player player);

    @Nullable EconomyAccount getTargetAccount(
            CommandSender sender,
            Player target
    );

    boolean deposit(
            @Nullable CommandSender source, Player target,
            double amount
    );

    default boolean deposit(Player target, double amount) {
        return deposit(null, target, amount);
    }

    boolean withdraw(
            @Nullable CommandSender source, Player target,
            double amount
    );

    default boolean withdraw(Player target, double amount) {
        return withdraw(null, target, amount);
    }

    boolean transfer(
            Player source, Player target,
            TransactionAmount amount
    );

    boolean hasEnough(Player source, double amount);

    double getBalance(Player source);

    void sendBalance(Player source);

}
