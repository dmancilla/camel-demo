package cl.dman.camel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CamelDemoApplication {

    public static void main(String[] args) {
        System.setProperty("hawtio.authenticationEnabled", "false");
        SpringApplication.run(CamelDemoApplication.class, args);
    }
}
