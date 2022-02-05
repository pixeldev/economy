package net.cosmogrp.economy.context;

import net.cosmogrp.economy.account.EconomyAccount;
import net.cosmogrp.economy.transaction.TransactionType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface TransactionContextBuilder {

    TransactionContextBuilder setSource(CommandSender source);

    TransactionContextBuilder setTarget(Player targetPlayer);

    TransactionContextBuilder setAmount(double amount);

    TransactionContextBuilder setType(TransactionType type);

    TransactionContextBuilder setSourceAccount(EconomyAccount sourceAccount);

    TransactionContextBuilder setTargetAccount(EconomyAccount targetAccount);

    TransactionContextBuilder setParentPath(String path);

    TransactionContext build();

}
