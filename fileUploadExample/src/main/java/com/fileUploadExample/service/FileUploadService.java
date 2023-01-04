package com.fileUploadExample.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {

	String saveFile(MultipartFile file);

	Resource loadFile(String fileName);

	void init();
}
