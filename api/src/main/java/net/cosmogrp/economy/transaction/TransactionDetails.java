package net.cosmogrp.economy.transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionDetails {

    private final List<Detail> details;

    public TransactionDetails() {
        details = new ArrayList<>();
    }

    public void append(String path, Object... replacements) {
        details.add(new Detail(path, replacements));
    }

    public Iterable<Detail> getDetails() {
        return details;
    }

    public static class Detail {

        private final String path;
        private final Object[] replacements;

        public Detail(String path, Object[] replacements) {
            this.path = path;
            this.replacements = replacements;
        }

        public String getPath() {
            return path;
        }

        public Object[] getReplacements() {
            return replacements;
        }
    }

}
