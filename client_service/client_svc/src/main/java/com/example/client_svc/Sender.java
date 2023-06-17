package com.example.client_svc;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.client_svc.models.entities.Client;
import com.example.client_svc.services.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

@Component
@RestController
public class Sender implements CommandLineRunner {

    static final String topicExchangeName = "eo-exchange";

    private final RabbitTemplate rabbitTemplate;
    private final ClientService clientService;

    @Autowired
    public Sender(RabbitTemplate rabbitTemplate, ClientService clientService) {
        this.rabbitTemplate = rabbitTemplate;
        this.clientService = clientService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
    }

    @GetMapping("/send/{message}")
    public String sendMessage(@PathVariable String message) {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(topicExchangeName, "foo.bar.baz", message);
        return "Message sent: " + message;
    }

    @PostMapping("/client/register")
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @PutMapping("/client/data")
    public Client updateClientData(@RequestBody Client client) {
        return clientService.updateClientData(client);
    }

    @GetMapping("/client/list")
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }
}
