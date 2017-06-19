package mobile.app.standalone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableAutoConfiguration
@ComponentScan("mobile.app")
@EnableMongoRepositories("mobile.app.repository")
public class StandaloneApp {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(StandaloneApp.class, args);
	}
}
