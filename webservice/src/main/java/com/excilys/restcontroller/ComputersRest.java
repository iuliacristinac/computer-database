package com.excilys.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.excilys.dto.ComputerDTO;
import com.excilys.mapper.IMapperDTO;
import com.excilys.model.Computer;
import com.excilys.service.IService;

@RestController
@RequestMapping("/computer")
public class ComputersRest  {

	@Autowired
	private IMapperDTO<Computer, ComputerDTO> computerMapperDTO;
		
	@Autowired
	private IService<Computer, Long> computerService;
	
	@RequestMapping(value="/list",
	        method=RequestMethod.GET,
	        produces={MediaType.APPLICATION_JSON_VALUE, 
	                  MediaType.APPLICATION_XML_VALUE})
	public List<ComputerDTO> getComputers( ) {
		return  computerMapperDTO.mapModelsToDTO(computerService.getAll());
	}
}