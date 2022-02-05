package net.cosmogrp.economy.message;

import org.bukkit.command.CommandSender;

public interface MessageSender {

    void sendMessage(
            CommandSender source,
            String path,
            Object... replacements
    );

}
