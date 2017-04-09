package com.vaya.excel;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FileUploadValidator implements Validator {
	@SuppressWarnings("rawtypes")
	@Override
	 public boolean supports(Class clazz) {  
	  //just validate the FileUpload instances  
	  return FileUpload.class.isAssignableFrom(clazz);  
	 }  
	  
	@Override
	 public void validate(Object target, Errors errors) {  
	    
	  FileUpload file = (FileUpload)target;  
	    
	  if(file.getFile().getSize()==0){  
	   errors.rejectValue("file", "uploadForm.selectFile",    
	         "Please select a file!");  
	  }  
	 }
}
