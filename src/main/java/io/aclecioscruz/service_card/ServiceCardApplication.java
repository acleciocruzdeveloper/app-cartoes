package io.aclecioscruz.service_card;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class ServiceCardApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceCardApplication.class, args);
    }

}
