package net.cosmogrp.economy.trew;

import me.yushust.inject.Binder;
import me.yushust.inject.key.TypeReference;
import me.yushust.inject.scope.Scopes;
import net.cosmogrp.economy.EconomyHandler;
import net.cosmogrp.economy.context.TransactionContext;
import net.cosmogrp.economy.transaction.executor.TransactionExecutor;

public interface EconomyModule extends Binder {

    default void bindEconomyHandler(
            String name,
            Class<? extends EconomyHandler> handler
    ) {
        bind(EconomyHandler.class)
                .named(name)
                .to(handler)
                .in(Scopes.SINGLETON);

        bind(new TypeReference<TransactionExecutor<TransactionContext>>() {})
                .named(name)
                .to(new TypeReference<InjectedTransactionExecutor<TransactionContext>>() {})
                .in(Scopes.SINGLETON);
    }

}
