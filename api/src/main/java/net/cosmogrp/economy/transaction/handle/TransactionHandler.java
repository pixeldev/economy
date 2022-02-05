package net.cosmogrp.economy.transaction.handle;

import net.cosmogrp.economy.context.TransactionContext;

/**
 * This class represents a transaction handler
 * which is responsible for handling a transaction
 * and deciding whether the transaction is valid,
 * or should be rejected.
 */
public interface TransactionHandler {

    /**
     * This method is responsible for handling a transaction
     *
     * @param context the transaction context
     * @return true if the handler has been executed successfully,
     * false otherwise and will stop the transaction execution.
     */
    boolean handle(TransactionContext context);

}
