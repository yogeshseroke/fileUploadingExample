package com.fileUploadExample.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileNotFoundException extends RuntimeException{

	private String message;

}
