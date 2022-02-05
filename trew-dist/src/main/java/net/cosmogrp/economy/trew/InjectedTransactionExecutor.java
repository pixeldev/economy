package net.cosmogrp.economy.trew;

import net.cosmogrp.economy.context.TransactionContext;
import net.cosmogrp.economy.message.MessageSender;
import net.cosmogrp.economy.transaction.executor.SimpleTransactionExecutor;

import javax.inject.Inject;

public class InjectedTransactionExecutor<T extends TransactionContext>
        extends SimpleTransactionExecutor<T> {
    public @Inject
    InjectedTransactionExecutor(MessageSender messageSender) {
        super(messageSender);
    }
}
