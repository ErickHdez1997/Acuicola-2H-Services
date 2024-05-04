package com.acuicola2h.monitor.controller;

import java.io.InputStream;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.acuicola2h.monitor.model.DataEntry;
import com.acuicola2h.monitor.service.DataEntryService;
import com.acuicola2h.monitor.service.EmailService;
import com.acuicola2h.monitor.service.ExcelBookService;
import com.acuicola2h.monitor.util.Constants;

@RestController
@RequestMapping(Constants.CONTROLLER_PATH)
public class Controller {
	
	ExcelBookService excelBookService;
	DataEntryService dataEntryService;
	EmailService emailService;
	
	public Controller(ExcelBookService excelBookService, DataEntryService dataEntryService, EmailService emailService) {
		this.excelBookService = excelBookService;
		this.dataEntryService = dataEntryService;
		this.emailService = emailService;
	}

	@PostMapping(Constants.PROCESS_FILE_PATH)
    public List<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("sendEmail") String sendEmail) {
		List<String> errorList = null;
        try (InputStream inputStream = file.getInputStream(); Workbook workbook = WorkbookFactory.create(inputStream)) {
            Boolean email = Boolean.parseBoolean(sendEmail);
            errorList = excelBookService.processExcelFile(workbook, email);
            return errorList;
        } catch (Exception e) {
        	e.printStackTrace();
            return errorList;
        } 
    }
	
	// To do - For future features
	@PostMapping(value = Constants.PROCESS_DATA_ENTRY_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> processDataEntry(@RequestBody DataEntry dataEntry) {
		//Validate Request
		//Process
		//Placeholder
		return new ResponseEntity<>(dataEntryService.processDataEntry(dataEntry), HttpStatus.OK);
	}
	
}
