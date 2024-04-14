package com.acuicola2h.monitor.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acuicola2h.monitor.model.DataEntry;

@Service
public class ExcelBookServiceImpl implements ExcelBookService {

	@Autowired
	EmailService emailService;
	
	//Can add a request parameter to state how many of the row should be validated.
	public void processExcelFile(Workbook workbook) {
		List<List<DataEntry>> allTanks = new ArrayList<>();
		List<DataEntry> tankEntry = new ArrayList<>();
		boolean flag = false; 
        // Assuming all sheets have the same format and should be read
        for (Sheet sheet : workbook) {
            // Skip the header row by starting at 1 instead of 0
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null && row.getCell(0) != null && row.getCell(0).getNumericCellValue() != 0.0) {
                	flag = true;
                	DataEntry dataEntry = new DataEntry();
                	dataEntry.setOxygen(row.getCell(0).getNumericCellValue());
                	dataEntry.setPH(row.getCell(1).getNumericCellValue());
                	dataEntry.setTemperature(row.getCell(2).getNumericCellValue());
                	dataEntry.setSalinity(row.getCell(3).getNumericCellValue());
                	dataEntry.setNitrite(row.getCell(4).getNumericCellValue());
                	dataEntry.setNitrate(row.getCell(5).getNumericCellValue());
                	dataEntry.setAmmonia(row.getCell(6).getNumericCellValue());
                	dataEntry.setTurbinez(row.getCell(7).getNumericCellValue());
                	dataEntry.setAlkalinide(row.getCell(8).getNumericCellValue());
                	dataEntry.setDeaths((int)row.getCell(9).getNumericCellValue());
                	dataEntry.setDate(row.getCell(10).getDateCellValue());
                	tankEntry.add(dataEntry);
                }
            }
            if (flag) allTanks.add(tankEntry); 
            flag = false;
        }
        for (List<DataEntry> dataEntry : allTanks) {
        	for (DataEntry data : dataEntry) {
        		System.out.println(data.toString());
        	}
        }
        //List of errors
        List<String> errorList = new ArrayList<>();
        int tankNumber = 0;
        //Validate allTanks (Each sheet)
        for (List<DataEntry> dataEntry : allTanks) {
        	tankNumber++;
        	//Validate each data row
        	for (DataEntry data : dataEntry) {
        		if (data.getOxygen() < 5.0) {
        			errorList.add("Tank #" + tankNumber + " - Oxygen is " + data.getOxygen() + 
        					" for " + data.getDate().toString());
        		}
        		if (data.getPH() < 6.0 || data.getPH() > 8.0) {
        			errorList.add("Tank #" + tankNumber + " - PH is " + data.getPH() + 
        					" for " + data.getDate().toString());
        		}
        		if (data.getTemperature() <= 22.0 || data.getTemperature() >= 34.0) {
        			errorList.add("Tank #" + tankNumber + " - Temperature is " + data.getTemperature() + 
        					" for " + data.getDate().toString());
        		}
        		/**
        		if (data.getSalinity() < 6.0 || data.getSalinity() > 8.0) {
        			errorList.add("Tank #" + tankNumber + " - Salinity is " + data.getSalinity() + 
        					" for " + data.getDate().toString());
        		}
        		*/
        		if (data.getNitrate() < 1.5 || data.getNitrate() > 2.0) {
        			errorList.add("Tank #" + tankNumber + " - Nitrate is " + data.getNitrate() + 
        					" for " + data.getDate().toString());
        		}
        		if (data.getNitrite() > 0.1) {
        			errorList.add("Tank #" + tankNumber + " - Nitrite is " + data.getNitrite() + 
        					" for " + data.getDate().toString());
        		}
        		if (data.getAmmonia() > 0.1) {
        			errorList.add("Tank #" + tankNumber + " - Ammonia is " + data.getAmmonia() + 
        					" for " + data.getDate().toString());
        		}
        		/**
        		if (data.getTurbinez() < 6.0 || data. <= 22.0() > 8.0) {
        			errorList.add("Tank #" + tankNumber + " - Turbinez is " + data.getTurbinez() + 
        					" for " + data.getDate().toString());
        		}
        		*/
        		if (data.getAlkalinide() < 50 || data.getAlkalinide() > 150) {
        			errorList.add("Tank #" + tankNumber + " - Alkalinide is " + data.getAlkalinide() + 
        					" for " + data.getDate().toString());
        		}
        		if (data.getDeaths() > 10) {
        			errorList.add("Tank #" + tankNumber + " - Deaths is " + data.getDeaths() + 
        					" for " + data.getDate().toString());
        		}
        	}
        }
        
        if (errorList.size() > 0) {
        	emailService.sendEmail(errorList);
        }
	}
	
}
