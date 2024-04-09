package com.acuicola2h.monitor.controller;

import java.io.InputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.acuicola2h.monitor.model.DataEntry;
import com.acuicola2h.monitor.service.DataEntryService;
import com.acuicola2h.monitor.util.Constants;

@RestController(value = Constants.CONTROLLER_PATH)
public class Controller {
	
	DataEntryService dataEntryService;
	
	public Controller(DataEntryService dataEntryService) {
		this.dataEntryService = dataEntryService;
	}
	
	@PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
		
        if (file.isEmpty()) {
            return "Please select a file to upload.";
        }

        try (InputStream inputStream = file.getInputStream()) {
            // Get the workbook instance for the Excel file
            Workbook workbook = WorkbookFactory.create(inputStream);

            // Process the Excel file
            // ...

            return "File uploaded and processed successfully!";
        } catch (Exception e) {
            return "Error during file upload: " + e.getMessage();
        }
    }
	
	/**
	 * This one will be worked on in the future when using a full web app to process the data
	 * @param dataEntry
	 * @return
	 */
	@PostMapping(value = Constants.PROCESS_DATA_ENTRY_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> processDataEntry(@RequestBody DataEntry dataEntry) {
		//Validate Request
		//Process
		//Placeholder
		return new ResponseEntity<>(dataEntryService.processDataEntry(dataEntry), HttpStatus.OK);
	}
	
	@PostMapping(value = Constants.PROCESS_FILE_PATH)
	public ResponseEntity<String> processFile() {
		return new ResponseEntity<>("Done", HttpStatus.OK);
	}
	
}
