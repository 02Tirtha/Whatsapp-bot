package com.example.whatsapp_bot.Model;

public class WhatsAppResponse {
    private String to;
    private String reply;

    public WhatsAppResponse(String to, String reply) {
        this.to = to;
        this.reply = reply;
    }

    public String getTo() { return to; }
    public String getReply() { return reply; }
}