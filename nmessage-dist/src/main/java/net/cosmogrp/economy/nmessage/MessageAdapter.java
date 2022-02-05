package net.cosmogrp.economy.nmessage;

import me.yushust.message.MessageHandler;
import net.cosmogrp.economy.message.MessageSender;

public final class MessageAdapter {

    private MessageAdapter() {
        throw new UnsupportedOperationException();
    }

    public static MessageSender createMessageSender(
            MessageHandler messageHandler
    ) {
        return messageHandler::send;
    }

}
