package airtickets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
public class AirticketsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AirticketsApplication.class, args);
		// mora
	}

}

