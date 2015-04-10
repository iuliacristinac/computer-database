package com.excilys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.service.ComputerService;

@Controller
public class DeleteComputer {
	
	@Autowired
	private ComputerService computerService;
	
	@RequestMapping("deleteComputer")
    public String delete(@RequestParam("id") Long id) {
        computerService.delete(id);
        return "redirect:/dashboard";
    }

}
