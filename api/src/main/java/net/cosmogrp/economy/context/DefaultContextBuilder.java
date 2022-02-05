package net.cosmogrp.economy.context;

public class DefaultContextBuilder
        extends LayoutContextBuilder<DefaultContextBuilder> {

    protected DefaultContextBuilder() {}

    @Override
    protected DefaultContextBuilder back() {
        return this;
    }

    @Override
    public DefaultTransactionContext build() {
        return new DefaultTransactionContext(
                source, target,
                amount, type,
                sourceAccount, targetAccount,
                parentPath
        );
    }
}
