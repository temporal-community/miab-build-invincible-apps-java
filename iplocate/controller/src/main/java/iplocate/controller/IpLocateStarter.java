package iplocate.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"iplocate"})
public class IpLocateStarter {
    public static void main(String[] args) {
        SpringApplication.run(IpLocateStarter.class, args);
    }
} 