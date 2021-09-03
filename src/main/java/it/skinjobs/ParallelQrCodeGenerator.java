package it.skinjobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import it.skinjobs.main.*;
import it.skinjobs.utils.FileStorageProperties;
import it.skinjobs.main.NamesContainer;

@SpringBootApplication(scanBasePackages={"it.skinjobs"})
@ComponentScan("it.skinjobs")
@EnableConfigurationProperties({ FileStorageProperties.class})
public class ParallelQrCodeGenerator implements CommandLineRunner{

	@Autowired
	ThreadContainer threadContainer;

	@Autowired
	NamesContainer names;
	
	public static void main(String[] args) {
		SpringApplication.run(ParallelQrCodeGenerator.class, args);	
	}

	@Override
	public void run(String... args) throws Exception {
		if (args.length > 0) {
			try {
				this.names.init();
				int numTask = Integer.parseInt(args[0]);
				this.threadContainer.runner(numTask);
			} catch(Exception e) {
				System.out.println("Please insert an integer value");
			}
		} else {
			System.out.println("Usage: \"command number_of_objects\"");
		}
	}	

}
