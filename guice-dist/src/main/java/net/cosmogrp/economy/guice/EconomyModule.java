package net.cosmogrp.economy.guice;

import com.google.inject.Binder;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import net.cosmogrp.economy.EconomyHandler;
import net.cosmogrp.economy.context.TransactionContext;
import net.cosmogrp.economy.transaction.executor.SimpleTransactionExecutor;
import net.cosmogrp.economy.transaction.executor.TransactionExecutor;

public interface EconomyModule extends Binder {

    default void bindEconomyHandler(
            String name,
            Class<? extends EconomyHandler> handler
    ) {
        Named named = Names.named(name);

        bind(EconomyHandler.class)
                .annotatedWith(named)
                .to(handler)
                .in(Singleton.class);

        bind(new TypeLiteral<TransactionExecutor<TransactionContext>>() {})
                .annotatedWith(named)
                .to(new TypeLiteral<InjectedTransactionExecutor<TransactionContext>>() {})
                .in(Singleton.class);
    }

}
