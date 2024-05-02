package com.acuicola2h.monitor.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

public interface ExcelBookService {

	List<String> processExcelFile(Workbook workbook, boolean sendEmail);
	
}
