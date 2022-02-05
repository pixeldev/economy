package net.cosmogrp.economy.transaction.executor;

import net.cosmogrp.economy.context.TransactionContext;
import net.cosmogrp.economy.transaction.TransactionType;
import net.cosmogrp.economy.transaction.handle.TransactionHandler;

/**
 * Represents a transaction executor.
 * This class must apply the interceptors & handlers
 * to the transaction, so we can execute it safely.
 *
 * @param <T> The type of transaction context.
 */
public interface TransactionExecutor<T extends TransactionContext> {

    /**
     * Will execute the transaction with the specified context,
     * it will be handled by the handlers and interceptors
     * registered to the specified transaction type.
     *
     * @param context the context.
     * @return if the transaction was successful.
     */
    boolean execute(T context);

    /**
     * It will add handlers to the specified {@link TransactionType}.
     *
     * @param transactionType the transaction type.
     * @param handlers        the handlers.
     */
    void addHandlers(
            TransactionType transactionType,
            TransactionHandler... handlers
    );

}
