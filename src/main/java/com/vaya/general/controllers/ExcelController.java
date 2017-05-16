package com.vaya.general.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.vaya.master.services.impl.MasterServiceImpl;

@Controller
@Secured({"ROLE_ADMIN"})
public class ExcelController {

	private MasterServiceImpl masterServiceImpl;
	
	@Autowired
	public ExcelController(MasterServiceImpl masterServiceImpl){
		this.masterServiceImpl = masterServiceImpl;
	}
	
	@RequestMapping(value="/downloadAccounting",method=RequestMethod.GET)
	public ModelAndView downloadAccountingExcel() {
		return new ModelAndView("accountingExcelExport","masters", masterServiceImpl.list());
	}
}
