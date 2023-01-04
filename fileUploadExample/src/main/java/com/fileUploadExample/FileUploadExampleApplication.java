package com.fileUploadExample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.fileUploadExample.properties.FileUploadProperties;

@SpringBootApplication
//@ComponentScan("com.fileUploadExample.repository")
//@SpringBootApplication(exclude =  {DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class })
@EnableConfigurationProperties({
    FileUploadProperties.class
})
public class FileUploadExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(FileUploadExampleApplication.class, args);
	}

}
