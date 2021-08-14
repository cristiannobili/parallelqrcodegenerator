package it.skinjobs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import it.skinjobs.utils.FileStorageProperties;

@SpringBootApplication(scanBasePackages={"it.skinjobs"})
@EnableConfigurationProperties({ FileStorageProperties.class})
public class ParallelQrCodeGenerator {

	public static void main(String[] args) {
		SpringApplication.run(ParallelQrCodeGenerator.class, args);
		
	}

}
