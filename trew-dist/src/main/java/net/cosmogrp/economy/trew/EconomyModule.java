package net.cosmogrp.economy.trew;

import net.cosmogrp.economy.EconomyHandler;
import net.cosmogrp.economy.context.TransactionContext;
import net.cosmogrp.economy.transaction.executor.TransactionExecutor;
import team.unnamed.inject.AbstractModule;
import team.unnamed.inject.key.TypeReference;

public class EconomyModule extends AbstractModule {

    public void bindEconomyHandler(
            String name,
            Class<? extends EconomyHandler> handler
    ) {
        bind(EconomyHandler.class)
                .named(name)
                .to(handler)
                .singleton();

        bind(new TypeReference<TransactionExecutor<TransactionContext>>() {})
                .named(name)
                .to(new TypeReference<InjectedTransactionExecutor<TransactionContext>>() {})
                .singleton();
    }

}
