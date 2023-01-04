package com.fileUploadExample.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fileUploadExample.response.FileResponse;
import com.fileUploadExample.service.FileUploadService;

@RestController
@RequestMapping("/api")
@ComponentScan("com.fileUploadExample.service")
public class FileUploadController {

	@Autowired
	FileUploadService fileUploadService;
	
	@PostMapping("/uploadfile")
	public ResponseEntity<FileResponse> uploadSingleFile(@RequestBody MultipartFile file){
		String upfile = fileUploadService.saveFile(file);
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/download/").path(upfile).toUriString();
		return ResponseEntity.status(HttpStatus.OK).body(new FileResponse(upfile, fileDownloadUri, "file uploaded with success"));
	}
	
	@PostMapping("/uploadfiles")
    public ResponseEntity<List<FileResponse>> uploadMultipleFiles (@RequestParam("files") MultipartFile[] files) {
        
        List<FileResponse> responses = Arrays
            .asList(files)
            .stream()
            .map(
                file -> {
                    String upfile = fileUploadService.saveFile(file);
                    String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                            .path("/api/download/")
                            .path(upfile)
                            .toUriString();
                    return new FileResponse(upfile,fileDownloadUri,"File uploaded with success!");
                }
            )
            .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }
	
	@GetMapping("/download/{filename:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
       
        Resource resource = fileUploadService.loadFile(filename);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

}
