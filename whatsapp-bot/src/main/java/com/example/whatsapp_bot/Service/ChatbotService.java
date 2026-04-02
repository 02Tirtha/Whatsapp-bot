package com.example.whatsapp_bot.Service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.whatsapp_bot.Model.WhatsAppMessage;
import com.example.whatsapp_bot.Model.WhatsAppResponse;

@Service
public class ChatbotService {

    private static final Logger logger = LoggerFactory.getLogger(ChatbotService.class);

    public WhatsAppResponse processMessage(WhatsAppMessage incoming) {
        String msg = incoming.getMessage();
        String from = incoming.getFrom();

        // Log the incoming message
        logger.info("Received message from [{}]: {}", from, msg);

        // Predefined reply logic
        String reply;
        if (msg == null) {
            reply = "Sorry, I didn't understand that.";
        } else {
            switch (msg.trim().toLowerCase()) {
                case "hi":
                case "hello":
                    reply = "Hello! How can I help you?";
                    break;
                case "bye":
                case "goodbye":
                    reply = "Goodbye! Have a great day!";
                    break;
                default:
                    reply = "Sorry, I didn't understand: " + msg;
            }
        }

        logger.info("Sending reply to [{}]: {}", from, reply);
        return new WhatsAppResponse(from, reply);
    }
}