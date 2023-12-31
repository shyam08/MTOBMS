package otbms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@Slf4j
@ComponentScan(basePackages = {"otbms.*"})
@EnableFeignClients
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        log.info("otbms server started..");
    }
}