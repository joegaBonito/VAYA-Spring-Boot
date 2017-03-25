package com.vaya.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vaya.services.MasterService;

@Controller
public class ExcelController {

	private MasterService masterService;
	
	@Autowired
	public ExcelController(MasterService masterService){
		this.masterService = masterService;
	}
	
	@RequestMapping(value="/downloadAccounting",method=RequestMethod.GET)
	public ModelAndView downloadAccountingExcel() {
		return new ModelAndView("accountingExcelExport","masters", masterService.list());
	}
}
