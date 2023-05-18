package sg.edu.nus.iss.day13demo;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.List;
import sg.edu.nus.iss.day13demo.utility.Utility;

@SpringBootApplication
public class Day13demoApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(Day13demoApplication.class);

		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);

		List<String> opsVal = appArgs.getOptionValues("dataDir");
		System.out.println(opsVal);

		if (opsVal != null) {
			Utility.createDir(opsVal.get(0));
		} else {
			System.exit(1);
		}

		app.run(args);
	}


}