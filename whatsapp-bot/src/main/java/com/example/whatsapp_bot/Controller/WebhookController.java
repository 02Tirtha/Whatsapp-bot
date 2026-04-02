package com.example.whatsapp_bot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.whatsapp_bot.Model.WhatsAppMessage;
import com.example.whatsapp_bot.Model.WhatsAppResponse;
import com.example.whatsapp_bot.Service.ChatbotService;

@RestController
public class WebhookController {

    @Autowired
    private ChatbotService chatbotService;

    @PostMapping("/webhook")
    public ResponseEntity<WhatsAppResponse> receiveMessage(@RequestBody WhatsAppMessage message) {
        WhatsAppResponse response = chatbotService.processMessage(message);
        return ResponseEntity.ok(response);
    }

    // Optional: health check endpoint
    @GetMapping("/health")
    public String health() {
        return "WhatsApp Bot is running!";
    }
}