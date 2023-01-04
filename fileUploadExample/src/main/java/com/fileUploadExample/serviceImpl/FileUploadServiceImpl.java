package com.fileUploadExample.serviceImpl;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fileUploadExample.exception.FileNotFoundException;
import com.fileUploadExample.exception.FileStorageException;
import com.fileUploadExample.properties.FileUploadProperties;
import com.fileUploadExample.service.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService{

	private Path dirLocation;
    @Autowired
    public void FileSystemStorageService(FileUploadProperties fileUploadProperties) {
        this.dirLocation = Paths.get(fileUploadProperties.getLocation())
                                .toAbsolutePath()
                                .normalize();
    }
	
	@Override
	public String saveFile(MultipartFile file) {
		// TODO Auto-generated method stub
		try {
            String fileName = file.getOriginalFilename();
            Path dfile = this.dirLocation.resolve(fileName);
            Files.copy(file.getInputStream(), dfile,StandardCopyOption.REPLACE_EXISTING);
            return fileName;
            
        } catch (Exception e) {
           // throw new FileStorageException("Could not upload file");
        	throw new FileNotFoundException("Could not upload file");
        }
		
	}

	@Override
	public Resource loadFile(String fileName) {
		try {
	          Path file = this.dirLocation.resolve(fileName).normalize();
	          Resource resource = new UrlResource(file.toUri());

	          if (resource.exists() || resource.isReadable()) {
	              return resource;
	          } 
	          else {
	              throw new FileNotFoundException("Could not find file");
	          }
	        } 
	        catch (MalformedURLException e) {
	            throw new FileNotFoundException("Could not download file");
	        }           

	}

	@Override
	public void init() {

		try {
            Files.createDirectories(this.dirLocation);
        } 
        catch (Exception ex) {
            throw new FileStorageException("Could not create upload dir!");
        }
	}

}
