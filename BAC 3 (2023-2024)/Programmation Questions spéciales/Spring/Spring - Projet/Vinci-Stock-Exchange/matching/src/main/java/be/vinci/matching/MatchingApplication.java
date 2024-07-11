package be.vinci.matching;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MatchingApplication {

  public static void main(String[] args) {
    SpringApplication.run(MatchingApplication.class, args);
  }

}
