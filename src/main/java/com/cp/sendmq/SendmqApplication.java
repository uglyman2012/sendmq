package com.cp.sendmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.cp")
public class SendmqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SendmqApplication.class, args);
    }

}
