package com.fileUploadExample.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

@Data
@ConfigurationProperties(prefix = "file.upload")
public class FileUploadProperties {

	private String location;

}
