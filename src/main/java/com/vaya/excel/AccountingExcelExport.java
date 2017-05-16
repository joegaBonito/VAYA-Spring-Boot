package com.vaya.excel;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.vaya.master.domain.Master;

@Component
public class AccountingExcelExport extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// change the file name
		String filename="VAYA Accounting.xls";
        response.setHeader("Content-Disposition", "inline; filename="+filename+";");

        @SuppressWarnings("unchecked")
		List<Master> masters = (List<Master>) model.get("masters");
        

        // create excel xls sheet
        Sheet sheet1 = workbook.createSheet("VAYA Accounting");
        /*Sheet sheet2 = workbook.createSheet("Mobile Accessory Inventory List");
        Sheet sheet3 = workbook.createSheet("Unlocked Phone Inventory List");*/
        
     // create sheet1 header row
        Row header1 = sheet1.createRow(0);
        header1.createCell(0).setCellValue("Name");
        header1.createCell(1).setCellValue("Current Budget");
        header1.createCell(2).setCellValue("Total Budget");

        // Create data cells for sheet1
        int rowCount1 = 1;
        for (Master master: masters){
        	Row courseRow = sheet1.createRow(rowCount1++);
        	if(master.getId() < 9) {
            	courseRow.createCell(0).setCellValue(master.getTeam().getTeamName());
                courseRow.createCell(1).setCellValue(master.getTeam().getAccounting().getCurrentBudget());
                courseRow.createCell(2).setCellValue(master.getTeam().getAccounting().getTotalBudget());
        	}	else if (master.getId() > 8 && master.getId() < 12) {
            	courseRow.createCell(0).setCellValue(master.getMeeting().getMeetingName());
                courseRow.createCell(1).setCellValue(master.getMeeting().getAccounting().getCurrentBudget());
                courseRow.createCell(2).setCellValue(master.getMeeting().getAccounting().getTotalBudget());
        	}	else if (master.getId() > 11 && master.getId() < 15) {
            	courseRow.createCell(0).setCellValue(master.getEtc().getEtcName());
                courseRow.createCell(1).setCellValue(master.getEtc().getAccounting().getCurrentBudget());
                courseRow.createCell(2).setCellValue(master.getEtc().getAccounting().getTotalBudget());
        	}	else if (master.getId() > 14 && master.getId() < 17) {
            	courseRow.createCell(0).setCellValue(master.getRetreat().getRetreatName());
                courseRow.createCell(1).setCellValue(master.getRetreat().getAccounting().getCurrentBudget());
                courseRow.createCell(2).setCellValue(master.getRetreat().getAccounting().getTotalBudget());
        	}	
        }
	}
}
