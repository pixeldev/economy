package net.cosmogrp.economy.context;

import net.cosmogrp.economy.account.EconomyAccount;
import net.cosmogrp.economy.transaction.TransactionDetails;
import net.cosmogrp.economy.transaction.TransactionType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

/**
 * Represents the context of a transaction.
 * We need this to be able to send the transaction
 * details to the player and apply the needed
 * handlers & interceptors since we use Chain of Responsibility
 * pattern.
 */
public interface TransactionContext {

    CommandSender getSource();

    @Nullable Player getTarget();

    TransactionType getType();

    String format(double amount);

    String getHumanOutput();

    double getInputAmount();

    double getOutputAmount();

    void setOutputAmount(double amount);

    double getSourceBalance();

    @Nullable EconomyAccount getSourceAccount();

    EconomyAccount getTargetAccount();

    TransactionDetails getSourceDetails();

    TransactionDetails getTargetDetails();

    /**
     * Appends a new detail to source details.
     * It will be translated to the source language.
     *
     * @param path         the path of the message
     * @param replacements the replacements for the message
     */
    void appendSourceDetail(String path, Object... replacements);

    /**
     * Appends a new detail to target details.
     * It will be translated to the target language.
     *
     * @param path         the path of the message
     * @param replacements the replacements for the message
     */
    void appendTargetDetail(String path, Object... replacements);

    static TransactionContextBuilder builder() {
        return new DefaultContextBuilder();
    }

}
