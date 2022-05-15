package prosky.java.course6.danilkovich_bot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
@EnableScheduling
@SpringBootApplication
public class DanilkovichBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(DanilkovichBotApplication.class, args);
    }

}
