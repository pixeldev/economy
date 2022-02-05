package net.cosmogrp.economy.context;

import net.cosmogrp.economy.account.EconomyAccount;
import net.cosmogrp.economy.transaction.TransactionDetails;
import net.cosmogrp.economy.transaction.TransactionType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static net.cosmogrp.economy.account.EconomyAccount.DECIMAL_FORMAT;

public class DefaultTransactionContext
        implements TransactionContext {

    private final CommandSender source;
    private final Player target;

    private final double inputAmount;
    private final TransactionType type;

    private final EconomyAccount sourceAccount;
    private final EconomyAccount targetAccount;

    private final TransactionDetails sourceDetails;
    private final TransactionDetails targetDetails;
    private final String parentPath;

    private String humanOutput;
    private double outputAmount;

    public DefaultTransactionContext(
            CommandSender source, Player target,
            double inputAmount, TransactionType type,
            EconomyAccount sourceAccount,
            EconomyAccount targetAccount,
            String parentPath
    ) {
        this.source = source;
        this.target = target;
        this.inputAmount = inputAmount;
        this.type = type;
        this.sourceAccount = sourceAccount;
        this.targetAccount = targetAccount;
        this.parentPath = parentPath;
        this.sourceDetails = new TransactionDetails();
        this.targetDetails = new TransactionDetails();
        setOutputAmount(inputAmount);
    }

    @Override
    public CommandSender getSource() {
        return source;
    }

    @Override
    public Player getTarget() {
        return target;
    }

    @Override
    public double getInputAmount() {
        return inputAmount;
    }

    @Override
    public TransactionType getType() {
        return type;
    }

    @Override
    public String format(double amount) {
        return DECIMAL_FORMAT.format(amount);
    }

    @Override
    public String getHumanOutput() {
        return humanOutput;
    }

    @Override
    public EconomyAccount getSourceAccount() {
        return sourceAccount;
    }

    @Override
    public EconomyAccount getTargetAccount() {
        return targetAccount;
    }

    @Override
    public TransactionDetails getSourceDetails() {
        return sourceDetails;
    }

    @Override
    public void appendSourceDetail(String path, Object... replacements) {
        sourceDetails.append(parentPath + "." + path, replacements);
    }

    @Override
    public TransactionDetails getTargetDetails() {
        return targetDetails;
    }

    @Override
    public void appendTargetDetail(String path, Object... replacements) {
        targetDetails.append(parentPath + "." + path, replacements);
    }

    @Override
    public double getOutputAmount() {
        return outputAmount;
    }

    @Override
    public void setOutputAmount(double outputAmount) {
        this.outputAmount = outputAmount;
        this.humanOutput = DECIMAL_FORMAT.format(outputAmount);
    }

    @Override
    public double getSourceBalance() {
        if (sourceAccount == null) {
            return targetAccount.getBalance();
        }

        return sourceAccount.getBalance();
    }
}
