package com.acuicola2h.monitor.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import com.acuicola2h.monitor.model.DataEntry;

@Service
public class ExcelBookServiceImpl implements ExcelBookService {

	public void processExcelFile(Workbook workbook) {
		List<List<DataEntry>> allTanks = new ArrayList<>();
		List<DataEntry> tankEntry = new ArrayList<>();
        // Assuming all sheets have the same format and should be read
        for (Sheet sheet : workbook) {
            // Skip the header row by starting at 1 instead of 0
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null) {
                	DataEntry dataEntry = new DataEntry();
                	dataEntry.setOxygen(row.getCell(0).getNumericCellValue());
                	dataEntry.setPH(row.getCell(1).getNumericCellValue());
                	dataEntry.setTemperature(row.getCell(2).getNumericCellValue());
                	dataEntry.setNitrite(row.getCell(3).getNumericCellValue());
                	dataEntry.setNitrate(row.getCell(4).getNumericCellValue());
                	dataEntry.setAmmonium(row.getCell(5).getNumericCellValue());
                	dataEntry.setAmmonia(row.getCell(6).getNumericCellValue());
                	dataEntry.setSalinity(row.getCell(7).getNumericCellValue());
                	dataEntry.setTurbinez(row.getCell(8).getNumericCellValue());
                	dataEntry.setAlkalinide(row.getCell(9).getNumericCellValue());
                	dataEntry.setDeaths((int)row.getCell(10).getNumericCellValue());
                	dataEntry.setDate(row.getCell(11).getDateCellValue());
                	tankEntry.add(dataEntry);
                }
            }
            allTanks.add(tankEntry);
        }
        //Validate allTanks 
	}
	
}
