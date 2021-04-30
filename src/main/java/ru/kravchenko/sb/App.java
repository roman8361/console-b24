package ru.kravchenko.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.kravchenko.sb.api.BootstrapService;


@SpringBootApplication
public class App {

    public static void main(String[] args) {
        BootstrapService bootstrapService = SpringApplication.run(App.class, args).getBean(BootstrapService.class);
        bootstrapService.init();
    }

}
