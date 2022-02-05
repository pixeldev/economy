package net.cosmogrp.economy.transaction.executor;

import net.cosmogrp.economy.context.TransactionContext;
import net.cosmogrp.economy.message.MessageSender;
import net.cosmogrp.economy.transaction.TransactionDetails;
import net.cosmogrp.economy.transaction.TransactionType;
import net.cosmogrp.economy.transaction.handle.DepositTransactionHandler;
import net.cosmogrp.economy.transaction.handle.EnoughBalanceHandler;
import net.cosmogrp.economy.transaction.handle.NegativeAmountHandler;
import net.cosmogrp.economy.transaction.handle.TransactionHandler;
import net.cosmogrp.economy.transaction.handle.TransferTransactionHandler;
import net.cosmogrp.economy.transaction.handle.WithdrawTransactionHandler;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleTransactionExecutor<T extends TransactionContext>
        implements TransactionExecutor<T> {

    private final Map<TransactionType, List<TransactionHandler>> handlers;
    private final MessageSender messageSender;

    public SimpleTransactionExecutor(MessageSender messageSender) {
        this.messageSender = messageSender;
        this.handlers = new HashMap<>();
    }

    @Override
    public boolean execute(T context) {
        if (context == null) {
            return false;
        }

        List<TransactionHandler> handlers =
                this.handlers.get(context.getType());

        if (handlers == null) {
            return false;
        }

        boolean success = true;

        for (TransactionHandler handler : handlers) {
            if (!handler.handle(context)) {
                // some handler has stopped the
                // execution of the transaction
                success = false;
                break;
            }
        }

        CommandSender source = context.getSource();
        Player target = context.getTarget();

        sendDetails(source, context.getSourceDetails());

        if (target != null) {
            sendDetails(target, context.getTargetDetails());
        }

        return success;
    }

    @Override
    public void addHandlers(
            TransactionType transactionType,
            TransactionHandler... handlers
    ) {
        List<TransactionHandler> registeredHandlers = this.handlers
                .computeIfAbsent(transactionType, k -> new ArrayList<>());

        registeredHandlers.add(new NegativeAmountHandler());

        if (transactionType != TransactionType.DEPOSIT) {
            registeredHandlers.add(new EnoughBalanceHandler());
        }

        Collections.addAll(registeredHandlers, handlers);

        TransactionHandler executionHandler = null;

        switch (transactionType) {
            case DEPOSIT: {
                executionHandler = new DepositTransactionHandler();
                break;
            }
            case WITHDRAW: {
                executionHandler = new WithdrawTransactionHandler();
                break;
            }
            case TRANSFER: {
                executionHandler = new TransferTransactionHandler();
                break;
            }
        }

        registeredHandlers.add(executionHandler);
    }

    public void sendDetails(
            CommandSender source,
            TransactionDetails details
    ) {
        for (TransactionDetails.Detail detail : details.getDetails()) {
            messageSender.sendMessage(
                    source, detail.getPath(),
                    detail.getReplacements()
            );
        }
    }

}
