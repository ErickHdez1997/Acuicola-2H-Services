package com.acuicola2h.monitor.service;

import org.apache.poi.ss.usermodel.Workbook;

public interface ExcelBookService {

	void processExcelFile(Workbook workbook);
	
}
