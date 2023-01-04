package com.fileUploadExample.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileStorageException extends RuntimeException{

	private String message;

}
