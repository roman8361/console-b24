package ru.kravchenko.sb.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.kravchenko.sb.api.BootstrapService;

@Service
@PropertySource("classpath:application.properties")
public class BootstrapServiceImpl implements BootstrapService {

    @Value("${password}")
    private String password;

    @Override
    public void init() {
        System.out.println(password);
    }

}
