package com.excilys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.model.Computer;
import com.excilys.service.IService;

@Controller
public class DeleteComputer {
	
	@Autowired
	private IService<Computer, Long> computerService;
	
	@RequestMapping("deleteComputer")
    public String delete(@RequestParam("id") Long id) {
		if (id == null) {
			return "404";
		}
        computerService.delete(id);
        return "redirect:/dashboard";
    }
}
