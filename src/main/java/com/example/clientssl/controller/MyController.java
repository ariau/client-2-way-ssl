package com.example.clientssl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/client")
public class MyController {

    /**
     * This property bean is created in  SslConfig#webClient
     **/
    @Autowired
    WebClient webClient;

    /**
     * This controller method invokes the server using the wired bean
     * {@link this#webClient} and returns back the secured data from the server
     *
     * @return
     * @throws InterruptedException
     */
    @GetMapping("/https")
    public String gatherDataFromServer() {
        Mono<String> dateFromServer = webClient.get()
                .uri("https://127.0.0.1:8082/server")
                .retrieve().bodyToMono(String.class);
        return dateFromServer.block();
    }
}
